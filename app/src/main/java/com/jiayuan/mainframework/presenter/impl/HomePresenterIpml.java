package com.jiayuan.mainframework.presenter.impl;

import com.jiayuan.mainframework.bean.BannerBean;
import com.jiayuan.mainframework.network.BaseRetrofit;
import com.jiayuan.mainframework.network.Constance;
import com.jiayuan.mainframework.otherbase.Base2Presenter;
import com.jiayuan.mainframework.view.fragment.HomeFragment;
import com.jiayuan.mainframework.view.viewinterface.HomeFragmentView;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by guojiayuan on 2017/8/22.
 */

public class HomePresenterIpml extends Base2Presenter<HomeFragmentView> {


    private HomeFragment mHomeFragment;
    public HomePresenterIpml(HomeFragment fragment2) {
        super(null, fragment2);
        mHomeFragment =  fragment2;
    }

    public void getBanner() {
        BaseRetrofit.getInstance().getApi().getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bannerBean -> {
                    String code = bannerBean.getErrorCode();

                    if (Constance.SUCCESS.equals(code)) {
                        List<BannerBean.DataBean.ResultListBean> list = bannerBean.getData().getResultList();
                        List<String> images = new ArrayList<String>();
                        List<String> links = new ArrayList<String>();
                        for (BannerBean.DataBean.ResultListBean bean : list) {
                            images.add(bean.getImgUrl());
                            links.add(bean.getLink());
                        }
                        mHomeFragment.showBanner(images,links);

                        System.out.println("==== 轮播图成功");
                    } else if (Constance.FAIL.equals(code)) {
                        System.out.println("轮播图失败0");
                    } else {
                        System.out.println("错误！轮播图状态码为code ==== " + code);
                    }
                }, this::showError);
    }
}
