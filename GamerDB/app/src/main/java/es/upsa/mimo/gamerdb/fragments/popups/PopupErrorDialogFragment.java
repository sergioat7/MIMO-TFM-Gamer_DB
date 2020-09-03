/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 2/9/2020
 */

package es.upsa.mimo.gamerdb.fragments.popups;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.upsa.mimo.gamerdb.R;

public class PopupErrorDialogFragment extends DialogFragment {

    //MARK: - Public properties

    @BindView(R.id.text_view_error)
    TextView tvError;

    @BindView(R.id.button_accept)
    Button btAccept;

    //MARK: - Private properties

    private String message;

    public PopupErrorDialogFragment(String message) {
        this.message = message;
    }

    //MARK: - Lifecycle methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_popup_error_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvError.setText(message);
        btAccept.setOnClickListener(v -> dismiss());
    }
}