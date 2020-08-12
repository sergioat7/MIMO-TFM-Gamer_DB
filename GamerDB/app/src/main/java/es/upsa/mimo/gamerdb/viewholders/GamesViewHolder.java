package es.upsa.mimo.gamerdb.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.viewpagerindicator.LinePageIndicator;
import java.util.ArrayList;
import java.util.List;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.adapters.ImageSliderAdapter;
import es.upsa.mimo.gamerdb.models.GameResponse;

public class GamesViewHolder extends RecyclerView.ViewHolder {

    private View itemView;

    public GamesViewHolder(@NonNull View itemView) {

        super(itemView);
        this.itemView = itemView;
    }

    public void fillData(GameResponse game, Context context) {

        ViewPager vpImages = itemView.findViewById(R.id.view_pager_images);
        TextView tvName = itemView.findViewById(R.id.text_view_name);
        TextView tvRating = itemView.findViewById(R.id.text_view_rating);
        LinePageIndicator indicator = itemView.findViewById(R.id.view_pager_indicator);

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.gta);
        images.add(R.drawable.portal2);

        vpImages.setAdapter(new ImageSliderAdapter(images, context));//TODO change to game images
        tvName.setText(game.getName());
        tvRating.setText(String.valueOf(game.getRating()));
        indicator.setViewPager(vpImages);
        indicator.setVisibility(
                images.size() > 1 ? View.VISIBLE : View.GONE
        );
    }
}