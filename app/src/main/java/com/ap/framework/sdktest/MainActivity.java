package com.ap.framework.sdktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ap.framework.base.Config;
import com.ap.framework.base.Context;
import com.ap.framework.manager.MsgManager;
import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends UnityPlayerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }

    private void Init(){
        Context.GetInstance().CurrentActivity = MainActivity.this;
    }

    public static void SendMsg(String type,String param){
        MsgManager.GetInstance().AcceptMsg(type,param);
    }
}
