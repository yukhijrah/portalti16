package redlocks.app.portalti16.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    public static Retrofit request(){
        return new Retrofit.Builder()
                //ini main url dari web services yg tersedia
                .baseUrl("http://35.186.145.167:1337/")
                //ini melakukan konversi dari json ke java object
                .addConverterFactory(GsonConverterFactory.create())
                //build it!
                .build();

    }
}
