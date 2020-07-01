package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface productDetailService {
    @GET("Product/GetProductsByTags")
    Call<ProductItem> getProductDetail(@Query("productId") int productid);
}
