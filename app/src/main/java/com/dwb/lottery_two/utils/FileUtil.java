package com.dwb.lottery_two.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 文件操作类
 * Created by dwb on 2016/11/18.
 */

public class FileUtil {



    public static String SDCard = Environment.getExternalStorageDirectory().getAbsolutePath();

    /**
     * 根据路径返回InputStream
     *
     * @param rootPath
     *            根路径
     * @param path
     *            路径
     * @return
     */
    public static InputStream getInputStream(Context mContext, String rootPath, String path) throws Exception {
        File file = new File(getDataPath() + rootPath + path);
        if (file.exists()) {
            return new FileInputStream(file);
        } else {
            return mContext.getAssets().open(path);
        }
    }

    /**
     * 返回数据目录
     *
     * @return
     */
    public static String getDataPath() {
        if (MemorySpaceCheckUtil.checkSDCard()) {
            return FileUtil.SDCard + "/Android/data/" + Constant.PACKAGE_NAME + "/";
        } else {
            return "/data/data/" + Constant.PACKAGE_NAME + "/";
        }
    }

    /**
     * 返回File 如果没有就创建
     *
     * @return directory
     */
    public static File getDirectory(String path) {
        File appDir = new File(path);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        return appDir;
    }

    public static byte[] getFileContent(String fileName) {
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(fileName);
            int length = fin.available();
            byte[] bytes = new byte[length];
            fin.read(bytes);
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除文件夹
     *
     * @param sPath
     *            路径
     * @return
     */
    public static boolean deleteDirectory(String sPath) {
        boolean flag = false;
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 创建文件夹
     *
     * @param filePath
     * @return 1 :创建成功 0 :已存在 -1 :创建失败
     */
    public static int createFloder(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return 0;
        }
        if (file.mkdirs()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 删除文件
     *
     * @param sPath
     *            路径
     * @return
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 读取文件
     *
     * @return
     */
    public static String readerFile(String filePath) {
        StringBuffer buffer = new StringBuffer();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(fis, "utf-8");// 文件编码Unicode,UTF-8,ASCII,GB2312,Big5
            Reader in = new BufferedReader(isr);
            int ch;
            while ((ch = in.read()) > -1) {
                buffer.append((char) ch);
            }
            in.close();
        } catch (Exception e) {
            return null;
        }
        return buffer.toString();
    }

    /**
     * 写入文件
     *
     * @param content
     * @return 1: 写入成功 0: 写入失败
     */
    public static int writeFile(String path, String content) {
        try {
            File f = new File(path);
            if (f.exists()) {
                f.delete();
            }
            if (f.createNewFile()) {
                FileOutputStream utput = new FileOutputStream(f);
                utput.write(content.getBytes());
                utput.close();
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    /**
     * 写入文件
     *
     * @param path
     * @param in
     * @return 1: 写入成功 0: 写入失败
     */
    public static int writeFile(String path, InputStream in) {
        try {
            if (in == null)
                return 0;
            File f = new File(path);
            if (f.exists()) {
                f.delete();
            }
            if (f.createNewFile()) {
                FileOutputStream utput = new FileOutputStream(f);
                byte[] buffer = new byte[1024];
                int count = -1;
                while ((count = in.read(buffer)) != -1) {
                    utput.write(buffer, 0, count);
                    utput.flush();
                }
                utput.close();
                in.close();
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    /**
     * 复制文件
     *
     * @param is
     * @param os
     * @return 1: 写入成功 0: 写入失败
     * @throws IOException
     */
    public static int copyStream(InputStream is, OutputStream os) {
        try {
            final int buffer_size = 1024;
            byte[] bytes = new byte[buffer_size];
            while (true) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1) {
                    break;
                }
                os.write(bytes, 0, count);
            }
            return 1;
        } catch (IOException e) {
            return 0;
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 读取序列化对象
     *
     * @param filePath
     * @return
     */
    public static Object readerObject(String filePath) {
        Object oo;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fis);
            oo = objectIn.readObject();
            objectIn.close();
            fis.close();
        } catch (Exception e) {
            return null;
        }
        return oo;
    }

    /**
     * 写入序列化对象
     *
     * @param path
     * @param object
     * @return
     */
    public static int writeObject(String path, Object object) {
        try {
            File f = new File(path);
            if (f.exists()) {
                f.delete();
            }
            if (f.createNewFile()) {
                FileOutputStream utput = new FileOutputStream(f);
                ObjectOutputStream objOut = new ObjectOutputStream(utput);
                objOut.writeObject(object);
                objOut.close();
                utput.close();
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    /**
     * 解压
     *
     * @param rootPath
     *            解压的根目录
     * @param fileIn
     *            要解压的ZIP、rar文件流
     * @throws Exception
     */
    public static void unzip(String rootPath, InputStream fileIn) {

        try {
			/* 创建根文件夹 */
            File rootFile = new File(rootPath);
            rootFile.mkdir();

            rootFile = new File(rootPath + "resource/");
            rootFile.mkdir();

            ZipInputStream in = new ZipInputStream(new BufferedInputStream(fileIn, 2048));

            ZipEntry entry = null;// 读取的压缩条目

			/* 解压缩开始 */
            while ((entry = in.getNextEntry()) != null) {
                decompression(entry, rootPath, in);// 解压
            }
            in.close();// 关闭输入流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能：解压
     *
     * @param entry
     *            解压条目
     * @param rootPath
     *            根目录
     * @throws Exception
     */
    private static void decompression(ZipEntry entry, String rootPath, ZipInputStream in) throws Exception {
		/* 如果是文件夹 */
        if ((entry.isDirectory() || -1 == entry.getName().lastIndexOf("."))) {
            File file = new File(rootPath + entry.getName().substring(0, entry.getName().length() - 1));
            file.mkdir();
        } else {
            File file = new File(rootPath + entry.getName());
            if (!file.exists())
                file.createNewFile();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file), 2048);
            int b;
            while ((b = in.read()) != -1) {
                bos.write(b);
            }
            bos.close();
        }
    }

    /**
     * Bitmap 转换成 byte[]
     *
     * @param bm
     * @return
     */
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 保存图片
     *
     * @param path
     * @param bitmap
     */
    public static void saveBitmap(String path, Bitmap bitmap) {
        try {
            File f = new File(path);
            if (f.exists())
                f.delete();
            f.createNewFile();
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
