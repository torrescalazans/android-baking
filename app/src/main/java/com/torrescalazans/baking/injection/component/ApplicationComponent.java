package com.torrescalazans.baking.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import com.torrescalazans.baking.data.DataManager;
import com.torrescalazans.baking.data.SyncService;
import com.torrescalazans.baking.data.local.DatabaseHelper;
import com.torrescalazans.baking.data.local.PreferencesHelper;
import com.torrescalazans.baking.data.remote.RibotsService;
import com.torrescalazans.baking.injection.ApplicationContext;
import com.torrescalazans.baking.injection.module.ApplicationModule;
import com.torrescalazans.baking.util.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    RibotsService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    RxEventBus eventBus();

}
