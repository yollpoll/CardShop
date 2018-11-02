package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;

import com.cardshop.cardshop.Base.SimpleActivity;
import com.cardshop.cardshop.Contract.ProtocolContract;
import com.cardshop.cardshop.PresenterImpl.ProtocolPresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.ProtocolFragment;

public class ProtocolActivity extends SimpleActivity {
    private String title;
    private int id;

    public static void gotoProtocolActivity(Context context, String title, int id) {
        Intent intent = new Intent(context, ProtocolActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        title = getIntent().getStringExtra("title");
        id = getIntent().getIntExtra("id", R.raw.service_protocol);
        new ProtocolPresenterImpl((ProtocolContract.IView) loadBaseFragment(ProtocolFragment.newInstance())
                , title, id);
    }
}
