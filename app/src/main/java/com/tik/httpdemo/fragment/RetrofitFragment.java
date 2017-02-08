package com.tik.httpdemo.fragment;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.tik.httpdemo.base.BaseFragment;
import com.tik.httpdemo.retrofit.HttpService;
import com.tik.httpdemo.R;
import com.tik.httpdemo.bean.WeatherBean;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitFragment extends BaseFragment {

    private String baseUrl2 = "http://www.weather.com.cn/data/";

    @BindView(R.id.show)
    TextView tvShow;

    @Override
    protected void afterBindViews() {
        Log.i("tag", "retrofit ----> afterBindViewS");
        start = System.currentTimeMillis();
        retrofitHttp();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_each;
    }

    private void retrofitHttp() {
        //baseurl必须以'/'结尾
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl2)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        HttpService service = retrofit.create(HttpService.class);
        Call<WeatherBean> call = service.getDataString();
        call.enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                long end = System.currentTimeMillis() - start;
                tvShow.setText(response.body().toString() + "\n耗时："+end+"ms");
            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            start = System.currentTimeMillis();
            retrofitHttp();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("tag", "retrofit --> onDestroy");
    }

}
