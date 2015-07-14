package br.com.caelum.contadorapp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.contadorapp.R;

/**
 * Created by matheus on 08/07/15.
 *
 *
 */
public class ListaFragment extends Fragment {

    private String nome;
    private ListView listView;
    private TextInputLayout layout;
    private EditText entradaValor;
    private List<Integer> valores ;
    private TextView resultadoSoma;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        nome = (String) bundle.getSerializable("nome");

        View view = inflater.inflate(R.layout.item_contador, container, false);

        valores = new ArrayList<>();
        TextView nomeTextView = (TextView) view.findViewById(R.id.nome_text);

        nomeTextView.setText(nome);

        entradaValor = (EditText) view.findViewById(R.id.entra_valor_contagem);

        layout = (TextInputLayout) entradaValor.getParent();

        listView = (ListView) view.findViewById(R.id.lista_contador);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Atencao")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("Remover : " + valores.get(position).toString() + "?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                valores.remove(position);
                                onResume();
                            }
                        })
                        .setNegativeButton("Não", null).show();

                return false;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!entradaValor.getText().toString().trim().isEmpty()) {
                    String valor = entradaValor.getText().toString();

                    valores.add(Integer.valueOf(String.valueOf(valor)));

                    entradaValor.getText().clear();
                    layout.setErrorEnabled(false);


                    onResume();
                } else {
                    layout.setError("Precisa de um valor");
                }
            }
        });


        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab2);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valores.clear();
                onResume();
            }
        });


        resultadoSoma = (TextView) view.findViewById(R.id.soma);




        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        int soma = 0;
        if (valores.size() > 0) {
            for (int valor : valores) {

                int somaNovo = valor;

                somaNovo = soma + somaNovo;

                soma = somaNovo;


            }
        }

        resultadoSoma.setText(String.valueOf(soma));

        carregaLista(valores);

    }

    public void carregaLista(List<Integer> valores){
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, valores);

        listView.setAdapter(adapter);

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("valores", (Serializable) valores);
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null) {
            valores = (List<Integer>) savedInstanceState.getSerializable("valores");
        }
    }

}
