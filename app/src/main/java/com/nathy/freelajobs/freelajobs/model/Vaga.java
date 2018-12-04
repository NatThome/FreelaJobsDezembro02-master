package com.nathy.freelajobs.freelajobs.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.nathy.freelajobs.freelajobs.helper.ConfiguracaoFirebase;
import com.nathy.freelajobs.freelajobs.helper.EmpresaFirebase;

import static com.nathy.freelajobs.freelajobs.helper.EmpresaFirebase.getIdEmpresa;

public class Vaga {

    private String idVaga;
    private String estado;
    private String cidade;
    private String titulo;
    private String evento;
    private String descricao;

    public Vaga() {
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        DatabaseReference vagaRef = referenciaFirebase
                .child("minhas_vagas");
        setIdVaga( vagaRef.push().getKey());
    }

    public Vaga(String idVaga, String estado, String cidade, String titulo, String evento, String descricao){
        this.estado = estado;
        this.cidade = cidade;
        this.titulo = titulo;
        this.evento = evento;
        this.descricao = descricao;
        setIdVaga(idVaga);
    }

    public Vaga(String estado, String cidade, String titulo, String evento, String descricao){
        setEstado(estado);
        setCidade(cidade);
        setTitulo(titulo);
        setEvento(evento);
        setDescricao(descricao);
    }

    public void salvar(){

        String idEmpresa = getIdEmpresa();
        DatabaseReference vagaRef = ConfiguracaoFirebase.getFirebase()
                .child("minhas_vagas");

        vagaRef.child(idEmpresa)
                .child(getIdVaga())
                .setValue(this);

        salvarVagaPublico();
    }

    public void salvarVagaPublico(){

        DatabaseReference vagaRef = ConfiguracaoFirebase.getFirebase()
                .child("vagas");

        vagaRef.child(getEstado())
                .child(getCidade())
                .child(getIdVaga())
                .setValue(this);
    }


    public void remover() {
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference vagaRef = firebaseRef
                .child("minhas_vagas")
                .child( getIdEmpresa() )
                .child( getIdVaga() );
        vagaRef.removeValue();

    }

    public String getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(String idVaga) {
        this.idVaga = idVaga;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}

