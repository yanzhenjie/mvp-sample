# mvp-sample
一个MVP的例子，当然稍微和传统上常见的MVP结构不一样，不然就不会分享到Github了。  

现在Activity/Fragment不再作为View了，而是作为Presenter，View单独提出去，Moduel也是单独提出去的。这样做的好处是扩展了Presenter的能力和灵活性，也就是说控制器的能力也附加给Presenter了（比如把Activity/Fragment的能力附加给它），一个功能的业务逻辑完全由Presenter来掌控。  

在Demo中提供了三种常见的形式的例子，还有一个Fragment的用法的例子，不想看我啰嗦的人直接下载代码看看。也许你已经看过这种方式的例子了，但是如果你真正看过这个例子的代码后，你会发现大不相同，尤其在很多细节方面。

## 特点
本项目最大的特点就在View的处理上，我设计的理念是把View当作一个Android中的一个普通的`android.view.View`来看待，而真正的主角是Presenter，由Presenter来管理业务和数据（Model）。

这次我们把View定义成了抽象类，而不是接口，Presenter还是接口，Module也是接口。

下面举一个遵循本例的设计思路时，以Activity作为Presenter的例子（Fragment的时候也是完全相同的），我们先定义Contract：
```java
public final class Contract {

    public interface LauncherPresenter extends BasePresenter {
    }

    public static abstract class LauncherView extends BaseView<LauncherPresenter> {
        public LauncherView(Activity activity, LauncherPresenter presenter) {
            super(activity, presenter);
        }
        
        /**
         * 绑定数据源。
         */
        public abstract void bindData(UserInfo userInfo);
    }
}
```

实际上和一般的定义没有什么区别，接着来看看Presenter的实现：
```java
public class LauncherActivity extends BaseActivity implements Contract.LauncherPresenter {

    private Contract.LauncherView mView;
    private BaseModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        mView = new LauncherView(this, this); // 初始化View，只需一句。
        mModel = new HttpModel(); // 初始化Model。

        request();
    }

    /**
     * 利用Module来请求数据，各自有各自的框架实现即可。
     */
    private void request() {
        Request request = new Request(URLConfig.URL_USER);
        mModel.load(request, new BaseModel.Listener<UserInfo>() {
            @Override
            public void onResult(ModelResult<UserInfo> result) {
                if (result.isSucceed()) {
                    UserInfo userInfo = result.getResult();
                    mView.bindData(userInfo); // 请求完成，绑定数据。
                } else {
                    mView.toast(result.getMessage());
                }
            }
        });
    }
}
```

接下来是重头戏，也就是View的实现：
```java
public class LauncherView extends Contract.LauncherView {

    @BindView(R.id.tv_message)
    TextView mTvMessage;

    public LauncherView(Activity activity, Contract.LauncherPresenter presenter) {
        super(activity, presenter);
    }

    @Override
    public void bindData(UserInfo userInfo) {
        mTvMessage.setText(userInfo.toString());
    }
}
```

这里大家如果看的仔细的话应该发现了，少了很多很多代码，什么View的bind，View的unBind都没有了，因为再底层已经都替你做好了。不过这只是一个小细节，这里的View而且还可以直接加载Menu：
```java
@Override
protected void onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
}

@Override
protected void onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_settings) {
        // Setting...
    }
}
```

比在Activity中时还要简单许多，实现的方式却是一毛一样的。当然它绝不仅仅如此，它还可以有和Activity/Fragment同步的生命周期:
```java
public class LauncherView extends Contract.LauncherView {

    @BindView(R.id.tv_message)
    TextView mTvMessage;

    public LauncherView(Activity activity, Contract.LauncherPresenter presenter) {
        super(activity, presenter);
    }

    @Override
    protected void onResume() {
    }

    @Override
    protected void onPause() {
    }

    @Override
    protected void onStop() {
    }

    @Override
    protected void onDestroy() {
        // 可以做一些特殊处理，比如释放webView等。
    }
}
```

