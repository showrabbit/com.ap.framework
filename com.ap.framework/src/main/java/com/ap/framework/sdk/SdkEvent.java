package com.ap.framework.sdk;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * Created by Regis on 2017/3/6.
 */

public class SdkEvent {
    public boolean Ok;
    public String Error;

    //把对象转换成事件字符串
    public String ToEventString() throws JSONException {
        JSONStringer stringer = new JSONStringer();
        return stringer.object().key("Ok").value(this.Ok).key("Error").value(this.Error).endObject().toString();
    }
}
