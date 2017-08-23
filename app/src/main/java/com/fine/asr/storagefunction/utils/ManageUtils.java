package com.fine.asr.storagefunction.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by long on 2017/1/19.
 */

public class ManageUtils {

    private MyToast mToast=null;

    /**
     * 长时间显示
     * @param context
     * @param message
     */
    public void ShowLongToast(Context context,String message){
        //Toast.makeText(context,message,Toast.LENGTH_LONG).show();
        // Toast.showToast(context,message, android.widget.Toast.LENGTH_SHORT);
        if(mToast==null){
            mToast = new MyToast(context);
        }
        mToast.show(message, 10);
    }

    /**
     * 短时间显示
     * @param context
     * @param message
     */
    public void ShowShortToast(Context context,String message){
        if(mToast==null){
            mToast = new MyToast(context);
        }
        mToast.show(message, 10);
    }

    /**
     * 页面跳转
     * @param activity
     * @param Theclass
     */
    public static void starIntent(AppCompatActivity activity, Class Theclass) {
        Intent intent = new Intent(activity, Theclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }
    public   void cancel(){
        if(mToast!=null){
            mToast.cancel();
        }
    }
}
