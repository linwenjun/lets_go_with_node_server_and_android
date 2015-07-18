package thoughtworks.academy.letsgoandroid;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;

public class RefreshGridView extends GridView implements AbsListView.OnScrollListener{

    boolean isPull = false;
    float startY;
    Activity activity;

    View header;

    public RefreshGridView(Context context) {
        super(context);
        initView(context);
    }

    public RefreshGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View header = mInflater.inflate(R.layout.header_good_list, null);
        activity = (Activity) context;
        setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int VisibleItemCount, int itemCount) {
        Log.i("onScroll", firstVisibleItem + ":" + VisibleItemCount + ":" + itemCount);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch(ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isPull = true;
                startY = ev.getY();
                Log.i("Touch Event", "Down");
                break;
            case MotionEvent.ACTION_UP:
                isPull = false;
                Log.i("Touch Event", "Up");
                break;
            case MotionEvent.ACTION_SCROLL:
                Log.i("Touch Event", "Scroll:");
                break;
            case MotionEvent.ACTION_MOVE:
                if(isPull) {
                    View header = activity.findViewById(R.id.list_header);
                    header.setPadding(0,0,0,0);
//                    float paddingTop = -177 + (ev.getY() - startY);
//                    Log.i("Touch Event", header.getMeasuredHeight() + "");
//                    paddingTop = Math.min(0, paddingTop);
//                    header.setPadding(0, (int) paddingTop, 0, 0);
                }
                break;
            default:
                Log.i("Touch Event", ev.getAction() + "");
        };
        return super.onTouchEvent(ev);
    }
}
