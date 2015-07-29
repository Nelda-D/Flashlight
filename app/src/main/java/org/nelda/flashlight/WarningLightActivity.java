package org.nelda.flashlight;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2015/7/5 0005.
 */
public class WarningLightActivity extends FlashlightActivity {

    protected Boolean mWarningLightState = true; //true:闪烁状态    false：停止闪烁
    protected Boolean mWarningLightAspect = true; //true:亮的在上面暗的在下面     false：暗的在上面亮的在下面


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWarningLightState = true;

    }

    class WarningLightThread extends Thread {
        @Override
        public void run() {
            mWarningLightState = true;
            try {
                while(mWarningLightState){
                    Thread.sleep(mFlashTime);
                    handler.sendEmptyMessage(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * handler更新WarningLight的UI状态
     */
   private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mWarningLightAspect){
                mImageView_UIWarningLight_Light1.setImageResource(R.drawable.warning_light_off);
                mImageView_UIWarningLight_Light2.setImageResource(R.drawable.warning_light_on);
                mWarningLightAspect =false;
            }else{
                mImageView_UIWarningLight_Light1.setImageResource(R.drawable.warning_light_on);
                mImageView_UIWarningLight_Light2.setImageResource(R.drawable.warning_light_off);
                mWarningLightAspect = true;
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        mWarningLightState =false;
    }



}
