package redlocks.app.portalti16.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import redlocks.app.portalti16.R;
import redlocks.app.portalti16.entity.Mahasiswa;
import redlocks.app.portalti16.holder.MahasiswaHolder;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaHolder> {

    private List<Mahasiswa> mahasiswas;

    public MahasiswaAdapter (List<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }


    @NonNull
    @Override
    public MahasiswaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa, parent, false);
        MahasiswaHolder holder = new MahasiswaHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaHolder holder, int position) {
        holder.txtNama.setText(mahasiswas.get(position).getName());
        holder.txtNim.setText(mahasiswas.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mahasiswas.size();
    }
}
