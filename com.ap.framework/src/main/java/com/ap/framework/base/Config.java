package com.ap.framework.base;

import android.content.res.AssetManager;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by Regis on 2017/3/14.
 */

public class Config {
    private static Config m_Config;

    private HashMap<String, String> m_Settings;

    private Config() {
        m_Settings = new HashMap<String, String>();
        Init();
    }

    public static Config GetInstance() {
        if (m_Config == null) {
            m_Config = new Config();
        }
        return m_Config;
    }

    /*
        初始化加载配置文件里的内容
     */
    private void Init() {
        // 读取文件
        try {
            InputStream is =  Context.GetInstance().CurrentActivity.getResources().getAssets().open("config.xml");
            Parse(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Parse(InputStream is) {
        m_Settings = new HashMap<String, String>();

        try {
            // 由android.util.Xml创建一个XmlPullParser实例
            XmlPullParser xpp = Xml.newPullParser();
            // 设置输入流 并指明编码方式
            xpp.setInput(is, "UTF-8");
            // 产生第一个事件
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    case XmlPullParser.START_TAG:
                        if(xpp.getName().equalsIgnoreCase("Setting"))
                        {
                            String key = xpp.getAttributeValue(null, "Key");
                            String value = xpp.getAttributeValue(null,"Value");
                            m_Settings.put(key,value);
                        }
                        break;
                    case XmlPullParser.END_TAG:

                        break;
                }
                // 进入下一个元素并触发相应事件
                eventType = xpp.next();
            }
            is.close();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String GetValue(String key) {
        return m_Settings.get(key);
    }
}