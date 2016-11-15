package org.project.base;

import android.app.Application;



/**
 * Created by wiesen on 2016/8/16.
 */
public class App extends Application {
    private static App mApp;
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static App getInstance(){
        return mApp;
    }


}
