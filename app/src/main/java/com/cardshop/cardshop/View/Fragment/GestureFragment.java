package com.cardshop.cardshop.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.GestureContract;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Widget.GesturePasswodLayout;
import com.cardshop.cardshop.Widget.GesturePasswordView;

import java.util.List;

public class GestureFragment extends BaseFragment implements GestureContract.IView, GesturePasswodLayout.OnGesturePosswordChangeListener {
    private GestureContract.IPresenter presenter;

    private TextView tvTip;
    private GesturePasswodLayout gesturePasswodLayout;

    public static GestureFragment newInstance() {
        return new GestureFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(GestureContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gesture, container, false);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
        setTitle("设置手势密码");
        showBack();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvTip = view.findViewById(R.id.tv_tips);
        gesturePasswodLayout = view.findViewById(R.id.gesture_password);

        gesturePasswodLayout.setOnGesturePosswordChangeListener(this);
    }

    @Override
    public void onCheck(List<GesturePasswordView> listPassword) {
        presenter.onCheckPsW(listPassword);
    }

    @Override
    public void setTip(String content) {
        tvTip.setText(content);
    }

    @Override
    public void onCheckResult(boolean result) {
        gesturePasswodLayout.onCheckEvent(true);
    }
}
