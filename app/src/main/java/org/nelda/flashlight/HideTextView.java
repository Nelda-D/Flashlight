package org.nelda.flashlight;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/7/9 0009.
 */
public class HideTextView extends TextView {

    public HideTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



//    Thread thread = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            handler.sendEmptyMessage(1);
//            try {
//                Thread.sleep(3000);
//                handler.sendEmptyMessage(0);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    });

    protected Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                setVisibility(GONE);
            }else if(msg.what == 1){
                setVisibility(VISIBLE);
            }
        }
    };

    class OpenHideThread extends Thread{
        @Override
        public void run() {
            super.run();
            handler.sendEmptyMessage(1);
            try {
                Thread.sleep(3000);
                handler.sendEmptyMessage(0);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void openHide(){
        new OpenHideThread().start();
    }

    public void setHideTextViewTextColor(int color){
        setTextColor(Color.rgb(255 - Color.red(color), 255 - Color.green(color), 255 - Color.blue(color)));
    }
}
