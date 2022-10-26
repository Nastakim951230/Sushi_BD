package com.example.sushi_bd;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RetrofitAPI {
    @POST("users")
    Call<DataModal> createPost(@Body DataModal dataModal);


}
