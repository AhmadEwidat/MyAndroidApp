package com.example.myproject.reycycler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;

import java.util.ArrayList;

import Mokup.services_req;

public class HistoryPaidRecyclerAdapter extends RecyclerView.Adapter<HistoryPaidRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<services_req> list;

    // Constructor
    public HistoryPaidRecyclerAdapter(Context context, ArrayList<services_req> list) {
        this.context = context;
        this.list = list;
    }

    // ViewHolder Class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView, statusTextView, modelTextView, phoneTextView, priceTextView;
        ImageView carImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views from car_recycler_layout.xml
            userNameTextView = itemView.findViewById(R.id.inServiceUserTV);
            statusTextView = itemView.findViewById(R.id.INServeiceStatusTV);
            modelTextView = itemView.findViewById(R.id.InServiceModelTV);
            phoneTextView = itemView.findViewById(R.id.InServiceNumberTV);
            priceTextView = itemView.findViewById(R.id.InServicePriceTV);
            carImageView = itemView.findViewById(R.id.INServiceIMageView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate car_recycler_layout.xml
        View view = LayoutInflater.from(context).inflate(R.layout.car_recycler_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Get the current item
        services_req currentCar = list.get(position);
        // Bind data to views
        holder.userNameTextView.setText(currentCar.getAccountt_UserName().getUserName());
        holder.statusTextView.setText(currentCar.getStatus());
        holder.modelTextView.setText(currentCar.getCar().getModel());
        holder.phoneTextView.setText(String.valueOf(currentCar.getAccountt_UserName().getPhone()));
        Log.d("eeeee", holder.userNameTextView.getText().toString());

        // Calculate the total price from services
        double totalPrice = 0;
        for (int i = 0; i < currentCar.getArrayList().size(); i++) {
            totalPrice += currentCar.getArrayList().get(i).getPrice();
        }
        holder.priceTextView.setText(String.valueOf(totalPrice));

        // Load the car image (ensure the image URL is valid or replace with a placeholder)
        Glide.with(context)
                .load(currentCar.getCar().getImage()) // URL or resource ID
                .placeholder(R.drawable.ic_launcher_background) // Fallback image
                .into(holder.carImageView);
    }

    @Override
    public int getItemCount() {
        Log.d("item count", "getItemCount: "+list.size());
        return list.size();
    }

    // Method to update the dataset
    public void updateData(ArrayList<services_req> newCarList) {
        this.list.clear();
        this.list.addAll(newCarList);
        notifyDataSetChanged(); // Refresh the RecyclerView
    }
}
