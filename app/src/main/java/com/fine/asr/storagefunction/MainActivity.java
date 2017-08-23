package com.fine.asr.storagefunction;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

import static com.fine.asr.storagefunction.MLApplication.mSoundManager;
import static com.fine.asr.storagefunction.utils.MlValue.MSG_GOTBARCODE;

public class MainActivity extends AppCompatActivity {


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String barCode = "";
            switch (msg.what) {
                case MSG_GOTBARCODE:
                    byte[] ret = (byte[]) msg.obj;
                    try {
                        barCode = new String(ret, "UTF-8");
                        Log.i("123",barCode+"--------------");
                        Toast.makeText(MainActivity.this,barCode+"barCode",Toast.LENGTH_LONG).show();
                    } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mSoundManager.playSound();

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MLApplication.ScanOpen(handler);     //扫描
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        MLApplication.onKeyDown(keyCode,event);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        MLApplication.onKeyUp(keyCode, event);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MLApplication.Scanclose();
    }
}
