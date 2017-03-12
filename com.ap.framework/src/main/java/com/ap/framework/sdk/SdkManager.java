package com.ap.framework.sdk;

import java.lang.ClassNotFoundException;

/**
 * Created by aplysia on 2017/3/5.
 */
public class SdkManager implements IUserListener ,IPayListener {
    // 单例对象
    private static SdkManager m_Instance;
    // 包含的sdk
    private IUser m_User;

    private IPay m_Pay;

    private SdkManager()
    {

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
        // 读取sdk配置
        // 设置sdk
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
}
