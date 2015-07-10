package br.com.caelum.contadorapp.activity;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.caelum.contadorapp.R;
import br.com.caelum.contadorapp.fragment.ListaFragment;


public class MainActivity extends AppCompatActivity {

    private Bundle bundle;
    private String nome = "Nós";
    private String nome1= "Eles";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bundle = savedInstanceState;

        ListaFragment listaFragmentDireita = new ListaFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("nome", nome);
        listaFragmentDireita.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_direita, listaFragmentDireita);
        transaction.commit();

        ListaFragment listaFragmentEsquerda = new ListaFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("nome", nome1);
        listaFragmentEsquerda.setArguments(bundle1);


        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.frame_esquerda, listaFragmentEsquerda);
        tx.commit();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sair) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("nome", nome);
        outState.putSerializable("nome1", nome1);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        nome = (String) savedInstanceState.getSerializable("nome");
        nome1 = (String) savedInstanceState.getSerializable("nome1");
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
