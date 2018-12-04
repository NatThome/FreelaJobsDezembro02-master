package com.nathy.freelajobs.freelajobs.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nathy.freelajobs.freelajobs.R;
import com.nathy.freelajobs.freelajobs.model.Usuario;

public class EditarCadastroUsuarioActivity extends AppCompatActivity {

    private EditText editNome, editDtNasc, editTel, editEmail, editCpf,
            editRg, editCep, editEnd, editCidade, editEstado;
    private Switch editSexo;
    private LinearLayout linearSexo;

    private Button buttonAlterar;
    private FirebaseDatabase database;
    private DatabaseReference firebaseRef;
    private String idUsuarioLogado;
    private ChildEventListener childEventListener;
    private Usuario usuarioLogada;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Usuario usuario = dataSnapshot.getValue(Usuario.class);

                editNome.setText(usuario.getNome());
                editDtNasc.setText(usuario.getDtNasc());
                editSexo.setText(usuario.getSexo());
                editEmail.setText(usuario.getEmail());
                editTel.setText(usuario.getTel());
                editRg.setText(usuario.getRg());
                editCpf.setText(usuario.getCpf());
                editCep.setText(usuario.getCep());
                editEnd.setText(usuario.getEnd());
                editCidade.setText(usuario.getCidade());
                editEstado.setText(usuario.getEstado());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cadastro_usuario);

        //Configuracoes iniciais
        inicializarComponentes();

        database = FirebaseDatabase.getInstance();
        firebaseRef = database.getReference("usuario")
               .child(Usuario.getUsuarioLogada().getIdUsuario());

        buttonAlterar = findViewById(R.id.buttonAlterar);
    }

    public void onClick(View v) {

        String nome = editNome.getEditableText().toString();
        String dtNasc = editDtNasc.getEditableText().toString();
        String sexo = editSexo.getEditableText().toString();
        String email = editEmail.getEditableText().toString();
        String tel = editTel.getEditableText().toString();
        String rg = editRg.getEditableText().toString();
        String cpf = editCpf.getEditableText().toString();
        String cep = editCep.getEditableText().toString();
        String end = editEnd.getEditableText().toString();
        String cidade = editCidade.getEditableText().toString();
        String estado = editEstado.getEditableText().toString();

        Usuario usuarioAtualizar = new Usuario( nome, dtNasc, sexo, email, tel,
                rg, cpf, cep, end, cidade, estado);

        firebaseRef.setValue(usuarioAtualizar);
    }

    private void inicializarComponentes(){

        editNome = (EditText)findViewById(R.id.editNome);
        editDtNasc= (EditText)findViewById(R.id.editDtNasc);
        editSexo = (Switch) findViewById(R.id.switchSexo);
        editEmail = (EditText)findViewById(R.id.editEmail);
        editTel = (EditText)findViewById(R.id.editTel);
        editRg = (EditText)findViewById(R.id.editRg);
        editCpf = (EditText)findViewById(R.id.editCpf);
        editCep = (EditText)findViewById(R.id.editCep);
        editEnd = (EditText)findViewById(R.id.editEnd);
        editCidade = (EditText)findViewById(R.id.editCidade);
        editEstado = (EditText)findViewById(R.id.editEstado);
        linearSexo = findViewById(R.id.linearSexo);
        buttonAlterar = (Button) findViewById(R.id.buttonAlterar);

      /*  altEmail.setFocusable(false);
        altDtNasc.setFocusable(false);
        altRg.setFocusable(false);
        altCpf.setFocusable(false);*/

    }
}
