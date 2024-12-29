package com.dn0ne.player.setup.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dn0ne.player.R
import com.dn0ne.player.core.util.getAppVersionName

@Composable
fun WelcomePage(
    onGetStartedClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(28.dp)
    ) {
        val context = LocalContext.current

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .graphicsLayer {
                        scaleX = 2f
                        scaleY = 2f
                    }
            )

            Text(
                text = context.resources.getString(R.string.app_name),
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = context.getAppVersionName(),
                color = MaterialTheme.colorScheme.outline
            )
        }

        Button(
            onClick = onGetStartedClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Text(text = context.resources.getString(R.string.get_started))
        }
    }
}