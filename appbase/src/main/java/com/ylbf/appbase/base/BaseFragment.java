package com.ylbf.appbase.base;

import androidx.fragment.app.Fragment;
import android.widget.Toast;

/**
 * Created by ylbf_ on 2016/4/20.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    // 将代理类通用行为抽出来
    protected T mPresenter;

    /**
     * 继承BaseView抽出显示信息通用行为
     *
     * @param msg
     */
    @Override
    public void toast(String msg) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), msg + "", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
