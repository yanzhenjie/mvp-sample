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
package com.yanzhenjie.mvp.sample.submit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.yanzhenjie.mvp.BaseModel;
import com.yanzhenjie.mvp.ModelResult;
import com.yanzhenjie.mvp.request.Request;
import com.yanzhenjie.mvp.sample.R;
import com.yanzhenjie.mvp.sample.base.BaseActivity;
import com.yanzhenjie.mvp.sample.http.HttpModel;

/**
 * Created by YanZhenjie on 2018/1/30.
 */
public class SubmitActivity extends BaseActivity implements Contract.SubmitPresenter {

    private Contract.SubmitView mView;
    private BaseModel mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        mView = new SubmitView(this, this);
        mModel = new HttpModel();
    }

    @Override
    public void submit(String name, String password) {
        if (TextUtils.isEmpty(name)) {
            mView.nameNull();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mView.passwordNull();
            return;
        }

        Request request = new Request("");
        mModel.load(request, new BaseModel.Listener<Object>() {
            @Override
            public void onResult(ModelResult<Object> result) {
                mView.toast(R.string.submit_succeed);
            }
        });
    }
}