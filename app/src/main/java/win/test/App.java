package win.test;

import android.app.Application;

/**
 * Created by win on 2016/12/21.
 */

public class App extends Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInstance(this);

    }

   public static String userId="";

    //  初始化
    public static synchronized void initializeInstance(Application application) {
        if (instance == null) {
            instance = application;
        }

    }

    // 获得  Application  的对象   同步保证了线程安全
    public static synchronized Application getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Application没有初始化");
        } else {
            return instance;
        }
    }


}
