package com.entersekt.malls.network;

import android.content.Context;

import com.entersekt.malls.network.api.CityApi;
import com.entersekt.malls.network.model.CityResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCityClient {

    private CityApi cityApi;
    private static final String BASE_URL = "http://www.mocky.io/";
    private static RetrofitCityClient INSTANCE;

    public static RetrofitCityClient getInstance(Context context) {
        INSTANCE = INSTANCE != null ? INSTANCE : new RetrofitCityClient(context);
        return INSTANCE;
    }

    private RetrofitCityClient() {
    }

    private RetrofitCityClient(Context context) {

        Interceptor interceptor = createInterceptor(context);
        Cache cache = createCache(context.getCacheDir());
        OkHttpClient okHttpClient = createOkHttpClient(cache, interceptor);
        Gson gson = createGson();

        Retrofit retrofit = createRetrofit(gson, okHttpClient);

        cityApi = retrofit.create(CityApi.class);
    }

    private Cache createCache(File file) {
        //5MB cache size for offline response storing
        final long cacheSize = (long) (5 * 1024 * 1024);
        return new Cache(file, cacheSize);
    }

    private Retrofit createRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    private OkHttpClient createOkHttpClient(Cache cache, Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    private Interceptor createInterceptor(Context context) {
        return chain -> {
            Request request = chain.request();
            if (NetworkCheck.hasNetwork(context)) {
                int maxAge = 60; // read from cache for 1 minute
                request = request.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 7; // tolerate 7 days stale
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return chain.proceed(request);
        };
    }

    private Gson createGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    public Single<CityResponse> fetchRemoteCityData() {
        return cityApi.getCities();
    }
}
