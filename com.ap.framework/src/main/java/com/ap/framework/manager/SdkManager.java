package com.ap.framework.manager;

import com.ap.framework.base.Config;
import com.ap.framework.base.EventTypes;
import com.ap.framework.base.IMsgListener;
import com.ap.framework.sdk.IPay;
import com.ap.framework.sdk.IPayListener;
import com.ap.framework.sdk.IUser;
import com.ap.framework.sdk.IUserListener;
import com.ap.framework.sdk.SdkEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ClassNotFoundException;

/**
 * Created by aplysia on 2017/3/5.
 */
public class SdkManager implements IUserListener,IPayListener,IMsgListener {
    // 单例对象
    private static SdkManager m_Instance;
    // 包含的sdk
    private IUser m_User;

    private IPay m_Pay;

    private SdkManager()
    {
        Init();
    }

    public static SdkManager GetInstance()
    {
        if(m_Instance == null)
        {
            m_Instance = new SdkManager();
        }
        return  m_Instance;
    }
    /*
    初始化
     */
    protected void Init() {
        MsgManager.GetInstance().AddListener(EventTypes.SdkLogin,SdkManager.this);
        MsgManager.GetInstance().AddListener(EventTypes.SdkLogout,SdkManager.this);
        MsgManager.GetInstance().AddListener(EventTypes.SdkPay,SdkManager.this);
        MsgManager.GetInstance().AddListener(EventTypes.SdkShowAccountCenter,SdkManager.this);
        MsgManager.GetInstance().AddListener(EventTypes.SdkSwitchLogin,SdkManager.this);
    }

    protected void InitConfig(){
        // 读取sdk配置
        // 设置sdk
        String user = Config.GetInstance().GetValue("SdkUser");
        SetUser(user);
        String pay = Config.GetInstance().GetValue("SdkPay");
        SetPay(pay);
    }

    protected void SetUser(String className)
    {
        try
        {
            Class<?> cls = Class.forName(className);
            IUser user = (IUser)cls.newInstance();
            m_User = user;
            m_User.SetListener(SdkManager.this);
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void SetPay(String className) {
        try
        {
            Class<?> cls = Class.forName(className);
            IPay pay = (IPay)cls.newInstance();
            m_Pay = pay;
            m_Pay.SetListener(SdkManager.this);
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void OnInited(Object sender, SdkEvent e) {

        try {
            MsgManager.GetInstance().SendMsg(EventTypes.SdkInited, e.ToEventString());
        } catch (JSONException el) {
            el.printStackTrace();
        }
    }

    @Override
    public void OnLogined(Object sender, SdkEvent e) {

        try {
            MsgManager.GetInstance().SendMsg(EventTypes.SdkLogined, e.ToEventString());
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void OnLogouted(Object sender, SdkEvent e) {
        try {
            MsgManager.GetInstance().SendMsg(EventTypes.SdkLogouted, e.ToEventString());
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void OnSwitchLogined(Object sender, SdkEvent e) {
        try {
            MsgManager.GetInstance().SendMsg(EventTypes.SdkSwitchLogined, e.ToEventString());
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void OnShowAccountCentered(Object sender, SdkEvent e) {
        try {
            MsgManager.GetInstance().SendMsg(EventTypes.SdkSwitchLogined, e.ToEventString());
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void OnPayed(Object sender, SdkEvent e) {
        try {
            MsgManager.GetInstance().SendMsg(EventTypes.SdkPayed, e.ToEventString());
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void Accept(Integer type, String param) {
        try {

            if (type == EventTypes.SdkLogin) {
                m_User.Login(param);
            } else if (type == EventTypes.SdkLogout) {
                m_User.Logout(param);
            } else if (type == EventTypes.SdkSwitchLogin) {
                m_User.SwitchLogin(param);
            } else if (type == EventTypes.SdkPay) {
                m_Pay.Pay(param);
            } else if(type == EventTypes.SdkInit){
                InitConfig();
                m_User.Init(param);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
