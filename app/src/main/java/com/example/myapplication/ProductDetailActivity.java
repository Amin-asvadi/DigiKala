package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //-----------------------get PRODUCTid from mainActivity-------------------//
        Bundle productData = getIntent().getExtras();
        int productId= productData.getInt("ProductId");
        getProductDetailData(productId);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void getProductDetailData(final int productId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dotnettest.ir/Api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        productDetailService detailService = retrofit.create(productDetailService.class);
        Call<ProductItem> call = detailService.getProductDetail(productId);

        call.enqueue(new Callback<ProductItem>() {
            @Override
            public void onResponse(Call<ProductItem> call, Response<ProductItem> response) {
                ProductItem product = response.body();
                if(response.isSuccessful()){


                    //------------------------------Slider---------------------------//
                    BannerSlider detailSlider = (BannerSlider)findViewById(R.id.imgSliderDETAIL);
                    List<Banner> banners = new ArrayList<>();
                    String imgUrl = "http:/"+product.getImgname();
                    banners.add(new RemoteBanner(imgUrl));

                    banners.add(new DrawableBanner(R.drawable.sl1));
                    banners.add(new DrawableBanner(R.drawable.sl2));
                    banners.add(new DrawableBanner(R.drawable.sl3));
                    banners.add(new DrawableBanner(R.drawable.sl4));
                    detailSlider.setBanners(banners);


                }else {
                    Toast.makeText(ProductDetailActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductItem> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}