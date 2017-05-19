package com.zekers.myuidemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/12/30.
 */
public class DBActivity extends Activity{
//    private List<People> peoples=new ArrayList<>();
//    private TextView textView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_db);
//        textView=(TextView) findViewById(R.id.db_show);
//    }
//
//    public void add(View view){
//        People people=new People("张三");
//        people.setPhone("13751876401");
//        people.save();
//        peoples.add(people);
//        show(peoples);
//    }
//
//    public void delete(View view){
//        if (peoples.size()>1)
//            peoples.get(peoples.size()-1).delete();
//        show(peoples);
//    }
//
//    public void update(View view){
//        if (peoples.size()>1){
//            People people=peoples.get(peoples.size()-1);
//            people.setName("李四");
//            people.update();
//        }
//        show(peoples);
//    }
//
//    public void quary(View view){
//        peoples=new Select().from(People.class).queryList();
//        if (peoples==null)
//            peoples=new ArrayList<>();
//        show(peoples);
//    }
//
//    public void show(List<People> peoples){
//        String peo="";
//        for (People bean:peoples){
//            peo=peo+new Gson().toJson(bean)+"\n";
//        }
//        textView.setText(peo);
//    }
}
