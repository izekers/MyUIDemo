package com.zekers.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.zekers.myuidemo.PayInterface;

/**
 * Created by Administrator on 2016/12/27.
 */

public class PayService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PayPangzi();
    }

    class PayPangzi extends PayInterface.Stub{
        @Override
        public void pay() throws RemoteException {
            PayService.this.pay();
        }
    }

    public void pay() {//虚假的支付功能，以log方式模拟
        System.out.println("加测运行环境");
        System.out.println("加密用户名和密码");
        System.out.println("建立连接");
        System.out.println("完成支付");
    }
}
