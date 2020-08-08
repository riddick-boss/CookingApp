package abandonedstudio.app.cookingapp.ViewModel;

import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private int categoryId;

    private String categoryName;

    public SharedViewModel() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
