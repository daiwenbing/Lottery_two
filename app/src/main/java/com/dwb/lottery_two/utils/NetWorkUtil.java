package com.dwb.lottery_two.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.DefaultHttpClient;

import java.lang.reflect.Method;

/**
 * 测试网络连接
 *
 * @author dwb
 * @version 2012-6-27 上午9:12:08
 */
public class NetWorkUtil {

    /**
     * 测试网络状态
     *
     * @param context
     * @return
     */
    public static boolean checkNetWorkStatus(Context context) {
        boolean result;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isConnected()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
    /**
     * 获取公网ip
     *
     * @return
     */
    public static String[] getNetIp() {
        return null;
    }
    /**
     * @return wifi是否连接可用
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWifi != null) {
            return mWifi.isConnected();
        }
        return false;
    }
    /**
     * 判断WIFI信号强弱
     *
     * @param context
     * @return
     */
    public static String judgeWIFIState(Context context) {
        int level = Math.abs(((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getRssi());
        if (level >= 0 && level <= 50) {
            return "WIFI信号很强";
        } else if (level > 50 && level <= 70) {
            return "WIFI信号偏差";
        } else {
            return "WIFI信号很差";
        }
    }
    /**
     * 当wifi不能访问网络时，mobile才会起作用
     *
     * @return GPRS是否连接可用
     */
    public static boolean isMobileConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mMobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mMobile != null) {
            return mMobile.isConnected();
        }
        return false;
    }

    /**
     * GPRS网络开关 反射ConnectivityManager中hide的方法setMobileDataEnabled 可以开启和关闭GPRS网络
     *
     * @param isEnable
     * @throws Exception
     */
    public static void toggleGprs(Context context, boolean isEnable) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Class<?> cmClass = cm.getClass();
            Class<?>[] argClasses = new Class[1];
            argClasses[0] = boolean.class;
            // 反射ConnectivityManager中hide的方法setMobileDataEnabled，可以开启和关闭GPRS网络
            Method method = cmClass.getMethod("setMobileDataEnabled", argClasses);
            method.invoke(cm, isEnable);
        } catch (Exception e) {
        }
    }

    /**
     * WIFI网络开关
     *
     * @param enabled
     * @return 设置是否success
     */
    public boolean toggleWiFi(Context context, boolean enabled) {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wm.setWifiEnabled(enabled);
    }

    /**
     *
     * @return 是否处于飞行模式
     */
    public boolean isAirplaneModeOn(Context context) {
        // 返回值是1时表示处于飞行模式
        int modeIdx = Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0);
        boolean isEnabled = (modeIdx == 1);
        return isEnabled;
    }

    /**
     * 飞行模式开关
     *
     * @param setAirPlane
     */
    public void toggleAirplaneMode(Context context, boolean setAirPlane) {
        Settings.System.putInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, setAirPlane ? 1 : 0);
        // 广播飞行模式信号的改变，让相应的程序可以处理。
        // 不发送广播时，在非飞行模式下，Android 2.2.1上测试关闭了Wifi,不关闭正常的通话网络(如GMS/GPRS等)。
        // 不发送广播时，在飞行模式下，Android 2.2.1上测试无法关闭飞行模式。
        Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        // intent.putExtra("Sponsor", "Sodino");
        // 2.3及以后，需设置此状态，否则会一直处于与运营商断连的情况
        intent.putExtra("state", setAirPlane);
        context.sendBroadcast(intent);
    }

    /**
     * 执行ping命令
     *
     * @param ip
     * @return
     */
    public static boolean pingIP(String ip) {
        boolean result;
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("ping -c 1 -w 1 " + ip);
            if (process.waitFor() == 0) {
                result = true;
            } else {
                result = false;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * 判断android SDK版本
     *
     * @return
     */

    public static int getSDKVersionNumber() {
        int sdkVersion;
        try {
            sdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            sdkVersion = 0;
        }
        return sdkVersion;
    }

    /**
     * 获取APP版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo("com.dwb.renrendaipai", 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * 获取APP版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            return 0;
        }
    }
    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    public static boolean checkPackInfo(Context context,String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    /**
     * 判断URL状态 200:正常 -1:网络连接异常
     *
     * @param url
     * @return
     */
    public static int getRespStatus(String url) {
        int status = -1;
        try {
            HttpHead head = new HttpHead(url);
            HttpClient client = new DefaultHttpClient();
            HttpResponse resp = client.execute(head);
            status = resp.getStatusLine().getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

}
