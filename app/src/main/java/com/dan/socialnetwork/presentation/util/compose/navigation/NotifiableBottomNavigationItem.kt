package com.dan.socialnetwork.presentation.util.compose.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dan.socialnetwork.presentation.ui.theme.Gray_ht
import com.dan.socialnetwork.presentation.ui.theme.Size_5
import com.dan.socialnetwork.presentation.ui.theme.Text_10
import java.lang.IllegalArgumentException
import kotlin.jvm.Throws

@Composable
@Throws(IllegalArgumentException::class)
fun RowScope.NotifiableBottomNavigationItem(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selected: Boolean = false,
    icon: ImageVector,
    iconSize: Dp = 37.dp,
    contentDescription: String,
    selectedColor: Color = MaterialTheme.colors.primary,
    unselectedColor: Color = Gray_ht,
    alerts: Int = 0,
    onClick: () -> Unit
) {
    if (alerts < 0) {
        throw IllegalArgumentException("illegal state: alerts < 0!")
    }
    BottomNavigationItem(
        enabled = enabled,
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        icon = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        if (selected) {
                            drawLine(
                                color = selectedColor,
                                strokeWidth = 5.dp.toPx(),
                                cap = StrokeCap.Round,
                                start = Offset(
                                    x = 40f,
                                    y = size.height
                                ),
                                end = Offset(
                                    x = size.width - 40,
                                    y = size.height
                                ),
                            )
                        }
                    }
                    .padding(Size_5)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    tint = if (selected) selectedColor else unselectedColor,
                    modifier = Modifier
                        .size(iconSize)
                        .align(Alignment.Center)
                )

                if (alerts > 0) {
                    Text(
                        text = getNotificationText(alerts),
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = Text_10,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .offset(15.dp)
                            .size(15.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.primary)
                    )
                }
            }
        }
    )
}

private fun getNotificationText(alerts: Int): String =
    if (alerts > 99) "99+" else alerts.toString()