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
package com.yanzhenjie.mvp.request;

/**
 * <p>
 *     这个Request只是用来模拟请求数据的，从本地或者从网络，开发者可以具体修改实现。
 * </p>
 * Created by YanZhenjie on 2018/1/30.
 */
public class Request {

    private String mUrl;

    public Request(String url) {
        this.mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }
}