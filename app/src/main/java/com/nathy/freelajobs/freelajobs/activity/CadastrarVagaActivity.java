package com.nathy.freelajobs.freelajobs.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.nathy.freelajobs.freelajobs.R;
import com.nathy.freelajobs.freelajobs.helper.ConfiguracaoFirebase;
import com.nathy.freelajobs.freelajobs.model.Vaga;

import dmax.dialog.SpotsDialog;

public class CadastrarVagaActivity extends AppCompatActivity
        implements View.OnClickListener{

        private EditText campoTitulo, campoEvento, campoDescricao;
        private Spinner campoEstado, campoCidade;
        private FirebaseAuth autenticacao;
        private Vaga vaga;
        private AlertDialog dialog;

        private String[] permissoes = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_vaga);

        //Configuracoes iniciais
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        inicializarComponentes();
        carregarDadosSpinner();
    }

    private void carregarDadosSpinner(){

        String[] estados = new String[]{
                "SP"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,
                estados
        );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        campoEstado.setAdapter( adapter );

        String[] cidades = new String[]{
                "Guarulhos",  "Osasco", "Santo André", "São Bernardo do Campo", "São Paulo", "Taboão da Serra"
        };

        ArrayAdapter<String> adapterC = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,
                cidades
        );
        adapterC.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        campoCidade.setAdapter( adapterC );
    }

    public void salvarVaga() {

        dialog = new SpotsDialog.Builder()
                .setContext( this )
                .setMessage( "Salvando Vaga" )
                .setCancelable( false )
                .build();
        dialog.show();

        vaga.salvar();

        dialog.dismiss();
        finish();
    }

    private Vaga configurarVaga(){

        String estado = campoEstado.getSelectedItem().toString();
        String cidade = campoCidade.getSelectedItem().toString();
        String titulo = campoTitulo.getText().toString();
        String evento = campoEvento.getText().toString();
        String descricao = campoDescricao.getText().toString();

        Vaga vaga = new Vaga();
        vaga.setEstado( estado );
        vaga.setCidade( cidade );
        vaga.setTitulo( titulo );
        vaga.setEvento( evento );
        vaga.setDescricao( descricao );

        return vaga;
    }

    public void validarDadosVaga(View view){

        vaga = configurarVaga();

        if( !vaga.getEstado().isEmpty() ) {
            if ( !vaga.getCidade().isEmpty() ) {
                if (!vaga.getTitulo().isEmpty()) {
                    if (!vaga.getEvento().isEmpty()) {
                        if (!vaga.getDescricao().isEmpty()) {

                            salvarVaga();

                        } else {
                            exibirMensagemErro("Preencha o campo Descrição!");
                        }
                    } else {
                        exibirMensagemErro("Preencha o campo Evento!");
                    }
                } else {
                    exibirMensagemErro("Preencha o campo Título!");
                }
            }else {
                exibirMensagemErro("Selecione o campo Cidade!");
            }
        }else {
            exibirMensagemErro("Selecione o campo Estado!");
        }
    }

    private void exibirMensagemErro(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }


    private void inicializarComponentes(){
        campoTitulo = findViewById(R.id.editTextTitulo);
        campoEvento = findViewById(R.id.editTextEvento);
        campoDescricao = findViewById(R.id.editTextDescricao);
        campoEstado = findViewById(R.id.spinnerEstado);
        campoCidade = findViewById(R.id.spinnerCidade);

    }


    @Override
    public void onClick(View view) {

    }
}
