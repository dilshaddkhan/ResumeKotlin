import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resume.R
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

    var experience_container: RecyclerView? = null


    // this is used to create the UI of it
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // this is used to invoke the fragment_experience ui xml layout
        val rootView = inflater.inflate(R.layout.fragment_experience, container, false)

        // this will initialise the recycler view
        experience_container = rootView.findViewById(R.id.experience_container) as RecyclerView // Add this
        loadExperienceData()
        return rootView

    }


    private fun loadExperienceData() {
        val resumeServiceInterface = ServiceBuilder.buildService(ResumeServiceInterface::class.java)
        val requestCall = resumeServiceInterface.getExperience()
        requestCall.enqueue(object : Callback<Experiences> {
            override fun onResponse(call: Call<Experiences>?, response: Response<Experiences>?) {

                if (response?.isSuccessful!!) {
                    val experiences = response.body()
                    setExperienceData(experiences)
                }
            }

            override fun onFailure(call: Call<Experiences>?, t: Throwable?) {
                var error = "wewefewewf"
            }
        })
    }

    private fun setExperienceData(experiences: Experiences) {

        if (experiences != null) {
           val listOfExperience = experiences.experience
            //  this will provide the layout to the recycler view
            experience_container?.layoutManager = LinearLayoutManager(activity)
            // initialising the ExperienceAdapter class to pass the context and the data to render on the UI
            val myExperienceAdapter = ExperienceAdapter(context!!, listOfExperience,CompanyLogoSupplier.logo)
            //passig the adapter to the recycler view
            experience_container?.adapter = myExperienceAdapter
        }
    }
}