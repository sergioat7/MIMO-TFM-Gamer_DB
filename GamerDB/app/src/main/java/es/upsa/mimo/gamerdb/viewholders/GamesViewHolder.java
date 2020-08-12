package es.upsa.mimo.gamerdb.viewholders;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.models.GameResponse;

public class GamesViewHolder extends RecyclerView.ViewHolder {

    public View itemView;
    public GamesViewHolder(@NonNull View itemView) {

        super(itemView);
        this.itemView = itemView;
    }

    public void fillData(GameResponse game) {

        TextView tvName = itemView.findViewById(R.id.text_view_name);
        TextView tvRating = itemView.findViewById(R.id.text_view_rating);

        tvName.setText(game.getName());
        tvRating.setText(String.valueOf(game.getRating()));
    }
}