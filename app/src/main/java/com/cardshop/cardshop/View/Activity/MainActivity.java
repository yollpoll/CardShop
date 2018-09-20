package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.cardshop.cardshop.Base.BaseActivity;
import com.cardshop.cardshop.PresenterImpl.FoundPresenterImpl;
import com.cardshop.cardshop.PresenterImpl.MainPresenterImpl;
import com.cardshop.cardshop.PresenterImpl.MinePresenterImpl;
import com.cardshop.cardshop.PresenterImpl.OrderPresenterImpl;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Fragment.FoundFragment;
import com.cardshop.cardshop.View.Fragment.MainFragment;
import com.cardshop.cardshop.View.Fragment.MineFragment;
import com.cardshop.cardshop.View.Fragment.OrderFragment;

public class MainActivity extends BaseActivity {
    public static final String FRAGMENT_TAG = "fragment_tag";

    private RadioButton rbHome, rbFound, rbOrder, rbMine;
    private FragmentManager fragmentManager;
    private MainFragment mainFragment;
    private FoundFragment foundFragment;
    private OrderFragment orderFragment;
    private MineFragment mineFragment;
    FragmentTransaction transaction;

    public static void gotoMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        super.initView();
        rbHome = findViewById(R.id.rb_home);
        rbFound = findViewById(R.id.rb_found);
        rbOrder = findViewById(R.id.rb_order);
        rbMine = findViewById(R.id.rb_mine);

        rbHome.setOnClickListener(this);
        rbFound.setOnClickListener(this);
        rbOrder.setOnClickListener(this);
        rbMine.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        mainFragment = MainFragment.newInstance();
        foundFragment = FoundFragment.newInstance();
        orderFragment=OrderFragment.newInstance();
        mineFragment=MineFragment.newInstance();
        new MainPresenterImpl(mainFragment);
        new FoundPresenterImpl(foundFragment);
        new OrderPresenterImpl(orderFragment);
        new MinePresenterImpl(mineFragment);
//        initFragments();
        loadFragment(mainFragment);
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.rb_home:
                loadFragment(mainFragment);
                break;
            case R.id.rb_found:
                loadFragment(foundFragment);
                break;
            case R.id.rb_order:
                loadFragment(orderFragment);
                break;
            case R.id.rb_mine:
                loadFragment(mineFragment);
                break;
        }
    }

    private void initFragments() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.rl_main, mainFragment);
        fragmentTransaction.add(R.id.rl_main, foundFragment);
        fragmentTransaction.commit();
    }

    //
    private void hideFragments() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(mainFragment);
        fragmentTransaction.hide(foundFragment);
        fragmentTransaction.commit();
    }

    //使用replace方式加载
    private void loadFragment(Fragment fragment) {
//        hideFragments();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.show(fragment);
        fragmentTransaction.replace(R.id.rl_main, fragment);
        fragmentTransaction.commit();
    }
}
