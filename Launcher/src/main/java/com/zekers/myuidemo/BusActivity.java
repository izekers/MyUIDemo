package com.zekers.myuidemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.functions.Action1;

/**
 *
 * Created by Administrator on 2017/1/22.
 */
public class BusActivity extends Activity{
    TextView text=(TextView) findViewById(R.id.text);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        File file=new File("");
        RequestBody requestFile=RequestBody.create(MediaType.parse("multipart/form-data"),file);

    }

}
