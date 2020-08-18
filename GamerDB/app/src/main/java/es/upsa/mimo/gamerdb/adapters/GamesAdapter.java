package es.upsa.mimo.gamerdb.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.models.GameResponse;
import es.upsa.mimo.gamerdb.viewholders.GamesViewHolder;

public class GamesAdapter extends RecyclerView.Adapter<GamesViewHolder> {

    private List<GameResponse> games;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public GamesAdapter(List<GameResponse> games, OnItemClickListener onItemClickListener) {

        this.games = games;
        this.onItemClickListener = onItemClickListener;
    }

    public void addGames(List<GameResponse> games) {

        int position = this.games.size();
        this.games.addAll(games);
        notifyItemInserted(position);
    }

    public void resetList() {

        this.games = new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        this.context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item, parent, false);
        return new GamesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesViewHolder holder, int position) {

        GameResponse game = games.get(position);
        holder.fillData(game, position, context, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int gameId);
    }
}