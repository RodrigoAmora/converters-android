package br.com.rodrigoamora.converters

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import br.com.rodrigoamora.converters.ui.activity.MainActivity
import org.hamcrest.CoreMatchers.anything
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TemperatureFragmentTest {

    private lateinit var context: Context

    @Before
    fun init() {
        ActivityScenario.launch(MainActivity::class.java)
        this.context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun testCelsiusToFahrenheit() {
        Espresso
            .onView(ViewMatchers.withId(R.id.temperature))
            .perform(ViewActions.click())
            .perform(ViewActions.typeText("32"))

        Espresso
            .onView(ViewMatchers.withId(R.id.bt_convert))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso
            .onView(ViewMatchers.withId(R.id.bt_convert))
            .perform(ViewActions.click())

        Espresso
            .onView(ViewMatchers.withId(R.id.tv_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("89.6 ºF")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testCelsiusToFahrenheitWithNegativeTemperature() {
        Espresso
            .onView(ViewMatchers.withId(R.id.temperature))
            .perform(ViewActions.click())
            .perform(ViewActions.typeText("-10"))

        Espresso
            .onView(ViewMatchers.withId(R.id.bt_convert))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso
            .onView(ViewMatchers.withId(R.id.bt_convert))
            .perform(ViewActions.click())

        Espresso
            .onView(ViewMatchers.withId(R.id.tv_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("14.0 ºF")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testFahrenheitToCelsius() {
        Espresso
            .onView(ViewMatchers.withId(R.id.temperature))
            .perform(ViewActions.click())
            .perform(ViewActions.typeText("100"))

        Espresso
            .onData(anything())
            .inAdapterView(ViewMatchers.withId(R.id.convert))
            .atPosition(1)
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        Espresso
            .onView(ViewMatchers.withId(R.id.bt_convert))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso
            .onView(ViewMatchers.withId(R.id.bt_convert))
            .perform(ViewActions.click())

        Espresso
            .onView(ViewMatchers.withId(R.id.tv_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("37.78 ºC")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testFahrenheitToCelsiusWithNegativeTemperature() {
        Espresso
            .onView(ViewMatchers.withId(R.id.temperature))
            .perform(ViewActions.click())
            .perform(ViewActions.typeText("-10"))

        Espresso
            .onData(anything())
            .inAdapterView(ViewMatchers.withId(R.id.convert))
            .atPosition(1)
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        Espresso
            .onView(ViewMatchers.withId(R.id.bt_convert))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso
            .onView(ViewMatchers.withId(R.id.bt_convert))
            .perform(ViewActions.click())

        Espresso
            .onView(ViewMatchers.withId(R.id.tv_result))
            .check(ViewAssertions.matches(ViewMatchers.withText("-23.33 ºC")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}
