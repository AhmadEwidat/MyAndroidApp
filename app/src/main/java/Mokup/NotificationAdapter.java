package Mokup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myproject.R;

import org.json.JSONObject;

import java.util.List;

public class NotificationAdapter extends ArrayAdapter<JSONObject> {
    private Context context;
    private List<JSONObject> notifications;

    public NotificationAdapter(Context context, List<JSONObject> notifications) {
        super(context, 0, notifications);
        this.context = context;
        this.notifications = notifications;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // إعادة استخدام العرض القديم إن وجد
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_notification, parent, false);
        }

        // جلب العنصر الحالي
        JSONObject notification = notifications.get(position);

        // ربط العناصر في التخطيط
        ImageView icon = convertView.findViewById(R.id.notification_icon);
        TextView title = convertView.findViewById(R.id.notification_title);
        TextView description = convertView.findViewById(R.id.notification_description);

        // تعيين البيانات
        try {
            title.setText(notification.getString("NameOfGarage"));
            description.setText(notification.getString("description"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }
}