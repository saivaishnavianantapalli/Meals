package com.example.vaishu.mealscapstone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vaishu on 12-01-2019.
 */

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.AreaViewHolder> {
    AreaActivity areaActivity;
    ArrayList<Pojo> arrayList;
    public AreaAdapter(AreaActivity areaActivity, ArrayList<Pojo> arrayList) {
        this.areaActivity=areaActivity;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(areaActivity).inflate(R.layout.area,null);
        return new AreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, final int position) {
        holder.areas.setText(arrayList.get(position).getArea());
        holder.areas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(areaActivity,MealsActivity.class);
                intent.putExtra(areaActivity.getResources().getString(R.string.strArea),arrayList.get(position).getArea());
                areaActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class AreaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.area)
            TextView areas;
        public AreaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
