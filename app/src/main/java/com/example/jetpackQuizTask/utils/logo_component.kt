package com.example.starbucks_ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetpackQuizTask.R


@Composable
fun logoComponent(
    modifier: Modifier = Modifier,
    size: Dp = 155.dp
) {

    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "",
        modifier = modifier.size(size = size)
    )

}

