package com.gjjx.carvideo;

import android.app.Application;
import android.content.Context;

import com.gjjx.carvideo.db.DaoMaster;
import com.gjjx.carvideo.db.DaoSession;
import com.socks.library.KLog;

/**
 * Created by ylbf_ on 2016/6/28.
 */
public class App extends Application {
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static final String DATABASE_NAME = "db_gjjx.db";
    //public static final String BASE_URL = "http://139.129.24.67:80/";
    public static final String BASE_URL = "http://115.28.213.49:80/";
    public static boolean IS_INSIDE_PLAYER = true;

    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(false);
    }

    /**
     * 取得DaoMaster
     *
     * @param context
     *
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, DATABASE_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context
     *
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
