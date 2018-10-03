package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.AddCardContract;
import com.cardshop.cardshop.PresenterImpl.AddCardPresenterImpl;
import com.cardshop.cardshop.View.Fragment.AddCardFragment;

public class AddCardActivity extends SimpleActivity {

    private AddCardFragment mFragment;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFragment.onReturnResult(requestCode, resultCode, data);
    }

    public static void gotoAddCardActivity(Context context) {
        Intent intent = new Intent(context, AddCardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        mFragment = AddCardFragment.newInstance();
        new AddCardPresenterImpl((AddCardContract.IView) loadBaseFragment(mFragment));
    }
}
