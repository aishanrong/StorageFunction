package com.fine.asr.storagefunction;

import android.app.Activity;
import android.app.Application;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;

import com.fine.asr.storagefunction.utils.ManageUtils;
import com.fine.asr.storagefunction.utils.fntech.io.service.IOManager;
import com.fine.asr.storagefunction.utils.fntech.io.service.SoundManager;
import com.fntech.Loger;

import java.io.FileOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class MLApplication extends Application {

    public   static Context mContext;
    public static ManageUtils manageUtils;
    public static SoundManager mSoundManager = new SoundManager();

    private Socket          mTcpSocket = null;
    private BluetoothSocket mBtSocket  = null;
    private List<Activity> activities = new ArrayList<Activity>();
    private List<Fragment> fragments  = new ArrayList<Fragment>();
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        manageUtils=new ManageUtils();
        mSoundManager.initSounds(mContext);

    }

    //打开串口（M10A与M10）
    public static void ScanOpen(Handler handler) {
        Log.i("123","打开串口");
        IOManager.INSTANCE.InitCommA(handler);
        IOManager.INSTANCE.InitComm(handler);
        //Toast.makeText(mContext, "串口打开成功", Toast.LENGTH_LONG).show();
}

    public static void Scanclose() {
        IOManager.INSTANCE.DeInitCommA();
        IOManager.INSTANCE.DeInitComm();
//        Toast.makeText(mContext, "串口关闭成功", Toast.LENGTH_LONG).show();
    }


    public static boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("keyCode   ", keyCode + "");
        Loger.disk_log("onkeydown", "" + keyCode, "M10_1D");
        switch (keyCode) {
            case 80: {
                writeFile("/sys/class/gpio/gpio907/direction", "out");
                writeFile("/sys/class/gpio/gpio907/ value", "0");
                writeFile("/sys/class/gpio/gpio909/ value", "1");
                Log.i("123", "键值是:" + keyCode);
            }
        }
        return true;
    }

    private static void writeFile(String fileName, String writestr) {
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            byte[] bytes = writestr.getBytes();
            fout.write(bytes);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case 80:
                writeFile("/sys/class/gpio/gpio907/value", "1");
                writeFile("/sys/class/gpio/gpio909/value", "1");
        }
        return true;
    }

}
