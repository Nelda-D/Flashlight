package org.nelda.flashlight;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2015/7/9 0009.
 */
public class BulbActivity extends MorseActivity {

    protected TransitionDrawable mTransitionDrawable;
    protected Boolean BULB_STATE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTransitionDrawable = (TransitionDrawable)mImageView_UIBulb_Bulb.getDrawable();
    }

    public void onClick_TransitionBulb(View view){
        if(!BULB_STATE){
            mTransitionDrawable.startTransition(500);
            installScreenBrightness(1f);
            BULB_STATE = true;
        }else if(BULB_STATE){
            mTransitionDrawable.reverseTransition(500);
            BULB_STATE = false ;
            installScreenBrightness(mDefaultScreenBrightness/255f);
        }
    }
}
