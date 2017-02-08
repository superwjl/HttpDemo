package com.tik.httpdemo.fragment;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.tik.httpdemo.base.BaseFragment;
import com.tik.httpdemo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;


public class HttpUrlConnFragment extends BaseFragment {

    private String baseUrl = "http://www.weather.com.cn/data/sk/101010100.html";

    @BindView(R.id.show)
    TextView tvShow;

    @Override
    protected void afterBindViews() {
        Log.i("tag", "httpurlconn ----> afterBindViewS");
        start = System.currentTimeMillis();
        new MyAsyncTask().execute(baseUrl);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_each;
    }

    class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            InputStream mInputStream = null;
            HttpURLConnection connection = null;
            String result = "";
            try {
                URL mUrl = new URL(params[0]);
                connection = (HttpURLConnection) mUrl.openConnection();
                connection.setConnectTimeout(20000);
                connection.setReadTimeout(40000);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Charset", "utf-8");
                connection.setRequestProperty("Content-Length", "0");
                connection.connect();
                int statusCode = connection.getResponseCode();
                String response = connection.getResponseMessage();
                mInputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(mInputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

                result = "StatusCode: " + statusCode + "\n"
                        + "Response: " + response + "\n"
                        + sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            long end = System.currentTimeMillis() - start;
            tvShow.setText(s + "\n耗时："+end+"ms");
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            start = System.currentTimeMillis();
            new MyAsyncTask().execute(baseUrl);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("tag", "httpurlconn --> onDestroy");
    }

}
