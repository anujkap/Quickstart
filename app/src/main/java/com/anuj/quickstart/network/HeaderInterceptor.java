package com.anuj.quickstart.network;

import android.content.Context;

import com.anuj.quickstart.utils.SharedPrefs;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    private final Context context;
    public HeaderInterceptor(Context context) {
        this.context = context;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        String authToken = SharedPrefs.getInstance(this.context).getAuthToken();
        Request request = chain.request()
                .newBuilder()
                .addHeader("authorization", authToken)
                .addHeader("Cookie", "User-Access-Token="+authToken)
                .build();
        return chain.proceed(request);
    }
}