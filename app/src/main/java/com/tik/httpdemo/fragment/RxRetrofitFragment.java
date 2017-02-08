package com.tik.httpdemo.fragment;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.tik.httpdemo.base.BaseFragment;
import com.tik.httpdemo.retrofit.HttpService;
import com.tik.httpdemo.R;
import com.tik.httpdemo.bean.WeatherBean;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RxRetrofitFragment extends BaseFragment {

    private String baseUrl2 = "http://www.weather.com.cn/data/";

    @BindView(R.id.show)
    TextView tvShow;

    @Override
    protected void afterBindViews() {
        Log.i("tag", "rxretrofit ----> afterBindViewS");
        start = System.currentTimeMillis();
        rxRetrofit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_each;
    }

    private void rxRetrofit(){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl2)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        HttpService service = retrofit.create(HttpService.class);
        final Call<WeatherBean> call = service.getDataString();
        Observable myObservable = Observable.create(new Observable.OnSubscribe<WeatherBean>() {
            @Override
            public void call(Subscriber<? super WeatherBean> subscriber) {
                try {
                    Response<WeatherBean> bean = call.execute();
                    subscriber.onNext(bean.body());
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        });
        myObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherBean>() {
                    @Override
                    public void onCompleted() {
                        Log.i("", "----rx retrofit onComplete------");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(WeatherBean weatherBean) {
                        long end = System.currentTimeMillis() - start;
                        tvShow.setText(weatherBean.toString() + "\n耗时："+end+"ms");
                    }

                });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            start = System.currentTimeMillis();
            rxRetrofit();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("tag", "rxretrofit --> onDestroy");
    }
}
