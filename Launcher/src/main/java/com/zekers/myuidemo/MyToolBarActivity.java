package com.zekers.myuidemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.zekers.ui.view.curtain.CurtainHeaderView;
import com.zekers.ui.view.widget.AbilityToolItem;
import com.zekers.ui.view.widget.AbilityToolMenu;
import com.zekers.ui.view.widget.MyToolBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoker on 2017/5/5.
 */

public class MyToolBarActivity extends Activity{
    MyToolBar myToolBar,myToolBar2;
    CurtainHeaderView mCurtainHeaderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytoolbar);
        myToolBar=(MyToolBar)findViewById(R.id.mytool_bar);
        myToolBar.setTitle("测试");
        myToolBar.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyToolBarActivity.this, "点击了Back按钮", Toast.LENGTH_SHORT).show();
            }
        });
        myToolBar.setMenuRes(R.menu.base_menu);
        myToolBar.setOnMenuClickListener(new MyToolBar.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(View view) {
                int id=view.getId();
                Toast.makeText(MyToolBarActivity.this,"id:="+id,Toast.LENGTH_SHORT).show();
            }
        });

        AbilityToolMenu menu=new AbilityToolMenu(this);
        menu.setMenuRes(R.menu.base_menu);
        menu.setIcon(R.drawable.arrow_down);
        menu.setOnMenuItemSelectListener(new AbilityToolMenu.OnMenuItemSelectListener() {
            @Override
            public void onMenuItemClick(View view, int id) {
                if (id==R.id.user_back){
                    Toast.makeText(MyToolBarActivity.this,"点击了user_back",Toast.LENGTH_SHORT).show();
                }else if (id==R.id.user_setting){
                    Toast.makeText(MyToolBarActivity.this,"点击了user_setting",Toast.LENGTH_SHORT).show();
                }
            }
        });

        myToolBar2=(MyToolBar)findViewById(R.id.mytool_bar2);
        myToolBar2.setRightSecondItem(new AbilityToolItem.Config().setView(R.layout.mytool_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyToolBarActivity.this,"点击了自定义",Toast.LENGTH_SHORT).show();
            }
        }));
        myToolBar2.setRightFirstItem(new AbilityToolItem.Config().setView(menu));

        myToolBar2.setTitle("测试");
        myToolBar2.setBackText("返回");
//        myToolBar2.setCenterItem(new AbilityToolItem.Config().setView(R.layout.toolbar_text));
        myToolBar2.setCenterItem(new AbilityToolItem.Config().setView(R.layout.curtain_layout_view));
        List<String> menus = new ArrayList<>();
        menus.add("微课");
        menus.add("培训");
        mCurtainHeaderView = (CurtainHeaderView) myToolBar2.getCenterItem().findViewById(R.id.menus);
        mCurtainHeaderView.setData(menus);
        mCurtainHeaderView.mTextView.setVisibility(View.VISIBLE);
        mCurtainHeaderView.mTextView.setText("测试");
        myToolBar2.setModel(MyToolBar.MODEL_DOUBLE_MENU);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
