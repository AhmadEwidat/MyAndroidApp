package com.example.myproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import Mokup.cars;

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>{
    private Context context;
    private List<cars> items;


    public CaptionedImagesAdapter(Context context, List<cars> items){
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
        final cars car = items.get(position);
        LinearLayout linearLayout = holder.linearLayout;
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.picture);
        Glide.with(context).load(car.getImage()).into(imageView);
        TextView txt = (TextView)linearLayout.findViewById(R.id.textCar);
        txt.setText(car.getModel());
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