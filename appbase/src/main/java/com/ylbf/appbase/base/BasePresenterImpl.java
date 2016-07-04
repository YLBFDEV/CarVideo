package com.ylbf.appbase.base;

import com.socks.library.KLog;
import com.ylbf.appbase.base.callback.RequestCallback;

import rx.Subscription;

/**
 * 代理基类实现
 * Created by ylbf_ on 2016/4/5.
 */
public class BasePresenterImpl<T extends BaseView, V> implements BasePresenter, RequestCallback<V> {

    protected Subscription mSubscription;
    protected T mView;

    public BasePresenterImpl(T mView) {
        this.mView = mView;
    }

    /**
     * 在Activity中调用onResume
     */
    @Override
    public void onResume() {
    }

    /**
     * 在Activity中调用onDestroy
     * 停止请求销毁View
     */
    @Override
    public void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mView = null;
    }

    /**
     * 请求之前调用
     */
    @Override
    public void beforeRequest() {
        mView.showProgress();
    }

    /**
     * 请求错误调用
     *
     * @param msg 错误信息
     */
    @Override
    public void requestError(String msg) {
        KLog.e(msg);
        mView.toast(msg);
        mView.hideProgress();
    }

    /**
     * 请求完成调用
     */
    @Override
    public void requestComplete() {
        mView.hideProgress();
    }

    /**
     * 请求成功调用
     *
     * @param data 数据
     */
    @Override
    public void requestSuccess(V data) {

    }
}
