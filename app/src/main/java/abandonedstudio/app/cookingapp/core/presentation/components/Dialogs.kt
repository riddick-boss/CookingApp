package abandonedstudio.app.cookingapp.core.presentation.components

import abandonedstudio.app.cookingapp.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
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
//            TODO: style and extract text
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        confirmButton = {
            TextButton(onClick = onTimeSet) {
//            TODO: extract string
                Text(text = stringResource(id = R.string.ok))
            }
        },
//        TODO: extract string
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
