package com.ap.framework.sdk;

/**
 * Created by Regis on 2017/3/6.
 */

public interface IUserListener {
    void OnInited(Object sender, SdkEvent e);

    void OnLogined(Object sender, SdkEvent e);

    void OnLogouted(Object sender, SdkEvent e);

    void OnSwitchLogined(Object sender, SdkEvent e);

    void OnShowAccountCentered(Object sender, SdkEvent e);

}
