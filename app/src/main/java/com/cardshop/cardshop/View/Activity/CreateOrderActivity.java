package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.CreateOrderContact;
import com.cardshop.cardshop.Module.GoodsModule;
import com.cardshop.cardshop.PresenterImpl.CreateOrderPresenterImpl;
import com.cardshop.cardshop.View.Fragment.CreateOrderFragment;

public class CreateOrderActivity extends SimpleActivity {
    private GoodsModule goodsModule;

    public static void gotoCreateOrderActivity(Context context, GoodsModule goodsModule) {
        Intent intent = new Intent(context, CreateOrderActivity.class);
        intent.putExtra("goods", goodsModule);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        goodsModule = (GoodsModule) getIntent().getSerializableExtra("goods");
        new CreateOrderPresenterImpl((CreateOrderContact.IView) loadBaseFragment(CreateOrderFragment.newInstance()), goodsModule);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
