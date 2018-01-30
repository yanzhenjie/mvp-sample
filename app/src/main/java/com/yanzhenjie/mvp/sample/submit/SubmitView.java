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
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.yanzhenjie.mvp.sample.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by YanZhenjie on 2018/1/30.
 */
public class SubmitView extends Contract.SubmitView {

    @BindView(R.id.layout_name)
    TextInputLayout mLayoutName;
    @BindView(R.id.layout_password)
    TextInputLayout mLayoutPassword;

    @BindView(R.id.edt_name)
    TextInputEditText mEdtName;
    @BindView(R.id.edt_password)
    TextInputEditText mEdtPassword;

    public SubmitView(Activity activity, Contract.SubmitPresenter presenter) {
        super(activity, presenter);
        mEdtName.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mLayoutName.setErrorEnabled(false);
            }
        });
        mEdtPassword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mLayoutName.setErrorEnabled(false);
            }
        });
    }

    @OnClick({
            R.id.btn_submit
    })
    void onViewClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_submit: {
                closeInputMethod();
                getPresenter().submit(mEdtName.getText().toString(), mEdtPassword.getText().toString());
                break;
            }
        }
    }

    @Override
    public void nameNull() {
        mLayoutName.setError(getString(R.string.name_null));
    }

    @Override
    public void passwordNull() {
        mLayoutPassword.setError(getString(R.string.password_null));
    }

    private static class SimpleTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}