package com.dwb.lottery_two.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dwb.lottery_two.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dwb on 2018/4/4.
 */

public class MapActivity extends AppCompatActivity {

    @BindView(R.id.toorbar_layout_main_back)
    LinearLayout toorbarLayoutMainBack;
    @BindView(R.id.toorbar_txt_main_title)
    TextView toorbarTxtMainTitle;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    private Intent mintent;
    private String url, title = null;
    private WebSettings webSettings;
    private static String javascripts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_map_view);
        ButterKnife.bind(this);
        initview();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @OnClick(R.id.toorbar_layout_main_back)
    public void onViewClicked() {
        finish();
    }
    public void initview() {
        mintent = getIntent();
        url = mintent.getStringExtra("url");
        title = mintent.getStringExtra("title");
        toorbarTxtMainTitle.setText(title);
        init();
    }
    private void init() {
        //WebView加载web资源
        webview.loadUrl(url);
        webview.setLayerType(View.LAYER_TYPE_HARDWARE, null);//启用加速，否则滑动界面不流畅
        webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setAppCacheEnabled(true);//是否使用缓存
        webSettings.setSupportZoom(true); // 支持缩放
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.INVISIBLE);
                //编写 javaScript方法  header_bg icon
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                view.loadUrl("file:///android_asset/error.html");//加载本地网页的路径
            }
        });
//        webView.addJavascriptInterface(new JsCallJava() {
//            @JavascriptInterface
//            @Override   window.android_daipai.androidMethod()
//            public void onCallback() {
//
//                Toast.makeText(getApplicationContext(),"JavaScript调用的java代码",Toast.LENGTH_SHORT).show();
//
//            }
//        }, "demo");
    }
}
