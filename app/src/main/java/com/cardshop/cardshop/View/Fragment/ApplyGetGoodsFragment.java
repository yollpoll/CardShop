package com.cardshop.cardshop.View.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.ApplyGetGoodsContact;
import com.cardshop.cardshop.Module.AddressModule;
import com.cardshop.cardshop.Module.OrderYigouModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Utils.DialogUtils;
import com.cardshop.cardshop.View.Activity.AddressChooseActivity;
import com.cardshop.cardshop.Widget.PasswordEditLayout;
import com.cardshop.framework.Utils.ImageUtils;

public class ApplyGetGoodsFragment extends BaseFragment implements ApplyGetGoodsContact.IView, PasswordEditLayout.OnInputCompleteListener {
    public static final int REQUEST_CHOOSE_ADDRESS = 1;
    private ApplyGetGoodsContact.Presenter presenter;
    private ImageView ivCard;
    private TextView tvCardName;
    private TextView tvCount;
    private TextView tvReduce, tvAdd, tvChooseCount;
    private RelativeLayout rlChooseAddress;
    private TextView tvOk;
    private TextView tvChooseAddress;
    private TextView tvAllPrice;
    private LinearLayout llAddress;
    private TextView tvPersonalMsg, tvAddressMsg;
    private RelativeLayout rlPhone, rlAddress;
    private EditText edtPhone;
    private Dialog dialog;


    public static ApplyGetGoodsFragment newInstance() {
        return new ApplyGetGoodsFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(ApplyGetGoodsContact.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_apply_get_goods, container, false);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
        showBack();
        setTitle("申请提货");
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ivCard = view.findViewById(R.id.iv_card);
        tvCardName = view.findViewById(R.id.tv_card_name);
        tvCount = view.findViewById(R.id.tv_count);
        tvReduce = view.findViewById(R.id.tv_reduce);
        tvAdd = view.findViewById(R.id.tv_add);
        tvChooseCount = view.findViewById(R.id.tv_choose_num);
        rlChooseAddress = view.findViewById(R.id.rl_choose_address);
        tvChooseAddress = view.findViewById(R.id.tv_choose_address);
        tvOk = view.findViewById(R.id.tv_ok);
        tvAllPrice = view.findViewById(R.id.tv_all_price);
        llAddress = view.findViewById(R.id.ll_address);
        tvPersonalMsg = view.findViewById(R.id.tv_people_msg);
        tvAddressMsg = view.findViewById(R.id.tv_address_msg);
        rlAddress = view.findViewById(R.id.rl_address);
        rlPhone = view.findViewById(R.id.rl_phone);
        edtPhone = view.findViewById(R.id.edt_phone);

        tvAdd.setOnClickListener(this);
        tvReduce.setOnClickListener(this);
        rlChooseAddress.setOnClickListener(this);
        tvOk.setOnClickListener(this);
    }

    @Override
    public void initModule(OrderYigouModule orderYigouModule) {
        ImageUtils.loadImage(orderYigouModule.getGoodsImage(), ivCard, getActivity());
        tvCardName.setText(orderYigouModule.getGoodsName());
        tvCount.setText("x" + orderYigouModule.getGoodsNum());
        tvAllPrice.setText(orderYigouModule.getAllPrice() + "元");
        tvChooseCount.setText("1");

    }

    @Override
    public void onSetCount(String count, String allPrice) {
        tvChooseCount.setText(count);
        tvAllPrice.setText(allPrice + "元");
    }

    @Override
    public void initCardType(boolean isRecharge) {
        if (isRecharge) {
            rlPhone.setVisibility(View.VISIBLE);
            rlAddress.setVisibility(View.GONE);
        } else {
            rlPhone.setVisibility(View.GONE);
            rlAddress.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_add:
                presenter.addCount();
                break;
            case R.id.tv_reduce:
                presenter.reduceCount();
                break;
            case R.id.rl_choose_address:
                AddressChooseActivity.gotoChooseActivity(getActivity(), REQUEST_CHOOSE_ADDRESS);
                break;
            case R.id.tv_ok:
                presenter.checkInput(edtPhone.getText().toString());
//                dialog = DialogUtils.showPayPasswordDialog(getActivity(), this);
//                presenter.applyGetGoods(edtPhone.getText().toString());
                break;
        }
    }

    @Override
    public void onReturnResult(int requestCode, int resultCode, Intent data) {
        super.onReturnResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CHOOSE_ADDRESS:
                if (resultCode == Activity.RESULT_OK) {
                    AddressModule addressModule = (AddressModule) data.getSerializableExtra("address");
                    presenter.chooseAddress(addressModule);
                    tvChooseAddress.setVisibility(View.GONE);
                    llAddress.setVisibility(View.VISIBLE);
                    tvPersonalMsg.setText(addressModule.getTrueName() + " " + addressModule.getMobPhone());
                    tvAddressMsg.setText(addressModule.getAreaInfo() + addressModule.getAddress());
                }
                break;
        }
    }

    @Override
    public void onComplete(String psw) {
        if (null != dialog)
            dialog.dismiss();
        presenter.checkPayPassword( edtPhone.getText().toString(),psw);
    }

    @Override
    public void showPassword() {
        dialog = DialogUtils.showPayPasswordDialog(getActivity(), this);
    }
}
