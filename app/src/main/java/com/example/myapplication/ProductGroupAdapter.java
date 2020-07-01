package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductGroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ProductGroupe> items;
    Context context;

    public ProductGroupAdapter(List<ProductGroupe> items, Context cotext) {
        this.context = cotext;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productgroupe_items, parent, false);
        return new ViewHolderProductGroup(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ProductGroupe productGroupeItems = items.get(position);
    final ViewHolderProductGroup vh =(ViewHolderProductGroup)holder;
    vh.textViewName.setText(productGroupeItems.getName());
    }

    @Override
    public int getItemCount() {
//
        return items.size();
    }
}

class ViewHolderProductGroup extends RecyclerView.ViewHolder {

    TextView textViewName;

    public ViewHolderProductGroup(@NonNull View itemView) {
        super(itemView);
        textViewName = (TextView) itemView.findViewById(R.id.txtproductgroupe);
    }
}