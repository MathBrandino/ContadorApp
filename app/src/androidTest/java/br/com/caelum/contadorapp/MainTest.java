package br.com.caelum.contadorapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import org.hamcrest.Matchers;

import br.com.caelum.contadorapp.activity.MainActivity;

/**
 * Created by matheus on 17/Z
                        //ViewMatchers.withText("Erro")
                )
        );
    }

    public void testaInclusao(){

        getActivity();

        Espresso.onView(
                ViewMatchers.withId(R.id.entra_valor_contagem)
        ).perform(
                ViewActions.typeText("123")
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.botao_adicionar)
        ).perform(
                ViewActions.click()
        );

        Espresso.onData(
                Matchers.allOf(
                        Matchers.is(
                                Matchers.instanceOf(Integer.class)
                        )
                )
        ).atPosition(0).check(
                ViewAssertions.matches(
                        ViewMatchers.withText("123")
                )
        );
    }

    public void testeExclusao(){

        getActivity();

        Espresso.onView(
                ViewMatchers.withId(R.id.entra_valor_contagem)
        ).perform(
                ViewActions.typeText("123")
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.botao_adicionar)
        ).perform(
                ViewActions.click()
        );

        Espresso.onData(
                Matchers.allOf(
                        Matchers.is(
                                Matchers.instanceOf(Integer.class)
                        )
                )
        ).atPosition(0).check(
                ViewAssertions.matches(
                        ViewMatchers.withText("123")
                )
        ).perform(
                ViewActions.longClick()
        );


    }

    public void testaValorInserido(){

        getActivity();


        Espresso.onView(
                ViewMatchers.withId(R.id.entra_valor_contagem)
        ).perform(
                ViewActions.typeText("123")
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.botao_adicionar)
        ).perform(
                ViewActions.click()
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.entra_valor_contagem)
        ).perform(
                ViewActions.typeText("123")
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.botao_adicionar)
        ).perform(
                ViewActions.click()
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.soma)
        ).check(
                ViewAssertions.matches(
                        ViewMatchers.withText("246")
                )
        );
    }

}
