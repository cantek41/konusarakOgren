package com.cantekin.demokonusarakogren;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Model.MediaRow;
import Model.ModelData;
import Model.MyListAdapter;
import web.IThreadDelegete;
import web.ThreadWebApiPost;

public class MainActivity extends AppCompatActivity implements IThreadDelegete {

    private static final String REQUEST_READ = "10001";
    private static final String webAddress = "https://www.wired.com/wp-json/wp/v2/posts?type=news";
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {

        new ThreadWebApiPost(REQUEST_READ, this, webAddress,null).execute();
        showProgress("Kaydediliyor");

    }

    public void showProgress(String msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            dismissProgress();
        mProgressDialog = ProgressDialog.show(this, getResources().getString(
                R.string.app_name), msg);
    }

    private void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    public void postResult(String data, String requestCode,ImageView img) {
        switch (requestCode) {
            case REQUEST_READ:
                data = data.replace("wp:", "").replace("\\\"", "").replace("\\n", "").replace("\\t", "");
                Type listType = new TypeToken<ArrayList<ModelData>>() {
                }.getType();
                List<ModelData> dd = new Gson().fromJson(data, listType);
                ListView listView = (ListView) findViewById(R.id.list);
                List<MediaRow> dataFive=new ArrayList<>();
                MediaRow row;
                for (ModelData news : dd) {
                    if(dd.indexOf(news)==5)
                        break;
                    row=new MediaRow();
                    row.setImgUrl(news.get_links().getFeaturedmedia().get(0).getHref());
                    row.setTitle(news.getTitle().getRendered());
                    row.setContent(news.getContent().getRendered());
                    row.setDate(news.getDate());
                    dataFive.add(row);

                }
                if(dataFive.size()>0) {
                    MyListAdapter adapter = new MyListAdapter(this, R.layout.row_list, dataFive);
                    listView.setAdapter(adapter);
                }
                break;
        }
        dismissProgress();
    }

}
