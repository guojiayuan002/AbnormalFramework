package com.jiayuan.mainframework.utils;


import android.os.Bundle;

import com.jiayuan.mainframework.otherbase.BaseFragment2;
import com.jiayuan.mainframework.view.fragment.HomeFragment;
import com.jiayuan.mainframework.view.fragment.MeFragment;
import com.jiayuan.mainframework.view.fragment.MessageFragment;
import com.jiayuan.mainframework.view.fragment.ShoppingCartFragment;

public class FragmentFactory {

    private static HomeFragment sHomeFragment;
    private static MessageFragment sMessageFragment;
    private static ShoppingCartFragment sShoppingCartFragment;
    private static MeFragment sMeFragment;

    public static BaseFragment2 getFragment(int position){
        BaseFragment2 baseFragment = null;
        switch (position) {
            case 0:
                if (sHomeFragment==null){
                    sHomeFragment = new HomeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("args", "我是界面0" );
                    sHomeFragment.setArguments(bundle);
                }
                baseFragment = sHomeFragment;
                break;
            case 1:
                if (sMessageFragment==null){
                    sMessageFragment = new MessageFragment();
                }
                baseFragment = sMessageFragment;
                break;
            case 2:
                if (sShoppingCartFragment==null){
                    sShoppingCartFragment = new ShoppingCartFragment();
                }
                baseFragment = sShoppingCartFragment;
                break;
            case 3:
                if (sMeFragment==null){
                    sMeFragment = new MeFragment();
                }
                baseFragment = sMeFragment;
                break;
        }
        return baseFragment;

    }

}
