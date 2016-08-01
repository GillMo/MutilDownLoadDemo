package com.gaomin.mutildownloaddemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button downBtn;
    private ProgressBar downloadProgressBar;
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MutilDownloader.LOADSUCESSED:
                        Toast.makeText(MainActivity.this,"文件下载完毕",Toast.LENGTH_LONG).show();;
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initView(){
        downBtn = (Button)findViewById(R.id.downBtn);
        downloadProgressBar = (ProgressBar)findViewById(R.id.downloadProgressBar);
    }

    private void initEvent(){
        downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new MutilDownloader(MainActivity.this).MutilDown();
                    }
                }).start();
            }
        });
    }
}
