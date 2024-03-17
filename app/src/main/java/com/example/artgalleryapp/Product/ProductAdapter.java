package com.example.artgalleryapp.Product;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artgalleryapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>
{

    ArrayList<ProductModule> list;
    Context context;

    public ProductAdapter (ArrayList<ProductModule> list, Context context)
    {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, int position)
    {
        ProductModule module = list.get(position);
        Picasso.get().load(module.getProductImage()).placeholder(R.drawable.abstract1).into(holder.ItemImage);

        holder.ItemHeadLine.setText(module.getHeadline());
        holder.ItemDiscription.setText(module.getDiscription());
        holder.ItemPrice.setText(module.getPrice());
        holder.ItemBrand.setText(module.getBrand());

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(context, SingleProductActivity.class);
                intent.putExtra("SingleImage", module.getProductImage());
                intent.putExtra("SingleHeadLine", module.getHeadline());
                intent.putExtra("SinglePrice", module.getPrice());
                intent.putExtra("SingleBrand", module.getBrand());
                intent.putExtra("SingleProductType", module.getProductType());
                intent.putExtra("SingleAboutProduct", module.getAboutProduct());
                intent.putExtra("SingleOrigin", module.getOrigin());
                intent.putExtra("pid",module.getPid());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount ( )
    {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView ItemHeadLine, ItemDiscription, ItemPrice, ItemBrand;
        ImageView ItemImage;

        public ViewHolder (@NonNull View itemView)
        {
            super(itemView);

            ItemHeadLine = itemView.findViewById(R.id.ItemHeadLine);
            ItemDiscription = itemView.findViewById(R.id.ItemDescription);
            ItemPrice = itemView.findViewById(R.id.ItemPrice);
            ItemBrand = itemView.findViewById(R.id.ItemBrand);
            ItemImage = itemView.findViewById(R.id.ItemImage);

        }
    }
}
