package com.cardshop.cardshop.PresenterImpl;

import com.cardshop.cardshop.Contract.AddCardContract;
import com.cardshop.cardshop.Module.BankInfoBean;

public class AddCardPresenterImpl extends AddCardContract.Presenter {
    private AddCardContract.IView mView;


    public AddCardPresenterImpl(AddCardContract.IView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void checkInput(String name, String code, String identity, String phone, String bank) {
//        if (TextUtils.isEmpty(name)) {
//            mView.showSnackerToast("姓名不能为空");
//        } else if (TextUtils.isEmpty(code)) {
//            mView.showSnackerToast("卡号不能为空");
//        } else if (TextUtils.isEmpty(identity)) {
//            mView.showSnackerToast("身份证号不能为空");
//        } else if (TextUtils.isEmpty(phone)) {
//            mView.showSnackerToast("手机号不能为空");
//        } else if (TextUtils.isEmpty(bank)) {
//            mView.showSnackerToast("银行不能为空");
//        } else {
        gotoNext(name, code, identity, phone, bank);
//        }
    }

    @Override
    public void findBankName(String code) {
        if (BankInfoBean.checkBankCard(code)) {
            BankInfoBean bankInfoBean = new BankInfoBean(code);
            mView.setBankName(bankInfoBean.getBankName());
        } else {

        }
    }

    public void gotoNext(String name, String code, String identity, String phone, String bank) {
        mView.gotoNext(name, code, identity, phone, bank);
    }
}
