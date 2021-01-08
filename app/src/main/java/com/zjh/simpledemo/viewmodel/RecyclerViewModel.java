package com.zjh.simpledemo.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.zjh.simplebase.base.BaseViewModel;
import com.zjh.simplebase.inter.OnItemClickInterface;
import com.zjh.simplebase.util.LogUtils;
import com.zjh.simplebase.util.OnSingleClickListener;
import com.zjh.simpledemo.BR;
import com.zjh.simpledemo.R;
import com.zjh.simpledemo.model.MultiTypeInfo;
import com.zjh.simpledemo.model.RecyclerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhujianhua
 * on 2021/1/8
 */
public class RecyclerViewModel extends BaseViewModel {

    public MutableLiveData<List<RecyclerModel>> dataList = new MutableLiveData<>();
    public int recyclerBrId = BR.recyclerInfo;
    public int recyclerLayoutId = R.layout.adapter_recycler;
    public int listNum = 1;
    public List<RecyclerModel> list = new ArrayList<>();
    public List<MultiTypeInfo> multiList = new ArrayList<>();

    public RecyclerViewModel(@NonNull Application application) {
        super(application);

        init();
    }


    private void init() {
        for (int i = 0; i < 3; i++) {
            list.add(new RecyclerModel("item" + listNum));
            listNum++;
        }
        dataList.postValue(list);

        multiList.add(new MultiTypeInfo(1, "类型1"));
        multiList.add(new MultiTypeInfo(2, "类型2"));
    }

    public void add() {
        list.add(new RecyclerModel("item" + listNum));
        listNum++;
        dataList.postValue(list);
    }

    /**
     * 单次点击事件
     */
    public OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View view) {
            add();
        }
    };

    /**
     * item点击事件
     */
    public OnItemClickInterface onItemClickInterface = (view, position) -> LogUtils.i("click position : " + position);

}
