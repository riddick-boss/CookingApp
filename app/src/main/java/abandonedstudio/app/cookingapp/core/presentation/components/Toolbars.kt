package abandonedstudio.app.cookingapp.core.presentation.components

import abandonedstudio.app.cookingapp.R
import abandonedstudio.app.cookingapp.core.presentation.util.contentDescription
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextToolbarWithSettings(
    @StringRes resId: Int,
    modifier: Modifier = Modifier,
    onSettingsClicked: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(resId),
                fontSize = 42.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp)
                    .align(Alignment.Center)
            )
            IconButton(
                onClick = onSettingsClicked,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_round_settings_24),
                    contentDescription = contentDescription(),
                    tint = Color.Yellow
                )
            }
        }
        Divider(color = Color.LightGray, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
    }
}

@Preview
@Composable
fun TextToolbarWithSettingsPreview() {
    TextToolbarWithSettings(R.string.app_name) {

    }
}