也许你以为这里就结束，然后并没有，它还可以更加简单的获取一些资源，比如color、string、array，而我们再调用时也不需要考虑方法的兼容性：
```java
int colorAccent = getColor(R.color.colorAccent); // 获取颜色。
CharSequence text = getText(R.string.app_name); // 获取Text。
String appName = getString(R.string.app_name); // 获取String。
String appNameFormat = getString(R.string.app_name, "我是可以Format的"); // 获取并格式化String。
String[] orderMenu = getStringArray(R.array.order_menu); // 获取StringArray。
int[] itemSort = getIntArray(R.array.item_sort); // 获取IntegerArray。
Drawable drawable = getDrawable(R.mipmap.ic_launcher); // 获取Drawable。
```

如果你愿意，你也可以在BaseView中添加更多你认为可取的方法，比如它可以提供一些常用的Dialog和消息提示：
```java
void showMessageDialog(); // 这个方法有很多实现，具体请看源码。

void toast(String message); // 同上。

void snackBar(String message); // 同上。
```

## 完整案例
下面是一个列表的完整案例，有下拉刷新、加载更多、点击反馈等。  

Contract:
```java
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
```

Presenter:
```java
public class UserListActivity extends BaseActivity implements Contract.UserListPresenter {

    private Contract.UserListView mView;
    private BaseModel mModel;

    private List<UserInfo> mUserInfoList; // 记录当前功能块的数据。
    private Page mPage; // 记录当前页码信息。

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
                if (result.isSucceed()) { // 请求成功。
                    ListWrapper<UserInfo> wrapper = result.getResult();
                    mUserInfoList = wrapper.getDataList(); // 拿到数据。
                    mPage = wrapper.getPage(); // 拿到页码。

                    mView.bindDataList(mUserInfoList, mPage); // 为View绑定数据源。
                } else {
                    mView.toast(result.getMessage()); // 失败了，提示错误信息。
                }

                mView.setRefresh(false); // 无论成功与否，让View停止刷新。
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
                    List<UserInfo> userInfoList = wrapper.getDataList(); // 拿到下一页数据。

                    // 如果这一页数据不为空，那么添加到数据源，并记录页码。
                    if (userInfoList != null && !userInfoList.isEmpty()) {
                        mUserInfoList.addAll(userInfoList);
                        mPage = wrapper.getPage();
                    }
                } else {
                    mView.toast(result.getMessage()); // 加载更多失败，提示错误。
                }

                // 无论是否成功加载下一页，停止加载更多，更新页面。
                mView.addDataList(mPage);
            }
        });
    }

    @Override
    public void clickItem(int position) {
        mView.toast(mView.getString(R.string.click_item, position));
    }
}
```

View:
```java
public class UserListView extends Contract.UserListView {

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;

    private ListAdapter mAdapter;

    public UserListView(Activity activity, Contract.UserListPresenter presenter) {
        super(activity, presenter);
        // 监听下拉刷新。
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().refresh();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(Color.GRAY));
        mRecyclerView.useDefaultLoadMore();
        // 监听加载更多。
        mRecyclerView.setLoadMoreListener(new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                getPresenter().loadMore();
            }
        });
        // 监听Item的点击。
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
        // 更新数据。
        mAdapter.setUserInfoList(dataList);
        mAdapter.notifyDataSetChanged();

        // 设置是否有下一页或者是否是空数据信息。
        boolean isEmpty = dataList == null || dataList.isEmpty();
        mRecyclerView.loadMoreFinish(isEmpty, page.getCurrentPage() < page.getPageCount());
    }

    @Override
    public void addDataList(Page page) {
        // 更新数据，并更新是否有下一页。
        mAdapter.notifyDataSetChanged();
        mRecyclerView.loadMoreFinish(false, page.getCurrentPage() < page.getPageCount());
    }
}
```

## License
```text
Copyright 2018 Yan Zhenjie

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```