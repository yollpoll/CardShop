package com.cardshop.cardshop.PresenterImpl;

import android.content.Context;

import com.cardshop.cardshop.Contract.ProtocolContract;
import com.cardshop.cardshop.Utils.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

public class ProtocolPresenterImpl extends ProtocolContract.IPresenter {
    private ProtocolContract.IView mView;
    private Context context;
    private String title;
    private int id;

    public ProtocolPresenterImpl(ProtocolContract.IView mView, String title, int id) {
        this.mView = mView;
        this.title = title;
        this.id = id;
        mView.setPresenter(this);
    }

    @Override
    public void start(Context context) {
        super.start();
        this.context = context;
        loadProtocol();
        mView.setTitle(title);
    }

    private void loadProtocol() {
        InputStream raw = context.getResources().openRawResource(id);
        try {
            mView.initProtocol(StreamUtils.readStreamToString(raw));
        } catch (IOException e) {
            e.printStackTrace();
            mView.showSnackerToast("加载协议出错");
        }
    }
}
