package com.example.resume.ui.activity

import AboutPersonalDetail
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.resume.R
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {


    // initializing the MainActivity class
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }


    @After
    fun tearDown() {
    }


    // creating the click test for the navigation tab
    @Test
    fun testEventNavigationTag() {
        onView(withId(R.id.navigation)).perform(click())
    }


    // creating the click test for the email and call button
    @Test
    fun testEventAboutFragment() {
        val scenario = launchFragmentInContainer<AboutPersonalDetail>()
        onView(withId(R.id.call_me))
            .perform(click())
        onView(withId(R.id.email_me))
            .perform(click())
    }
}