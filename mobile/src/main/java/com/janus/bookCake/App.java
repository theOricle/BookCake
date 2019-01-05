package com.janus.bookCake;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.janus.bookCake.di.app.component.AppComponent;
import com.janus.bookCake.di.app.component.DaggerAppComponent;
import com.janus.bookCake.di.app.module.AppModule;
import com.janus.bookCake.BuildConfig;


public class App extends Application {
    public static final String TAG = App.class.getSimpleName();

    private AppComponent component;
    public AppComponent getComponent() {
        return component;
    }
    private void setAppComponent() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    // Needed to replace the component with a com.cremy.shared.di.app.test one
    // !!! For testing purpose !!!
    public void setComponent(AppComponent applicationComponent) {
        component = applicationComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        this.setAppComponent();

        if (BuildConfig.DEBUG) {
            enableStrictMode();
        }

        initRemoteConfig();
    }

    /**
     * Allows to get the Application class instance
     * @param context
     * @return
     */
    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }


    private void enableStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
    }

    private void initRemoteConfig() {
        component.configHelper().setGlobalDefaultValues();
        component.configHelper().fetch(0);
    }
}