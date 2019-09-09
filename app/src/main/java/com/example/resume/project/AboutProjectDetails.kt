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

//creating the class for the ProjectsImages
class AboutProjectDetails : Fragment() {

    var project_container:RecyclerView?=null
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
        project_container = rootView.findViewById(R.id.project_container) as RecyclerView // Add this
        loadProjectData()
        return rootView

    }

    private fun loadProjectData() {
        val resumeServiceInterface = ServiceBuilder.buildService(ResumeServiceInterface::class.java)
        val requestCall = resumeServiceInterface.getProject()
        requestCall.enqueue(object : Callback<Project> {
            override fun onResponse(call: Call<Project>?, response: Response<Project>?) {

                if (response?.isSuccessful!!) {
                    val project = response.body()
                    setProjectData(project)
                }
            }

            override fun onFailure(call: Call<Project>?, t: Throwable?) {
                var error = "wewefewewf"
            }
        })
    }

    private fun setProjectData(project: Project) {
        if (project !=null){
            val totalProject = project.projects
            //  this will provide the layout to the recycler view
            project_container!!.layoutManager = LinearLayoutManager(activity)
            // initialising the ProjectAdapter class to pass the context and the data to render on the UI
            val myProjectAdapter = ProjectAdapter(context!!, totalProject,ProjectsImagesSupplier.projects)
            //passig the adapter to the recycler view
            project_container!!.adapter = myProjectAdapter
        }
    }

}