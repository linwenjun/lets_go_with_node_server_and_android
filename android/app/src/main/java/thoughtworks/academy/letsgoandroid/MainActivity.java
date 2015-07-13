package thoughtworks.academy.letsgoandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.academy.letsgoandroid.bean.Good;


public class MainActivity extends Activity {

    GridView gridView;
    List<Good> goodList;
    int[] images = new int[] {R.mipmap.good_01, R.mipmap.good_02, R.mipmap.good_03, R.mipmap.good_04};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.grid_view);
        goodList = new ArrayList<>();

        for(int i=0; i<20; i++) {
            goodList.add(new Good("name" + i, i, images[i % 4]));
        }

        gridView.setAdapter(new MyGoodGridAdapter(getApplicationContext(), goodList));

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
            View rootView = layoutInflater.inflate(R.layout.item_good, null);
            ImageView goodImage = (ImageView) rootView.findViewById(R.id.good_img);
            TextView goodName = (TextView) rootView.findViewById(R.id.good_name);
            TextView goodPrice = (TextView) rootView.findViewById(R.id.good_price);
            Button buyBtn = (Button) rootView.findViewById(R.id.button_buy);

            buyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "已经添加至购物车", Toast.LENGTH_SHORT).show();
                }
            });

            Good g = goodList.get(i);
            goodImage.setImageResource(g.imgSrcId);
            goodName.setText(g.name);
            goodPrice.setText("$" + g.price + ".00");


            return rootView;
        }
    }
}
