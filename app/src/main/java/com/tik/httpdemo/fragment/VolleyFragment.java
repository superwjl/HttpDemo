package com.tik.httpdemo.fragment;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tik.httpdemo.base.BaseFragment;
import com.tik.httpdemo.volley.MyStringRequest;
import com.tik.httpdemo.R;
import com.tik.httpdemo.volley.RequestManager;

import butterknife.BindView;


public class VolleyFragment extends BaseFragment {

    private String baseUrl = "http://www.weather.com.cn/data/sk/101010100.html";


    @BindView(R.id.show)
    TextView tvShow;

    @Override
    protected void afterBindViews() {
        Log.i("tag", "volley ----> afterBindViewS");
        start = System.currentTimeMillis();
        volleyHttp();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_each;
    }

    private void volleyHttp(){
        final MyStringRequest request = new MyStringRequest(Request.Method.GET, baseUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        long end = System.currentTimeMillis() - start;
                        tvShow.setText(s + "\n耗时："+end+"ms");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestManager.addRequest(request, this);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            start = System.currentTimeMillis();
            volleyHttp();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("tag", "volley --> onDestroy");
    }
}
