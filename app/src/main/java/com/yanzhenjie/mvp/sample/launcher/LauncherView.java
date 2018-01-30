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

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yanzhenjie.mvp.sample.R;
import com.yanzhenjie.mvp.sample.entity.UserInfo;
import com.yanzhenjie.mvp.sample.list.UserListActivity;
import com.yanzhenjie.mvp.sample.submit.SubmitActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by YanZhenjie on 2018/1/30.
 */
public class LauncherView extends Contract.LauncherView {

    @BindView(R.id.tv_message)
    TextView mTvMessage;

    public LauncherView(Activity activity, Contract.LauncherPresenter presenter) {
        super(activity, presenter);
    }

    @OnClick({
            R.id.btn_list, R.id.btn_submit
    })
    void onViewClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_list: {
                getContext().startActivity(new Intent(getContext(), UserListActivity.class));
                break;
            }
            case R.id.btn_submit: {
                getContext().startActivity(new Intent(getContext(), SubmitActivity.class));
                break;
            }
        }
    }

    @Override
    public void bindData(UserInfo userInfo) {
        mTvMessage.setText(userInfo.toString());
    }

    @Override
    protected void onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    protected void onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            toast("Setting");
        }
    }
}