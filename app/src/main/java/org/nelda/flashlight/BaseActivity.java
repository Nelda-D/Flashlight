package org.nelda.flashlight;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/**
 * Created by Administrator on 2015/7/4 0004.
 */
public class BaseActivity extends Activity {
    /**
     * 全局的、主界面的UI控件
     */
    protected LinearLayout mUIMaininterface;
    protected ImageView mController;
    protected enum UIType{UI_TYPE_MAIN,UI_TYPE_FLASHLIGHT,UI_TYPE_WARNINGLIGHT,UI_TYPE_MORSE,UI_TYPE_BULB,UI_TYPE_COLORLIGHT,UI_TYPE_POLICE,UI_TYPE_SETTINGS}
    protected UIType mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
    protected UIType mLastUIType = UIType.UI_TYPE_FLASHLIGHT;
    /**
     *Flashlight的UI控件
     */
    protected ImageView mImageView;
    protected FrameLayout mUIFlashlight;
    protected Camera mCamera;
    protected Camera.Parameters mParameters;
    /**
     * WarningLight的UI控件
     */
    protected ImageView mImageView_UIWarningLight_Light1;
    protected ImageView mImageView_UIWarningLight_Light2;
    protected LinearLayout mUIWarningLight;
    protected int mDefaultScreenBrightness;//屏幕的默认亮度
    /**
     * Morse的UI控件
     */
    protected EditText mEditText_UIMorse_MorseChar;
    protected Button mButton_UIMorse_Send;
    protected LinearLayout mUIMorse;
    /**
     * Bulb的UI控件
     */
    protected ImageView mImageView_UIBulb_Bulb;
    protected FrameLayout mUIBulb;
    protected HideTextView mHideTextView_UIBulb_Hinttext;

    /**
     * ColorLight的UI控件
     */
    protected HideTextView mHideTextView_UIColorLight_Hinttext;
    protected FrameLayout mUIColorLight;

    /**
     * Police的UI控件
     */
    protected LinearLayout mUIPolice;

    /**
     * Settings的UI控件
     */
    protected LinearLayout mUISettings;
    protected SeekBar mSeekBarWarningLight;
    protected SeekBar mSeekBarPolice;
    protected static int mFlashTime = 250; //闪烁的更换速度
    protected static int mFlickerFrequency = 200;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.imageview_uiflashlight_flashlight);
        mUIMaininterface =(LinearLayout)findViewById(R.id.linearlayout_maininterface);
        mUIFlashlight = (FrameLayout)findViewById(R.id.framelayout_flashlight);
        mController =(ImageView)findViewById(R.id.imageview_maininterface_controller);
        //Warninglight
        mImageView_UIWarningLight_Light1 = (ImageView)findViewById(R.id.imageview_uiwarninglight_light1);
        mImageView_UIWarningLight_Light2 = (ImageView)findViewById(R.id.imageview_uiwarninglight_light2);
        mUIWarningLight = (LinearLayout)findViewById(R.id.linearlayout_warninglight);
        //Morse
        mEditText_UIMorse_MorseChar = (EditText)findViewById(R.id.edittext_uimorse_morsechar);
        mButton_UIMorse_Send = (Button)findViewById(R.id.button_uimorse_send);
        mUIMorse = (LinearLayout)findViewById(R.id.linearlayout_morse);
        //Bulb
        mImageView_UIBulb_Bulb = (ImageView)findViewById(R.id.imageview_uibulb_bulb);
        mUIBulb = (FrameLayout)findViewById(R.id.framelayout_bulb);
        mHideTextView_UIBulb_Hinttext = (HideTextView)findViewById(R.id.hidetextview_uibulb_hinttext);
        //ColorLight
        mHideTextView_UIColorLight_Hinttext = (HideTextView)findViewById(R.id.hidetextview_uicolorlight_hinttext);
        mUIColorLight = (FrameLayout)findViewById(R.id.framelayout_colorlight);
        //Police
        mUIPolice = (LinearLayout)findViewById(R.id.linearlayout_police);
        //Settings
        mUISettings = (LinearLayout)findViewById(R.id.linearlayout_settings);
        mSeekBarWarningLight = (SeekBar)findViewById(R.id.seekbar_warninglight);
        mSeekBarPolice = (SeekBar)findViewById(R.id.seekbar_police);
        mSeekBarWarningLight.setProgress(mFlashTime - 50);
        mSeekBarPolice.setProgress(mFlickerFrequency - 100);

        //屏幕当前亮度
        mDefaultScreenBrightness = getScreenBrightness();
    }

    protected void hideAllUI(){     //隐藏所有界面的UI
        mUIFlashlight.setVisibility(View.GONE);
        mUIMaininterface.setVisibility(View.GONE);
        mUIWarningLight.setVisibility(View.GONE);
        mUIMorse.setVisibility(View.GONE);
        mUIBulb.setVisibility(View.GONE);
        mUIColorLight.setVisibility(View.GONE);
        mUIPolice.setVisibility(View.GONE);
        mUISettings.setVisibility(View.GONE);
    }

    //设置屏幕亮度 值为0到1 类型为float
    protected void installScreenBrightness(float value){
        WindowManager.LayoutParams layoutParams =getWindow().getAttributes();
        layoutParams.screenBrightness = value;
        getWindow().setAttributes(layoutParams);
    }

    //获取屏幕亮度
    protected int getScreenBrightness(){
        int value = 0;
        try {
        value = android.provider.Settings.System.getInt(getContentResolver(),Settings.System.SCREEN_BRIGHTNESS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }



}
