package com.example.sushi_bd;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RetrofitAPIUpdate {
    @PUT("Sushis/")
    Call<DataModal> updateData(@Query("Id")int id, @Body DataModal dataModal);

}
