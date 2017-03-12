package com.ap.framework.sdk;

import org.json.JSONException;

/**
 * Created by Regis on 2017/3/12.
 */

public class Pay implements IPay {
    protected IPayListener m_Listener;

    @Override
    public void SetListener(IPayListener l) {
        m_Listener = l;
    }

    @Override
    public void Pay(String param) throws JSONException {

    }
}
