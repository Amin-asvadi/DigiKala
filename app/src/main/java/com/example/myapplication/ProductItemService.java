package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductItemService {
    @GET("Product/GetProducts")
    Call<List<ProductItem>> getProducts();
}
