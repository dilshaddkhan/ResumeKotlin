import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.resume.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.project_item.view.*

// creating the adapter class for the Project fragment and passing two parameters to its object

class ProjectAdapter(var context: Context,var projects: List<Project>) :
    RecyclerView.Adapter<ProjectAdapter.MyProjectViewHolder>() {


    // overriding the onCreateViewHolder class to provide the custom ui
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyProjectViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.project_item, p0, false)
        return MyProjectViewHolder(view)
    }

    // this will provide the total no of counts
    override fun getItemCount(): Int {
        return projects.size
    }

    //with the help of this we are binding the data to the view
    override fun onBindViewHolder(p0: MyProjectViewHolder, p1: Int) {
        //this will return the object of project class
        val projects = projects.get(p1)

        // with the help of this we are passing the project data to set on the ui fields
        p0.setData(projects)
    }

    // inner class to create the custom ui for the adapter of recycler view
    inner class MyProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // setting of each Projects data
        fun setData(projects: Project?) {

            // initialising and setting the projects title
            itemView.project_title.text = projects!!.projectName
            // initialising and setting the projects description
            itemView.project_playstore_link.text = projects!!.playStoreLink
            itemView.technology_used.text=projects!!.technologies
            // initialising and setting the projects logo
            Picasso.with(itemView.context).load(projects.companylogo).resize(150, 150).centerInside().into(itemView.project_logo)
            // initialising and setting the projects images
            val urlList=projects.uRLS
            Picasso.with(context).load(urlList.url1).fit().centerCrop().into(itemView.image_1)
            Picasso.with(context).load(urlList.url2).fit().centerCrop().into(itemView.image_2)
            Picasso.with(context).load(urlList.url3).fit().centerCrop().into(itemView.image_3)
        }
    }
}