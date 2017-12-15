package com.example.administrator.ontouchevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private Button viewById;
    private TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = findViewById(R.id.btn_view);
        tv_text = findViewById(R.id.tv_text);
        //注册EventBus
        EventBus.getDefault().register(this);

        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),SecondActivity.class));
            }
        });

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(psd event){

        String msg = "onEventMainThread收到了消息：" + "name:"+event.userName+" passwrod:"+event.passd;
        Log.d("harvic", msg);
        tv_text.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
