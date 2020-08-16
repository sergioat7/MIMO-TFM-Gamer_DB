package es.upsa.mimo.gamerdb.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;
import es.upsa.mimo.gamerdb.R;

public class ImageSliderAdapter extends PagerAdapter {

    private List<String> images;
    private LayoutInflater inflater;

    public ImageSliderAdapter(List<String> images, Context context) {

        this.images = images;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View imageLayout = inflater.inflate(R.layout.image_slider, container, false);
        assert imageLayout != null;
        ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image_view);
        ProgressBar loading = imageLayout.findViewById(R.id.progress_bar_loading);

        loading.setVisibility(View.VISIBLE);
        Picasso.get().load(images.get(position)).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                loading.setVisibility(View.GONE);
            }
        });
        container.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {}

    @Nullable
    @Override
    public Parcelable saveState() {
        return null;
    }
}
