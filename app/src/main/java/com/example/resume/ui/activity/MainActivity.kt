package com.example.resume.ui.activity

import AboutExperienceDetails
import AboutPersonalDetail
import AboutProjectDetails
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.resume.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // creating the val for the support fragment manager
    private val manager = supportFragmentManager

    // creating the UI for the Main activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialising the home fragment which is the AboutPersonalDetails
        createFragmentAboutPersonalDetails()
        // setting up the navigation tab for the important aspects of the resume
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }


    // creating the bottom navigation tab to look into the different pages of the resume.
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

            // setting up the first tab fragment which is AboutPersonalDetails
            R.id.navigation_about -> {

                //calling the first fragment  which is AboutExperienceDetails
                createFragmentAboutPersonalDetails()
                return@OnNavigationItemSelectedListener true
            }

            // setting up the second tab fragment which is AboutPersonalDetails
            R.id.navigation_experience -> {

                //calling the second tab fragment which is AboutExperienceDetails
                createFragmentAboutExperienceDetails()
                return@OnNavigationItemSelectedListener true
            }

            // setting up the third tab fragment which is AboutProjectDetails
            R.id.navigation_projects -> {

                //calling the second tab fragment which is AboutProjectDetails
                createFragmentAboutProjectDetails()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    /**
     * this method is used to initialise the AboutPersonalDetails fragment which will be the first fragment which will render
     * on the screen when for the first time app will be start
     */
    private fun createFragmentAboutPersonalDetails() {
        val transaction = manager.beginTransaction()
        // initialising the AboutPersonalDetail fragment
        val fragment = AboutPersonalDetail()
        transaction.replace(R.id.content, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    /**
     * this will be the second fragment consist AboutExperienceDetails and while clicking on the second
     * tab present on the screen will be invoked
     */
    private fun createFragmentAboutExperienceDetails() {
        val transaction = manager.beginTransaction()

        // initialising the AboutExperienceDetails fragment
        val fragment = AboutExperienceDetails()
        transaction.replace(R.id.content, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    /**
     * this will be the third fragment consist AboutProjectDetails and while clicking on the second
     * tab present on the screen will be invoked
     */
    private fun createFragmentAboutProjectDetails() {
        val transaction = manager.beginTransaction()

        // initialising the AboutProjectDetails fragment
        val fragment = AboutProjectDetails()
        transaction.replace(R.id.content, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
