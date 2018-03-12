package com.torrescalazans.baking.injection.component;

import dagger.Subcomponent;
import com.torrescalazans.baking.injection.PerActivity;
import com.torrescalazans.baking.injection.module.ActivityModule;
import com.torrescalazans.baking.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
