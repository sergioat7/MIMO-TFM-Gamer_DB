/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 27/8/2020
 */

package es.upsa.mimo.gamerdb.fragments.popups;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.upsa.mimo.gamerdb.R;

public class PopupVideoDialogFragment extends DialogFragment {

    //MARK: - Public properties

    @BindView(R.id.web_view_video)
    WebView web;

    //MARK: - Private properties

    private String url;

    public PopupVideoDialogFragment(String url) {
        this.url = url;
    }

    //MARK: - Lifecycle methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_popup_video_dialog, container, false);
        ButterKnife.bind(this, view);
        initializeUI();

        return view;
    }

    //MARK: - Private functions

    private void initializeUI() {

        //TODO move to Constants
        String frameVideo = "<html><body><br><iframe width=\"320\" height=\"200\" src=\"" + url + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.loadData(frameVideo, "text/html", "utf-8");
    }
}