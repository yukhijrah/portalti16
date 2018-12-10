package redlocks.app.portalti16.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    public static Retrofit request(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        return new Retrofit.Builder()
                //ini main url dari web services yg tersedia
                .baseUrl("http://35.186.145.167:1337/")
                //tambahkan client okhttp
                .client(client)
                //ini melakukan konversi dari json ke java object
                .addConverterFactory(GsonConverterFactory.create())
                //build it!
                .build();

    }
}
