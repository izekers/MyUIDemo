package com.zekers.myuidemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zekers.pjutils.BaseApplication;
import com.zekers.ui.utils.DensityUtils;
import com.zekers.ui.utils.UIImageLoader;
import com.zekers.utils.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by Zoker on 2017/3/1.
 */

public class IconActivity extends Activity {
    ImageView img;
    private final int scan_code = 0x123;
    private final int Imagec = 0x124;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon);
        ThemeConfig config=new ThemeConfig.Builder()
                .setTitleBarTextColor(Color.WHITE)
                .setTitleBarBgColor(Color.BLUE)
                .build();
        FunctionConfig functionConfig=new FunctionConfig.Builder()
                .build();
        //配置imageloader
        ImageLoader imageloader = new UIImageLoader();
        //设置核心配置信息
        CoreConfig coreConfig = new CoreConfig.Builder(BaseApplication.Instance, imageloader, ThemeConfig.DEFAULT)
                .setDebug(BuildConfig.DEBUG)
                .setFunctionConfig(functionConfig)
        .build();
        GalleryFinal.init(coreConfig);

        ZXingLibrary.initDisplayOpinion(BaseApplication.Instance);
        img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryFinal.openGallerySingle(Imagec, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        Glide.with(IconActivity.this).load(resultList.get(0).getPhotoPath()).into(img);
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
            }
        });
    }

    public void onClick(View view) {
//        Intent intent = new Intent(this, com.google.zxing.CaptureActivity.class);
//        intent.setAction(Intents.Scan.ACTION);
//        int size = DensityUtils.dp2px(this, 200);
//        intent.putExtra(Intents.Scan.HEIGHT, size);
//        intent.putExtra(Intents.Scan.WIDTH, size);
//        startActivityForResult(intent, scan_code);
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, scan_code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == scan_code && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString(CodeUtils.RESULT_STRING);
            Logger.d("scan", "#ScanCode result=" + result);
            if (TextUtils.isEmpty(result)) {
                Toast.makeText(this, "抱歉,什么都没扫描到!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
