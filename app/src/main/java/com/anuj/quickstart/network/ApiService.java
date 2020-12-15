package com.anuj.quickstart.network;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("")
    Call<ResponseBody> test(
            @Body String reqBody
    );

}
