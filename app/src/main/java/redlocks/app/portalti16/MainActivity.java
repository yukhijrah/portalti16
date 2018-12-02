package redlocks.app.portalti16;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import redlocks.app.portalti16.adapter.MahasiswaAdapter;
import redlocks.app.portalti16.entity.DaftarMahasiswa;
import redlocks.app.portalti16.network.Network;
import redlocks.app.portalti16.network.Routes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void requestDaftarMahasiswa() {
        //pertama , memanggil request() dari retrofit yang sudah dibuat
        Routes services = Network.request().create(Routes.class);

        //kita melakukan request terhadap getMahasiswa()
        services.getMahasiswa().enqueue(new Callback<DaftarMahasiswa>() {
            @Override
            public void onResponse(Call<DaftarMahasiswa> call, Response<DaftarMahasiswa> response) {
                //mengecek request yang dilakukan, berhasil/tidak
                if (response.isSuccessful()) {
                    //casting data yang didapatkan, menjadi DaftarMahasiswa
                    DaftarMahasiswa mahasiswas = response.body();

                    //get title
                    Log.d("REDLOCKS", mahasiswas.getTitle());

                    //tampilkan daftar mahasiswa di recyclerview
                    MahasiswaAdapter adapter = new MahasiswaAdapter(mahasiswas.getData());

                }
            }

            @Override
            public void onFailure(Call<DaftarMahasiswa> call, Throwable t) {

            }
        });
    }
}
