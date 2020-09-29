package com.ylbf.appbase.base;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Activity 基类
 * Created by ylbf_ on 2016/4/11.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    /**
     * 将代理类通用行为抽出来
     */
    protected T mPresenter;

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    /**
     * 初始化View
     */
    protected abstract void initView();

    public void showActivity(Activity acitvity, Intent intent) {
        acitvity.startActivity(intent);
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(BaseActivity.this, msg + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
    }

}
