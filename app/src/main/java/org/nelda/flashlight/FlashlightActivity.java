package org.nelda.flashlight;

import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Administrator on 2015/7/4 0004.
 */
public class FlashlightActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageView.setTag(false);
    }

    public void onClick_Flashlight(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            Toast.makeText(this,"该设备没有摄像头",Toast.LENGTH_SHORT).show();
            return;
        }else if(!(Boolean)mImageView.getTag()){
            openFlashlight();
        }else {
            closeFlashlight();
        }
    }

    protected void openFlashlight(){
        TransitionDrawable transition =(TransitionDrawable)mImageView.getDrawable();
        transition.startTransition(200);
        mImageView.setTag(true);

        try {
            mCamera = Camera.open();
            //纹理
            int texttureId = 0;
            mCamera.setPreviewTexture(new SurfaceTexture(texttureId));
            mCamera.startPreview();
            //打开摄像头
            mParameters =mCamera.getParameters();
            mParameters.setFlashMode(mParameters.FLASH_MODE_TORCH);
            mCamera.setParameters(mParameters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void closeFlashlight(){
        TransitionDrawable transition = (TransitionDrawable)mImageView.getDrawable();
        if((Boolean)mImageView.getTag()) {
            transition.reverseTransition(200);
            mImageView.setTag(false);
            if(mCamera != null){
                mParameters.setFlashMode(mParameters.FLASH_MODE_OFF);
                mCamera.setParameters(mParameters);
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null ;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
            if(mCamera != null){
                mParameters.setFlashMode(mParameters.FLASH_MODE_OFF);
                mCamera.setParameters(mParameters);
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null ;
            }
    }
}
