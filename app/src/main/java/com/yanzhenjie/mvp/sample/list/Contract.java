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

import android.app.Activity;

import com.yanzhenjie.mvp.BasePresenter;
import com.yanzhenjie.mvp.BaseView;
import com.yanzhenjie.mvp.sample.entity.Page;
import com.yanzhenjie.mvp.sample.entity.UserInfo;

import java.util.List;

/**
 * Created by YanZhenjie on 2018/1/30.
 */
public final class Contract {

    public interface UserListPresenter extends BasePresenter {

        /**
         * 刷新数据。
         */
        void refresh();

        /**
         * 加载更多数据。
         */
        void loadMore();

        /**
         * 点击Item。
         */
        void clickItem(int position);
    }

    public static abstract class UserListView extends BaseView<UserListPresenter> {
        public UserListView(Activity activity, UserListPresenter presenter) {
            super(activity, presenter);
        }

        /**
         * 设置View刷新状态。
         */
        public abstract void setRefresh(boolean refresh);

        /**
         * 绑定数据源。
         */
        public abstract void bindDataList(List<UserInfo> dataList, Page page);

        /**
         * 向数据源添加数据。
         */
        public abstract void addDataList(Page page);
    }
}