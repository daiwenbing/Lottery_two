package com.dwb.lottery_two.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.allenliu.versionchecklib.callback.APKDownloadListener;
import com.allenliu.versionchecklib.callback.CommitClickListener;
import com.allenliu.versionchecklib.callback.DialogDismissListener;
import com.allenliu.versionchecklib.core.VersionDialogActivity;
import com.dwb.lottery_two.R;
import com.dwb.lottery_two.application.DSLApplication;
import com.dwb.lottery_two.immersive.UltimateBar;
import com.dwb.lottery_two.style.BaseDialog;

import java.io.File;


/**
 * Created by dwb on 2017/8/17.
 */

public class CustomVersionDialogActivity extends VersionDialogActivity implements CommitClickListener, DialogDismissListener, APKDownloadListener {
    public static int customVersionDialogIndex = 3;
    public static boolean isForceUpdate = false;
    public static boolean isCustomDownloading = false;
    private String messagg;
    private Intent intent;
    private TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这里是几个回调
        DSLApplication.getInstance().addActivity(this);
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar();
        setApkDownloadListener(this);
        setCommitClickListener(this);
        setDialogDimissListener(this);
        intent=getIntent();
        messagg=intent.getStringExtra("text");
        if (null!=tv2&&!"".equals(tv2)){
            tv2.setText(Html.fromHtml(messagg));
        }
    }

    @Override
    public void onDownloadSuccess(File file) {
        Log.e("CustomVersionDialogActi", "文件下载成功回调");
        Intent intent=new Intent(CustomVersionDialogActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDownloadFail() {
        Intent intent=new Intent(CustomVersionDialogActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onDownloading(int progress) {

//        Log.e("CustomVersionDialogActi", "正在下载中回调...");
    }

    @Override
    public void onCommitClick() {
//        Log.e("CustomVersionDialogActi", "确认按钮点击回调");
    }

    /**
     * 自定义更新展示界面 直接重写此方法就好
     */
    @Override
    public void showVersionDialog() {
        //使用默认的提示框直接调用父类的方法,如果需要自定义的对话框，那么直接重写此方法
        // super.showVersionDialog();
    /*    if (customVersionDialogIndex == 1) {
        } else if (customVersionDialogIndex == 2) {
            customVersionDialogTwo();
        } else {
            super.showVersionDialog();
        }*/
        CustomVersionDialogActivity.super.dealAPK();

//        Toast.makeText(this, "重写此方法显示自定义对话框", Toast.LENGTH_SHORT).show();
    }

    /**
     * 自定义dialog two
     */
    private void customVersionDialogTwo() {
        versionDialog = new BaseDialog(this, R.style.BaseDialog, R.layout.custom_dialog_two_layout);
        versionDialog.setCancelable(false);
        versionDialog.setCanceledOnTouchOutside(false);
        versionDialog.show();
        //设置dismiss listener 用于强制更新会回调dialogDismiss方法
        versionDialog.setOnDismissListener(this);
        TextView tvUpdate = (TextView) versionDialog.findViewById(R.id.tv_update);
        tv2= (TextView) versionDialog.findViewById(R.id.tv2);
        ImageView delete_x= (ImageView) versionDialog.findViewById(R.id.delete_x);
//        tv2.setText(messagg);

        versionDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
             /*   versionDialog.dismiss();
                CustomVersionDialogActivity.super.dealAPK();
                finish();*/
            }
        });
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                versionDialog.dismiss();
//                downloadFile();
                CustomVersionDialogActivity.super.dealAPK();
//                finish();

            }
        });
        delete_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                versionDialog.dismiss();
//                downloadFile();
                CustomVersionDialogActivity.super.dealAPK();
//                finish();
            }
        });
        versionDialog.show();
    }

    /**
     * 自定义下载失败重试对话框
     * 使用父类的failDialog
     */
    @Override
    public void showFailDialog() {
        super.showFailDialog();

//        Toast.makeText(this, "重写此方法使用自定义失败加载框", Toast.LENGTH_SHORT).show();
    }

    View loadingView;

    /**
     * 要更改下载中界面 只需要重写此方法即可
     * 因为下载的时候会不断回调此方法
     * dialog使用全局 只初始化一次
     * 使用父类的loadingDialog保证下载成功会dimiss掉dialog
     *
     * @param currentProgress
     */
    @Override
    public void showLoadingDialog(int currentProgress) {
        if (!isCustomDownloading) {
            super.showLoadingDialog(currentProgress);
        } else {
            //使用父类的loadingDialog保证下载成功会dimiss掉dialog
            if (loadingDialog == null) {
                loadingView = LayoutInflater.from(this).inflate(R.layout.custom_download_layout, null);
                loadingDialog = new AlertDialog.Builder(this).setTitle("").setView(loadingView).create();
                loadingDialog.setCancelable(false);
                loadingDialog.setCanceledOnTouchOutside(false);
                loadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                });
            }
            ProgressBar pb = (ProgressBar) loadingView.findViewById(R.id.pb);
            TextView tvProgress = (TextView) loadingView.findViewById(R.id.tv_progress);
            tvProgress.setText(String.format(getString(R.string.versionchecklib_progress), currentProgress));
            pb.setProgress(currentProgress);
            loadingDialog.show();
        }
//        Toast.makeText(this, "显示自定义的下载加载框", Toast.LENGTH_SHORT).show();
    }

    /**
     * versiondialog dismiss 的时候会回调此方法
     *
     * @param dialog
     */
    @Override
    public void dialogDismiss(DialogInterface dialog) {
        Intent intent=new Intent(CustomVersionDialogActivity.this,MainActivity.class);
        startActivity(intent);
//        Log.e("CustomVersionDialogActi", "dialog dismiss 回调");
        if (isForceUpdate) {
            finish();
            //我这里为了简便直接finish 就行了
//            MainActivity.mainActivity.finish();
        }
    }
}
