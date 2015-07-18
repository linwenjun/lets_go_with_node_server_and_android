package thoughtworks.academy.letsgoandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.academy.letsgoandroid.bean.Good;


public class MainActivity extends Activity {

    RefreshGridView gridView;
    TextView header;
    List<Good> goodList;
    int[] images = new int[] {R.mipmap.good_01, R.mipmap.good_02, R.mipmap.good_03, R.mipmap.good_04};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (RefreshGridView) findViewById(R.id.grid_view);
        header = (TextView) findViewById(R.id.list_header);

        measureView(header);
        header.setPadding(0, 0, 0, 0);
//        Log.i("height", header.getMeasuredHeight() + "");
//        header.setPadding(0, -1 * header.getMeasuredHeight(), 0, 0);

        goodList = new ArrayList<>();

        for(int i=0; i<20; i++) {
            goodList.add(new Good("name" + i, i, images[i % 4]));
        }

        gridView.setAdapter(new MyGoodGridAdapter(getApplicationContext(), goodList));

    }

    private void measureView(View child) {
        ViewGroup.LayoutParams params = child.getLayoutParams();
        if(null == params) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0, params.width);
        int lpHeight = params.height;
        int childHeightSpec;
        if(lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }

        child.measure(childWidthSpec, childHeightSpec);
    }

    class MyGoodGridAdapter extends BaseAdapter {

        private List<Good> goodList;
        private LayoutInflater layoutInflater;

        public MyGoodGridAdapter(Context context, List<Good> goodList) {
            this.goodList = goodList;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return goodList.size();
        }

        @Override
        public Object getItem(int i) {
            return goodList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder viewHolder = null;

            if(view == null) {
                viewHolder = new ViewHolder();
                view = layoutInflater.inflate(R.layout.item_good, null);
                viewHolder.goodImage = (ImageView) view.findViewById(R.id.good_img);
                viewHolder.goodName = (TextView) view.findViewById(R.id.good_name);
                viewHolder.goodPrice = (TextView) view.findViewById(R.id.good_price);

                Button buyBtn = (Button) view.findViewById(R.id.button_buy);
                buyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "已经添加至购物车", Toast.LENGTH_SHORT).show();
                    }
                });

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            Good g = goodList.get(i);
            viewHolder.goodImage.setImageResource(g.imgSrcId);
            viewHolder.goodName.setText(g.name);
            viewHolder.goodPrice.setText("$" + g.price + ".00");

            return view;
        }

        private class ViewHolder {
            ImageView goodImage;
            TextView goodName;
            TextView goodPrice;
        }
    }
}
