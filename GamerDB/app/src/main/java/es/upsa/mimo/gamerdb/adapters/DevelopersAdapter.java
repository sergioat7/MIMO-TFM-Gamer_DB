/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 17/9/2020
 */

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
import es.upsa.mimo.gamerdb.models.DeveloperResponse;
import es.upsa.mimo.gamerdb.viewholders.DevelopersViewHolder;
import es.upsa.mimo.gamerdb.viewholders.LoadMoreItemsViewHolder;

public class DevelopersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //MARK: - Private properties

    private List<DeveloperResponse> developers;
    private OnItemClickListener onItemClickListener;
    private Context context;

    //MARK: - Lifecycle methods

    public DevelopersAdapter(List<DeveloperResponse> developers,
                             OnItemClickListener onItemClickListener) {

        this.developers = developers;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {

        if (developers.get(position).getId() > 0) {
            return R.layout.developer_item;
        } else {
            return R.layout.load_more_items_item;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        this.context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        RecyclerView.ViewHolder holder;
        if (viewType == R.layout.developer_item) {
            holder = new DevelopersViewHolder(itemView);
        } else {
            holder = new LoadMoreItemsViewHolder(itemView);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof DevelopersViewHolder) {

            DevelopersViewHolder developersViewHolder = (DevelopersViewHolder) holder;
            DeveloperResponse developer = developers.get(position);
            developersViewHolder.fillData(developer, context);
        } else {

            LoadMoreItemsViewHolder loadMoreItemsViewHolder = (LoadMoreItemsViewHolder) holder;
            loadMoreItemsViewHolder.setItem(onItemClickListener, false);
        }
    }

    @Override
    public int getItemCount() {

        if (developers == null) {
            return 0;
        } else {
            return developers.size();
        }
    }

    //MARK: - Public methods

    public void setDevelopers(List<DeveloperResponse> newDevelopers) {

        int position = this.developers.size();
        this.developers = newDevelopers;
        notifyItemInserted(position);
    }

    public void resetList() {

        this.developers = new ArrayList<>();
        notifyDataSetChanged();
    }
}
