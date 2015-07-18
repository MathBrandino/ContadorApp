package br.com.caelum.contadorapp.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.contadorapp.R;


public class MainActivity extends AppCompatActivity {


    private ListView listaValores;
    private List<Integer> valores;
    private TextView somatoria;
    private ArrayAdapter<Integer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);

        final EditText entradaValor = (EditText) findViewById(R.id.entra_valor_contagem);

        FloatingActionButton botaoAdiciona = (FloatingActionButton) findViewById(R.id.botao_adicionar);

        listaValores = (ListView) findViewById(R.id.lista_contador);

        valores = new ArrayList<>();

        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, valores);

        listaValores.setAdapter(adapter);

        botaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!entradaValor.getText().toString().trim().isEmpty()) {

                    int valor = Integer.parseInt(entradaValor.getText().toString());
                    valores.add(valor);
                    entradaValor.getText().clear();
                    onResume();
                }

            }
        });

        listaValores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                valores.remove(position);
                adapter.notifyDataSetChanged();

                return false;
            }
        });



        somatoria = (TextView) findViewById(R.id.soma);

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();

        int soma = 0;
        if (valores.size() > 0) {
            for (int valor : valores) {

                int somaNovo = valor;

                somaNovo = soma + somaNovo;

                soma = somaNovo;


            }
            somatoria.setText(String.valueOf(soma));
        }

    }
}