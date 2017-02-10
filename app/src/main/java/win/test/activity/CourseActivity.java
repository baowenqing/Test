package win.test.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import win.test.R;
import win.test.custom.CircleImageView;
import win.test.model.Model;

public class CourseActivity extends BaseActivity {


    List<Model> mList;
    Context mContext;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_course;
    }

    @Override
    protected void initViews() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("事项");
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {
        mContext = CourseActivity.this;
        String type = getIntent().getStringExtra("type");
        switch (type) {
            case "0":
                setdata("上午 ");
                break;

            case "1":
                setdata("中午 ");
                break;
            case "2":
                setdata("下午 ");
                break;
        }

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new Adapter());
    }


    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        class ViewHolder {
            TextView textView;
            CircleImageView imageView;
        }

        ViewHolder holder;

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null) {

                convertView = getLayoutInflater().inflate(R.layout.list_item, parent, false);

                holder = new ViewHolder();

                holder.textView = (TextView) convertView.findViewById(R.id.textView);

                holder.imageView = (CircleImageView) convertView.findViewById(R.id.imageView);

                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();

            }

            holder.imageView.setImageResource(mList.get(position).photo);
            holder.textView.setText(mList.get(position).name);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra("data", (Serializable) mList.get(position));
                    startActivity(intent);
                }
            });


            return convertView;
        }
    }


    private void setdata(String type) {
        mList = new ArrayList<>();
        mList.add(new Model(R.drawable.eat, type + "吃饭",  "吃饭：" + "美食什么的，我最喜欢啦！"));
        mList.add(new Model(R.drawable.shop, type + "购物",  "购物：" + "去找找剁手的感觉吧！"));
        mList.add(new Model(R.drawable.sport, type + "运动",  "运动：" + "有氧运动可以使人年轻，更有助于身心健康！"));
        mList.add(new Model(R.drawable.work, type + "工作",  "工作：" + "积极的工作更能找到自己的人生定位！"));
        mList.add(new Model(R.drawable.movie, type + "看电影",  "和心爱的人或者好基友去电影院赏心悦目吧！"));
        mList.add(new Model(R.drawable.sleep, type + "休息", "休息：" + "适当的休息一下有助于身体健康哦！"));

    }


}


