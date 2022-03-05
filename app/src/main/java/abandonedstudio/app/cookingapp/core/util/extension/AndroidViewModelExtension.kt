package abandonedstudio.app.cookingapp.core.util.extension

import android.widget.Toast
import androidx.lifecycle.AndroidViewModel

fun AndroidViewModel.showToast(text: String) {
    if (text.isNotBlank()) Toast.makeText(this.getApplication(), text, Toast.LENGTH_SHORT).show()
}

fun AndroidViewModel.showToast(e: Exception) {
    e.message?.run { showToast(this) }
}