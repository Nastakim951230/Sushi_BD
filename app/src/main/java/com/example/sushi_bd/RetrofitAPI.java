package com.example.sushi_bd;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("Sushis")
    Call<DataModal> createPost(@Body DataModal dataModal);


}
