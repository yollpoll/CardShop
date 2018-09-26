package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cardshop.cardshop.Base.BaseActivity;
import com.cardshop.cardshop.Contract.PersonalContract;
import com.cardshop.cardshop.PresenterImpl.PersonalMsgContractImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.PersonalMsgFragment;

public class PersonalMsgActivity extends BaseActivity {
    public static void gotoPersonalMsgActivity(Context context) {
        Intent intent = new Intent(context, PersonalMsgActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initData();
    }

    @Override
    protected void initData() {
        super.initData();
        new PersonalMsgContractImpl((PersonalContract.IView) loadBaseFragment(PersonalMsgFragment.newInstance()));
    }
}
