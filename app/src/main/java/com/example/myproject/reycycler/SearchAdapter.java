package com.example.myproject.reycycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
//import com.example.myproject.Cars;
import com.example.myproject.R;

import java.util.List;

import Mokup.garagee;
import Mokup.cars;

import Mokup.services;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    private Context context;
    private List<garagee> items;


    public SearchAdapter(Context context, List<garagee> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout l = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prof,
                parent,
                false);

        return new ViewHolder(l);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final garagee  gar = items.get(position);
        LinearLayout linearLayout = holder.linearLayout;
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.picture);
        Glide.with(context).load(gar.getImage()).into(imageView);
        TextView txt = (TextView)linearLayout.findViewById(R.id.textCar);



        txt.setText(gar.getNameOfGarage());

        linearLayout.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;
        public ViewHolder(LinearLayout linearLayout ){
            super(linearLayout);
            this.linearLayout = linearLayout;
        }

    }
}