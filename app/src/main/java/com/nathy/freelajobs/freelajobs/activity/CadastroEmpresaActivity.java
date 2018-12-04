package com.nathy.freelajobs.freelajobs.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.nathy.freelajobs.freelajobs.R;
import com.nathy.freelajobs.freelajobs.helper.ConfiguracaoFirebase;
import com.nathy.freelajobs.freelajobs.model.Empresa;

public class CadastroEmpresaActivity extends AppCompatActivity {

    private EditText campoCnpj, campoNomeFant, campoRazaoSocial, campoEmail, campoTel, campoCep,
            campoCidade, campoEnd;
    private Button botaoCadastrar;
    private FirebaseAuth autenticacao;
    private Empresa empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_empresa);

        //Configuracoes iniciais
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        inicializarComponentes();
    }

    public void salvarEmpresa(){

        try {

        empresa.salvar();

        Toast.makeText(CadastroEmpresaActivity.this,
                "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            //alteracao 29/11 19h
            //autenticacao.signOut();


        } catch (Exception e) {
            Toast.makeText(CadastroEmpresaActivity.this,
                    "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    private Empresa configurarEmpresa(){
        String editCnpj = campoCnpj.getText().toString();
        String editNomeFant = campoNomeFant.getText().toString();
        String editRazaoSocial = campoRazaoSocial.getText().toString();
        String editTel = campoTel.getText().toString();
        String editCep = campoCep.getText().toString();
        String editCidade = campoCidade.getText().toString();
        String editEnd = campoEnd.getText().toString();
        String editEmail = campoEmail.getText().toString();

        Empresa empresa = new Empresa();
        empresa.setCnpj(editCnpj);
        empresa.setNomeFant(editNomeFant);
        empresa.setRazaoSocial(editRazaoSocial);
        empresa.setTel(editTel);
        empresa.setCep(editCep);
        empresa.setCidade(editCidade);
        empresa.setEnd(editEnd);
        empresa.setEmail(editEmail);

        return empresa;
    }

     public void validarDadosEmpresa(View view){

        empresa = configurarEmpresa();

        //Validar se os campos foram preenchidos
         if (!empresa.getCnpj().isEmpty()) {
             if (!empresa.getNomeFant().isEmpty()) {
                 if (!empresa.getRazaoSocial().isEmpty()) {
                     if (!empresa.getTel().isEmpty()) {
                         if (!empresa.getCep().isEmpty()) {
                             if (!empresa.getCidade().isEmpty()) {
                                 if (!empresa.getEnd().isEmpty()) {
                                     if (!empresa.getEmail().isEmpty()) {

                                         salvarEmpresa();

                                     } else {
                                         Toast.makeText(CadastroEmpresaActivity.this, "Preenchao Email!!!", Toast.LENGTH_SHORT).show();
                                     }
                                 } else {
                                     Toast.makeText(CadastroEmpresaActivity.this, "Preenchao endereço!!!", Toast.LENGTH_SHORT).show();
                                 }
                             } else {
                                 Toast.makeText(CadastroEmpresaActivity.this, "Preencha a Cidade!!!", Toast.LENGTH_SHORT).show();
                             }
                         } else {
                             Toast.makeText(CadastroEmpresaActivity.this, "Preencha o CEP!!!", Toast.LENGTH_SHORT).show();
                         }
                     } else {
                         Toast.makeText(CadastroEmpresaActivity.this, "Preencha o Tel!!!", Toast.LENGTH_SHORT).show();
                     }
                 } else {
                     Toast.makeText(CadastroEmpresaActivity.this, "Preencha a Razão Social!!!", Toast.LENGTH_SHORT).show();
                 }
             } else {
                 Toast.makeText(CadastroEmpresaActivity.this, "Preencha o nome fantasia!!!", Toast.LENGTH_SHORT).show();
             }
         } else {
             Toast.makeText(CadastroEmpresaActivity.this, "Preencha o CNPJ!!!", Toast.LENGTH_SHORT).show();
         }
    }

    private void inicializarComponentes(){
        campoCnpj = findViewById(R.id.editCnpj);
        campoNomeFant = findViewById(R.id.editNomeFant);
        campoRazaoSocial = findViewById(R.id.editRazaoSocial);
        campoTel = findViewById(R.id.editTel);
        campoCep = findViewById(R.id.editCep);
        campoCidade = findViewById(R.id.editCidade);
        campoEnd = findViewById(R.id.editEnd);
        campoEmail = findViewById(R.id.editEmail);
        botaoCadastrar = findViewById(R.id.buttonCadastrar);
    }
}
