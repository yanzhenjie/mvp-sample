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

import android.app.Activity;

import com.yanzhenjie.mvp.BasePresenter;
import com.yanzhenjie.mvp.BaseView;

/**
 * Created by YanZhenjie on 2018/1/30.
 */
public final class Contract {

    public interface SubmitPresenter extends BasePresenter {
        /**
         * 提交数据。
         */
        void submit(String name, String password);
    }

    public static abstract class SubmitView extends BaseView<SubmitPresenter> {

        public SubmitView(Activity activity, SubmitPresenter presenter) {
            super(activity, presenter);
        }

        /**
         * 用户名空。
         */
        public abstract void nameNull();

        /**
         * 密码空。
         */
        public abstract void passwordNull();
    }

}