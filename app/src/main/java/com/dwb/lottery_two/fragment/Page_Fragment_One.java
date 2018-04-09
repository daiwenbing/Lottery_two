package com.dwb.lottery_two.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.dwb.lottery_two.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dwb on 2018/4/3.
 */

public class Page_Fragment_One extends Fragment {

    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    private WebSettings webSettings;
    private static String javascripts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webview_notitle_normal, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }
        private void init() {
            //WebView加载web资源
            webview.loadUrl("http://live.m.500.com/home/zq/jczq/cur?render=local");
            webview.setLayerType(View.LAYER_TYPE_HARDWARE, null);//启用加速，否则滑动界面不流畅
            webSettings = webview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setUseWideViewPort(true);//关键点
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
            webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
//        webSettings.setAppCacheEnabled(true);//是否使用缓存
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
//                    view.loadUrl(url);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    // TODO Auto-generated method stub
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    if (url.equals("http://m.500.com/")||url.contains("500.com/user/index.php")){
                        view.loadUrl("http://live.m.500.com/home/zq/jczq/cur?render=local");
                        return true;
                    }
                    view.loadUrl(url);
                    return false;
                }

                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    super.onReceivedError(view, request, error);
                    view.loadUrl("file:///android_asset/error.html");//加载本地网页的路径
                }
            });

        }

}
