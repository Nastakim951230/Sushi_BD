package com.example.sushi_bd;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface RetrofitAPIUpdate {
    @PUT("api/users/2")
    Call<DataModal> updateData(@Body DataModal dataModal);
}
