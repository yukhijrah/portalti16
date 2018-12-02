package redlocks.app.portalti16.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import redlocks.app.portalti16.entity.Mahasiswa;
import redlocks.app.portalti16.holder.MahasiswaHolder;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaHolder> {

    private List<Mahasiswa> mahasiswas;

    public MahasiswaAdapter (List<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }


    @NonNull
    @Override
    public MahasiswaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaHolder mahasiswaHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
