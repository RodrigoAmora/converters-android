package br.com.rodrigoamora.converters

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import br.com.rodrigoamora.converters.ui.activity.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RomanNumberFragmentTest {

    private lateinit var context: Context

    @Before
    fun init() {
        ActivityScenario.launch(MainActivity::class.java)
        this.context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun testKilometerToMile() {
        Espresso
            .onView(ViewMatchers.withId(R.id.drawer_layout))
            //.check(ViewMatchers(ViewAssertions.isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())

        Espresso
            .onView(ViewMatchers.withId(R.id.nav_roman_number))
            .perform(ViewActions.click())

        Espresso
            .onView(ViewMatchers.withId(R.id.input_number))
            .perform(ViewActions.click())
            .perform(ViewActions.typeText("3"))

        Espresso
            .onView(ViewMatchers.withId(R.id.bt_convert))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso
            .onView(ViewMatchers.withId(R.id.bt_convert))
            .perform(ViewActions.click())

        Espresso
            .onView(ViewMatchers.withId(R.id.tv_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("III")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}