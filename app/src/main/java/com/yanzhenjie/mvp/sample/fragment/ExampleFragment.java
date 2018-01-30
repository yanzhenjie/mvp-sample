/*
 * Copyright © Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.mvp.sample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanzhenjie.mvp.BaseModel;
import com.yanzhenjie.mvp.sample.R;
import com.yanzhenjie.mvp.sample.base.BaseFragment;
import com.yanzhenjie.mvp.sample.http.HttpModel;

/**
 * <p>
 *      这个Fragment没有被使用，这里只是给出Fragment初始化View的例子，其它用法同Activity。
 * </p>
 * Created by YanZhenjie on 2018/1/30.
 */
public class ExampleFragment extends BaseFragment implements Contract.FragmentPresenter {

    private Contract.FragmentView mView;
    private BaseModel mModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_launcher, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mView = new FragmentView(view, this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mModel = new HttpModel();

        request();
    }

    private void request() {
        // mView.set...
    }
}