package com.torrescalazans.baking;

import android.app.Application;
import android.content.Context;

import com.torrescalazans.baking.injection.component.ApplicationComponent;
import com.torrescalazans.baking.injection.component.DaggerApplicationComponent;
import com.torrescalazans.baking.injection.module.ApplicationModule;

import timber.log.Timber;

public class BakingApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static BakingApplication get(Context context) {
        return (BakingApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
