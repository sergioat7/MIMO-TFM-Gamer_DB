/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 29/8/2020
 */

package es.upsa.mimo.gamerdb.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ImagesAdapter extends BaseAdapter {

    private Context context;
    private List<String> imageUrls;

    public ImagesAdapter(Context context, List<String> urls) {
        this.context = context;
        this.imageUrls = urls;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return imageUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView(context);
        Picasso.get().load(imageUrls.get(position)).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        return imageView;
    }
}