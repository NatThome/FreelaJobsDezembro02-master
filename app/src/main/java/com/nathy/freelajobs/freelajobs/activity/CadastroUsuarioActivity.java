package com.nathy.freelajobs.freelajobs.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.nathy.freelajobs.freelajobs.R;
import com.nathy.freelajobs.freelajobs.helper.ConfiguracaoFirebase;
import com.nathy.freelajobs.freelajobs.model.Usuario;

import static android.app.ProgressDialog.show;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText campoNome, campoDtnasc, campoTel, campoEmail, campoCpf,
            campoRg, campoCep, campoEnd, campoCidade, campoEstado;
    private Switch campoSexo;
    private LinearLayout linearSexo;

    private FirebaseAuth autenticacao;
    private Button buttonSalvar;
    private FirebaseDatabase database;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        //Configuracoes iniciais
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        //database = FirebaseDatabase.getInstance();

        inicializarComponentes();
    }

    private void salvarUsuario (){

        try {

            usuario.salvar();

            Toast.makeText(CadastroUsuarioActivity.this,"Cadastrado com sucesso", Toast.LENGTH_SHORT).show();

            //autenticacao.signOut();
            /// /startActivity(new Intent(getApplicationContext(), UsuarioFeedActivity.class));
            //finish();

        } catch (Exception e) {
            Toast.makeText(CadastroUsuarioActivity.this,"Erro ao Cadastrar", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    private Usuario configurarUsuario(){

        String editNome = campoNome.getText().toString();
        String editDtNasc = campoDtnasc.getText().toString();
        String editTel = campoTel.getText().toString();
        String editEmail = campoEmail.getText().toString();
        String editCpf = campoCpf.getText().toString();
        String editRg = campoRg.getText().toString();
        String editCep = campoCep.getText().toString();
        String editEnd = campoEnd.getText().toString();
        String editeCidade = campoCidade.getText().toString();
        String editEstado = campoEstado.getText().toString();
        String editSexo = campoSexo.getText().toString();

        Usuario usuario = new Usuario();
        usuario.setNome(editNome);
        usuario.setDtNasc(editDtNasc);
        usuario.setTel(editTel);
        usuario.setEmail(editEmail);
        usuario.setCpf(editCpf);
        usuario.setRg(editRg);
        usuario.setCep(editCep);
        usuario.setEnd(editEnd);
        usuario.setCidade(editeCidade);
        usuario.setEstado(editEstado);
        usuario.setSexo(editSexo);

        return usuario;
    }

    public void validarDadosUsuario(View view){

        usuario = configurarUsuario();

        //Validar se os campos foram preenchidos
        if(!usuario.getNome().isEmpty()){
            if(!usuario.getDtNasc().isEmpty()){
                if (!usuario.getTel().isEmpty()){
                    if(!usuario.getEmail().isEmpty()){
                        if (!usuario.getCpf().isEmpty()){
                            if (!usuario.getRg().isEmpty()){
                                if (!usuario.getCep().isEmpty()){
                                    if (!usuario.getEnd().isEmpty()){
                                        if (!usuario.getCidade().isEmpty()){
                                            if (!usuario.getEstado().isEmpty()){


                                                    salvarUsuario();


                                            }else {
                                                Toast.makeText(CadastroUsuarioActivity.this, "Preencha Estado!!!", Toast.LENGTH_SHORT).show(); }
                                        }else {
                                            Toast.makeText(CadastroUsuarioActivity.this, "Preencha Cidade!!!", Toast.LENGTH_SHORT).show(); }
                                    }else {
                                        Toast.makeText(CadastroUsuarioActivity.this, "Preencha Endereço!!!", Toast.LENGTH_SHORT).show(); }
                                }else {
                                    Toast.makeText(CadastroUsuarioActivity.this, "Preencha o CEP!!!", Toast.LENGTH_SHORT).show(); }
                            }else {
                                Toast.makeText(CadastroUsuarioActivity.this, "Preencha o Rg!!!", Toast.LENGTH_SHORT).show(); }
                        }else {
                            Toast.makeText(CadastroUsuarioActivity.this, "Preencha o CPF!!!", Toast.LENGTH_SHORT).show(); }
                    }else {
                        Toast.makeText(CadastroUsuarioActivity.this, "Preencha o Email!!!", Toast.LENGTH_SHORT).show(); }
                }else {
                    Toast.makeText(CadastroUsuarioActivity.this, "Preencha o Tel!!!", Toast.LENGTH_SHORT).show(); }
            }else {
                Toast.makeText(CadastroUsuarioActivity.this, "Preencha a Nascimento!!!", Toast.LENGTH_SHORT).show(); }
        }else {
            Toast.makeText(CadastroUsuarioActivity.this, "Preencha o Nome!!!", Toast.LENGTH_SHORT).show(); }
    }

    private String getSexo(){
        return campoSexo.isChecked() ? "F" : "M";
    }

    private void inicializarComponentes(){

        campoNome = (EditText)findViewById(R.id.editNome);
        campoDtnasc = (EditText)findViewById(R.id.editDtNasc);
        campoTel = (EditText)findViewById(R.id.editTel);
        campoEmail = (EditText)findViewById(R.id.editEmail);
        campoCpf = (EditText)findViewById(R.id.editCpf);
        campoRg = (EditText)findViewById(R.id.editRg);
        campoCep = (EditText)findViewById(R.id.editCep);
        campoEnd = (EditText)findViewById(R.id.editEnd);
        campoCidade = (EditText)findViewById(R.id.editCidade);
        campoEstado = (EditText)findViewById(R.id.editEstado);
        campoSexo = (Switch) findViewById(R.id.switchSexo);
        linearSexo = findViewById(R.id.linearSexo);

        buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
    }
}
