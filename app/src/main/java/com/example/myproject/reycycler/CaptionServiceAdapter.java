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
import com.example.myproject.R;

import java.util.List;

import Mokup.services;

public class CaptionServiceAdapter extends RecyclerView.Adapter<CaptionServiceAdapter.ViewHolder>{
    private Context context;
    private List<services> items;


    public CaptionServiceAdapter(Context context, List<services> items){
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
        final services service = items.get(position);
        LinearLayout linearLayout = holder.linearLayout;
        TextView txtCar = (TextView)linearLayout.findViewById(R.id.textCar);
        TextView txtDes=(TextView)linearLayout.findViewById(R.id.txtDes);
        TextView txtPrice=(TextView)linearLayout.findViewById(R.id.txtPrice);
        TextView txtTime=(TextView)linearLayout.findViewById(R.id.txtTime);
        txtCar.setText(service.getUserName());
        txtDes.setText(service.getDescription());
        txtPrice.setText(String.valueOf(service.getPrice()));
        txtTime.setText(service.getTime());
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