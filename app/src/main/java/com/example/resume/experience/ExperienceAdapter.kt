import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resume.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.experience_item.view.*

// creating the adapter class for the Experience fragment and passing three parameters to its object
class ExperienceAdapter(var context: Context, var companyInfo: List<Experience>,var companyLogo: List<CompanyLogo>) :
    RecyclerView.Adapter<ExperienceAdapter.MyExperienceViewHolder>() {



    // overriding the onCreateViewHolder class to provide the custom ui
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyExperienceViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.experience_item, p0, false)
        return MyExperienceViewHolder(view)
    }

    // this will provide the total no of counts of the company
    override fun getItemCount(): Int {
        return companyInfo.size
    }

    //with the help of this we are binding the data to the view
    override fun onBindViewHolder(p0: MyExperienceViewHolder, p1: Int) {

        //this will return the object of Experience class
        val experienceItem = companyInfo.get(p1)
        val companyLogo=companyLogo.get(p1)

        // with the help of this we are passing the Experience data to set on the ui fields
        p0.setData(experienceItem,companyLogo)
    }



    // inner class to create the custom ui for the adapter of recycler view
    inner class MyExperienceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        // setting of each Experience data
        fun setData(experience: Experience?,companyLogo:CompanyLogo?) {

            // initialising and setting the experience company
            itemView.company_name.text = experience!!.company

            // initialising and setting the experience website
            itemView.website.text = experience!!.website

            // initialising and setting the experience position
            itemView.positions.text = experience!!.position

            // initialising and setting the experience date
            itemView.experience_date.text = experience!!.startDate+" - "+experience.endDate

            // initialising and setting the experience responsibilities
            itemView.responsibility.text = experience!!.responsibilities

            // initialising and setting the companies logo
            Picasso.with(itemView.context).load(companyLogo!!.image).resize(80, 80).centerInside().into(itemView.experience_logo)
        }
    }
}