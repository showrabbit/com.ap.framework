package com.ap.framework.sdk;

/**
 * Created by Regis on 2017/3/6.
 */

public interface IUserListener {
    void OnInit(Object sender, SdkEvent e);

    void OnLogin(Object sender, SdkEvent e);

    void OnLogout(Object sender, SdkEvent e);

    void OnSwitchLogin(Object sender, SdkEvent e);

    void OnShowAccountCenter(Object sender, SdkEvent e);

}
