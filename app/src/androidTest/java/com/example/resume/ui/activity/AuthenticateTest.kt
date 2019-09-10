package com.example.resume.ui.activity

import androidx.core.content.MimeTypeFilter.matches
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.resume.R
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AuthenticateTest {


    // initializing the Authenticate class
    @get:Rule
    var activityRule: ActivityTestRule<Authenticate> = ActivityTestRule(Authenticate::class.java)

    private lateinit var validEmail: String
    private lateinit var inValidEmail: String
    private lateinit var mobileNumber: String


    @Before
    fun initValidString() {
        // Specify a valid string.
        validEmail = "dilshaddkhan@outlook.com"
        inValidEmail = "dilshaddkhan@outlook"
        mobileNumber = "9834997912"
    }

    @After
    fun tearDown() {
    }

    // creating the click test for the navigation tab
    @Test
    fun testEventNavigationTag() {
        onView(withId(R.id.mobileNumber))
            .perform(typeText(mobileNumber), closeSoftKeyboard())
        onView(withId(R.id.email))
            .perform(typeText(validEmail), closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.submit)).perform(ViewActions.click())
    }
}