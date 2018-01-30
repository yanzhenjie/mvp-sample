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
package com.yanzhenjie.mvp.sample.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by YanZhenjie on 2018/1/30.
 */
public class ListWrapper<Data> implements Parcelable {

    private List<Data> mDataList;
    private Page mPage;

    public ListWrapper() {
    }

    protected ListWrapper(Parcel in) {
        mPage = in.readParcelable(Page.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mPage, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListWrapper> CREATOR = new Creator<ListWrapper>() {
        @Override
        public ListWrapper createFromParcel(Parcel in) {
            return new ListWrapper(in);
        }

        @Override
        public ListWrapper[] newArray(int size) {
            return new ListWrapper[size];
        }
    };

    public List<Data> getDataList() {
        return mDataList;
    }

    public void setDataList(List<Data> dataList) {
        mDataList = dataList;
    }

    public Page getPage() {
        return mPage;
    }

    public void setPage(Page page) {
        mPage = page;
    }
}