package com.cantekin.demokonusarakogren;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import Model.Image.ModelDataImage;
import Model.MediaRow;
import web.IThreadDelegete;
import web.ThreadWebApiPost;

public class DetailActivity extends AppCompatActivity implements IThreadDelegete {
    private String textData;
    private List<String> wordLists;
    Button btnAnaliz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        MediaRow row = (MediaRow) intent.getExtras().getSerializable("data");
        TextView detail = (TextView) findViewById(R.id.txtDetil);
        detail.setText(Html.fromHtml(row.getContent()));
        textData = detail.getText().toString();
        new ThreadWebApiPost("10001", this, row.getImgUrl(), null).execute();
        getSupportActionBar().setTitle(Html.fromHtml(row.getTitle()));
        analiz();
        btnAnaliz = (Button) findViewById(R.id.btnAnaliz);
        btnAnaliz.setEnabled(false);
        btnAnaliz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] words = wordLists.toArray(new String[wordLists.size()]);

                final AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setTitle("Analiz");
                builder.setItems(words, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // I want to write my code here
                    }
                }).create().show();

            }
        });
    }

    @Override
    public void postResult(String data, String requestCode, ImageView imageView) {
        if (requestCode.equals("10001")) {
            ImageView img = (ImageView) findViewById(R.id.imgDetail);
            ModelDataImage dd = new Gson().fromJson(data, ModelDataImage.class);
            Picasso.with(this).load(dd.getMedia_details().getSizes().getMedium().getSource_url()).into(img);
        } else {
            data=data.replace("_mstc2([","").replace("]);","");
            String key="";
            try {
                key=new JSONObject(data).getString("TranslatedText");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            wordLists.add(requestCode + "=" + key);
            if(wordLists.size()==5)
                btnAnaliz.setEnabled(true);

        }
    }

    public void analiz() {
        textData = textData.replace("\"", "").replace("'", "").replace("-", " ").replace("\\n", " ").replace(".", " ").replace(",", " ").replace(";", " ").replace(":", " ").toLowerCase();
        List<String> list = Arrays.asList(textData.split(" "));

        Set<String> uniqueWords = new HashSet<>(list);
        Map<String, Integer> map = new TreeMap<>();
        for (String word : uniqueWords) {
            if (word.length() > 3)
                map.put(word, Collections.frequency(list, word));
        }
        List<Map.Entry<String, Integer>> list1 = MapUtil.entriesSortedByValues(map);
        wordLists = new ArrayList<>();
        wordLists.add("Karakter sayısı 3 üzeri olan kelimeler işleme alınmıştır");
        int i = 0;
        for (Map.Entry<String, Integer> entry : list1) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            String addess = "https://api.microsofttranslator.com/v2/ajax.svc/TranslateArray?appId=\"T5uWi86iQEDukekMWQlXe1vn9QNlEWDN5J7nnPmfVKyg*\"&texts=[\"" + key + "\"]&from=\"en\"&to=\"tr\"&oncomplete=_mstc2&onerror=_mste2&loc=en&ctr=&ref=WidgetV2&rgp=1adbff21";
            new ThreadWebApiPost(key + "(" + value + ")", this, addess, null).execute();
            if (i >= 4)
                break;
            i++;
        }
    }
}

class MapUtil {
    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    static <K, V extends Comparable<? super V>>
    List<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {

        List<Map.Entry<K, V>> sortedEntries = new ArrayList<Map.Entry<K, V>>(map.entrySet());

        Collections.sort(sortedEntries,
                new Comparator<Map.Entry<K, V>>() {
                    @Override
                    public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
                        return e2.getValue().compareTo(e1.getValue());
                    }
                }
        );

        return sortedEntries;
    }
}
