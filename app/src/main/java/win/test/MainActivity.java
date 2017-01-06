package win.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import win.test.DB.DbService;
import win.test.custom.CircleImageView;
import win.test.model.Model;
import win.test.utils.CommonAdapter;
import win.test.utils.CommonViewHolder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    List<String> list;
    Context mContext;
    CommonAdapter<String> mAdapter;
    Model mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;

        TextView title = (TextView) findViewById(R.id.title);

        title.setText("事项列表");


        final ImageView back = (ImageView) findViewById(R.id.back);
        TextView java = (TextView) findViewById(R.id.java);
        TextView android = (TextView) findViewById(R.id.android);
        TextView ios = (TextView) findViewById(R.id.ios);
        ListView listView = (ListView) findViewById(R.id.list);
        LinearLayout llContains = (LinearLayout) findViewById(R.id.llContains);

        list = new ArrayList<>();

        list.addAll(DbService.queryWholeRecord());
        mAdapter = new CommonAdapter<String>(this, list, R.layout.list_item) {
            @Override
            protected void convertView(View item, String model, int position) {

                TextView textView = CommonViewHolder.get(item, R.id.textView);
                CircleImageView img = CommonViewHolder.get(item, R.id.imageView);
                LinearLayout llContains = CommonViewHolder.get(item, R.id.llContains);


                if (model.contains("吃饭")) {
                    img.setImageResource(R.drawable.eat);
                    mModel = new Model(R.drawable.eat, model, "吃饭：" + "美食什么的，我最喜欢啦！");
                } else if (model.contains("购物")) {
                    img.setImageResource(R.drawable.shop);
                    mModel = new Model(R.drawable.shop, model, "购物：" + "去找找剁手的感觉吧！");
                } else if (model.contains("运动")) {
                    img.setImageResource(R.drawable.sport);
                    mModel = new Model(R.drawable.sport, model, "运动：" + "有氧运动可以使人年轻，更有助于身心健康！");
                } else if (model.contains("工作")) {
                    img.setImageResource(R.drawable.work);
                    mModel = new Model(R.drawable.work, model, "工作：" + "积极的工作更能找到自己的人生定位！");
                } else if (model.contains("看电影")) {
                    img.setImageResource(R.drawable.movie);
                    mModel = new Model(R.drawable.movie, model, "和心爱的人或者好基友去电影院赏心悦目吧！");
                } else if (model.contains("休息")) {
                    img.setImageResource(R.drawable.sleep);
                    mModel = new Model(R.drawable.sleep, model, "休息：" + "适当的休息一下有助于身体健康哦！");
                }


                textView.setText(model);


                llContains.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(mContext, DetailsActivity.class);
                        intent.putExtra("data",  mModel);
                        startActivity(intent);
                    }
                });


            }
        };

        View emptyView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_empty_content, llContains, false);
        ((ViewGroup) listView.getParent()).

                addView(emptyView);

        listView.setEmptyView(emptyView);


        listView.setAdapter(mAdapter);


        back.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        finish();
                                    }
                                }

        );
        java.setOnClickListener(this);
        android.setOnClickListener(this);
        ios.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        list.addAll(DbService.queryWholeRecord());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, CourseActivity.class);

        switch (v.getId()) {

            case R.id.java:
                intent.putExtra("type", "0");
                break;
            case R.id.android:
                intent.putExtra("type", "1");
                break;
            case R.id.ios:
                intent.putExtra("type", "2");
                break;

        }
        startActivity(intent);

    }
}
