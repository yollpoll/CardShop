package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.cardshop.cardshop.Base.BaseActivity;
import com.cardshop.cardshop.Module.GoodsModule;
import com.cardshop.cardshop.PresenterImpl.GoodsDetailPresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.GoodsDetailFragment;

public class GoodsDetailActivity extends BaseActivity {
    private GoodsModule goodsModule;

    public static void gotoGoodsDetailActivity(Context context, GoodsModule goodsModule) {
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        intent.putExtra("goods", goodsModule);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        goodsModule = (GoodsModule) getIntent().getSerializableExtra("goods");
        FragmentManager fragmentManager = getSupportFragmentManager();
        GoodsDetailFragment fragment = (GoodsDetailFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (fragment == null) {
            fragment = (GoodsDetailFragment) GoodsDetailFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.rl_root, fragment, FRAGMENT_TAG).commit();
        }
        //初始化presenter
        new GoodsDetailPresenterImpl(fragment,goodsModule);
    }
}
