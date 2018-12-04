package com.nathy.freelajobs.freelajobs.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nathy.freelajobs.freelajobs.R;
import com.nathy.freelajobs.freelajobs.model.Empresa;

public class EditarCadastroEmpresaActivity extends AppCompatActivity {

    private EditText editCnpj, editNomeFant, editRazaoSocial, editTel,
            editCep, editCidade, editEnd, editEmail;
    private Button buttonAtualizar;
    private DatabaseReference firebaseRef;
    private String idEmpresa;
    private String idUsuarioLogado;
    private Empresa empresa;
    private ChildEventListener childEventListener;
    private FirebaseDatabase database;

    //public void atualizarEmpresa(){
      // empresa.atualizar();
    //}

    @Override
    protected void onStart() {
        super.onStart();

        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //empresa.clear();
                Empresa empresa = dataSnapshot.getValue(Empresa.class);
                /*for (DataSnapshot filho : dataSnapshot.getChildren()){
                    empresa = filho.getValue(Empresa.class);
                    empresa.setIdEmpresa(filho.getKey());
                    empresa.atualizar();
                }*/
                editCnpj.setText(empresa.getCnpj());
                editNomeFant.setText(empresa.getNomeFant());
                editRazaoSocial.setText(empresa.getRazaoSocial());
                editTel.setText(empresa.getTel());
                editCep.setText(empresa.getCep());
                editCidade.setText(empresa.getCidade());
                editEnd.setText(empresa.getEnd());
                editEmail.setText(empresa.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cadastro_empresa);

        //Configuracoes iniciais
        inicializarComponentes();
        database = FirebaseDatabase.getInstance();
        firebaseRef = database.getReference("empresa")
                .child(Empresa.getEmpresaLogada().getIdEmpresa());

        //idEmpresa = EmpresaFirebase.getIdEmpresa();

        buttonAtualizar = findViewById(R.id.buttonAlterar);
    }

        public void onClick(View v) {
            String cnpj = editCnpj.getEditableText().toString();
            String nomeFant = editNomeFant.getEditableText().toString();
            String razaoScial = editRazaoSocial.getEditableText().toString();
            String tel = editTel.getEditableText().toString();
            String cep = editCep.getEditableText().toString();
            String cidade = editCidade.getEditableText().toString();
            String end = editEnd.getEditableText().toString();
            String email = editEmail.getEditableText().toString();

            Empresa empresaAtualizar = new Empresa(cnpj, nomeFant, razaoScial,
                    tel, cep, cidade, end, email);

            /*DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
            DatabaseReference EmpresaRef = firebaseRef
                    .child("empresa")
                    .child(getIdEmpresa()).setValue(empresaAtualizar);*/
            firebaseRef.setValue(empresaAtualizar);
        }

        /*public void removerEmpresa(View v){

        empresa.remover();
        }
    private void recuperarDadosEmpresa(View view) {

        DatabaseReference empresaRef = firebaseRef
                .child("empresa")
                .child( idEmpresa );

        empresaRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if( dataSnapshot.exists() ){
                   Empresa empresa = dataSnapshot.getValue(Empresa.class);
                   editCnpj.setText(empresa.getCnpj());
                    editNomeFant.setText(empresa.getNomeFant());
                    editRazaoSocial.setText(empresa.getRazaoSocial());
                    editTel.setText(empresa.getTel());
                    editCep.setText(empresa.getCep());
                    editCidade.setText(empresa.getCidade());
                    editEnd.setText(empresa.getEnd());
                    editEmail.setText(empresa.getEmail());;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", databaseError.getMessage());
            }
        });
    }*/

    private void inicializarComponentes(){
        editCnpj = findViewById(R.id.editCnpj);
        editNomeFant = findViewById(R.id.editNomeFant);
        editRazaoSocial = findViewById(R.id.editRazaoSocial);
        editTel = findViewById(R.id.editTel);
        editCep = findViewById(R.id.editCep);
        editCidade = findViewById(R.id.editCidade);
        editEnd = findViewById(R.id.editEnd);
        editEmail = findViewById(R.id.editEmail);
        buttonAtualizar = findViewById(R.id.buttonAlterar);

        editEmail.setFocusable(false);
        editCnpj.setFocusable(false);
        editRazaoSocial.setFocusable(false);
    }

}
