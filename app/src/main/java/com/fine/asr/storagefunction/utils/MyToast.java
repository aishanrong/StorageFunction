package com.fine.asr.storagefunction.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by mating on 2017/4/28.
 */
public class MyToast {
    Context mContext;
    Toast   mToast;

    public MyToast(Context context) {
        mContext = context;
        mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        //mToast.setGravity(17, 0, -30);//居中显示
    }

    public void show(int resId, int duration) {
        show(mContext.getText(resId), duration);
    }

    public void show(CharSequence s, int duration) {
        mToast.setDuration(duration);
        mToast.setText(s);
        mToast.show();
    }

    public void cancel() {
        mToast.cancel();
    }

}
