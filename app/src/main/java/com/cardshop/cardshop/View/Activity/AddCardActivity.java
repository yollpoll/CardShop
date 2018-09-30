package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.AddCardContract;
import com.cardshop.cardshop.PresenterImpl.AddCardPresenterImpl;
import com.cardshop.cardshop.View.Fragment.AddCardFragment;

public class AddCardActivity extends SimpleActivity {

    public static void gotoAddCardActivity(Context context) {
        Intent intent = new Intent(context, AddCardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        new AddCardPresenterImpl((AddCardContract.IView) loadBaseFragment(AddCardFragment.newInstance()));
    }
}
