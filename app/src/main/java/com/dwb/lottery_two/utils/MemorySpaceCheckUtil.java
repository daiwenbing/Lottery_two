package com.dwb.lottery_two.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import com.dwb.lottery_two.application.DSLApplication;

import java.io.File;
import java.lang.reflect.Method;

/**
 * 检测设备存储空间
 * Created by dwb on 2016/11/18.
 */

public class MemorySpaceCheckUtil {

    /**
     *
     * 计算剩余空间
     * @param path
     * @return
     */
    private static long getAvailableSize(String path){
        StatFs fileStats = new StatFs(path);
        fileStats.restat(path);
        return (long) fileStats.getAvailableBlocks() * fileStats.getBlockSize(); // 注意与fileStats.getFreeBlocks()的区别
    }

    /**
     *
     * 计算总空间
     * @param path
     * @return
     */
    private static long getTotalSize(String path){
        StatFs fileStats = new StatFs(path);
        fileStats.restat(path);
        return (long) fileStats.getBlockCount() * fileStats.getBlockSize();
    }

    /**
     * 检测SDCard是否可用
     *
     * @return
     */
    public static boolean checkSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * 计算SD卡的剩余空间
     * @return 剩余空间
     */
    public static long getSDAvailableSize(){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return getAvailableSize(Environment.getExternalStorageDirectory().toString());
        }
        return 0;
    }

    /**
     *
     * 计算系统的剩余空间
     * @return 剩余空间
     */
    public static long getSystemAvailableSize(){
//		 context.getFilesDir().getAbsolutePath();
        return getAvailableSize("/data");
    }

    /**
     *
     * 是否有足够的空间
     * @param filePath
     *            文件路径，不是目录的路径
     * @return
     */
    public static boolean hasEnoughMemory(String filePath){
        File file = new File(filePath);
        long length = file.length();
        if (filePath.startsWith("/sdcard") || filePath.startsWith("/mnt/sdcard")){
            return getSDAvailableSize() > length;
        }else{
            return getSystemAvailableSize() > length;
        }
    }

    /**
     *
     * 获取SD卡的总空间
     * @return
     */
    public static long getSDTotalSize(){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return getTotalSize(Environment.getExternalStorageDirectory().toString());
        }
        return 0;
    }

    /**
     *
     * 获取系统可读写的总空间
     * @return
     */
    public static long getSysTotalSize(){
        return getTotalSize("/data");
    }

    /**
     * 获取当前android可用内存大小
     * @return
     */
    public static String getAvailMemory() {
        Context context = DSLApplication.getInstance().getBaseContext();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
//		return Formatter.formatFileSize(context, mi.availMem);// 将获取的内存大小规格化
        return (mi.availMem / 1024 / 1024)+"";
    }

    /**
     * 获得系统总内存
     * @return
     */
    public static String getTotalMemory() {
        Method _readProclines = null;
        try {
            Class<?> procClass = Class.forName("android.os.Process");
            Class<?> parameterTypes[] = new Class[] { String.class,String[].class, long[].class };
            _readProclines = procClass.getMethod("readProcLines",parameterTypes);
            Object arglist[] = new Object[3];
            final String[] mMemInfoFields = new String[] { "MemTotal:","MemFree:", "Buffers:", "Cached:" };
            long[] mMemInfoSizes = new long[mMemInfoFields.length];
            mMemInfoSizes[0] = 30;
            mMemInfoSizes[1] = -30;
            arglist[0] = new String("/proc/meminfo");
            arglist[1] = mMemInfoFields;
            arglist[2] = mMemInfoSizes;
            if (_readProclines != null) {
                _readProclines.invoke(null, arglist);
//				for (int i = 0; i < mMemInfoSizes.length; i++) {
//					Log.d("GetFreeMem", mMemInfoFields[i] + " : "+ mMemInfoSizes[i] / 1024);
//				}
                return ""+mMemInfoSizes[0] / 1024;
            }
            return "-1";
        } catch (Exception e) {
            return "-1";
        }
    }




}
