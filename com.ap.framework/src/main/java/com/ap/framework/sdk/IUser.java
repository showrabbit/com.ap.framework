package com.ap.framework.sdk;

import org.json.JSONException;

/**
 * Created by aplysia on 2017/3/5.
 */

public interface IUser {
    void SetListener(IUserListener l);
    void Init(String param) throws JSONException;
    void Login(String param)  throws JSONException;
    void Logout(String param)  throws JSONException;
    void SwitchLogin(String param)  throws JSONException;
    void ShowAccountCenter(String param)  throws JSONException;
}
