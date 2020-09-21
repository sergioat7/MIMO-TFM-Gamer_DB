/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 17/9/2020
 */

package es.upsa.mimo.gamerdb.adapters;

import android.view.View;

public interface OnItemClickListener {

    void onItemClick(View v, int id);
    void onLoadMoreItemsClick(View v);
}
