package org.nelda.flashlight;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2015/7/13 0013.
 */
public class PoliceActivity extends ColorLightActivity {

    protected boolean mFlickerState = true;

    protected static int mColorNum = 0;

    class FlickerThread extends Thread{
        @Override
        public void run() {
            super.run();
            mFlickerState =true;
                try {
                while(mFlickerState) {
                    Thread.sleep(mFlickerFrequency);
                    handler.sendEmptyMessage(0);
                }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }

    protected Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                switch (mColorNum){
                    case 0:
                        mUIPolice.setBackgroundColor(Color.RED);
                        break;
                    case 1:
                        mUIPolice.setBackgroundColor(Color.GREEN);
                        break;
                    case 2:
                        mUIPolice.setBackgroundColor(Color.BLUE);
                        break;
                    case 3:
                        mUIPolice.setBackgroundColor(Color.YELLOW);
                        break;
                    case 4:
                        mUIPolice.setBackgroundColor(Color.MAGENTA);
                        break;
                    default:
                        mUIPolice.setBackgroundColor(Color.CYAN);
                        mColorNum=-1;
                        break;
                }
                mColorNum++;
            }
        }
    };

}
