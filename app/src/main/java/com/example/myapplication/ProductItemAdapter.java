package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class ProductItemAdapter extends RecyclerView.Adapter<ViewHolderProducItem> {

    List<ProductItem> items;
    Context context;
    private ClickListener clickListener;

    public ProductItemAdapter(List<ProductItem> items, Context cotext,ClickListener clickListen) {
        this.context = cotext;
        this.items = items;
        this.clickListener = clickListen;
    }

    @NonNull
    @Override
    public ViewHolderProducItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newproduct_items, parent, false);
        return new ViewHolderProducItem(view,clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducItem holder, int position) {
        ProductItem productItems = items.get(position);
        final ViewHolderProducItem viewHolder = (ViewHolderProducItem) holder;
        viewHolder.textViewName.setText(productItems.getName());
        viewHolder.textViewPrice.setText(productItems.getPrice() + " تومان");
        // viewHolder.newProductImgView.setImageResource(productItems.getImgname());
        Picasso.with(context).load("http://" + productItems.getImgname()).into(viewHolder.newProductImgView);

    }

    @Override
    public int getItemCount() {
//
        return items.size();
    }
}

class ViewHolderProducItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView textViewName;
    TextView textViewPrice;
    ImageView newProductImgView;
    ClickListener onClickLis;
    //CardView crdViewNewProduct;


    public ViewHolderProducItem(@NonNull View itemView, ClickListener onClickListener) {
        super(itemView);
        textViewName = (TextView) itemView.findViewById(R.id.txttitlenewProduct);
        textViewPrice = (TextView) itemView.findViewById(R.id.txtnewproductprice);
        newProductImgView = (ImageView) itemView.findViewById(R.id.imgnewproduct);
        this.onClickLis = onClickListener;
        itemView.setOnClickListener(this);
        //crdViewNewProduct = (CardView)itemView.findViewById(R.id.newProductcard);
    }




    @Override
    public void onClick(View v) {
    onClickLis.onclick(getAdapterPosition());
    }
}

interface ClickListener {
    void onclick(int position);
}