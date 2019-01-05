package com.janus.bookCake.di.app.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.janus.bookCake.App;
import com.janus.bookCake.external.AnalyticsInterface;
import com.janus.bookCake.external.ConfigInterface;
import com.janus.bookCake.di.app.component.AppComponent;
import com.janus.bookCake.di.scope.ApplicationScope;
import com.janus.bookCake.external.TaskReminderInterface;
import com.janus.bookCake.firebase.FirebaseAnalyticsHelper;
import com.janus.bookCake.firebase.FirebaseJobDispatcherHelper;
import com.janus.bookCake.firebase.FirebaseRemoteConfigHelper;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * This module is in charge of providing dependencies to the {@link AppComponent}
 * Created by Matin on 23/05/2016.
 */
@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @ApplicationScope
    public App provideApplication() {
        return app;
    }

    @Provides
    @ApplicationScope
    public Context provideContext() {
        return app;
    }

    @Provides
    @ApplicationScope
    public Gson provideGSON() {
        return new Gson();
    }

    @Provides
    @ApplicationScope
    public FirebaseDatabase provideFirebaseDatabase() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        return firebaseDatabase;
    }

    @Provides
    @ApplicationScope
    public FirebaseAuth provideFirebaseAuth() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        return firebaseAuth;
    }

    @Provides
    @ApplicationScope
    public AnalyticsInterface provideAnalyticsHelper(Context context) {
        AnalyticsInterface AnalyticsInterface = new FirebaseAnalyticsHelper(FirebaseAnalytics.getInstance(context));
        return AnalyticsInterface;
    }

    @Provides
    @ApplicationScope
    public ConfigInterface provideConfigHelper() {
        ConfigInterface configInterface = new FirebaseRemoteConfigHelper(FirebaseRemoteConfig.getInstance());
        return configInterface;
    }

    @Provides
    @ApplicationScope
    public TaskReminderInterface provideTaskReminderHelper() {
        TaskReminderInterface taskReminderInterface = new FirebaseJobDispatcherHelper();
        return taskReminderInterface;
    }

    @Provides
    @ApplicationScope
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
