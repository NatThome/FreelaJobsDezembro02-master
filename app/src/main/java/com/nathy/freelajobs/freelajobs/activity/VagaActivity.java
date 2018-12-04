package com.nathy.freelajobs.freelajobs.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nathy.freelajobs.freelajobs.AdapterVagas.AdapterVagas;
import com.nathy.freelajobs.freelajobs.R;
import com.nathy.freelajobs.freelajobs.helper.ConfiguracaoFirebase;
import com.nathy.freelajobs.freelajobs.model.Empresa;
import com.nathy.freelajobs.freelajobs.model.Vaga;
import com.nathy.freelajobs.freelajobs.listener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class VagaActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewVagas;
    private AdapterVagas adapter;
    private List<Vaga> vagas = new ArrayList<>();
    private DatabaseReference firebaseRef;
    private Vaga todasVagas;
    private LinearLayoutManager mLayoutManagerTodasVagas;
    private FirebaseDatabase database;
    private FirebaseAuth autenticacao;
    private Empresa empresaLogada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaga);

        mRecyclerViewVagas = findViewById(R.id.recycleViewTodasVagas);

        //Configuracoes iniciais
        inicializarComponentes();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        empresaLogada = Empresa.getEmpresaLogada() ;
        firebaseRef = ConfiguracaoFirebase.getFirebase();
        database = FirebaseDatabase.getInstance();
        firebaseRef = database.getReference("minhas_vagas")
                .child(Empresa.getEmpresaLogada().getIdEmpresa());

        mRecyclerViewVagas.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewVagas.setHasFixedSize(true);
        adapter = new AdapterVagas(vagas, this);
        mRecyclerViewVagas.setAdapter(adapter);

        recuperarVagas();
        carregarTodasVagas();

        //Adiciona evento de clique no recyclerview
        mRecyclerViewVagas.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        mRecyclerViewVagas,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Vaga vagaSelecionado = vagas.get(position);
                                vagaSelecionado.remover();
                                Toast.makeText(VagaActivity.this,
                                        "Vaga excluída com sucesso!",
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }

    private void inicializarComponentes() {
        mRecyclerViewVagas = findViewById(R.id.recycleViewTodasVagas);
    }

    private void recuperarVagas() {

        DatabaseReference vagasRef = firebaseRef
                .child("minhas_vagas");

        vagasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vagas.clear();

                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    vagas.add(ds.getValue(Vaga.class));
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void carregarTodasVagas(){

        mRecyclerViewVagas.setHasFixedSize(true);

        mLayoutManagerTodasVagas = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerViewVagas.setLayoutManager(mLayoutManagerTodasVagas);

        vagas = new ArrayList<>();

        firebaseRef = FirebaseDatabase.getInstance().getReference();

        firebaseRef.child("minhas_vagas").orderByChild("idVaga").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    todasVagas = postSnapshot.getValue(Vaga.class);

                    vagas.add(todasVagas);

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter = new AdapterVagas(vagas, this);

        mRecyclerViewVagas.setAdapter(adapter);
    }


}