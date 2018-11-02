package com.cardshop.cardshop.Base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cardshop.cardshop.R;
import com.cardshop.framework.Utils.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;

public abstract class BaseFragment<V, P extends BasePresenter<V>>
        extends Fragment implements View.OnClickListener {

    protected ImmersionBar mImmersionBar;
    protected P mPresenter;
    protected View rootView;
    protected ProgressDialog mProgressDialog;
    protected ProgressBar mProgressBar;
    protected View mNoData;
    protected View mError;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mImmersionBar = ((BaseActivity) getActivity()).getmImmersionBar();
        initPresenter();
    }

//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        if (null != mPresenter) {
//            outState.putSerializable("presenter", mPresenter);
//        }
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        if (null != savedInstanceState) {
//            P savePresenter = (P) savedInstanceState.getSerializable("presenter");
//            if (null != savePresenter) {
//                mPresenter = savePresenter;
//            }
//        }
//        this.mImmersionBar = ((BaseActivity) getActivity()).getmImmersionBar();
        rootView = view;
        initView(view);
        initData();
    }

    private void initPresenter() {
        mPresenter = createPresenter();
        if (null == mPresenter)
            return;
        mPresenter.attach((V) this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head_left:
                goBack();
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
        mPresenter.stop();
//        if (mImmersionBar != null)
//            mImmersionBar.destroy();

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    protected void initData() {
        mPresenter.start();
        mPresenter.start(getActivity());
    }

    protected void initView(View view) {
    }

    public void setTitle(String content) {
        if (null == rootView)
            return;
        TextView tvTitle = rootView.findViewById(R.id.tv_title);
        if (null == tvTitle)
            return;
        tvTitle.setText(content);
    }

    public void setHeadRightIv(int id, View.OnClickListener onClickListener) {
        if (null == rootView)
            return;
        ImageView ivHead = rootView.findViewById(R.id.iv_head_right);
        ivHead.setVisibility(View.VISIBLE);
        ivHead.setImageResource(id);
        ivHead.setOnClickListener(onClickListener);
    }

    public void setHeadRightText(String content, View.OnClickListener onClickListener) {
        if (null == rootView)
            return;
        TextView tvHeadRight = rootView.findViewById(R.id.tv_head_right);
        tvHeadRight.setOnClickListener(onClickListener);
        tvHeadRight.setText(content);
        tvHeadRight.setVisibility(View.VISIBLE);
    }


    public void setNoStatusBar() {
        mImmersionBar
                .transparentBar()
                .fitsSystemWindows(false)
                .navigationBarEnable(false)
                .init();
    }

    public abstract P createPresenter();

    public void showSnackerToast(String content) {
        if (null == rootView)
            return;
        ToastUtils.SnackerShowShort(rootView, content);
    }

    public void showToast(String content) {
        ToastUtils.showShort(content);
    }

    public void showBack() {
        if (null == rootView)
            return;
        ImageView ivBack = rootView.findViewById(R.id.iv_head_left);
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(this);
    }

    public void goBack() {
        if (null != getActivity())
            getActivity().finish();
    }

    public void showLoading(String title, String message) {
        if (null == mProgressDialog)
            mProgressDialog = new ProgressDialog(getActivity());//1.创建一个ProgressDialog的实例
        mProgressDialog.setTitle(title);//2.设置标题
        mProgressDialog.setMessage(message);//3.设置显示内容
        mProgressDialog.setCancelable(false);//4.设置可否用back键关闭对话框
        mProgressDialog.show();//5.将ProgessDialog显示出来

    }

    public void hideLoading() {
        if (null == mProgressDialog)
            return;
        mProgressDialog.dismiss();
    }

    public void showProgressbar() {
        if (null == mProgressBar) {
            if (null == rootView)
                return;
            mProgressBar = rootView.findViewById(R.id.progressBar);
        }
        if (null != mProgressBar)
            mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressbar() {
//        if (null == mProgressBar) {
        if (null == rootView)
            return;
        mProgressBar = rootView.findViewById(R.id.progressBar);
//        }
        if (null != mProgressBar) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    public void onReturnResult(int requestCode, int resultCode, Intent data) {

    }

    public void showNoData() {
        if (null == rootView)
            return;
        ViewGroup viewGroup = rootView.findViewById(R.id.root);
        if (null == viewGroup)
            return;
        if (null != mNoData)
            viewGroup.removeView(mNoData);
        mNoData = LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_no_data, null, false);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mNoData.setLayoutParams(params);
        viewGroup.addView(mNoData, 0);
    }

    public void hideNoData() {
        if (null == mNoData)
            return;
        ViewGroup viewGroup = rootView.findViewById(R.id.root);
        if (null == viewGroup)
            return;
        viewGroup.removeView(mNoData);
        mNoData = null;
    }

    public void showError() {
        hideNoData();
        if (null == rootView)
            return;
        ViewGroup viewGroup = rootView.findViewById(R.id.root);
        if (null == viewGroup)
            return;
        if (null != mError)
            viewGroup.removeView(mError);
        mError = LayoutInflater.from(getActivity()).inflate(R.layout.layout_error, null, false);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mError.setLayoutParams(params);
        viewGroup.addView(mError, 0);
    }

    public void hideError() {
        if (null == mError)
            return;
        ViewGroup viewGroup = rootView.findViewById(R.id.root);
        if (null == viewGroup)
            return;
        viewGroup.removeView(mError);
        mError = null;
    }

    public Activity getmContext() {
        return getActivity();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
