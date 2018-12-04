package com.nathy.freelajobs.freelajobs.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
import com.nathy.freelajobs.freelajobs.model.Usuario;
import com.nathy.freelajobs.freelajobs.model.Vaga;

import java.util.ArrayList;
import java.util.List;

public class FeedVagasActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewVagas;
    private AdapterVagas adapter;
    private List<Vaga> vagas;
    private DatabaseReference firebaseRef;
    private Vaga todasVagas;
    private LinearLayoutManager mLayoutManagerTodasVagas;
    private FirebaseDatabase database;
    private FirebaseAuth autenticacao;
    private Usuario usuarioLogada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_vagas);
        mRecyclerViewVagas = findViewById(R.id.recycleViewTodasVagas);

        //Configuracoes iniciais
        inicializarComponentes();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        usuarioLogada = Usuario.getUsuarioLogada();
        firebaseRef = ConfiguracaoFirebase.getFirebase();
        database = FirebaseDatabase.getInstance();
        firebaseRef = database.getReference("vagas");

        mRecyclerViewVagas.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewVagas.setHasFixedSize(true);
        adapter = new AdapterVagas(vagas, this);
        mRecyclerViewVagas.setAdapter(adapter);

        recuperarVagas();
        carregarTodasVagas();
    }

    private void recuperarVagas() {

        DatabaseReference vagasRef = firebaseRef
                .child("vagas");

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

    private void inicializarComponentes() {
        mRecyclerViewVagas = findViewById(R.id.recycleViewTodasVagas);
    }

    private void carregarTodasVagas(){

        mRecyclerViewVagas.setHasFixedSize(true);

        mLayoutManagerTodasVagas = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerViewVagas.setLayoutManager(mLayoutManagerTodasVagas);

        vagas = new ArrayList<>();

        firebaseRef = FirebaseDatabase.getInstance().getReference();

        firebaseRef.child("vagas").orderByChild("idVaga").addValueEventListener(new ValueEventListener() {
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

