package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cardshop.cardshop.Base.BaseActivity;
import com.cardshop.cardshop.Contract.PersonalContract;
import com.cardshop.cardshop.PresenterImpl.PersonalMsgContractImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.PersonalMsgFragment;

public class PersonalMsgActivity extends BaseActivity {
    private PersonalMsgFragment mFragment;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public static void gotoPersonalMsgActivity(Context context) {
        Intent intent = new Intent(context, PersonalMsgActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFragment.onReturnResult(requestCode, resultCode, data);
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
        mFragment = PersonalMsgFragment.newInstance();
        new PersonalMsgContractImpl((PersonalContract.IView) loadBaseFragment(mFragment));
    }
}
