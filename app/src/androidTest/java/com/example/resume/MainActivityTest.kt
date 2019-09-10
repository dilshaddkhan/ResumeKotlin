package com.example.resume

import AboutPersonalDetail
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }


    @After
    fun tearDown() {
    }


    @Test
    fun changeText_sameActivity() {
        onView(withId(R.id.navigation)).perform(click())
    }


    @Test
    fun testEventFragment() {
        val scenario = launchFragmentInContainer<AboutPersonalDetail>()
        onView(withId(R.id.call_me))
            .perform(click())
        onView(withId(R.id.email_me))
            .perform(click())
    }
}