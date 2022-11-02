package com.example.sushi_bd;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Query;

public interface RetrofitAPIDelete {
    @DELETE("Sushis/")
    Call<DataModal> deleteData(@Query("Id")int id);
}
