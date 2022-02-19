package abandonedstudio.app.cookingapp.di

import abandonedstudio.app.cookingapp.core.data.local.room.Database
import abandonedstudio.app.cookingapp.core.util.Constants.DATABASE_NAME
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, Database::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDishCategoryDao(db: Database) = db.dishCategoryDao()

    @Singleton
    @Provides
    fun provideDishDao(db: Database) = db.dishDao()

    @Singleton
    @Provides
    fun provideIngredientDao(db: Database) = db.ingredientDao()

    @Singleton
    @Provides
    fun providePreparationStepDao(db: Database) = db.preparationStepDao()
}