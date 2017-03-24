package web;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;


/**
 * post metodunu arka planda çalıştırmak için
 * bunu kullanacak actitivyler ThreadInterface interfacesi ile
 * implemente olmalı, çünkü
 * dönen değer postResult() metodune gönderiliyor.
 * Created by Cantekin on 28.7.2016.
 */

public class ThreadWebApiPost<T> extends AsyncTask<T, String, String> {

    private static final String TAG = "ThreadWebApiPost";
    private IThreadDelegete delegate = null;
    private final String webApiAddres;
    private String requestCode;
    private ImageView img;

    public ThreadWebApiPost(String requestCode, IThreadDelegete delegate, String webApiAddres, ImageView img) {
        this.delegate = delegate;
        this.webApiAddres = webApiAddres;
        this.requestCode = requestCode;
        this.img=img;
    }

    @Override
    protected String doInBackground(T... params) {
        return new RestApi<T>(webApiAddres).GET();
    }

    @Override
    protected void onPostExecute(String result) {
        if (delegate != null) {
            delegate.postResult(result,requestCode,img);
        } else {
            Log.e(TAG, "delegate is null");
        }
    }
}
