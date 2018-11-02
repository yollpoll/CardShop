package com.cardshop.cardshop.View.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseFragment;
import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Contract.CreateOrderContact;
import com.cardshop.cardshop.Module.GoodsModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Utils.DialogUtils;
import com.cardshop.cardshop.Widget.PasswordEditLayout;
import com.cardshop.framework.Utils.ImageUtils;

public class CreateOrderFragment extends BaseFragment implements CreateOrderContact.IView, PasswordEditLayout.OnInputCompleteListener, RadioGroup.OnCheckedChangeListener {
    private CreateOrderContact.Presenter presenter;
    private ImageView ivCard;
    private TextView tvCard;
    private TextView tvPrice;
    private TextView tvLastNum;
    private TextView tvAllPrice;
    private TextView tvPurchase;
    private TextView tvSeller;
    private TextView tvAdd, tvReduce, tvChooseNum;
    private RadioButton rbBalance;
    private Dialog payPasswordDialog;
    private RadioGroup rgPay;
    private int payWay = R.id.rb_balance;


    public static CreateOrderFragment newInstance() {
        return new CreateOrderFragment();
    }

    @Override
    public BasePresenter createPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(CreateOrderContact.Presenter presenter) {
        this.presenter = presenter;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_order, container, false);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
        showBack();
        setTitle("确认订单");
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ivCard = view.findViewById(R.id.iv_card);
        tvCard = view.findViewById(R.id.tv_card_name);
        tvPrice = view.findViewById(R.id.tv_price);
        tvLastNum = view.findViewById(R.id.tv_last_num);
        tvAllPrice = view.findViewById(R.id.tv_all_price);
        tvPurchase = view.findViewById(R.id.tv_purchase);
        tvSeller = view.findViewById(R.id.tv_seller);
        tvAdd = view.findViewById(R.id.tv_add);
        tvReduce = view.findViewById(R.id.tv_reduce);
        tvChooseNum = view.findViewById(R.id.tv_choose_num);
        rbBalance = view.findViewById(R.id.rb_balance);
        rgPay = view.findViewById(R.id.rg_buy);

        rgPay.setOnCheckedChangeListener(this);
        tvAdd.setOnClickListener(this);
        tvReduce.setOnClickListener(this);
        tvPurchase.setOnClickListener(this);
    }

    @Override
    public void initGoods(GoodsModule goodsModule) {
        ImageUtils.loadImage(goodsModule.getGoodsImage(), ivCard, getActivity());
        tvCard.setText(goodsModule.getGoodsName());
        tvPrice.setText("¥" + goodsModule.getAllPrice() + "");
        tvLastNum.setText("剩余数量:" + goodsModule.getGoodsNum());
        tvAllPrice.setText(goodsModule.getAllPrice() + "元");
        tvSeller.setText("卖家:" + goodsModule.getMemberName());
        tvChooseNum.setText("1");
    }

    @Override
    public void onSetCount(int count, double amount) {
        tvChooseNum.setText(count + "");
        tvAllPrice.setText(amount + "元");
    }

    @Override
    public void initBalance(String balance) {
        String content = "账户余额: ¥" + balance;
        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        builder.setSpan(new ForegroundColorSpan(getActivity().getResources().getColor(R.color.colorPrimary)), 6, content.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        rbBalance.setText(builder);
    }

    @Override
    public void onCreateOrder() {
        switch (payWay) {
            case R.id.rb_balance:
                payPasswordDialog = DialogUtils.showPayPasswordDialog(getActivity(), this);
                break;
            case R.id.rb_alipay:
                presenter.payAli();
                break;
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
            case R.id.tv_purchase:
                presenter.createOrder();
                break;
        }
    }

    @Override
    public void onComplete(String psw) {
        if (null != payPasswordDialog)
            payPasswordDialog.dismiss();
        presenter.payByBalance(psw);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        payWay = i;
    }
}
