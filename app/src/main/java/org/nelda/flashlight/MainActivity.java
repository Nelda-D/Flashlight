package org.nelda.flashlight;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends SettingsActivity {

    public void onClick_Controller(View view){
        if(mCurrentUIType != UIType.UI_TYPE_MAIN){
            hideAllUI();
            mUIMaininterface.setVisibility(View.VISIBLE);
            mLastUIType =mCurrentUIType;
            mCurrentUIType = UIType.UI_TYPE_MAIN;
            mWarningLightState = false;
            mFlickerState = false;
            installScreenBrightness(mDefaultScreenBrightness / 255f);
        }else{
            hideAllUI();
            switch (mLastUIType){
                case UI_TYPE_FLASHLIGHT:
                    mUIFlashlight.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
                    mLastUIType = UIType.UI_TYPE_FLASHLIGHT;
                    break;
                case UI_TYPE_WARNINGLIGHT:
                    mUIWarningLight.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_WARNINGLIGHT;
                    mLastUIType = UIType.UI_TYPE_WARNINGLIGHT;
                    new WarningLightThread().start();
                    installScreenBrightness(1f);
                    break;
                case UI_TYPE_MORSE:
                    mUIMorse.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_MORSE;
                    mLastUIType = UIType.UI_TYPE_MORSE;

                    break;
                case UI_TYPE_BULB:
                    mUIBulb.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_BULB;
                    mLastUIType = UIType.UI_TYPE_BULB;
                    mHideTextView_UIBulb_Hinttext.openHide();
                    break;
                case UI_TYPE_COLORLIGHT:
                    mUIColorLight.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_COLORLIGHT;
                    mLastUIType = UIType.UI_TYPE_COLORLIGHT;
                    installScreenBrightness(1f);
//                    mHideTextView_UIColorLight_Hinttext.openHide();
                    mHideTextView_UIColorLight_Hinttext.setHideTextViewTextColor(mCurrentColorLight);
                    break;
                case UI_TYPE_POLICE:
                    mUIPolice.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_POLICE;
                    mLastUIType = UIType.UI_TYPE_POLICE;
                    new FlickerThread().start();
                    installScreenBrightness(1f);
                    break;
                case UI_TYPE_SETTINGS:
                    mUISettings.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_SETTINGS;
                    mLastUIType = UIType.UI_TYPE_SETTINGS;
                default:
                    break;
            }
        }
    }
    public void onClick_Main_Flashlight(View view){
        hideAllUI();
        mUIFlashlight.setVisibility(View.VISIBLE);
        mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
        mLastUIType = UIType.UI_TYPE_FLASHLIGHT;
    }

    public void onClick_Main_WarningLight(View view){
        hideAllUI();
        mUIWarningLight.setVisibility(View.VISIBLE);
        mCurrentUIType = UIType.UI_TYPE_WARNINGLIGHT;
        mLastUIType = UIType.UI_TYPE_WARNINGLIGHT;
        mWarningLightState = true;
        new WarningLightThread().start();
        installScreenBrightness(1f);
    }

    public void onClick_Main_Morse(View view){
        hideAllUI();
        mUIMorse.setVisibility(View.VISIBLE);
        mCurrentUIType = UIType.UI_TYPE_MORSE;
        mLastUIType = UIType.UI_TYPE_MORSE;
    }
    public void onClick_Main_Bulb(View view){
        hideAllUI();
        mUIBulb.setVisibility(View.VISIBLE);
        mCurrentUIType = UIType.UI_TYPE_BULB;
        mLastUIType = UIType.UI_TYPE_BULB;
        mHideTextView_UIBulb_Hinttext.openHide();
    }
    public void onClick_Main_Colorlight(View view){
        hideAllUI();
        mUIColorLight.setVisibility(View.VISIBLE);
        mCurrentUIType = UIType.UI_TYPE_COLORLIGHT;
        mLastUIType = UIType.UI_TYPE_COLORLIGHT;
        installScreenBrightness(1f);
//        mHideTextView_UIColorLight_Hinttext.openHide();
        mHideTextView_UIColorLight_Hinttext.setHideTextViewTextColor(mCurrentColorLight);
    }
    public void onClick_Main_Police(View view){
        hideAllUI();
        mUIPolice.setVisibility(View.VISIBLE);
        mCurrentUIType = UIType.UI_TYPE_POLICE;
        mLastUIType = UIType.UI_TYPE_POLICE;
        new FlickerThread().start();
        installScreenBrightness(1f);
    }
    public void onClick_Main_Settings(View view){
        hideAllUI();
        mUISettings.setVisibility(View.VISIBLE);
        mCurrentUIType = UIType.UI_TYPE_SETTINGS;
        mLastUIType = UIType.UI_TYPE_SETTINGS;
    }

    //按下返回键的处理
    public void onBackPressed() {
        super.onBackPressed();
        mWarningLightState =false;
        mFlickerState = false;
        installScreenBrightness(mDefaultScreenBrightness/255f);
    }

}
