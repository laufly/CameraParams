package com.wilddog.cameraparams;

import android.hardware.Camera;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
     public static final String TAG = "Camera Params";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCameraParams();
    }
    private void getCameraParams(){
        Camera camera;
        int cameraCount = Camera.getNumberOfCameras();
        Log.e(TAG, Build.MODEL+"cameraCount:"+cameraCount);
        for(int i =0;i<cameraCount;i++){
            try{
            camera = Camera.open(i);
            camera.lock();
            Camera.Parameters parameters = camera.getParameters();
                // 用的这个
            List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
            for(Camera.Size size:supportedPreviewSizes){
                Log.e(TAG,"camera "+i+" PreviewSizes height*width:"+size.height+"x"+size.width);}
            List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
            for(Camera.Size size:supportedPictureSizes){
                Log.e(TAG,"camera "+i+"PictureSizes height*width:"+size.height+"x"+size.width);}
               List<Integer> rates = parameters.getSupportedPreviewFrameRates();
                for(Integer rate:rates){
                    Log.e(TAG,"camera "+i+"getSupportedPreviewFrameRates rate:"+rate);}
                List<int[]> previewFpsRanges = parameters.getSupportedPreviewFpsRange();
                for(int[] previewFpsRange:previewFpsRanges){
                    Log.e(TAG,"camera "+i+"previewFpsRange length:"+previewFpsRange.length);
                    for(int j:previewFpsRange){
                    Log.e(TAG,"camera "+i+"getSupportedPreviewFpsRange rate:"+j);}}
            camera.unlock();
            camera.release();
            camera=null;}
            catch (Exception e){
                // 部分手机会出错
                Log.e(TAG,"camera"+i+"can't get camera");
            }
        }
    }
}
