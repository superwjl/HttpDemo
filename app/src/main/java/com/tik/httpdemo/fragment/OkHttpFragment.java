package com.tik.httpdemo.fragment;


import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.tik.httpdemo.base.BaseFragment;
import com.tik.httpdemo.R;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpFragment extends BaseFragment {

    private String baseUrl = "http://www.weather.com.cn/data/sk/101010100.html";

    @BindView(R.id.show)
    TextView tvShow;

    @Override
    protected void afterBindViews() {
        Log.i("tag", "okhttp ----> afterBindViewS");
        start = System.currentTimeMillis();
        okhttp();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_each;
    }

    private void okhttp(){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseUrl)
                .method("GET", null)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = mHandler.obtainMessage(0);
                msg.sendToTarget();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = mHandler.obtainMessage(1);
                msg.obj = response.body().string();
                msg.sendToTarget();
            }
        });
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Toast.makeText(getActivity(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    long end = System.currentTimeMillis() - start;
                    tvShow.setText(msg.obj.toString() + "\n耗时："+end+"ms");
                    break;
            }
        }
    };

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            start = System.currentTimeMillis();
            okhttp();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("tag", "okhttp --> onDestroy");
    }
}
