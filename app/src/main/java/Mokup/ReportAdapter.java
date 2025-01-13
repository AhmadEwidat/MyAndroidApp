package Mokup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder>{
    private Context context;
    private List<Report> items;


    public ReportAdapter(Context context, List<Report> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Report report = items.get(position);
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.ivGarageImage);
        Glide.with(context).load(report.getImage()).into(imageView);
        TextView tvGarageName = (TextView)cardView.findViewById(R.id.tvGarageName);
        TextView tvCarId = (TextView)cardView.findViewById(R.id.tvCarId);
        TextView tvCarModel = (TextView)cardView.findViewById(R.id.tvCarModel);
        TextView tvServicePrice = (TextView)cardView.findViewById(R.id.tvServicePrice);
        TextView tvStartTime = (TextView)cardView.findViewById(R.id.tvStartTime);
        TextView tvEndTime = (TextView)cardView.findViewById(R.id.tvEndTime);
        TextView tvDescription = (TextView)cardView.findViewById(R.id.tvDescription);
        TextView tvStatus = (TextView)cardView.findViewById(R.id.tvStatus);
        TextView tvServiceName = (TextView)cardView.findViewById(R.id.tvServiceName);
        tvGarageName.setText(report.getNameOfGarage());
        tvCarId.setText(report.getId());
        tvCarModel.setText(report.getModel());
        tvServicePrice.setText(report.getPrice());
        tvStartTime.setText(report.getStartTime());
        tvEndTime.setText(report.getEndTime());
        tvDescription.setText(report.getDescription());
        tvStatus.setText(report.getStatus());
        tvServiceName.setText(report.getUserName());

        cardView.setOnClickListener( new View.OnClickListener(){
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
        private CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }

    }

    public static class AdapterOfGarageNotf extends AppCompatActivity {

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_client_report);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }
}