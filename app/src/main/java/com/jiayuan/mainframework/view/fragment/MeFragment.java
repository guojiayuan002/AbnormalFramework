package com.jiayuan.mainframework.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.jiayuan.mainframework.R;
import com.jiayuan.mainframework.otherbase.BaseFragment2;
import com.jiayuan.mainframework.presenter.impl.HomePresenterIpml;
import com.jiayuan.mainframework.view.activity.CreateViewActivity;
import com.jiayuan.mainframework.view.activity.LoginActivity;
import com.jiayuan.mainframework.view.activity.PhoneRegistActivity;
import com.jiayuan.mainframework.view.activity.RecycleViewActivity;
import com.jiayuan.mainframework.view.activity.StoreInfoActivity;
import com.jiayuan.mainframework.view.viewinterface.HomeFragmentView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jiayuan.mainframework.base.BaseActivity.REQUEST_SCAN_CODE;

/**
 * Created by guojiayuan on 2017/8/22.
 */
public class MeFragment extends BaseFragment2<HomeFragmentView,HomePresenterIpml> {

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
        View view = View.inflate(mContext, R.layout.fragent_me, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick({R.id.btn_login, R.id.btn_regist, R.id.btn_go_store,
            R.id.btn_scan, R.id.btn_create,R.id.btn_RecycleView})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_login:
                intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_regist:
                intent = new Intent(mContext, PhoneRegistActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_go_store:
                intent = new Intent(mContext, StoreInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_scan:
                intent = new Intent(mContext, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_SCAN_CODE);
                break;
            case R.id.btn_create:
                intent = new Intent(mContext, CreateViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_RecycleView:
                intent = new Intent(mContext, RecycleViewActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_SCAN_CODE:
                //处理扫描结果（在界面上显示）
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(mContext, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
