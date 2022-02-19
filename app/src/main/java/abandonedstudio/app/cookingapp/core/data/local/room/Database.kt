package abandonedstudio.app.cookingapp.core.data.local.room

import abandonedstudio.app.cookingapp.core.data.local.room.dish.Dish
import abandonedstudio.app.cookingapp.core.data.local.room.dish.DishDao
import abandonedstudio.app.cookingapp.core.data.local.room.dish_category.DishCategory
import abandonedstudio.app.cookingapp.core.data.local.room.dish_category.DishCategoryDao
import abandonedstudio.app.cookingapp.core.data.local.room.ingredient.Ingredient
import abandonedstudio.app.cookingapp.core.data.local.room.ingredient.IngredientDao
import abandonedstudio.app.cookingapp.core.data.local.room.preparation_step.PreparationStep
import abandonedstudio.app.cookingapp.core.data.local.room.preparation_step.PreparationStepDao
import androidx.room.Database
import androidx.room.RoomDatabase

//TODO: converet, save photo
@Database(entities = [DishCategory::class, Dish::class, PreparationStep::class, Ingredient::class], version = 2)
abstract class Database: RoomDatabase() {
    abstract fun dishCategoryDao(): DishCategoryDao
    abstract fun dishDao(): DishDao
    abstract fun preparationStepDao(): PreparationStepDao
    abstract fun ingredientDao(): IngredientDao
}