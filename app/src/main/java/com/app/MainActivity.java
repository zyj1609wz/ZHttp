package com.app;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.app.http.HttpCallBack;
import com.app.http.MAsyncTask;

public class MainActivity extends AppCompatActivity {

    MAsyncTask mAsyncTask ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //请求地址
        String url = "http://www.baidu.com" ;

        //修复空指针
        if ( url != null ){

        }

        mAsyncTask = new MAsyncTask( url  , new HttpCallBack() {
            @Override
            protected void start() {
                super.start();
                Toast.makeText( MainActivity.this , "开始了" , Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void finished(String result) {
                super.finished(result);
                Toast.makeText( MainActivity.this , "完成了" + result , Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void cancle() {
                super.cancle();
                Toast.makeText( MainActivity.this , "取消了" , Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void progressUpdate(Integer progress) {
                super.progressUpdate(progress);
                Toast.makeText( MainActivity.this , "进度" + progress , Toast.LENGTH_SHORT).show();
            }
        }) ;

    }

}
