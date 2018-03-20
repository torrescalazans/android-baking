package com.torrescalazans.baking.injection.module;

import android.app.Application;
import android.content.Context;

import com.torrescalazans.baking.data.remote.RecipesService;
import com.torrescalazans.baking.data.remote.RibotsService;
import com.torrescalazans.baking.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    RibotsService provideRibotsService() {
        return RibotsService.Creator.newRibotsService();
    }

    @Provides
    @Singleton
    RecipesService provideRecipesService() {
        return RecipesService.Creator.newRecipesService();
    }
}
