package com.cardshop.cardshop.Contract;

import com.cardshop.cardshop.Base.BasePresenter;
import com.cardshop.cardshop.Base.IBaseView;
import com.cardshop.cardshop.Module.AddressModule;

import java.util.List;

public interface AddressChooseContract {
    abstract class Presenter<V> extends BasePresenter<V> {
        public abstract void refreshData();

        public abstract void onItemClick(int position);
    }

    interface IView extends IBaseView<AddressChooseContract.Presenter> {
        void initRecyclerView(List<AddressModule> list);

        void refreshRecyclerView();

        void gotoEditAddress(AddressModule addressModule);
    }
}
