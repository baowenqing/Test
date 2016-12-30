package win.test;

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

import win.test.custom.CircleImageView;
import win.test.model.Model;

public class CourseActivity extends AppCompatActivity {


    List<Model> mList;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        mContext = CourseActivity.this;
        String type = getIntent().getStringExtra("type");


        switch (type) {
            case "0":
                setdata("java ");
                break;

            case "1":
                setdata("android ");
                break;
            case "2":
                setdata("ios ");
                break;
        }

        TextView title = (TextView) findViewById(R.id.title);
        title.setText("授课老师");
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


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
        mList.add(new Model(R.drawable.a1, type + "吴亦凡",type+"老师："+"吴亦凡（Kris），1990年11月06日出生于广东省广州市，演员、歌手。2007年吴亦凡通过S.M. GlobalAudition Canada加入了韩国SM娱乐公司，参加练习生培训。"));
        mList.add(new Model(R.drawable.a2, type + "范冰冰",type+"老师："+"范冰冰，1981年9月16日出生于山东青岛，电影演员，流行音乐女歌手，影视制片人。1998年演电视剧《还珠格格》成名，2001年起投身大银幕。"));
        mList.add(new Model(R.drawable.a3, type + "Angelababy",type+"老师："+"Angelababy，中文名杨颖，1989年2月28日出生于上海，中国内地影视女演员、模特。2003年以模特身份出道。2007年将演艺事业的重心转向大银幕。2009年主演爱情电影《全城热恋》"));
        mList.add(new Model(R.drawable.a4, type + "周杰伦",type+"老师："+"周杰伦（Jay Chou），1979年1月18日出生于台湾省新北市，中国台湾流行乐男歌手、音乐人、演员、导演、编剧、监制、商人。2000年发行首张个人专辑《Jay》。"));
        mList.add(new Model(R.drawable.a5, type + "李易峰",type+"老师："+"李易峰，中国内地男演员、歌手、制片人，1987年5月4日出生于四川成都，2010年毕业于四川师范大学电影电视学院。2007年参加《加油好男儿》出道，同年发行首张EP《四叶草》。"));
        mList.add(new Model(R.drawable.a6, type + "杨幂",type+"老师："+"杨幂，1986年9月12日出生于北京市，中国内地影视女演员、流行乐歌手、影视制片人。2005年，杨幂进入北京电影学院表演系本科班就读。2006年，杨幂因出演金庸武侠剧《神雕侠侣》"));

    }


}


