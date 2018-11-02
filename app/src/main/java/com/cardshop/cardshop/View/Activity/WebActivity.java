package com.cardshop.cardshop.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.cardshop.cardshop.Base.BaseActivity;
import com.cardshop.cardshop.Module.UserModule;
import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Utils.LogUtil;
import com.cardshop.cardshop.Utils.MD5Utils;

public class WebActivity extends BaseActivity {
    public static final String PUBLIC_KEY = "ctt23cda8db4a773ef";
    private WebView webView;
    private String url;
    private TextView tvClose, tvRefresh;
    private ImageView ivBack, ivRefresh;
    private String sign;

    public static void gotoWebActivity(String url, Context context) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        super.initView();
        webView = findViewById(R.id.web);
        ivBack = findViewById(R.id.iv_head_left);
        tvRefresh = findViewById(R.id.tv_head_left2);
        tvClose = findViewById(R.id.tv_head_left);
        ivRefresh = findViewById(R.id.iv_head_right);

        ivRefresh.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        tvRefresh.setOnClickListener(this);
        tvClose.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        setNoStatusBar();
        url = getIntent().getStringExtra("url");
        initWeb();
    }

    private void initWeb() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.setWebViewClient(new MyWebClient());
        webView.setWebChromeClient(new WebChromeClient());

        webView.loadUrl(url);
    }

    private class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            LogUtil.Log("aisoaisoaisoaiosa");
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (url.equalsIgnoreCase(WebActivity.this.url)) {
                webView.loadUrl(WebActivity.this.url + "?sign=" + getSign() + "&userid=" +
                        UserModule.getCurrentUser().getMember().getMemberId());
            }
        }
    }

    private String getSign() {
        String sign = PUBLIC_KEY +
                UserModule.getCurrentUser().getMember().getMemberId() +
                UserModule.getCurrentUser().getMember().getMemberPasswd();
        return MD5Utils.md5(sign);

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_head_left:
                WebActivity.this.finish();
                break;
            case R.id.iv_head_right:
                webView.reload();
                break;
            case R.id.iv_head_left:
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    WebActivity.this.finish();
                }
                break;
        }
    }
}
