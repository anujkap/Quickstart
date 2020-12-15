package com.anuj.quickstart.network;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.anuj.quickstart.utils.ErrorUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SampleViewModel extends ViewModel {
    MutableLiveData<ApiResponse<ResponseBody>> dashboardLiveData = new MutableLiveData<>();
    
    public void sendFeaturedRequestList(Context context){
        ApiService apiService = RetrofitInstance.getRetrofitInstance(context).create(ApiService.class);
        Call<ResponseBody> responseCall = apiService.test("reqBody");
        dashboardLiveData.postValue(ApiResponse.getLoadingResponse());
        responseCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("Here", "Response");
                if(response.isSuccessful()) {
                    dashboardLiveData.postValue(ApiResponse.getSuccessResponse(response.body()));
                }
                else{
                    ApiError apiError = ErrorUtils.parseError(context,response);
                    dashboardLiveData.postValue(ApiResponse.getFailureResponse(apiError));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Here", "Failure??");
                dashboardLiveData.postValue(ApiResponse.getFailureResponse(new ApiError("Error sending search trailer request")));
            }
        });

    }


    public LiveData<ApiResponse<ResponseBody>> getSearchTrailerListener(){
        return dashboardLiveData;
    }

}
