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
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.yanzhenjie.mvp.sample.R;
import com.yanzhenjie.mvp.sample.entity.Page;
import com.yanzhenjie.mvp.sample.entity.UserInfo;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by YanZhenjie on 2018/1/30.
 */
public class UserListView extends Contract.UserListView {

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;

    private ListAdapter mAdapter;

    public UserListView(Activity activity, Contract.UserListPresenter presenter) {
        super(activity, presenter);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().refresh();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(Color.GRAY));
        mRecyclerView.useDefaultLoadMore();
        mRecyclerView.setLoadMoreListener(new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                getPresenter().loadMore();
            }
        });
        mRecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                getPresenter().clickItem(position);
            }
        });

        mAdapter = new ListAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setRefresh(boolean refresh) {
        mRefreshLayout.setRefreshing(refresh);
    }

    @Override
    public void bindDataList(List<UserInfo> dataList, Page page) {
        mAdapter.setUserInfoList(dataList);
        mAdapter.notifyDataSetChanged();

        boolean isEmpty = dataList == null || dataList.isEmpty();
        mRecyclerView.loadMoreFinish(isEmpty, page.getCurrentPage() < page.getPageCount());
    }

    @Override
    public void addDataList(Page page) {
        mRecyclerView.loadMoreFinish(false, page.getCurrentPage() < page.getPageCount());
    }
}