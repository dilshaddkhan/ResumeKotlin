import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.resume.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// this fragment is for the Personal Details
class AboutPersonalDetail : Fragment() {


    // it is used to pass the request code to check on the permission result
    private val PERMISSION_CALL_REQUEST = 123
    var name: TextView? = null
    var designation: TextView? = null
    var summary: TextView? = null
    var interest: TextView? = null
    var linkedin: TextView? = null
    var github: TextView? = null
    var skillText:TextView?=null
    var number:String?=null
    var email:String?=null

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
        name = rootView.findViewById(R.id.name) as TextView
        designation = rootView.findViewById(R.id.positions) as TextView
        summary = rootView.findViewById(R.id.summary) as TextView
        interest = rootView.findViewById(R.id.interest) as TextView
        linkedin = rootView.findViewById(R.id.linkedin) as TextView
        github = rootView.findViewById(R.id.github) as TextView
        skillText=rootView.findViewById(R.id.skill) as TextView
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
        loadAboutData()
        return rootView
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
                PERMISSION_CALL_REQUEST
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
        if (requestCode == PERMISSION_CALL_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callAction()
            }
        }
    }


    private fun loadAboutData() {
        val resumeServiceInterface = ServiceBuilder.buildService(ResumeServiceInterface::class.java)
        val requestCall = resumeServiceInterface.getAbout()
        requestCall.enqueue(object : Callback<About> {
            override fun onResponse(call: Call<About>?, response: Response<About>?) {

                if (response?.isSuccessful!!) {
                    val about = response.body()
                    setData(about)
                }
            }

            override fun onFailure(call: Call<About>?, t: Throwable?) {
                var error = "wewefewewf"
            }
        })
    }


    private fun setData(about: About) {
        if (about != null) {
            number=about.phone
            email=about.email
            interest!!.text = about!!.interests
            name!!.text = about!!.name
            summary!!.text = about!!.summary
            designation!!.text = about!!.designation
            github!!.text = about!!.gitHUbProfile
            linkedin!!.text = about!!.linkedInProfile
            skillText!!.text=about!!.skills
        }
    }
}