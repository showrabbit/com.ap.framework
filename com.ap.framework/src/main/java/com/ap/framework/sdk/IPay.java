package com.ap.framework.sdk;

import org.json.JSONException;

/**
 * Created by Regis on 2017/3/12.
 */

public interface IPay {
    void SetListener(IPayListener l);
    void Pay(String param) throws JSONException;
}
