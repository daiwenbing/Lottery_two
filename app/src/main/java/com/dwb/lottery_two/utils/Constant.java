package com.dwb.lottery_two.utils;


import com.dwb.lottery_two.R;
import com.dwb.lottery_two.application.DSLApplication;

/**
 * Created by dwb on 2016/11/18.
 */

public class Constant {
    public static String imei_Code = "";
    /**
     * 应用包名
     */
    public final static String PACKAGE_NAME = DSLApplication.getInstance().getPackageName();
    public final static String GO_Package="com.bxvip.app.xycaizy";
    /**
     * 屏幕宽度
     */
    public static int SCREEN_WIDTH = 800;
    /**
     * 屏幕分辨率宽度
     */
    public static int WIDTHPIXELS = 800;
    /**
     * 屏幕高度
     */
    public static int SCREEN_HEIGHT = 740;
    /**
     * 屏幕分辨率高度
     */
    public static int HEIGHTPIXELS = 640;
    /**
     * 屏幕密度
     */
    public static int DENSITYDPI = 240;
    /**
     * 缓存根目录
     */
    public final static String BREDPOU_PATH = FileUtil.getDataPath() + DSLApplication.getInstance().getString(R.string.bredpou_folder);
    /**
     * 错误目录
     */
    public final static String cacheErrorPath = BREDPOU_PATH + DSLApplication.getInstance().getString(R.string.error_folder);
    /**
     * 图片缓存目录
     */
    public final static String cachePath = BREDPOU_PATH + DSLApplication.getInstance().getString(R.string.cache_folder);
    /**
     * 是否禁止在线下载图片
     */
    public static boolean isBanDowloadImage = false;
    /**
     * tag_key
     */
    public final static int TAG_KEY = R.mipmap.ic_launcher;

    /**
     * 拍照
     */
    public final static int CAMERA_REQUEST_CODE = 1;
    /**
     * 图片选择
     */
    public final static int IMAGE_REQUEST_CODE = 2;
    /**
     * 预览图片
     */
    public final static int SHOW_PHOTO_REQUEST_CODE = 3;
    /**
     * 上传用户照片
     */
    public final static int UPLOAD_MY_PICTURE_CODE = 4;
    /**
     * 设置 更新用户头像
     */
    public final static int SETTING_UPDATE_USER_ICON_CODE = 5;
    /**
     * app异常
     */
    public final static String APP_EXCEPTION_CODE = "110";
    /**
     * CallService
     */
    public final static String METHOD_CALLSERVICE = "CallService";
}
