package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.ApplyGetGoodsContact;
import com.cardshop.cardshop.Module.OrderYigouModule;
import com.cardshop.cardshop.PresenterImpl.ApplyGetGoodsPresenterImpl;
import com.cardshop.cardshop.View.Fragment.ApplyGetGoodsFragment;

public class ApplyGetGoodsActivity extends SimpleActivity {
    private OrderYigouModule orderYigouModule;
    private ApplyGetGoodsFragment mFragment;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFragment.onReturnResult(requestCode, resultCode, data);
    }

    public static void gotoApplyGetGoodsActivity(Context context, OrderYigouModule orderYigouModule) {
        Intent intent = new Intent(context, ApplyGetGoodsActivity.class);
        intent.putExtra("orderYigouModule", orderYigouModule);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        mFragment = ApplyGetGoodsFragment.newInstance();
        orderYigouModule = (OrderYigouModule) getIntent().getSerializableExtra("orderYigouModule");
        new ApplyGetGoodsPresenterImpl((ApplyGetGoodsContact.IView) loadBaseFragment(mFragment), orderYigouModule);
    }
}
