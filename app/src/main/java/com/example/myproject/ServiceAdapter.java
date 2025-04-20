package com.example.myproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {
    private List<service> serviceList;

    public ServiceAdapter(List<service> serviceList) {
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prof, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        service service = serviceList.get(position);
        holder.text.setText(service.getName());
        holder.txtDes.setText(service.getDescription());
        holder.txtPrice.setText(String.valueOf(service.getPrice()));
        holder.txtTime.setText(service.getTime());
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView text, txtDes, txtPrice, txtTime;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textCar);
            txtDes = itemView.findViewById(R.id.txtDes);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtTime = itemView.findViewById(R.id.txtTime);
        }
    }
}

