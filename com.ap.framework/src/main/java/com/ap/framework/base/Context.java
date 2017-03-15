package com.ap.framework.base;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Regis on 2017/3/12.
 */

public class Context {


    private static Context m_Instance;


    public static Context GetInstance()
    {
        if(m_Instance == null)
        {
            m_Instance = new Context();
        }
        return m_Instance;
    }
    /*
        当前运行的activity
     */
    public Activity CurrentActivity;

    /*
        是否在编辑运行状态
     */
    public boolean IsEditor = false;


    protected Context() {

    }


}
