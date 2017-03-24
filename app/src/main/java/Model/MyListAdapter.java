package Model;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cantekin.demokonusarakogren.DetailActivity;
import com.cantekin.demokonusarakogren.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import java.util.List;
import Model.Image.ModelDataImage;
import web.IThreadDelegete;
import web.ThreadWebApiPost;


/**
 * Created by Cantekin on 24.3.2017.
 */

public class MyListAdapter extends ArrayAdapter<MediaRow> implements IThreadDelegete {
    public MyListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MediaRow> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final MediaRow rowData = getItem(position);
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row_list, null);
        }
        if (rowData != null) {
            TextView title = (TextView) v.findViewById(R.id.txtTitle);
            TextView date = (TextView) v.findViewById(R.id.txtDate);
            ImageView imgNews = (ImageView) v.findViewById(R.id.imageView);
            LinearLayout row = (LinearLayout) v.findViewById(R.id.rowLayout);
            title.setText(Html.fromHtml(rowData.getTitle()));
            date.setText(rowData.getDate());
            new ThreadWebApiPost("10001", this, rowData.getImgUrl(),imgNews).execute();
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent=new Intent(getContext(), DetailActivity.class);
                    myIntent.putExtra("data", rowData);
                    getContext().startActivity(myIntent);
                }
            });

        }
        return v;
    }

    @Override
    public void postResult(String data, String requestCode, ImageView imageView) {
        ModelDataImage dd = new Gson().fromJson(data, ModelDataImage.class);
        Picasso.with(getContext()).load(dd.getMedia_details().getSizes().getThumbnail().getSource_url()).into(imageView);
    }

}
