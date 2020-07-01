package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,ClickListener {


    DrawerLayout drawer;
    ImageView imageViewDrawer;
    BannerSlider bannerSlider;


    //برای اون لیست بیضی سبزه
    ProductGroupAdapter groupAdapter;
    RecyclerView groupRecycler;
    ArrayList<ProductGroupe> productGroupItems = new ArrayList<>();


    //برای اون لیست زیر ساعت با عکس موبایل
    ProductItemAdapter productItemAdapter;
    ArrayList<ProductItem> productNewItems = new ArrayList<>();
    RecyclerView productItemRecycler;


    //برای پرفروش ترین
    ProductItemAdapter PorforoshProductItemAdapter;
    ArrayList<ProductItem> PorforoshProductItemsList = new ArrayList<>();
    RecyclerView PorForoushproductitemRecycler;


    //برای جدیدترین محصولات
    ProductItemAdapter jadidProductItemAdapter;
    ArrayList<ProductItem> jadidProductItemsList = new ArrayList<>();
    RecyclerView jadidProductItemRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

////////////////////////////////Drawer/////////////////////////////////////////////////
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        imageViewDrawer = (ImageView) findViewById(R.id.btnmenudrawer);
        imageViewDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);
            }
        });


        //////////////////////banner Slider////////////////////////////////////////
        bannerSlider = (BannerSlider) findViewById(R.id.bannerSlider);
        bannersliderImage();


        //////////////////////////////fill product groupe////////////////////لیست سبزه
        productGroupData();


        //////////////////fill product Items///////////////
        ProductItemData();

        ///////////////////////////////////////////////////////////////////////////
        //پرفروش ترین ها
        PORForoushProductData();

//////////////////////////////////////////////////////////////////////////////
        //جدیدترین محصولات
        jadidProductData();


    }



