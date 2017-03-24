package web;

import android.widget.ImageView;

/**
 * Created by Cantekin on 28.7.2016.
 * ThreadPost kullanabilmek için activitylerin bu interfaceyi
 * Implemente etmesi gerekir
 */

public interface IThreadDelegete {
    void postResult(String data, String requestCode,ImageView imageView);
}
