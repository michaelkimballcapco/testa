package com.capco.weatherapp.help;

public class HelpFragmentPresenter implements HelpPresenter {

    private HelpView helpView;

    @Override
    public void registerHelpView(HelpView helpView) {
        this.helpView = helpView;
        this.helpView.initialize();
    }
}
