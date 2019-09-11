import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.resume.R
import com.example.resume.util.CheckInternet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.project_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// this fragment is for the Personal Details
class AboutPersonalDetail : Fragment() {


    // it is used to pass the request code to check on the permission result
    private val permissionCallRequest = 123
    private var name: TextView? = null
    private var designation: TextView? = null
    private var summary: TextView? = null
    private var interest: TextView? = null
    private var linkedin: TextView? = null
    private var github: TextView? = null
    private var skillText: TextView? = null
    private var number: String? = null
    private var email: String? = null
    private var progressBar: ProgressBar? = null
    private var backgroundImage:ImageView?=null
    private var profilePic:ImageView?=null


    // this function is used to return the object of the AboutPersonalDetail fragment
    companion object {
        fun newInstance(): AboutPersonalDetail {
            return AboutPersonalDetail()
        }
    }


    // the creation of the fragment will start from here
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // initialising the root view of the fragment
        val rootView = inflater.inflate(R.layout.fragment_about, container, false)
        // initialising the call button to call on the number given by the resume owner
        val callMe = rootView!!.findViewById(R.id.call_me) as Button
        // initialising the email button to email
        val emailMe = rootView.findViewById(R.id.email_me) as Button
        initializeView(rootView)
        // handling the click listener of call button
        callMe.setOnClickListener {
            // this method will get call when user click on the call button to call on the number
            callAction()
        }
        // handling the click listener of email button
        emailMe.setOnClickListener {
            // this method is used to handle the email action which will get invoke once user click on the email button
            emailAction()
        }
        return rootView
    }

    // this method is used to initialize the ui
    private fun initializeView(rootView:View){
        name = rootView.findViewById(R.id.name) as TextView
        designation = rootView.findViewById(R.id.positions) as TextView
        summary = rootView.findViewById(R.id.summary) as TextView
        interest = rootView.findViewById(R.id.interest) as TextView
        linkedin = rootView.findViewById(R.id.linkedin) as TextView
        github = rootView.findViewById(R.id.github) as TextView
        skillText = rootView.findViewById(R.id.skill) as TextView
        profilePic=rootView.findViewById(R.id.profile)as ImageView
        backgroundImage=rootView.findViewById(R.id.background)as ImageView
        // Create progressBar dynamically...
        progressBar = ProgressBar(context)
        progressBar!!.layoutParams =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val linearLayout = rootView.findViewById<LinearLayout>(R.id.rootContainer)
        // Add ProgressBar to LinearLayout
        linearLayout?.addView(progressBar)
        progressBar!!.visibility

        if (CheckInternet.checkConnection(context)) {
            loadAboutData()
        } else {
            var message = getString(R.string.internet_error)
            val visibility = if (progressBar!!.visibility == View.GONE) View.VISIBLE else View.GONE
            progressBar!!.visibility = visibility
            showAlertPopup(message)
        }
    }

    // this method will load the About data of the Resume
    private fun loadAboutData() {
        val resumeServiceInterface = ServiceBuilder.buildService(ResumeServiceInterface::class.java)
        val requestCall = resumeServiceInterface.getAbout()
        requestCall.enqueue(object : Callback<About> {
            override fun onResponse(call: Call<About>?, response: Response<About>?) {
                if (response?.isSuccessful!!) {
                    val about = response.body()
                    setData(about)
                    val visibility = if (progressBar!!.visibility == View.GONE) View.VISIBLE else View.GONE
                    progressBar!!.visibility = visibility
                }
            }
            override fun onFailure(call: Call<About>?, t: Throwable?) {
                var message = getString(R.string.error_msg)
                val visibility = if (progressBar!!.visibility == View.GONE) View.VISIBLE else View.GONE
                progressBar!!.visibility = visibility
                showAlertPopup(message)
            }
        })
    }


    // this method will set the data
    private fun setData(about: About) {
        number = about.phone
        email = about.email
        interest!!.text = about.interests
        name!!.text = about.name
        summary!!.text = about.summary
        designation!!.text = about.designation
        github!!.text = about.gitHUbProfile
        linkedin!!.text = about.linkedInProfile
        skillText!!.text = about.skills
        Picasso.with(context).load(about.backgroundimage).resize(150, 150).centerInside()
            .into(backgroundImage)
        Picasso.with(context).load(about.profilepic).resize(150, 150).centerInside()
            .into(profilePic)
    }

    //this method is used to show the alert dialogue with respect to the event
    private fun showAlertPopup(message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.heading_msg))
        builder.setMessage(message)
        builder.show()
    }

    // this method is used to permform the logic when user click on the call button on the screen
    private fun callAction() {
        // this condition will check weather the device is requied permission to permform the operation or not
        if (ActivityCompat.checkSelfPermission(
                activity!!, Manifest.permission.CALL_PHONE
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(Manifest.permission.CALL_PHONE),
                permissionCallRequest
            )
        } else {

            // these codes are used to invoke the call functionality of the device
            val intent = Intent(Intent.ACTION_CALL)

            // this will provide the contact number to call
            val phoneNumber: String = number.toString()
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }

    }


    // this method is used to perform the operation when user click on the message button,
    private fun emailAction() {
        // this will provide the contact email address
        val emailId: String = email.toString()
        val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", emailId, null))
        startActivity(Intent.createChooser(intent, context!!.getString(R.string.send_mail)))
    }

    // Receive the permissions request result
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionCallRequest) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callAction()
            }
        }
    }

}