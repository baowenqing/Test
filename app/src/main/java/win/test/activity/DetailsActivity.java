package win.test.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import win.test.App;
import win.test.DB.DbService;
import win.test.R;
import win.test.custom.CircleImageView;
import win.test.custom.sweetAlertDialog.SweetAlertDialog;
import win.test.model.Model;
import win.test.utils.BlurBitmapUtil;

public class DetailsActivity extends BaseActivity {


    String isYue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        final Model model = (Model) getIntent().getSerializableExtra("data");

        TextView name = (TextView) findViewById(R.id.name);
        TextView introduce = (TextView) findViewById(R.id.introduce);
        ImageView back = (ImageView) findViewById(R.id.back);
        ImageView img = (ImageView) findViewById(R.id.img);
        TextView title = (TextView) findViewById(R.id.title);
        CircleImageView circle_img = (CircleImageView) findViewById(R.id.circle_img);
        final Button button = (Button) findViewById(R.id.button);


        title.setText("事项详情");

        isYue = DbService.queryRecord(model.name, App.userId);


        if (isYue != null) {
            button.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
            button.setText("已预约");
        } else {
            button.setBackgroundColor(getResources().getColor(R.color.blue));
            button.setText("马上预约");
        }


        Bitmap oldBmp = BitmapFactory.decodeResource(getResources(), model.photo);
        Bitmap newBmp = BlurBitmapUtil.blurBitmap(DetailsActivity.this, oldBmp, 5f);
        img.setImageBitmap(newBmp);




        circle_img.setImageResource(model.photo);
        introduce.setText("\t\t\t\t" + model.introduce);
        name.setText(model.name);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isYue != null) {
                    new SweetAlertDialog(DetailsActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("确定取消预约?")
                            .setCancelText("取消")
                            .setConfirmText("是的!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    button.setBackgroundColor(getResources().getColor(R.color.blue));
                                    button.setText("马上预约");
                                    sweetAlertDialog.dismiss();
                                    DbService.addRecord(model.name, null,App.userId);
                                    isYue = null;
                                    Snackbar.make(button, "取消预约成功", 1500).show();
                                }
                            }).show();

                } else {


                    new SweetAlertDialog(DetailsActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("确定预约?")
                            .setContentText("预约该老师的课程!")
                            .setCancelText("取消")
                            .setConfirmText("是的!")
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    // reuse previous dialog instance


                                    sDialog.setTitleText("预约成功!")
                                            .setContentText("")
                                            .setConfirmText("OK")
                                            .setCancelClickListener(null)
                                            .showCancelButton(false)
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {
                                                    DbService.addRecord(model.name, "yue",App.userId);
                                                    button.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
                                                    button.setText("已预约");

                                                    isYue = "yue";

                                                    sDialog.dismiss();
                                                    Snackbar.make(button, "预约成功", 1500).show();
                                                }
                                            })
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);


                                }
                            })
                            .show();
                }
            }
//
//            }
        });

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_details;
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
}
