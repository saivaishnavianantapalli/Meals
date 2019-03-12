package com.example.vaishu.mealscapstone;

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

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {
    DetailsActivity detailsActivity;
    ArrayList<Pojo> arrayList;
    public DetailsAdapter(DetailsActivity detailsActivity, ArrayList<Pojo> arrayList) {
        this.detailsActivity=detailsActivity;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(detailsActivity).inflate(R.layout.details,null);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        Picasso.with(detailsActivity).load(arrayList.get(position).getMealimage()).error(R.drawable.ic_launcher_background).into(holder.mealimage);
        holder.mname.setText(arrayList.get(position).getMealname());
        holder.mealcategory.setText(arrayList.get(position).getCategory());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mealname)
        TextView mname;
        @BindView(R.id.image)
        ImageView mealimage;
        @BindView(R.id.category)
        TextView mealcategory;
        public DetailsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
