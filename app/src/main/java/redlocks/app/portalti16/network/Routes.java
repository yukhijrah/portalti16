package redlocks.app.portalti16.network;

import redlocks.app.portalti16.entity.DaftarMahasiswa;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Routes {

    /* Mendifinisikan route dari web services yg disediakan
    jika di deskripsikan, berarti :
    https://ti16.herokuapp.com/list.php
     */
    @GET("list.php")
    Call<DaftarMahasiswa> getMahasiswa();

}
