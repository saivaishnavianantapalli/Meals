package com.example.vaishu.mealscapstone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vaishu on 12-01-2019.
 */

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealsViewHolder>{
    MealsActivity mealsActivity;
    ArrayList<Pojo> arrayList;
    public MealsAdapter(MealsActivity mealsActivity, ArrayList<Pojo> arrayList) {
        this.arrayList=arrayList;
        this.mealsActivity=mealsActivity;
    }

    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mealsActivity).inflate(R.layout.meals,null);
        return new MealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder holder, final int position) {
        holder.name.setText(arrayList.get(position).getName());
        Picasso.with(mealsActivity).load(arrayList.get(position).getImage()).error(R.drawable.ic_launcher_background).into(holder.image);



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MealsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.mealname)
        TextView name;
        @BindView(R.id.mealimage)
        ImageView image;
        public MealsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Intent intent=new Intent(mealsActivity,DetailsActivity.class);
            intent.putExtra(mealsActivity.getResources().getString(R.string.strMeal),arrayList.get(position).getName());
            mealsActivity.startActivity(intent);

        }
    }
}
