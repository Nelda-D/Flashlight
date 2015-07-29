package org.nelda.flashlight;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2015/7/13 0013.
 */
public class ColorLightActivity extends BulbActivity {

    protected int mCurrentColorLight = Color.RED;
    protected int mChangeColor = 0; //颜色的状态 0是设置为绿色  1是设置为蓝色 2是设置为白色 3是设置为红色

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onClick_ScreenColorLight(View view) {
        switch (mChangeColor) {
            case 0:
                mCurrentColorLight = Color.GREEN;
                mUIColorLight.setBackgroundColor(mCurrentColorLight);
                mHideTextView_UIColorLight_Hinttext.setHideTextViewTextColor(mCurrentColorLight);
                break;
            case 1:
                mCurrentColorLight = Color.BLUE;
                mUIColorLight.setBackgroundColor(mCurrentColorLight);
                mHideTextView_UIColorLight_Hinttext.setHideTextViewTextColor(mCurrentColorLight);
                break;
            case 2:
                mCurrentColorLight = Color.WHITE;
                mUIColorLight.setBackgroundColor(mCurrentColorLight);
                mHideTextView_UIColorLight_Hinttext.setHideTextViewTextColor(mCurrentColorLight);
                break;
            case 3:
                mCurrentColorLight = Color.RED;
                mUIColorLight.setBackgroundColor(mCurrentColorLight);
                mHideTextView_UIColorLight_Hinttext.setHideTextViewTextColor(mCurrentColorLight);
                break;
            default:
                break;
        }
        if (mChangeColor == 3) {
            mChangeColor = 0;
        }else{
            mChangeColor++;
        }

    }


}
