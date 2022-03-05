package abandonedstudio.app.cookingapp.di

import abandonedstudio.app.cookingapp.core.navigation.NavigationManager
import abandonedstudio.app.cookingapp.core.navigation.NavigationManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNavigationManager(): NavigationManager = NavigationManagerImpl()
}
