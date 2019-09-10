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

//creating the class for the ProjectsImages
class AboutProjectDetails : Fragment() {

    private var projectContainer:RecyclerView?=null
    private var progressBar: ProgressBar? = null
    // it will return the object of the AboutProjectDetails class
    companion object {
        fun newInstance(): AboutProjectDetails {
            return AboutProjectDetails()

        }
    }

    // creating the UI for the AboutProjectDetails fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // this is used to invoke the fragment_project ui xml layout
        val rootView = inflater.inflate(R.layout.fragment_project, container, false)
        // this will initialise the recycler view for project container

        // Create progressBar dynamically...
        progressBar = ProgressBar(context)
        progressBar!!.layoutParams =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val linearLayout = rootView.findViewById<LinearLayout>(R.id.rootContainer)
        // Add ProgressBar to LinearLayout
        linearLayout?.addView(progressBar)


        projectContainer = rootView.findViewById(R.id.project_container) as RecyclerView // Add this
        if (CheckInternet.checkConnection(context)) {
            loadProjectData()
        } else {
            var message=getString(R.string.internet_error)
            val visibility = if (progressBar!!.visibility == View.GONE) View.VISIBLE else View.GONE
            progressBar!!.visibility = visibility
            showAlertPopup(message)
        }
        return rootView

    }

    //this method will load the project data from the server
    private fun loadProjectData() {
        val resumeServiceInterface = ServiceBuilder.buildService(ResumeServiceInterface::class.java)
        val requestCall = resumeServiceInterface.getProject()
        requestCall.enqueue(object : Callback<Project> {
            override fun onResponse(call: Call<Project>?, response: Response<Project>?) {

                if (response?.isSuccessful!!) {
                    val project = response.body()
                    setProjectData(project)
                    val visibility = if (progressBar!!.visibility == View.GONE) View.VISIBLE else View.GONE
                    progressBar!!.visibility = visibility
                }
            }

            override fun onFailure(call: Call<Project>?, t: Throwable?) {
                var message=getString(R.string.error_msg)
                val visibility = if (progressBar!!.visibility == View.GONE) View.VISIBLE else View.GONE
                progressBar!!.visibility = visibility
                showAlertPopup(message)
            }
        })
    }

    //this method will set the pass the project data to the project adapter
    private fun setProjectData(project: Project) {
        if (project !=null){
            val totalProject = project.projects
            //  this will provide the layout to the recycler view
            projectContainer!!.layoutManager = LinearLayoutManager(activity)
            // initialising the ProjectAdapter class to pass the context and the data to render on the UI
            val myProjectAdapter = ProjectAdapter(context!!, totalProject,ProjectsImagesSupplier.projects)
            //passig the adapter to the recycler view
            projectContainer!!.adapter = myProjectAdapter
        }
    }

    //this method is used to show the alert dialogue with respect to the event
    private fun showAlertPopup(message:String){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.heading_msg))
        builder.setMessage(message)
        builder.show()
    }
}