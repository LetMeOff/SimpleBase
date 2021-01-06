package com.zjh.simplebase.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * base viewModel
 *
 * @author zhujianhua
 * on 2021/1/6
 */
public class BaseViewModel extends AndroidViewModel implements IBaseViewModel {

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }
}
