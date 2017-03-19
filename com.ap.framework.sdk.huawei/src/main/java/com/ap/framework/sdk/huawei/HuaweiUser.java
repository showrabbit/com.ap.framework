package com.ap.framework.sdk.huawei;

import com.ap.framework.sdk.IUser;
import com.ap.framework.sdk.SdkEvent;
import com.ap.framework.sdk.User;
import com.huawei.gameservice.sdk.GameServiceSDK;
import com.ap.framework.base.Context;
import com.huawei.gameservice.sdk.api.Result;

import org.json.JSONObject;
/**
 * Created by Regis on 2017/3/12.
 */

public class HuaweiUser extends User {

    /*
      {AppID,CpID,}
     */
    public void Init(String param)  throws org.json.JSONException{
        HuaweiHandler handler = new HuaweiHandler() {
            @Override
            public void onResult(Result result) {
                super.onResult(result);
                // 处理初始化结果
                SdkEvent event = new SdkEvent();
                if (result.rtnCode == 0) {
                    // 成功
                    event.Ok = true;
                    event.Error = "";

                } else {
                    // 失败
                    event.Ok = false;
                    event.Error = result.description;
                }
                m_Listener.OnInited(HuaweiUser.this, event);
            }

            @Override
            public String getGameSign(String s, String s1, String s2) {
                return super.getGameSign(s, s1, s2);
            }
        };
        JSONObject obj = new JSONObject(param);
        GameServiceSDK.init(Context.GetInstance().CurrentActivity, obj.getString("AppID"), obj.getString("CpID"), "", handler);
    }
    // {GameType:int}
    public void Login(String param)  throws org.json.JSONException{
        HuaweiHandler handler = new HuaweiHandler(){
            @Override
            public void onResult(Result result) {
                super.onResult(result);
                SdkEvent event = new SdkEvent();
                if (result.rtnCode == 0) {
                    // 成功
                    event.Ok = true;
                    event.Error = "";

                } else {
                    // 失败
                    event.Ok = false;
                    event.Error = result.description;
                }
                m_Listener.OnLogined(HuaweiUser.this, event);
            }
        };
        JSONObject obj = new JSONObject(param);

        GameServiceSDK.login(Context.GetInstance().CurrentActivity,handler,obj.getInt("GameType"));
    }

    public void Logout(String param) throws org.json.JSONException{

    }

    public void SwitchLogin(String param) throws org.json.JSONException{

    }

    public void ShowAccountCenter(String param) throws org.json.JSONException{

    }


}
