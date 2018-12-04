package com.nathy.freelajobs.freelajobs.AdapterVagas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nathy.freelajobs.freelajobs.R;
import com.nathy.freelajobs.freelajobs.model.Vaga;

import java.util.ArrayList;
import java.util.List;

public class AdapterVagas extends RecyclerView.Adapter<AdapterVagas.ViewHolder> {

    private List<Vaga> mVagasList;
    private Context context;
    private DatabaseReference referenciaFirebase;
    private List<Vaga> vagas;
    private Vaga todasVagas;


    public AdapterVagas(List<Vaga> v, Context c){
        context = c;
        mVagasList = v ;
    }

    @Override
    public AdapterVagas.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_todas_vagas, viewGroup, false);

        return new AdapterVagas.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterVagas.ViewHolder holder, int position) {

        final Vaga item = mVagasList.get(position);

        vagas = new ArrayList<>();

        referenciaFirebase = FirebaseDatabase.getInstance().getReference();

        referenciaFirebase.child("minhas_vagas").orderByChild("idVaga").equalTo(item.getIdVaga()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vagas.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    todasVagas = postSnapshot.getValue(Vaga.class);

                    vagas.add(todasVagas);

                    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

                    final int height = (displayMetrics.heightPixels / 4);
                    final int width = (displayMetrics.widthPixels / 2);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        holder.editTextEvento.setText(item.getEvento());
        holder.editTextTitulo.setText(item.getTitulo());
//        holder.editTextDescricao.setText(item.getDescricao());
        holder.editTextCidade.setText(item.getCidade());
        holder.editTextEstado.setText(item.getEstado());

        holder.linearLayoutVagas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mVagasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView editTextTitulo;
        protected TextView editTextEvento;
        protected TextView editTextDescricao;
        protected TextView editTextCidade;
        protected TextView editTextEstado;
        protected LinearLayout linearLayoutVagas;

        public ViewHolder(View itemView){
            super(itemView);

            editTextTitulo = itemView.findViewById(R.id.editTextTitulo);
            editTextEvento = itemView.findViewById(R.id.editTextEvento);
            editTextEvento = itemView.findViewById(R.id.editTextEvento);
            editTextCidade = itemView.findViewById(R.id.editTextCidade);
            editTextEstado = itemView.findViewById(R.id.editTextEstado);
            linearLayoutVagas = (LinearLayout) itemView.findViewById(R.id.linearLayoutVagas);
        }
    }

}
