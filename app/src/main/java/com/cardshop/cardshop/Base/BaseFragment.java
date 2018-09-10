package com.cardshop.cardshop.Base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.cardshop.cardshop.R;
import com.gyf.barlibrary.ImmersionBar;

public abstract class BaseFragment<V, P extends BasePresenter<V>>
        extends Fragment implements View.OnClickListener {

    protected ImmersionBar mImmersionBar;
    protected P mPresenter;
    protected View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mImmersionBar = ImmersionBar.with(this);
        mPresenter = createPresenter();
        mPresenter.attach((V) this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
        initView(view);
        initData();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
        if (mImmersionBar != null)
            mImmersionBar.destroy();

    }

    protected void initData() {
    }

    protected void initView(View view) {
    }

    protected void setTitle(String content) {
        if (null == rootView)
            return;
        TextView tvTitle = rootView.findViewById(R.id.tv_title);
        if (null == tvTitle)
            return;
        tvTitle.setText(content);
    }

    /**
     * 注意，这个方法并不是返回实际statusview
     * 而是返回title中一个高度为0的控件
     */
    protected View getStatusView() {
        if (null == rootView)
            return null;
        return rootView.findViewById(R.id.view_status);
    }

    protected void setNoStatusBar() {
        mImmersionBar
                .transparentBar()
                .fitsSystemWindows(false)
                .init();
    }

    public abstract P createPresenter();

//    protected void showSnackerToast(String content) {
//        if (null == rootView)
//            return;
//        ToastUtils.SnackerShowShort(rootView, content);
//    }
//
//    protected void showToast(String content) {
//        ToastUtils.showShort(content);
//    }
}
