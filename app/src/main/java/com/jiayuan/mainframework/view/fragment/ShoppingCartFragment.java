package com.jiayuan.mainframework.view.fragment;

import android.view.View;

import com.jiayuan.mainframework.R;
import com.jiayuan.mainframework.otherbase.BaseFragment2;
import com.jiayuan.mainframework.presenter.impl.HomePresenterIpml;
import com.jiayuan.mainframework.view.viewinterface.HomeFragmentView;

/**
 * Created by guojiayuan on 2017/8/22.
 */
public class ShoppingCartFragment extends BaseFragment2<HomeFragmentView,HomePresenterIpml> {
    @Override
    protected HomePresenterIpml createPresenter() {
        return null;
    }

    @Override
    public void startloadData() {
        onLoadDataSuccess();
    }

    @Override
    public View onCreateContentView() {
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart, null);

        return view;
    }
}
