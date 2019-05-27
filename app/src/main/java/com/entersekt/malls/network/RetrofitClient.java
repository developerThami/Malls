package com.entersekt.malls.network;

import com.entersekt.malls.network.model.City;
import com.entersekt.malls.network.api.CityApi;
import com.entersekt.malls.network.model.CityResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private final CityApi cityApi;
    private static final String BASE_URL = "http://www.mocky.io/";
    private static RetrofitClient retrofitClient;

    private RetrofitClient() {

        Gson gson = createGson();
        OkHttpClient okHttpClient = createOkHttpClient();
        Retrofit retrofit = createRetrofit(gson, okHttpClient);

        cityApi = retrofit.create(CityApi.class);
    }

    private Retrofit createRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    private OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    private Gson createGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    public static RetrofitClient start() {
        return retrofitClient != null ? retrofitClient : new RetrofitClient();
    }

    public Single<CityResponse> getCities() {
        return cityApi.getCities();
    }
}
