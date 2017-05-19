package zoker.demo.music.control;

import zoker.demo.music.view.BaseView;

/**
 * MVP框架基础控制者
 * Created by Zoker on 2017/2/23.
 */

public interface BasePresenter<T extends BaseView> {
    //与类建立连接
    void attachView(T view);
    //Rx相关
    void subscribe();

    void unsubscribe();
}
