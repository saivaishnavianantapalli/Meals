package com.example.vaishu.mealscapstone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaishu.mealscapstone.Roomdatabase.Favmeal;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vaishu on 12-01-2019.
 */

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder> {
    FavouritesActivity favouritesActivity;
    List<Favmeal> list;
    public FavouritesAdapter(FavouritesActivity areaActivity, List<Favmeal> list1) {
        this.favouritesActivity=areaActivity;
        list=list1;
    }

    @NonNull
    @Override
    public FavouritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(favouritesActivity).inflate(R.layout.favorite,null);
        return new FavouritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        Picasso.with(favouritesActivity).load(list.get(position).getImage()).error(R.drawable.ic_launcher_background).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FavouritesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.mealname)
        TextView name;
        @BindView(R.id.mealimage)
        ImageView imageView;
        public FavouritesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                int position=getAdapterPosition();
            Intent intent=new Intent(favouritesActivity,DetailsActivity.class);
            intent.putExtra(favouritesActivity.getResources().getString(R.string.strMeal),list.get(position).getName());
            favouritesActivity.startActivity(intent);
        }
    }
}
