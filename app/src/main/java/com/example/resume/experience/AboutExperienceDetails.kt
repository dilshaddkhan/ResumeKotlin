import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resume.R
import com.example.resume.util.CheckInternet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// this fragment is for the Experience2 details
class AboutExperienceDetails : Fragment() {


    // this will the return the object of the AboutExperienceDetails fragment
    companion object {

        fun newInstance(): AboutExperienceDetails {
            return AboutExperienceDetails()
        }
    }

    private var experienceContainer: RecyclerView? = null
    private var progressBar: ProgressBar? = null


    // this is used to create the UI of it
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // this is used to invoke the fragment_experience ui xml layout
        val rootView = inflater.inflate(R.layout.fragment_experience, container, false)

        // Create progressBar dynamically...
        progressBar = ProgressBar(context)
        progressBar!!.layoutParams =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val linearLayout = rootView.findViewById<LinearLayout>(R.id.rootContainer)
        // Add ProgressBar to LinearLayout
        linearLayout?.addView(progressBar)
        progressBar!!.visibility

        // this will initialise the recycler view
        experienceContainer = rootView.findViewById(R.id.experience_container) as RecyclerView // Add this
        if (CheckInternet.checkConnection(context)) {
            loadExperienceData()
        } else {
            var message = getString(R.string.internet_error)
            val visibility = if (progressBar!!.visibility == View.GONE) View.VISIBLE else View.GONE
            progressBar!!.visibility = visibility
            showAlertPopup(message)
        }

        return rootView

    }


    //this method will load the experience data from the server
    private fun loadExperienceData() {
        val resumeServiceInterface = ServiceBuilder.buildService(ResumeServiceInterface::class.java)
        val requestCall = resumeServiceInterface.getExperience()
        requestCall.enqueue(object : Callback<Experiences> {
            override fun onResponse(call: Call<Experiences>?, response: Response<Experiences>?) {

                if (response?.isSuccessful!!) {
                    val experiences = response.body()
                    setExperienceData(experiences)
                    val visibility = if (progressBar!!.visibility == View.GONE) View.VISIBLE else View.GONE
                    progressBar!!.visibility = visibility

                }
            }

            override fun onFailure(call: Call<Experiences>?, t: Throwable?) {
                var message = getString(R.string.error_msg)
                val visibility = if (progressBar!!.visibility == View.GONE) View.VISIBLE else View.GONE
                progressBar!!.visibility = visibility
                showAlertPopup(message)
            }
        })
    }


    //this method will set the pass the experience data to the Experience adapter
    private fun setExperienceData(experiences: Experiences) {

        if (experiences != null) {
            val listOfExperience = experiences.experience
            //  this will provide the layout to the recycler view
            experienceContainer?.layoutManager = LinearLayoutManager(activity)
            // initialising the ExperienceAdapter class to pass the context and the data to render on the UI
            val myExperienceAdapter = ExperienceAdapter(context!!, listOfExperience, CompanyLogoSupplier.logo)
            //passig the adapter to the recycler view
            experienceContainer?.adapter = myExperienceAdapter
        }
    }

    //this method is used to show the alert dialogue with respect to the event
    private fun showAlertPopup(message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.heading_msg))
        builder.setMessage(message)
        builder.show()
    }
}