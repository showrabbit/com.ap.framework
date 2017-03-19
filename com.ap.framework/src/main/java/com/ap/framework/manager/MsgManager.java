package com.ap.framework.manager;

import com.ap.framework.base.IMsgListener;
import com.unity3d.player.UnityPlayer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Regis on 2017/3/15.
 */

public class MsgManager {
    private HashMap<Integer,ArrayList<IMsgListener>> m_MsgListener;

    private static MsgManager m_Instance;


    public static MsgManager GetInstance()
    {
        if(m_Instance == null)
        {
            m_Instance = new MsgManager();
        }
        return m_Instance;
    }

    private MsgManager(){
        m_MsgListener = new HashMap<>();
    }

    public void AddListener(Integer type,IMsgListener listener){
        if(m_MsgListener.containsKey(type) == false ){
            m_MsgListener.put(type, new ArrayList<IMsgListener>());
        }
        m_MsgListener.get(type).add(listener);
    }

    public void RemoveListener(String type,IMsgListener l){
        if( m_MsgListener.containsKey(type)){
            m_MsgListener.get(type).remove(l);
        }
    }
    //接收来自UNITY的消息
    public void AcceptMsg(Integer type,String param) {
        if(m_MsgListener.containsKey(type)) {
            for (IMsgListener i : m_MsgListener.get(type)) {
                i.Accept(type,param);
            }
        }
    }
    //发送消息到UNITY
    public void SendMsg(Integer type,String parma){
        String msg = String.format("%d@#%s",type,parma);
        UnityPlayer.UnitySendMessage("Managers","AndroidAcceptMessage",msg);
    }
}
