package org.project.net.config;

/**
 * Created by wiesen on 2016/11/15.
 */

public interface CallBackResponse<T> {
    void onSuccess(T t);
    void onFail(String msg);
}
