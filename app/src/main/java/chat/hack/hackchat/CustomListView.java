package chat.hack.hackchat;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Rudra on 26-07-2015.
 */
public class CustomListView extends ListView {
    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        setSelection(getCount() - 1);
    }
}
