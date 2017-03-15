package com.ap.framework.manager;

import com.ap.framework.base.Config;
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
    protected void Init()
    {
        MsgManager.GetInstance().AddListener("Sdk",SdkManager.this);
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
    public void OnInit(Object sender, SdkEvent e) {
        // 读取sdk配置
        // 设置sdk
        String user = Config.GetInstance().GetValue("SdkUser");
        SetUser(user);
        String pay = Config.GetInstance().GetValue("SdkPay");
        SetPay(pay);
    }

    @Override
    public void OnLogin(Object sender, SdkEvent e) {

    }

    @Override
    public void OnLogout(Object sender, SdkEvent e) {

    }

    @Override
    public void OnSwitchLogin(Object sender, SdkEvent e) {

    }

    @Override
    public void OnShowAccountCenter(Object sender, SdkEvent e) {

    }

    @Override
    public void OnPay(Object sender, SdkEvent event) {

    }

    @Override
    public void Accept(String type, String param) {
        try {
            JSONObject obj = new JSONObject(param);
            String fun = obj.getString("Fun");
            String value = obj.getString("Value");
            if(fun.contentEquals("Login")){
                m_User.Login(value);
            }
            else if(fun.contentEquals("Logout")){
                m_User.Logout(value);
            }
            else if(fun.contentEquals("SwitchLogin")){
                m_User.SwitchLogin(value);
            }
            else if(fun.contentEquals("Pay")){
                m_Pay.Pay(value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
