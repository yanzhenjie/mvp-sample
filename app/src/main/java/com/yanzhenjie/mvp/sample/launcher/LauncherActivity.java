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
package com.yanzhenjie.mvp.sample.launcher;

import android.os.Bundle;

import com.yanzhenjie.mvp.BaseModel;
import com.yanzhenjie.mvp.ModelResult;
import com.yanzhenjie.mvp.request.Request;
import com.yanzhenjie.mvp.sample.R;
import com.yanzhenjie.mvp.sample.URLConfig;
import com.yanzhenjie.mvp.sample.base.BaseActivity;
import com.yanzhenjie.mvp.sample.http.HttpModel;
import com.yanzhenjie.mvp.sample.entity.UserInfo;

/**
 * Created by YanZhenjie on 2018/1/30.
 */
public class LauncherActivity extends BaseActivity implements Contract.LauncherPresenter {

    private Contract.LauncherView mView;
    private BaseModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        mView = new LauncherView(this, this);
        mModel = new HttpModel();

        request();
    }

    private void request() {
        Request request = new Request(URLConfig.URL_USER);
        mModel.load(request, new BaseModel.Listener<UserInfo>() {
            @Override
            public void onResult(ModelResult<UserInfo> result) {
                if (result.isSucceed()) {
                    UserInfo userInfo = result.getResult();
                    mView.bindData(userInfo);
                } else {
                    mView.toast(result.getMessage());
                }
            }
        });
    }
}
