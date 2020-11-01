# CookingApp
#### Android app for every cooking lover. Purpose of the app is to store recipes, store them in convenient, organized way and help during shopping as there is option to do shopping list.

#### App is published on Google Play [https://play.google.com/store/apps/details?id=abandonedstudio.app.cookingapp]

Used:
- Java 8+
- RxJava
- Room Database
- MVVM
- Material Design
- GlideApp
- Permissions
- Notifications
- Fragments
- Dialogs
- Navigation Component
- AsyncTask
- Lambda Expressions
- Data Binding
- Annotations
- LiveData
- MutableLiveData
- Animations

## General info

App is organized in MVVM architecture. Navigating between fragments is realized with AndroidX Navigation Component.

## UI

#### 1. Dish categories fragment

<img src="/screenshots/dish_categories_list.png" width="200">

Fragment shown on app opening. Here is a RecyclerView with all categories which user created. If button is pushed for longer time, layout with options to delete or edit category appears as show on this screenshot:

< im src="edit_category.png" width="200">

If floating button with plus sign is clicked, user is navigated to Add Dish Category fragment.

#### 2. Add Dish Category fragment

<img src="/screenshots/add_category.png" width="200">

Here user can add new category.

#### 3. Dishes List fragment

<img src="/screenshots/dishes_list.png" width="200">

After choosing category user is taken to this fragment, where is list of all dishes from this specified category. Photo is loaded with usage of GlideApp. Each dish is show with usage of CardView. There is photo of a dish, name and preparation time shown. Deleting dish is similar as in Dish Categories fragment. However, if user wants to add or edit existing dish, app navigates to Edit Dish fragment.

#### 4. Dish fragment

<img src="/screenshots/dish.png" width="200">

There is all information about dish - preparation time, photo (loaded with GlideApp), ingredients and recipe. On the bottom of the screen there is a button which navigates to Shopping List fragment.

#### 5. Shopping List fragment

<img src="/screenshots/shopping_list1.png" width="200">
<img src="/screenshots/shopping_list2.png" width="200">

Firstly, user is asked to choose what he needs to buy. There is also a CheckBox with option to choose all ingredients at once. After choosing ingredients, final shopping list is shown (that is another fragment) and simultaneously notification is created, so even if app is closed, user can easily check what he needs to buy and there is no need to unlock phone. By clicking "cook" button, app goes back to Dish fragment.

#### 6. Edit Dish fragment

<img src="/screenshots/add_dish.png" width="200">
<img src="/screenshots/edit_dish.png" width="200">

When user wants to create new dish this fragment handles it. Here are options to: choose preparation time - after tapping on clock image, dialog opens, enter dish name, add photo (there is a photo loaded by default as shown on screenshot), ingredients and preparation steps. To access user's gallery app asks for reading external storage permission. User can also delete or change previously added photo - it will be set to default photo. To change, edit or delete ingredients or preparation steps, user needs to click on it and another layout will be loaded (only on specified step/ingredient) with options to take this actions (deleting is done with animation). Bad preparation steps order? Well, no problem as by "drag and drop" a step it is very easy to change their order. By clicking button "ready" user can add dish, but app is preventing from adding dish without any ingredients, steps or without name. For every situation customized toast is shown - "enter name", "enter at least 1 ingredient" or "enter at least 1 step". If time was not entered, it would be set to 0.
When user is editing dish, also this fragment is shown, but bound with dish's data.
When user wants to add dish, that is when RxJava is used, as app needs new dish ID to be able to fill RecyclerView with it. Firstly, ProgressDialog opens up. MutableLiveData waits until disposable calls OnSucces/OnError (means that dish has already been entered or error occurred) method and changes its value. When it changes value to true (meaning dish was successfully added), user is taken to Dishes List fragment. Otherwise app stays at current fragment.

## SQLite Room Database

There is 1 database with 4 entities: DishCategory, Dish, PreparationStep and Ingredient. They are related by unique IDs, so even if user adds few exactly same dishes they all will be shown - it is convenient, because user might want to add few options of same dish. Entities are related in cascade mode, so when e.g dish is deleted, all steps and ingredients related also are deleted in order to free up memory space.

## Notifications

```java
public class CreateShoppingListFragment extends Fragment {

@Override
public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      okButton.setOnClickListener(v -> {
            if(!adapter.getIngredientsToBuy().isEmpty()){
                sharedViewModel.setIngredientsToBuy(adapter.getIngredientsToBuy());
                //start notification service
                ShoppingListNotification notification = new ShoppingListNotification();
                int notificationId= (int) System.currentTimeMillis();
                StringBuilder content = new StringBuilder();
                for (int i=0; i<adapter.getIngredientsToBuy().size(); i++){
                    content.append(adapter.getIngredientsToBuy().get(i));
                    if (i<adapter.getIngredientsToBuy().size()-1){
                        content.append(",").append(System.lineSeparator());
                    }
                }
        });
    }
}           
```
Each notification has its own unique ID (based on milis), so there can be multiple notification from e.g few dishes.
