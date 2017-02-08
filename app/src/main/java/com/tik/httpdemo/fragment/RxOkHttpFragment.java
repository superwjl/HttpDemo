package com.tik.httpdemo.fragment;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.tik.httpdemo.base.BaseFragment;
import com.tik.httpdemo.R;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RxOkHttpFragment extends BaseFragment {

    private String baseUrl = "http://www.weather.com.cn/data/sk/101010100.html";

    @BindView(R.id.show)
    TextView tvShow;

    @Override
    protected void afterBindViews() {
        Log.i("tag", "rxokhttp ---> afterBindViews");
        start = System.currentTimeMillis();
        rxOkHttp();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_each;
    }

    private void rxOkHttp(){
        final OkHttpClient client = new OkHttpClient.Builder().build();
        final Request request = new Request.Builder()
                .url(baseUrl)
                .method("GET", null)
                .build();
        Observable myObservable = Observable.create(new Observable.OnSubscribe<okhttp3.Response>() {
            @Override
            public void call(Subscriber<? super Response> subscriber) {
                try {
                    okhttp3.Response bean = client.newCall(request).execute();
                    subscriber.onNext(bean);
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }

        });
        myObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<okhttp3.Response>() {
                    @Override
                    public void onCompleted() {
                        Log.i("", "----rx okhttp onComplete------");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(okhttp3.Response response) {
                        try {
                            long end = System.currentTimeMillis() - start;
                            tvShow.setText(response.body().string() + "\n耗时："+end+"ms");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            start = System.currentTimeMillis();
            rxOkHttp();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("tag", "rxokhttp --> onDestroy");
    }
}
