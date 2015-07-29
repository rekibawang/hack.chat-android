package chat.hack.hackchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Rudra on 13-07-2015.
 */
public class OnlineNavDrawerAdapter extends BaseAdapter {
    private ArrayList<String> onlineList;
    Context context;

    OnlineNavDrawerAdapter(ArrayList<String> onlineList, Context context) {
        this.onlineList = onlineList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return onlineList.size();
    }

    @Override
    public Object getItem(int position) {
        return onlineList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.online_drawer_row, null);
        }

        final TextView tvOnlineDrawer = (TextView) v.findViewById(R.id.tvOnlineDrawer);
        final String user = onlineList.get(position);
        tvOnlineDrawer.setText(user);

        return v;
    }
}
