package com.databing.view;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.databing.model.User;
import com.example.dagger2demo.R;
import com.example.dagger2demo.databinding.Databing1Binding;

/**
 *
 * Created by zeker on 2017/2/20.
 */
public class DatabingDemo1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Databing1Binding binding = DataBindingUtil.setContentView(this, R.layout.databing_1);

        User user = new User("fei", "Liang");
//        binding.setUser(user);
//        binding.setEmail("1102412457@qq.com");
//        binding.setAge(15);

        com.databing.utils.User user1=new com.databing.utils.User(null,"zeker");
//        binding.setUtilsUser(user1);

        user.setShow(true);
        user.setFirstName("zhang");
        user.setLastName("san");

//        binding.setLarge(true);

        binding.withId.setText("通过带ID的WITH直接产生VIEW进行操作");
    }
}
