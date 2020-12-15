package com.anuj.quickstart.network;

import android.content.Context;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.anuj.quickstart.data.Constants.BASE_URL;

public class RetrofitInstance {
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(Context context) {
        OkHttpClient client = getOkHttpClient(context);
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getOkHttpClient(Context context) {
        return new OkHttpClient.Builder()
                .readTimeout(60,TimeUnit.SECONDS)
                .callTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor(context))
                .build();
    }

}
