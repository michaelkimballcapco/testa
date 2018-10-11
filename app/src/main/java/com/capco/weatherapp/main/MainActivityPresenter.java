package com.capco.weatherapp.main;

public class MainActivityPresenter implements MainPresenter{

    private MainView mainView;

    @Override
    public void registerActivity(MainView mainView) {
        this.mainView = mainView;
        this.mainView.initialize();
    }

    @Override
    public void unregisterActivity() {
        this.mainView = null;
    }
}
