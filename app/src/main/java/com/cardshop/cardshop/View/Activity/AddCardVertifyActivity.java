package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.AddCardVertifyContract;
import com.cardshop.cardshop.Module.AddCardModule;
import com.cardshop.cardshop.PresenterImpl.AddCardVertifyPresenterImpl;
import com.cardshop.cardshop.View.Fragment.AddCardVertifyFragment;

public class AddCardVertifyActivity extends SimpleActivity {
    private String name, code, identity, phone, bank;

    public static void gotoAddCardVertifyActivity(Context context, String name, String code,
                                                  String identity, String phone, String bank) {
        Intent intent = new Intent(context, AddCardVertifyActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("code", code);
        intent.putExtra("identity", identity);
        intent.putExtra("phone", phone);
        intent.putExtra("bank", bank);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        name = getIntent().getStringExtra("name");
        code = getIntent().getStringExtra("code");
        identity = getIntent().getStringExtra("identity");
        phone = getIntent().getStringExtra("phone");
        bank = getIntent().getStringExtra("bank");
        AddCardModule addCardModulen = new AddCardModule(name, code, identity, phone, bank);

        new AddCardVertifyPresenterImpl((AddCardVertifyContract.IView) loadBaseFragment(AddCardVertifyFragment.newInstance())
                , addCardModulen);
    }
}
