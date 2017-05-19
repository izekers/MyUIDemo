package com.zekers.myuidemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.File;

import bingo.com.weextest.cammera.CameraControl;

/**
 * Created by Zoker on 2017/5/19.
 */

public class PictureActivity extends Activity{
    private static final String TAG = PictureActivity.class.getSimpleName();
    private static final int takePic = 384;
    private static final int selectPic = 522;
    private static final int cropPic = 222;
    Bitmap bitmap;
    File file;

    String paths=null;
    ImageView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        show=(ImageView) findViewById(R.id.show);
    }

    public void openCamera(View view){
        paths=System.currentTimeMillis()+".jpg";
        CameraControl.openCamera(this,paths,takePic);
    }

    public void openPick(View view){
        CameraControl.openPick(this,selectPic);
    }

    public void openCrop(View view){
        if (file==null)
            return;
        CameraControl.openCROP(this,file,cropPic);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri=null;
        String path=null;
        Log.e(TAG,"#onActivityResult data="+new Gson().toJson(data));
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case takePic:
                    if (data!=null){
                        uri=data.getData();
                        path=CameraControl.getAbsolutePath(this,uri);
                        Log.e(TAG,"#takePic="+path);
                        file=new File(path);
                        show.setImageBitmap(CameraControl.getPicture(file));
                    }else {
                        file=CameraControl.getCacheFile(this,paths);
                        Log.e(TAG,"#takePic="+file.getAbsolutePath());
                        show.setImageBitmap(CameraControl.getPicture(file));
                    }
                    break;
                case cropPic:
                    Bundle extras=data.getExtras();
                    Bitmap photo = extras.getParcelable("data");
                    show.setImageBitmap(photo);
//                    if (data!=null){
//                        uri=data.getData();
//                        path=CameraControl.getAbsolutePath(this,uri);
//                        Log.e(TAG,"#cropPic="+path);
//                        file=new File(path);
//                        show.setImageBitmap(CameraControl.getPicture(file));
//                    }else {
//                        file=new File(file.getAbsolutePath());
//                        Log.e(TAG,"#cropPic="+path);
//                        show.setImageBitmap(CameraControl.getPicture(file));
//                    }
                    break;
                case selectPic:
                    uri=data.getData();
                    path=CameraControl.getAbsolutePath(this,uri);
                    Log.e(TAG,"#cropPic="+path);
                    file=new File(path);
                    show.setImageBitmap(CameraControl.getPicture(file));
                    break;
            }
        }
    }
}
