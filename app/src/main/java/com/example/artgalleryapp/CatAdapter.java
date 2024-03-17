package com.example.artgalleryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Locale;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.MyviewHolder> implements Filterable {

    Context context;
    ArrayList<Categories> categoriesArrayList;
    ArrayList<Categories> categoriesArrayListFull;

    public CatAdapter (Context context, ArrayList<Categories> categoriesArrayList) {
        this.context = context;
        this.categoriesArrayListFull = categoriesArrayList;
        this.categoriesArrayList = new ArrayList<>(categoriesArrayListFull);
    }


    @NonNull
    @Override
    public CatAdapter.MyviewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder (@NonNull CatAdapter.MyviewHolder holder, int position) {

        Categories categories = categoriesArrayList.get(position);
        holder.tvHeading.setText(categories.heading);
        holder.title_image.setImageResource(categories.titleImage);

    }

    @Override
    public int getItemCount ( ) {
        return categoriesArrayList.size();
    }
    @Override
    public Filter getFilter()
    {
        return categoryFilter;
    }
    private final Filter categoryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering (CharSequence constraint) {

            ArrayList<Categories> filteredcategoryList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0 )
            {
                filteredcategoryList.addAll(categoriesArrayList);
            }
            else
            {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Categories categories : categoriesArrayListFull)
                {
                     if (categories.heading.toLowerCase().contains(filterPattern))
                         filteredcategoryList.add(categories);

                }

            }
            FilterResults results = new FilterResults();
            results.values = filteredcategoryList;
            results.count = filteredcategoryList.size();
            return results;


        }

        @Override
        protected void publishResults (CharSequence constraint, FilterResults results) {

            categoriesArrayList.clear();
            categoriesArrayList.addAll((ArrayList)results.values);
            notifyDataSetChanged();

        }
    };
    public static class MyviewHolder extends RecyclerView.ViewHolder
    {
        TextView tvHeading;
        ShapeableImageView title_image;


        public MyviewHolder (@NonNull View itemView) {
            super(itemView);
            tvHeading = itemView.findViewById(R.id.tvHeading);
            title_image = itemView.findViewById(R.id.title_image);
        }
    }
}
