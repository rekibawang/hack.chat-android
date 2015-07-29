package chat.hack.hackchat;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;


/**
 * Created by Rudra on 12-07-2015.
 */
public class ListViewAdapter extends BaseAdapter {
    private ArrayList<MessageItem> messageList;
    Context context;

    ListViewAdapter(ArrayList<MessageItem> messageList, Context context) {
        this.messageList = messageList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row, null);
        }

        final TextView message = (TextView) v.findViewById(R.id.tvMessage);
        final TextView sender = (TextView) v.findViewById(R.id.tvSender);
        TextView time = (TextView) v.findViewById(R.id.tvTime);
        TextView trip = (TextView) v.findViewById(R.id.tvTrip);

        final MessageItem item;
        item = messageList.get(position);

        time.setTextAppearance(context, R.style.TextAppearance_AppCompat_Small);
        time.setTextColor(Color.parseColor("#424242"));

        trip.setTextColor(Color.parseColor("#424242"));

        if (item.getCls() == "info") {
            message.setTextAppearance(context, R.style.TextAppearance_AppCompat_Small);
            message.setTextColor(Color.parseColor("#99C21D")); // NOT from website - 60ac39
            sender.setTextColor(Color.parseColor("#99C21D"));
        } else if (item.getCls() == "warn") {
            message.setTextAppearance(context, R.style.TextAppearance_AppCompat_Small);
            message.setTextColor(Color.parseColor("#fbba37"));
            sender.setTextColor(Color.parseColor("#fbba37"));
        } else if (item.getCls() == "me") {
            message.setTextAppearance(context, R.style.TextAppearance_AppCompat_Medium);
            message.setTextColor(Color.parseColor("#a6a28c"));
            sender.setTextColor(Color.parseColor("#b854d4"));
        } else if (item.getCls() == "admin") {
            message.setTextAppearance(context, R.style.TextAppearance_AppCompat_Medium);
            message.setTextColor(Color.parseColor("#e94749"));
            sender.setTextColor(Color.parseColor("#e94749"));
        } else if (item.getCls() == "") {
            message.setTextAppearance(context, R.style.TextAppearance_AppCompat_Medium);
            message.setTextColor(Color.parseColor("#a6a28c")); // from website
            sender.setTextColor(Color.parseColor("#6684e1")); // from website
        }

        message.setText(item.getMessage());

        if (item.getMyNick().matches("[A-Za-z0-9_]+")) {
            if (item.getMessage().matches(".*?@" + item.getMyNick() + "\\b.*?")) {
                message.setTextColor(Color.parseColor("#FFFF66"));
            }
        }

        sender.setText(item.getSender());
        trip.setText(item.getTrip() + "  ");

        String epochTime = item.getTime();
        if (epochTime.equals("") == false) {
            Date UTCDate = new Date(Long.parseLong(epochTime));

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            String result = simpleDateFormat.format(UTCDate);

            time.setText(result);
        }

        return v;
    }

}
