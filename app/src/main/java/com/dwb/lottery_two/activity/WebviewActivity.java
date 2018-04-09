package com.dwb.lottery_two.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
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
import com.dwb.lottery_two.application.DSLApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dwb on 2018/3/27.
 */

public class WebviewActivity extends AppCompatActivity {
    @BindView(R.id.toorbar_layout_main_back)
    LinearLayout toorbar_layout_main_back;
    @BindView(R.id.toorbar_txt_main_title)
    TextView toorbar_txt_main_title;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    private Intent mintent;
    private WebSettings webSettings;
    private String url, title = null;
    private static String javascripts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DSLApplication.getInstance().addActivity(this);
        setContentView(R.layout.webview_title_view);
        ButterKnife.bind(this);
        initview();
    }

    public void initview() {
        mintent = getIntent();
        url = mintent.getStringExtra("url");
        title = mintent.getStringExtra("title");
        toorbar_txt_main_title.setText(title);
        init();
    }
    @OnClick(R.id.toorbar_layout_main_back)
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.toorbar_layout_main_back) {
            if (webview.canGoBack()) {
                webview.goBack();
                return;
            }
            finish();
        }
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
        webSettings.setDatabaseEnabled(true);
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
                if (null == title || "".equals(title)) {
                    toorbar_txt_main_title.setText(view.getTitle());
                }
                //编写 javaScript方法  header_bg icon

                if (url.contains("http://m.159cai.com/gong/index.html")||url.contains("http://m.159cai.com/gong/news.html")||url.contains("http://m.159cai.com/gong/dailycontest.html")) {
                    javascripts = "javascript:function hideOther() {" + "document.getElementsByClassName('top')[0].remove();}";
//                    view.loadUrl("javascript:function setTop(){document.querySelector('.top').style.display=\"none\";}setTop();");
                }
                else if (url.contains("http://m.dididapiao.com/bet")) {
                    javascripts = "javascript:function hideOther() {" +
                            "document.getElementsByClassName('one_i')[0].remove();" +
                            "document.getElementsByClassName('choose_session')[0].remove();}";
                } else if (url.contains("http://m.159cai.com/sjbguanyajun/guanjun.html")) {
                    javascripts = "javascript:function hideOther() {" +
                            "document.getElementsByClassName('menu')[0].remove();" +
                            "document.getElementsByTagName('footer')[0].remove();}";
                } else if (url.contains("http://m.lottech.cn")) {
                    javascripts = "javascript:function hideOther() {" + "document.getElementsByClassName('fixed_bottom')[0].remove();}";
                }
                else {
         /*           javascripts =  "javascript:function hideOther() {" +
                            "document.getElementsByClassName('one_i')[0].remove();" +
                            "document.getElementsByClassName('choose_session')[0].remove();" +
                            "document.getElementsByClassName('top_back')[0].remove();" +
                            "document.getElementsByClassName('fixed_bottom')[0].remove();" +
                            "document.getElementsByClassName('back')[0].remove();}" ;*/
                }
                //创建方法
                view.loadUrl(javascripts);
                //加载方法
                view.loadUrl("javascript:hideOther();");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                if (url.equals("http://m.500.com/")){
                    view.loadUrl("http://live.m.500.com/home/zq/jczq/cur?render=local");
                    return true;
                }
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                view.loadUrl("https://m.qmcai.com/hd/caipiaoclass/index.html?h5ControlTitle=true&backH5Control=true");//加载本地网页的路径
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

    /**
     * 返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webview.canGoBack()) {
                webview.goBack();
                return true;
            }
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }


}
