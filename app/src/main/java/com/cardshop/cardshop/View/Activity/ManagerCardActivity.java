package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.ManagerCardContract;
import com.cardshop.cardshop.Module.CardModule;
import com.cardshop.cardshop.PresenterImpl.ManagerCardPresenterImpl;
import com.cardshop.cardshop.View.Fragment.ManagerCardFragment;

public class ManagerCardActivity extends SimpleActivity {
    private CardModule cardModule;

    public static void gotoManagerActivity(Context context, CardModule cardModule) {
        Intent intent = new Intent(context, ManagerCardActivity.class);
        intent.putExtra("card", cardModule);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        initCard();
        new ManagerCardPresenterImpl((ManagerCardContract.IView) loadBaseFragment(ManagerCardFragment.newInstance())
                , cardModule);
    }

    private void initCard() {
        cardModule = (CardModule) getIntent().getSerializableExtra("card");
    }
}
