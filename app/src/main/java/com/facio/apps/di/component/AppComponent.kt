package com.facio.apps.di.component

import android.app.Application
import com.facio.apps.App
import com.facio.apps.di.builder.ActivityBuilder
import com.facio.apps.di.builder.FragmentBuilder
import com.facio.apps.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class, FragmentBuilder::class])
interface AppComponent {
    fun inject(app: App?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?

        fun build(): AppComponent?
    }
}