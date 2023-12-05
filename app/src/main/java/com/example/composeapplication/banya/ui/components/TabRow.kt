package com.example.composeapplication.banya.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeapplication.banya.ui.BanyaScreen
import com.example.composeapplication.banya.ui.Dimens.InactiveTabOpacity
import com.example.composeapplication.banya.ui.Dimens.TabFadeInAnimationDelay
import com.example.composeapplication.banya.ui.Dimens.TabFadeInAnimationDuration
import com.example.composeapplication.banya.ui.Dimens.TabFadeOutAnimationDuration
import com.example.composeapplication.banya.ui.Dimens.TabHeight

@Composable
fun BanyaTabRow(
    allScreens: List<BanyaScreen>,
    onTabSelected: (BanyaScreen) -> Unit,
    currentScreen: BanyaScreen
) {
    Surface(
        Modifier
            .height(TabHeight)
            .fillMaxWidth()
    ) {
        Row(
            Modifier.selectableGroup(),
            horizontalArrangement = Arrangement.Center
        ) {
            allScreens.forEach { screen ->
                BanyaTab(
                    text = screen.name,
                    icon = screen.icon,
                    onSelected = { onTabSelected(screen) },
                    selected = currentScreen == screen
                )
            }
        }
    }
}

@Composable
private fun BanyaTab(
    text: String,
    icon: ImageVector,
    onSelected: () -> Unit,
    selected: Boolean
) {
    val color = MaterialTheme.colorScheme.primary
    val durationMillis = if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val tabTintColor by animateColorAsState(
        targetValue = if (selected) color else color.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec,
        label = "",
    )
    Row(
        modifier = Modifier
            .padding(16.dp)
            .animateContentSize()
            .height(TabHeight)
            .selectable(
                selected = selected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            )
            .clearAndSetSemantics { contentDescription = text }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = tabTintColor
        )
//        if (selected) {
//            Spacer(Modifier.width(12.dp))
//            Text(
//                text.uppercase(Locale.getDefault()),
//                color = tabTintColor,
//                textAlign = Alignment.CenterVertically
//            )
//        }
    }
}
