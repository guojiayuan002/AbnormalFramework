package com.jiayuan.mainframework.view.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jiayuan.mainframework.R;
import com.jiayuan.mainframework.adapter.HomeRVAdapter;
import com.jiayuan.mainframework.otherbase.BaseFragment2;
import com.jiayuan.mainframework.presenter.impl.HomePresenterIpml;
import com.jiayuan.mainframework.utils.ToastUtils;
import com.jiayuan.mainframework.view.viewinterface.HomeFragmentView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

import static com.jiayuan.mainframework.base.BaseActivity.REQUEST_SCAN_CODE;

/**
 * Created by guojiayuan on 2017/8/22.
 */
public class HomeFragment extends BaseFragment2<HomeFragmentView, HomePresenterIpml> implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecycleView;
    private List<String> list = new ArrayList<>();
    private HomeRVAdapter mHomeRVAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected HomePresenterIpml createPresenter() {
        return new HomePresenterIpml( this);
    }

    @Override
    public void startloadData() {
        mPresenter.getBanner();

    }

    @Override
    public View onCreateContentView() {
        View view = View.inflate(mContext, R.layout.fragent_home, null);
        mRecycleView = (RecyclerView) view.findViewById(R.id.rv_recycleView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        initData();
        return view;
    }

    private void initData() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.font_red_color);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        for (int i = 0; i < 100; i++) {
            list.add("我是条目" + i);
        }

        mRecycleView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 300);//300
        mRecycleView.setLayoutManager(layoutManager);

        layoutManager.setSpanSizeLookup(mHomeRVAdapter.getSpanSizeLookup());
        mRecycleView.setAdapter(mHomeRVAdapter);

    }

    View.OnClickListener mOnAdapterClickListener = new View.OnClickListener() {
        @Override
        @OnClick({R.id.home_btn_scan2x, R.id.home_btn_promo_code2x, R.id.home_btn_announcement2x, R.id.home_btn_all2x})
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.home_btn_scan2x://扫一扫
                    intent = new Intent(mContext, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_SCAN_CODE);
                    break;
                case R.id.home_btn_promo_code2x://推广码
                    ToastUtils.showToast("在我的界面生成二维码");
                    break;
                case R.id.home_btn_announcement2x:
                    ToastUtils.showToast("点击了公告");
                    break;
                case R.id.home_btn_all2x:
                    ToastUtils.showToast("点击了全部");
                    break;
            }
        }
    };

    //下拉刷新
    @Override
    public void onRefresh() {
//        mSwipeRefreshLayout.setRefreshing(false);//隐藏掉
        // 延时1s关闭下拉刷新
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        }, 1000);
    }


    public void showBanner(List<String> images, List<String> links) {
        mHomeRVAdapter = new HomeRVAdapter(mContext, list, mOnAdapterClickListener);
        mHomeRVAdapter.setBannerData(images, links);
        //多个异步请求同时发出时候，只能隐藏一个
        int visibility = mProgressBar.getVisibility();
        if (visibility == View.VISIBLE){
            onLoadDataSuccess();
        }
    }
}
