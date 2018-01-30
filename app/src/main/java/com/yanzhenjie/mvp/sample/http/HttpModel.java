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
package com.yanzhenjie.mvp.sample.http;

import com.yanzhenjie.mvp.BaseModel;
import com.yanzhenjie.mvp.ModelResult;
import com.yanzhenjie.mvp.request.Request;
import com.yanzhenjie.mvp.sample.URLConfig;
import com.yanzhenjie.mvp.sample.entity.ListWrapper;
import com.yanzhenjie.mvp.sample.entity.Page;
import com.yanzhenjie.mvp.sample.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanZhenjie on 2018/1/30.
 */
public class HttpModel implements BaseModel {

    public HttpModel() {
    }

    @Override
    public <T> void load(Request request, Listener<T> listener) {
        String url = request.getUrl();
        switch (url) {
            case URLConfig.URL_USER: {
                UserInfo userInfo = new UserInfo();
                userInfo.setAvatar("我是头像");
                userInfo.setName("我是名称");
                userInfo.setExplain("我是说明");

                T t = (T) userInfo; // 模拟FastJson解析数据成entity.
                ModelResult<T> modelResult = new ModelResult<>(t);
                listener.onResult(modelResult);
                break;
            }
            case URLConfig.URL_USER_LIST: {
                ListWrapper<UserInfo> listWrapper = new ListWrapper<>();
                List<UserInfo> userInfoList = new ArrayList<>();
                for (int i = 0; i < 60; i++) {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setAvatar("我是头像");
                    userInfo.setName("我是名称");
                    userInfo.setExplain("我是说明");
                    userInfoList.add(userInfo);
                }
                listWrapper.setDataList(userInfoList);
                Page page = new Page();
                page.setCurrentPage(1);
                page.setItemCount(60);
                page.setPageCount(10);
                listWrapper.setPage(page);

                T t = (T) listWrapper; // 模拟解析数据成 list + page.
                ModelResult<T> modelResult = new ModelResult<>(t);
                listener.onResult(modelResult);
                break;
            }
            default: {
                T t = (T) new Object();
                ModelResult<T> modelResult = new ModelResult<>(t);
                listener.onResult(modelResult);
                break;
            }
        }
    }

    @Override
    public <T> void loadMore(Request request, Listener<T> listener) {
        String url = request.getUrl();
        switch (url) {
            case URLConfig.URL_USER: {
                UserInfo userInfo = new UserInfo();
                userInfo.setAvatar("我是头像");
                userInfo.setName("我是名称");
                userInfo.setExplain("我是说明");

                T t = (T) userInfo; // 模拟FastJson解析数据成entity.
                ModelResult<T> modelResult = new ModelResult<>(t);
                listener.onResult(modelResult);
                break;
            }
            case URLConfig.URL_USER_LIST: {
                ListWrapper<UserInfo> listWrapper = new ListWrapper<>();
                List<UserInfo> userInfoList = new ArrayList<>();
                for (int i = 0; i < 60; i++) {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setAvatar("我是头像");
                    userInfo.setName("我是名称");
                    userInfo.setExplain("我是说明");
                    userInfoList.add(userInfo);
                }
                listWrapper.setDataList(userInfoList);
                Page page = new Page();
                page.setCurrentPage(1);
                page.setItemCount(60);
                page.setPageCount(10);
                listWrapper.setPage(page);

                T t = (T) listWrapper; // 模拟解析数据成 list + page.
                ModelResult<T> modelResult = new ModelResult<>(t);
                listener.onResult(modelResult);
                break;
            }
        }
    }
}