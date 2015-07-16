package br.com.caelum.contadorapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.AdapterView;
import android.widget.ListView;

import org.hamcrest.Matchers;

import br.com.caelum.contadorapp.activity.MainActivity;

/**
 * Created by matheus on 16/07/15.
 */
@LargeTest
public class MainTest extends ActivityInstrumentationTestCase2 {
    public MainTest() {
        super(MainActivity.class);
    }

    public void testa(){
        getActivity();

        Espresso.onView(
                ViewMatchers.withId(R.id.entra_valor_contagem)
        ).perform(
                ViewActions.typeText("123")
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.fab)
        ).perform(
                ViewActions.click()
        );

        Espresso.onData(
                Matchers.allOf(
                        Matchers.is(
                            Matchers.instanceOf(Integer.class)
                        )
                )
        ).perform(
                ViewActions.longClick()
        );

    }
}
