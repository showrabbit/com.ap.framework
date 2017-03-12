package com.ap.framework.sdk;

import org.json.JSONException;

/**
 * Created by Regis on 2017/3/12.
 */

public class User implements IUser {
    protected IUserListener m_Listener;

    @Override
    public void SetListener(IUserListener l) {
        m_Listener = l;
    }

    @Override
    public void Init(String param) throws JSONException {

    }

    @Override
    public void Login(String param) throws JSONException {

    }

    @Override
    public void Logout(String param) throws JSONException {

    }

    @Override
    public void SwitchLogin(String param) throws JSONException {

    }

    @Override
    public void ShowAccountCenter(String param) throws JSONException {

    }
}
