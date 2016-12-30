package win.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import win.test.DB.Db;
import win.test.DB.DbService;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText mPhone;
    private EditText mPassword;
    Button BtnRegist;
    Context mContext;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        mContext = RegistActivity.this;
        initView();
        addListener();


    }

    private void addListener() {
        BtnRegist.setOnClickListener(this);

        back.setOnClickListener(this);
    }

    private void initView() {


        TextView title = (TextView) findViewById(R.id.title);
        title.setText("用户注册");
        back = (ImageView) findViewById(R.id.back);
        mPassword = (EditText) findViewById(R.id.password);
        mPhone = (EditText) findViewById(R.id.phone);
        BtnRegist = (Button) findViewById(R.id.regist);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.back:
                finish();
                break;
            case R.id.regist:
                String userId = mPhone.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(userId.equals("")||password.equals("")){
                    Toast.makeText(mContext, "手机号或者密码没有输入！无法注册！！！", Toast.LENGTH_SHORT).show();
                }else {
                    DbService.addUser(userId, password);

                    Toast.makeText(mContext, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(mContext,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }


                break;

        }

    }


}
