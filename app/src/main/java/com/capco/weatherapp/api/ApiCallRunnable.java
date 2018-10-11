package com.capco.weatherapp.api;

import java.util.ArrayList;
import java.util.List;

public class ApiCallRunnable implements Runnable {
    private List<ApiListener> apiListeners = new ArrayList<>();
    protected Object lastResult;
    public void registerListener(ApiListener apiListener){
        this.apiListeners.add(apiListener);
        if(lastResult != null)
            apiListener.update(lastResult);
    }
    public void unregisterListener(ApiListener apiListener){
        this.apiListeners.remove(apiListener);
    }

    @Override
    public void run() {
        updateListeners(lastResult);
    }



    private void updateListeners(Object data) {
        for(ApiListener apiListener : apiListeners){
            apiListener.update(data);
        }
    }
}
