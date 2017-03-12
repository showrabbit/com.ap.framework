package com.ap.framework.sdk.huawei;

import com.ap.framework.base.Context;
import com.ap.framework.sdk.Pay;
import com.huawei.gameservice.sdk.GameServiceSDK;
import com.huawei.gameservice.sdk.api.PayResult;
import com.huawei.gameservice.sdk.api.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Regis on 2017/3/12.
 */

public class HuaweiPay extends Pay {
    @Override
    public void Pay(String param) throws JSONException {
        super.Pay(param);

        HuaweiHandler handler = new HuaweiHandler(){
            @Override
            public void onResult(Result result) {
                super.onResult(result);

                PayResult pay = (PayResult) result;
            }

            @Override
            public String getGameSign(String s, String s1, String s2) {
                return super.getGameSign(s, s1, s2);
            }
        };

        Map<String,Object> info = MakePayInfo(param);
        GameServiceSDK.startPay(Context.GetInstance().CurrentActivity,info,handler);
    }

    protected Map<String,Object> MakePayInfo(String parma) throws JSONException{
        JSONObject obj = new JSONObject(parma);
        Map<String,Object> info = new HashMap<String,Object>();
        //info.put()


        return  info;
    }

}
