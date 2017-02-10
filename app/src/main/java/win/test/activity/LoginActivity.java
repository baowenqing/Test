package win.test.activity;

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

import win.test.App;
import win.test.DB.DbService;
import win.test.MainActivity;
import win.test.R;


public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private EditText mPhone;
    private EditText mPassword;
    Button BtnLogin,BtnRegist;
    Context mContext;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        App.userId="";
        mContext = LoginActivity.this;
        initView();
        addListener();
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }


    @Override
    protected void initDatas() {

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

