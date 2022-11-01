package com.example.sushi_bd;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitAPIUpdate {
    @PUT("Sushis/{id}")
    Call<DataModal> updateData(@Path("id")int id, @Body DataModal dataModal);
    @DELETE("Sushis/{id}")
    Call<DataModal> deleteData(@Path("id")int id);
}
