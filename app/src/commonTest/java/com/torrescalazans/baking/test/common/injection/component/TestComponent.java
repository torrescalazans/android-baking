package com.torrescalazans.baking.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import com.torrescalazans.baking.injection.component.ApplicationComponent;
import com.torrescalazans.baking.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
