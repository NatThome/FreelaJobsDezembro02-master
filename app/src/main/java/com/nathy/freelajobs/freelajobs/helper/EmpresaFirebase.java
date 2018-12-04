package com.nathy.freelajobs.freelajobs.helper;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;

public class EmpresaFirebase {

    //retorna o id do usuario
    public static String getIdEmpresa(){
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        return autenticacao.getCurrentUser().getUid();
    }

    //recuperando os dados do / Empresa atual logado
    public static FirebaseUser getEmpresaAtual(){
        FirebaseAuth empresa = ConfiguracaoFirebase.getFirebaseAutenticacao();
        return empresa.getCurrentUser();
    }

}
