package com.nathy.freelajobs.freelajobs.model;

import com.google.firebase.database.DatabaseReference;
import com.nathy.freelajobs.freelajobs.helper.ConfiguracaoFirebase;

import java.util.HashMap;
import java.util.Map;

public class Usuario {

    private String idUsuario;
    //private String senha;
    private String nome;
    private String dtNasc ;
    private String sexo;
    private String email;
    private String tel;
    private String rg;
    private String cpf;
    private String cep;
    private String end;
    private String cidade;
    private String estado;

    private static Usuario usuarioLogada;

    public static Usuario getUsuarioLogada() {
        return usuarioLogada;
    }

    public static void setUsuarioLogada(Usuario usuarioLogada) {
        Usuario.usuarioLogada = usuarioLogada;
    }

    public Usuario() {
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference usuarioRef = firebaseRef
                .child("usuario");
        setIdUsuario( usuarioRef.push().getKey());
    }

    public Usuario(String idUsuario, /*String senha,*/ String nome, String dtNasc, String sexo,String email,
                   String tel, String rg, String cpf, String cep, String end, String cidade, String estado) {

        //this.senha = senha;
        this.nome = nome;
        this.dtNasc = dtNasc;
        this.sexo = sexo;
        this.email = email;
        this.tel = tel;
        this.rg = rg;
        this.cpf = cpf;
        this.cep = cep;
        this.end = end;
        this.cidade = cidade;
        this.estado = estado;
        setIdUsuario(idUsuario);
    }

    public Usuario(/*String senha,*/ String nome, String dtNasc, String sexo,String email,
                 String tel, String rg, String cpf, String cep, String end, String cidade, String estado) {

        setNome(nome);
        setDtNasc(dtNasc);
        setSexo(sexo);
        setEmail(email);
        setTel(tel);
        setRg(rg);
        setCpf(cpf);
        setCep(cep);
        setEnd(end);
        setCidade(cidade);
        setEstado(estado);
    }

    public void salvar(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference usuarioRef = firebaseRef
                .child("usuario")
                .child( getIdUsuario() );
        usuarioRef.setValue( this );
    }

    public void atualizar(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference usuarioRef = firebaseRef
                .child("usuario")
                .child(idUsuario);
        Map<String, Object> valoresUsuario = converterParaMap();
        usuarioRef.updateChildren( valoresUsuario );
    }

    public Map<String, Object> converterParaMap(){

        HashMap<String, Object> usuarioMap = new HashMap<>();

        usuarioMap.put("nome", getNome());
        usuarioMap.put("sexo",getSexo());
        usuarioMap.put("tel", getTel());
        usuarioMap.put("cep", getCep());
        usuarioMap.put("end", getEnd());
        usuarioMap.put("cidade", getCidade());
        usuarioMap.put("estado", getEstado());

        return usuarioMap;
    }

    public void remover(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference usuarioRef = firebaseRef
                .child("usuario")
                .child( getIdUsuario() );
        usuarioRef.removeValue();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String id) {
        this.idUsuario = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   /* public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }*/
}