@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void productGroupData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dotnettest.ir/Api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductGroupService GroupService = retrofit.create(ProductGroupService.class);
        Call<List<ProductGroupe>> call = GroupService.getGroupList();


        call.enqueue(new Callback<List<ProductGroupe>>() {
            @Override
            public void onResponse(Call<List<ProductGroupe>> call, Response<List<ProductGroupe>> response) {
                if (response.isSuccessful()) {

                    List<ProductGroupe> group = response.body();

                    groupRecycler = findViewById(R.id.prdgrpuoe);
                    groupRecycler.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                    productGroupItems = (ArrayList<ProductGroupe>) group;
                    groupAdapter = new ProductGroupAdapter(productGroupItems, MainActivity.this);
                    LinearLayoutManager linearLayoutManagerGroupe = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    groupRecycler.setLayoutManager(linearLayoutManagerGroupe);
                    groupRecycler.setAdapter(groupAdapter);
                } else {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductGroupe>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


//        productGroupeItems.add(new ProductGroupe("کالای دیجیتال"));
//        productGroupeItems.add(new ProductGroupe("آرایشی و بهداشتی"));
//        productGroupeItems.add(new ProductGroupe("خودرو ابزار و اداری"));
//        productGroupeItems.add(new ProductGroupe("مد و پوشاک"));
//        productGroupeItems.add(new ProductGroupe("خانه و آشپزخانه"));
//        productGroupeItems.add(new ProductGroupe("کتاب لوازم تحریر و هنر"));
//        productGroupeItems.add(new ProductGroupe("اسباب بازی کودک و نوزاد"));
//        productGroupeItems.add(new ProductGroupe("ورزش و سفر"));
//        productGroupeItems.add(new ProductGroupe("خوردنی و آشامیدنی"));
//        productGroupeItems.add(new ProductGroupe("کارت هدیه"));
    }




///////////***************************************//////////////////////////
    public void ProductItemData() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dotnettest.ir/Api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductItemService productItemService = retrofit.create(ProductItemService.class);

        Call<List<ProductItem>> call = productItemService.getProducts();

        call.enqueue(new Callback<List<ProductItem>>() {

            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if (response.isSuccessful()) {

                    List<ProductItem> PItems = response.body();

                    productItemRecycler = findViewById(R.id.productItemrecycler);

                    productNewItems = (ArrayList<ProductItem>) PItems;
                    productItemAdapter = new ProductItemAdapter(productNewItems, MainActivity.this,MainActivity.this);
                    LinearLayoutManager linearLayoutProductItem = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    productItemRecycler.setLayoutManager(linearLayoutProductItem);
                    productItemRecycler.setAdapter(productItemAdapter);
                } else {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
//
//
////        productNewItems.add(new ProductItem(R.drawable.mobil, "گوشی موبایل سامسونگ مدل A5", "2.818.000"));
////        productNewItems.add(new ProductItem(R.drawable.mobil, "گوشی موبایل سامسونگ مدل A6", "2.900.000"));
////        productNewItems.add(new ProductItem(R.drawable.mobil, "گوشی موبایل سامسونگ مدل A7", "2.500.000"));
////        productNewItems.add(new ProductItem(R.drawable.mobil, "گوشی موبایل سامسونگ مدل A8", "2.200.000"));
//    }



    /////////***************************************************/////////////////////////////////
    //برای پرفروش
    public void PORForoushProductData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dotnettest.ir/Api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductItemService itemService = retrofit.create(ProductItemService.class);

        Call<List<ProductItem>> call = itemService.getProducts();

        call.enqueue(new Callback<List<ProductItem>>() {

            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if (response.isSuccessful()) {
                    List<ProductItem> ForoushItems = response.body();

                    PorForoushproductitemRecycler = (RecyclerView) findViewById(R.id.recPorFroush);
                    PorForoushproductitemRecycler.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                    PorforoshProductItemsList = (ArrayList<ProductItem>) ForoushItems;
                    PorforoshProductItemAdapter = new ProductItemAdapter(PorforoshProductItemsList, MainActivity.this, MainActivity.this);
                    LinearLayoutManager linearLayoutProductPOrForoush = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    PorForoushproductitemRecycler.setLayoutManager(linearLayoutProductPOrForoush);
                    PorForoushproductitemRecycler.setAdapter(PorforoshProductItemAdapter);
                } else {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
////        PorforoshProductItemsList.add(new ProductItem(R.drawable.mobil, "گوشی موبایل سامسونگ مدل A5", "3.818.000"));
////        PorforoshProductItemsList.add(new ProductItem(R.drawable.mobil, "گوشی موبایل سامسونگ مدل A6", "3.900.000"));
////        PorforoshProductItemsList.add(new ProductItem(R.drawable.mobil, "گوشی موبایل سامسونگ مدل A7", "3.500.000"));
////        PorforoshProductItemsList.add(new ProductItem(R.drawable.mobil, "گوشی موبایل سامسونگ مدل A8", "3.200.000"));
//    }




//////////*******************************************************************//////////////////////////
    //برای جدیترین ها
    public void jadidProductData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dotnettest.ir/Api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductItemService itemService = retrofit.create(ProductItemService.class);

        Call<List<ProductItem>> call = itemService.getProducts();

        call.enqueue(new Callback<List<ProductItem>>() {

            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if (response.isSuccessful()) {
                    List<ProductItem> jadidItems = response.body();

                    jadidProductItemRecycler = (RecyclerView) findViewById(R.id.recjadidTarin);
                    jadidProductItemRecycler.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                    jadidProductItemsList = (ArrayList<ProductItem>) jadidItems;
                    jadidProductItemAdapter = new ProductItemAdapter(jadidProductItemsList, MainActivity.this, MainActivity.this);
                    LinearLayoutManager linearLayoutProductJADID = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    jadidProductItemRecycler.setLayoutManager(linearLayoutProductJADID);
                    jadidProductItemRecycler.setAdapter(jadidProductItemAdapter);
                } else {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

//
////        jadidProductItemsList.add(new ProductItem(R.drawable.mobil, "گوشی موبایل سامسونگ مدل A5", "4.818.000"));
////        jadidProductItemsList.add(new ProductItem(R.drawable.mobil, "گوشی موبایل سامسونگ مدل A6", "4.900.000"));
////        jadidProductItemsList.add(new ProductItem(R.drawable.mobil, "گوشی موبایل سامسونگ مدل A7", "4.500.000"));
////        jadidProductItemsList.add(new ProductItem(R.drawable.mobil, "گوشی موبایل سامسونگ مدل A8", "4.200.000"));
//    }


    }



//////////******************************************************//////////////////////////
    public void bannersliderImage() {

        List<Banner> banners = new ArrayList<Banner>();
        banners.add(new DrawableBanner(R.drawable.s1));
        banners.add(new DrawableBanner(R.drawable.s2));
        banners.add(new DrawableBanner(R.drawable.s3));
        banners.add(new DrawableBanner(R.drawable.s4));
        banners.add(new DrawableBanner(R.drawable.s5));
        bannerSlider.setBanners(banners);
    }

//////////***********************************************************//////////////////
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(Gravity.RIGHT)) {
            drawer.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();
        }
    }
////****************************************************************************///////
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.btnhome) {

        } else if (id == R.id.btnlist) {

        } else if (id == R.id.bestSeller) {

        } else if (id == R.id.bestView) {

        } else if (id == R.id.newProduct) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//////////********************************************************************************//////////////
    @Override
    public void onclick(int position) {
        ProductItem item = productNewItems.get(position);

        Intent detail  = new Intent(MainActivity.this,ProductDetailActivity.class);
        detail.putExtra("ProductId",item.getId());
        startActivity(detail);

       //Toast.makeText(this, item.getName(), Toast.LENGTH_SHORT).show();
    }
}