package redlocks.app.portalti16.network;

import redlocks.app.portalti16.entity.DaftarMahasiswa;
import redlocks.app.portalti16.entity.Mahasiswa;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Routes {

    /* Mendefinisikan route dari web services yg disediakan
    jika di deskripsikan, berarti :
    http://ti16-api.herokuapp.com/
     */
    @GET("list_mahasiswa")
    Call<DaftarMahasiswa> getMahasiswa();

    /*
    untuk melakukan post data mahasiswa baru
    jika di deskripsikan, berarti :
    http://ti16-api.herokuapp.com/
     */

    @POST("post_mahasiswa")
    @FormUrlEncoded
    Call<Mahasiswa> postMahasiswa(
            @Field("name") String name,
            @Field("nim") String nim
    );


}
