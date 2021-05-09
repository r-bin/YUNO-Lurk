package org.yuno.apps.lurk.dependencies

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.yuno.apps.lurk.screens.main.Database

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): Database {
        return Database(appContext)
    }
}