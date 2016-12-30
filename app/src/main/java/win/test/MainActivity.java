package win.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;

        TextView title = (TextView) findViewById(R.id.title);
        title.setText("预约课程");


        ImageView back = (ImageView) findViewById(R.id.back);
        TextView java = (TextView) findViewById(R.id.java);
        TextView android = (TextView) findViewById(R.id.android);
        TextView ios = (TextView) findViewById(R.id.ios);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        java.setOnClickListener(this);
        android.setOnClickListener(this);
        ios.setOnClickListener(this);


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
