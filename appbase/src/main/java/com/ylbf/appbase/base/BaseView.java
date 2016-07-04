package com.ylbf.appbase.base;

/**
 * 视图基类
 * Created by ylbf_ on 2016/4/5.
 */
public interface BaseView {

    /**
     * 弹出Tost消息
     *
     * @param msg
     */
    void toast(String msg);

    /**
     * 显示进度条
     */
    void showProgress();

    /**
     * 隐藏进度条
     */
    void hideProgress();
}
