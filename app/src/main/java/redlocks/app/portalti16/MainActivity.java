package redlocks.app.portalti16;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import redlocks.app.portalti16.adapter.MahasiswaAdapter;
import redlocks.app.portalti16.entity.DaftarMahasiswa;
import redlocks.app.portalti16.entity.Mahasiswa;
import redlocks.app.portalti16.network.Network;
import redlocks.app.portalti16.network.Routes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView lstMahasiswa;
    Button btnMahasiswa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstMahasiswa = (RecyclerView) findViewById(R.id.lst_mahasiswa);
        lstMahasiswa.setLayoutManager(new LinearLayoutManager(this));

        btnMahasiswa = (Button) findViewById(R.id.btn_to_add);

        requestDaftarMahasiswa();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menampilkan menu di activity
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                //Ketika icon refresh di klik, maka panggil ...
                requestDaftarMahasiswa();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestDaftarMahasiswa();
        onButtonMahasiswa();
    }

    private void requestDaftarMahasiswa() {
        //pertama, memanggil request() dari retrofit yang sudah dibuat
        final Routes services = Network.request().create(Routes.class);

        services.getMahasiswa().enqueue(new Callback<DaftarMahasiswa>() {
            @Override
            public void onResponse(Call<DaftarMahasiswa> call, Response<DaftarMahasiswa> response) {
                if (response.isSuccessful()) {
                    DaftarMahasiswa mahasiswas = response.body();

                    Log.d("TI16", mahasiswas.getTitle());

                    //tampilkan daftar mahasiswa di recyclerview
                    MahasiswaAdapter adapter = new MahasiswaAdapter(mahasiswas.getData());

                    //untuk handle button delete di item mahasiswa
                    //fungsinya untuk menghapus data yang ada di API
                    adapter.setListener(new MahasiswaAdapter.MahasiswaListener() {
                        @Override
                        public void onDelete(int mhsId) {
                            String id = String.valueOf(mhsId); //konversi int to string
                            deleteMahasiswa(services, id);
                        }
                    });


                    lstMahasiswa.setAdapter(adapter);

                } else {
                    onMahasiswaError();
                }
            }

            @Override
            public void onFailure(Call<DaftarMahasiswa> call, Throwable t) {
                onMahasiswaError();
            }
        });
    }

    private void onMahasiswaError() {
        Toast.makeText(
                MainActivity.this,
                "Gagal. Silahkan periksa koneksi internet anda",
                Toast.LENGTH_LONG).show();
    }

    private void onButtonMahasiswa() {
        btnMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MainActivity.this, AddMahasiswaActivity.class);
                startActivity(pindah);
            }
        });
    }


    private void deleteMahasiswa(final Routes services, final String mhsId) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.app_name);
        alert.setMessage("are you sure?");
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                services.deleteMahasiswa(mhsId).enqueue(new Callback<Mahasiswa>() {
                    @Override
                    public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                        if (response.isSuccessful()) {
                            requestDaftarMahasiswa();
                        } else {
                            onMahasiswaError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Mahasiswa> call, Throwable t) {
                        onMahasiswaError();
                    }
                });
            }
        });
        alert.show();
    }
}