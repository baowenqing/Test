package win.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import win.test.DB.DbService;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText mPhone;
    private EditText mPassword;
    Button BtnLogin,BtnRegist;
    Context mContext;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        App.userId="";
        mContext = LoginActivity.this;
        initView();
        addListener();
    }


    private void addListener() {
        BtnLogin.setOnClickListener(this);
        BtnRegist.setOnClickListener(this);

        back.setOnClickListener(this);
    }

    private void initView() {


        TextView title = (TextView) findViewById(R.id.title);
        title.setText("用户登录");
         back = (ImageView) findViewById(R.id.back);
        mPassword = (EditText) findViewById(R.id.password);
        mPhone = (EditText) findViewById(R.id.phone);
        BtnLogin = (Button) findViewById(R.id.login);
        BtnRegist= (Button) findViewById(R.id.regist);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.back:
                finish();
                break;
            case R.id.login:
                String Phone = mPhone.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if ( password.equals(DbService.queryPassword(Phone))) {

                    App.userId=Phone;
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(mContext, "账号或者密码错误", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.regist:
                Intent intent = new Intent(mContext, RegistActivity.class);
                startActivity(intent);
                break;

        }

    }
}

