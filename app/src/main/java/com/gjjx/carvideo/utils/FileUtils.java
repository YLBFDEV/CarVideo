package com.gjjx.carvideo.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ylbf_ on 2016/6/28.
 */
public class FileUtils {
    public static String getDataFolderPath(Context paramContext) {
        return Environment.getDataDirectory() + "/data/" + paramContext.getPackageName() + "/files";
    }

    public static String getMyFileDir(Context context) {
        return context.getFilesDir().toString();
    }

    public static String getMyCacheDir(Context context) {
        return context.getCacheDir().toString();
    }

    /**
     * @desc 保存内容到文件中  * @param fileName  * @param content  * @throws Exception
     */
    public static void save(Context context, String fileName, String content, int module) {
        try {
            FileOutputStream os = context.openFileOutput(fileName, module);
            os.write(content.getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @desc 读取文件内容  * @param fileName  * @return
     */
    public static String read(Context context, String fileName) {

        try {
            FileInputStream fis = context.openFileInput(fileName);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = fis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            byte[] data = bos.toByteArray();
            fis.close();
            bos.close();
            return new String(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @desc 将文本内容保存到sd卡的文件中  * @param context  * @param fileName  * @param content  * @throws IOException
     */
    public static void saveToSDCard(Context context, String fileName, String content) throws IOException {

        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(content.getBytes());
        fos.close();
    }

    /**
     * @desc 读取sd卡文件内容  * @param fileName  * @return  * @throws IOException
     */
    public static String readSDCard(String fileName) throws IOException {

        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        byte[] data = bos.toByteArray();
        fis.close();
        bos.close();

        return new String(data);
    }

    /**
     * 获取缓存路径，存储临时文件，可被一键清理和卸载清理
     * 可以看到，当SD卡存在或者SD卡不可被移除的时候，
     * 就调用getExternalCacheDir()方法来获取缓存路径，
     * 否则就调用getCacheDir()方法来获取缓存路径。
     * 前者获取到的就是/sdcard/Android/data/<application package>/cache 这个路径，
     * 而后者获取到的是 /data/data/<application package>/cache 这个路径。
     *
     * @param context
     * @param uniqueName
     * @return
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /*返回缓存路径*/
    public static File getDiskCacheDir(Context context) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath);
    }

    /*判断sd卡可用*/
    public static boolean hasSDCardMounted() {
        String state = Environment.getExternalStorageState();
        if (state != null && state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param dbPath 需要拷贝的文件位置
     * @param rawDb  本地Raw中的文件
     */
    public static boolean copyDb(Context context, String dbPath, int rawDb) {
        if (TextUtils.isEmpty(dbPath)) {
            return false;
        }
        if (rawDb == 0) {
            return false;
        }
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = context.getResources().openRawResource(rawDb);
            fos = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024 * 8];
            int count = 0;
            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File dbFile = new File(dbPath);
        if (dbFile.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
