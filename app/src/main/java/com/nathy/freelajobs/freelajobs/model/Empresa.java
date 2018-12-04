package com.nathy.freelajobs.freelajobs.model;

import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.nathy.freelajobs.freelajobs.activity.LoginActivity;
import com.nathy.freelajobs.freelajobs.helper.ConfiguracaoFirebase;
import com.nathy.freelajobs.freelajobs.helper.EmpresaFirebase;

import java.util.HashMap;
import java.util.Map;

import static com.nathy.freelajobs.freelajobs.helper.EmpresaFirebase.getIdEmpresa;

public class Empresa {

    private String idEmpresa;
    private String cnpj;
    private String nomeFant;
    private String razaoSocial;
    private String tel;
    private String cep;
    private String cidade;
    private String end;
    private String email;

    private static Empresa empresaLogada;

    public static Empresa getEmpresaLogada() {
        return empresaLogada;
    }

    public static void setEmpresaLogada(Empresa empresaLogada) {
        Empresa.empresaLogada = empresaLogada;
    }

    public Empresa() {
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference empresaRef = firebaseRef
                .child("empresa");
        setIdEmpresa( empresaRef.push().getKey());
    }

    public Empresa(String idEmpresa, String cnpj, String nomeFant, String razaoSocial, String tel,
                   String cep, String cidade, String end, String email) {
        this.cnpj = cnpj;
        this.nomeFant = nomeFant;
        this.razaoSocial = razaoSocial;
        this.tel = tel;
        this.cep = cep;
        this.cidade = cidade;
        this.end = end;
        this.email = email;
        setIdEmpresa(idEmpresa); 
    }
    
    public Empresa(String cnpj, String nomeFant, String razaoSocial, String tel,
                   String cep, String cidade, String end, String email){
        setCnpj(cnpj);
        setNomeFant(nomeFant);
        setRazaoSocial(razaoSocial);
        setTel(tel);
        setCep(cep);
        setCidade(cidade);
        setEnd(end);
        setEmail(email);
    }

    public void salvar(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference empresaRef = firebaseRef
                .child("empresa")
                .child( getIdEmpresa() );
        empresaRef.setValue( this );
    }

    public void atualizar(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference empresaRef = firebaseRef
                .child("empresa")
                .child(idEmpresa);
        Map<String, Object> valoresEmpresa = converterParaMap();
        empresaRef.updateChildren( valoresEmpresa );
    }

    public Map<String, Object> converterParaMap(){

        HashMap<String, Object> empresaMap = new HashMap<>();

        empresaMap.put("nome fantasia", getNomeFant());
        empresaMap.put("tel", getTel());
        empresaMap.put("cep", getCep());
        empresaMap.put("cidade", getCidade());
        empresaMap.put("end", getEnd());

        return empresaMap;
    }

    public void remover(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference empresaRef = firebaseRef
                .child("empresa")
                .child( getIdEmpresa());
            empresaRef.removeValue();

    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFant() {
        return nomeFant;
    }

    public void setNomeFant(String nomeFant) {
        this.nomeFant = nomeFant;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public void clear() {
    }
}
