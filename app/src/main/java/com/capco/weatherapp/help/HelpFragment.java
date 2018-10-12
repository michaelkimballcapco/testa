package com.capco.weatherapp.help;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.capco.weatherapp.ApplicationState;
import com.capco.weatherapp.R;

public class HelpFragment extends Fragment implements HelpView {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_help,
                container, false);
        ApplicationState.getHelpPresenter().registerHelpView(this);
        return view;
    }

    @Override
    public void initialize() {
        WebView webView = view.findViewById(R.id.wv_help);
        webView.loadUrl("file:///android_asset/help.html");
    }
}
