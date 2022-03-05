package abandonedstudio.app.cookingapp.core.presentation.components

import abandonedstudio.app.cookingapp.R
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun TimePickerDialog(
    onTimeSet: () -> Unit,
    onDismissRequest: () -> Unit,
    minValue: Int = 0,
    maxValue: Int = 500
) {
    val timeState = remember { mutableStateOf(0) }
    AlertDialog(
        onDismissRequest = onDismissRequest,
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = stringResource(id = android.R.string.cancel))
            }
        },
        confirmButton = {
            TextButton(onClick = onTimeSet) {
                Text(text = stringResource(id = android.R.string.ok))
            }
        },
        title = { Text(text = stringResource(id = R.string.choose_preparation_time_dialog_title)) },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.choose_preparation_time_dialog_message))
                NumberPicker(
                    state = timeState,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    range = minValue..maxValue
                ) {
                    timeState.value = it
                }
            }
        }
    )
}

@Composable
fun TextAlertDialog(
    @StringRes title: Int? = null,
    @StringRes text: Int,
    onDismissRequest: () -> Unit,
    onConfirmClicked: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = stringResource(id = android.R.string.cancel))
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirmClicked) {
                Text(text = stringResource(id = android.R.string.ok))
            }
        },
        title = {
            if (title != null) {
                Text(text = stringResource(id = title))
            }
        },
        text = { Text(text = stringResource(id = text)) }
    )
}

@Composable
fun TextInputDialog(
    @StringRes title: Int? = null,
    text: String = "",
    onDismissRequest: () -> Unit,
    onConfirmClicked: (String) -> Unit
) {
    val editableText = remember { mutableStateOf(text) }
    val editableTextValid = remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = stringResource(id = android.R.string.cancel))
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (editableText.value.isNotBlank()) {
                    onConfirmClicked(editableText.value)
                } else {
                    editableTextValid.value = true
                }
            }) {
                Text(text = stringResource(id = android.R.string.ok))
            }
        },
        title = {
            if (title != null) {
                Text(text = stringResource(id = title))
            }
        },
        text = {
            OutlinedTextField(
                value = editableText.value,
                isError = editableTextValid.value,
                onValueChange = {
                        editableText.value = it
                        editableTextValid.value = false
                })
        }
    )
}
