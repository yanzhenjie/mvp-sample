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
package com.yanzhenjie.mvp.sample.list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yanzhenjie.mvp.BaseModel;
import com.yanzhenjie.mvp.ModelResult;
import com.yanzhenjie.mvp.request.Request;
import com.yanzhenjie.mvp.sample.R;
import com.yanzhenjie.mvp.sample.URLConfig;
import com.yanzhenjie.mvp.sample.base.BaseActivity;
import com.yanzhenjie.mvp.sample.entity.ListWrapper;
import com.yanzhenjie.mvp.sample.entity.Page;
import com.yanzhenjie.mvp.sample.http.HttpModel;
import com.yanzhenjie.mvp.sample.entity.UserInfo;

import java.util.List;

/**
 * Created by YanZhenjie on 2018/1/30.
 */
public class UserListActivity extends BaseActivity implements Contract.UserListPresenter {

    private Contract.UserListView mView;
    private BaseModel mModel;

    private List<UserInfo> mUserInfoList;
    private Page mPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        mView = new UserListView(this, this);
        mModel = new HttpModel();

        mView.setRefresh(true);
        refresh();
    }

    @Override
    public void refresh() {
        Request request = new Request(URLConfig.URL_USER_LIST);
        mModel.load(request, new BaseModel.Listener<ListWrapper<UserInfo>>() {
            @Override
            public void onResult(ModelResult<ListWrapper<UserInfo>> result) {
                if (result.isSucceed()) {
                    ListWrapper<UserInfo> wrapper = result.getResult();
                    mUserInfoList = wrapper.getDataList();
                    mPage = wrapper.getPage();

                    mView.bindDataList(mUserInfoList, mPage);
                } else {
                    mView.toast(result.getMessage());
                }

                mView.setRefresh(false);
            }
        });
    }

    @Override
    public void loadMore() {
        Request request = new Request(URLConfig.URL_USER_LIST);
        mModel.load(request, new BaseModel.Listener<ListWrapper<UserInfo>>() {
            @Override
            public void onResult(ModelResult<ListWrapper<UserInfo>> result) {
                if (result.isSucceed()) {
                    ListWrapper<UserInfo> wrapper = result.getResult();
                    List<UserInfo> userInfoList = wrapper.getDataList();

                    if (userInfoList != null && !userInfoList.isEmpty()) {
                        mUserInfoList.addAll(userInfoList);
                        mPage = wrapper.getPage();
                    }
                } else {
                    mView.toast(result.getMessage());
                }

                mView.addDataList(mPage);
            }
        });
    }

    @Override
    public void clickItem(int position) {
        mView.toast(mView.getString(R.string.click_item, position));
    }
}