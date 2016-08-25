package org.project.base;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by wiesen on 2016/8/16.
 */
public class App extends Application {
    private static App mApp;
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        initImageLoader();
    }

    public static App getInstance(){
        return mApp;
    }


    private void initImageLoader(){
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .threadPoolSize(4)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 MiB
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .writeDebugLogs()
                .memoryCache(new WeakMemoryCache())
                ; // Remove for release app

        ImageLoader.getInstance().init(config.build());
    }

}
