package redlocks.app.portalti16.network;

import redlocks.app.portalti16.entity.DaftarMahasiswa;
import redlocks.app.portalti16.entity.Mahasiswa;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Routes {

    //mendefinisikan route dari web services yang disediakan
    @GET("dev/list_mahasiswa")
    Call<DaftarMahasiswa> getMahasiswa();

    //untuk melakukan post data mahasiswa baru
    @POST("dev/insert_mahasiswa")
    @FormUrlEncoded
    Call<Mahasiswa> postMahasiswa(
            @Field("name") String name,
            @Field("nim") String nim
    );

    //untuk menghapus mahasiswa berdasarkan id
    @DELETE("mahasiswatest/{mhsId}")
    Call<Mahasiswa> deleteMahasiswa(
            @Path("mhsId") String mhsId
    );

    //untuk memperbaharui data mahasiswa
    @PUT("mahasiswatest/{mhsId}")
    @FormUrlEncoded
    Call<Mahasiswa> updateMahasiswa(
            @Path("mhsId") String mhsId,
            @Field("name") String name,
            @Field("nim") String nim
    );

}
