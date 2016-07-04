package com.gjjx.carvideo.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gjjx.carvideo.R;

import java.io.File;

/**
 * Created by ylbf_ on 2016/6/28.
 */
public class DBUtils {
    /**
     * 从外部获取数据库
     *
     * @param context
     * @return
     */
    public static Context getDbFromOut(final Context context) {
        return new ContextWrapper(context) {
            /**
             * 获得数据库路径，如果不存在，则创建对象对象
             *
             * @param name
             */
            @Override
            public File getDatabasePath(String name) {
                // 判断是否存在sd卡
                boolean sdExist = FileUtils.hasSDCardMounted();
                if (!sdExist) {// 如果不存在,
                    Log.e("SD卡管理：", "SD卡不存在，请加载SD卡");
                    return null;
                } else {// 如果存在
                    //  获取sd卡缓存路径设置数据库所在目录
                    String dbDir = FileUtils.getDiskCacheDir(context).getAbsolutePath()
                            + File.separator
                            + "database";
                    // 判断目录是否存在，不存在则创建该目录
                    File dirFile = new File(dbDir);
                    if (!dirFile.exists())
                        dirFile.mkdirs();

                    String dbPath = dbDir + File.separator + name;// 数据库路径

                    // 数据库文件是否创建成功
                    boolean isFileCreateSuccess = false;
                    // 判断文件是否存在，不存在则创建该文件
                    File dbFile = new File(dbPath);
                    if (!dbFile.exists()) {
                        isFileCreateSuccess = FileUtils.copyDb(context, dbFile.getAbsolutePath(), R.raw.db_gjjx);
                    } else
                        isFileCreateSuccess = true;
                    // 返回数据库文件对象
                    if (isFileCreateSuccess)
                        return dbFile;
                    else
                        return super.getDatabasePath(name);
                }
            }

            /**
             * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
             *
             * @param name
             * @param mode
             * @param factory
             */
            @Override
            public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
                return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
            }

            /**
             * Android 4.0会调用此方法获取数据库。
             *
             * @see android.content.ContextWrapper#openOrCreateDatabase(java.lang.String,
             *      int,
             *      android.database.sqlite.SQLiteDatabase.CursorFactory,
             *      android.database.DatabaseErrorHandler)
             * @param name
             * @param mode
             * @param factory
             * @param errorHandler
             */
            @Override
            public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
                return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
            }
        };
    }
}


