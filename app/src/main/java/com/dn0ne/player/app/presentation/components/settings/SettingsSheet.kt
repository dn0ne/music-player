package com.dn0ne.player.app.presentation.components.settings

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.ColorLens
import androidx.compose.material.icons.rounded.Lyrics
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dn0ne.player.R
import com.dn0ne.player.app.presentation.components.topbar.ColumnWithCollapsibleTopBar
import com.kmpalette.DominantColorState
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsSheet(
    state: SettingsSheetState,
    onCloseClick: () -> Unit,
    dominantColorState: DominantColorState<ImageBitmap>,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onCloseClick()
    }

    AnimatedVisibility(
        visible = state.isShown,
        enter = fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMedium)) + slideInVertically(
            initialOffsetY = { it / 10 }),
        exit = fadeOut(animationSpec = spring(stiffness = Spring.StiffnessMedium)) + slideOutVertically(
            targetOffsetY = { it / 10 }),
    ) {
        Box(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .clickable(
                    enabled = false,
                    onClick = {}
                )
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = SettingsRoutes.Main,
                enterTransition = {
                    fadeIn() + slideInHorizontally(initialOffsetX = { it / 5 })
                },
                exitTransition = {
                    fadeOut() + slideOutHorizontally(targetOffsetX = { -it / 5 })
                },
                popEnterTransition = {
                    fadeIn() + slideInHorizontally(initialOffsetX = { -it / 5 })
                },
                popExitTransition = {
                    fadeOut() + slideOutHorizontally(targetOffsetX = { it / 5 })
                }
            ) {
                composable<SettingsRoutes.Main> {
                    val context = LocalContext.current
                    var collapseFraction by remember {
                        mutableFloatStateOf(0f)
                    }

                    ColumnWithCollapsibleTopBar(
                        topBarContent = {
                            IconButton(
                                onClick = onCloseClick,
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.ArrowBackIosNew,
                                    contentDescription = context.resources.getString(R.string.close_settings_sheet)
                                )
                            }

                            Text(
                                text = context.resources.getString(R.string.settings),
                                fontSize = lerp(
                                    MaterialTheme.typography.titleLarge.fontSize,
                                    MaterialTheme.typography.displaySmall.fontSize,
                                    collapseFraction
                                ),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        },
                        collapseFraction = {
                            collapseFraction = it
                        },
                        contentPadding = PaddingValues(horizontal = 24.dp),
                        contentHorizontalAlignment = Alignment.CenterHorizontally,
                        contentVerticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .safeDrawingPadding()
                    ) {
                        val settings = remember {
                            listOf(
                                SettingsItem(
                                    title = context.resources.getString(R.string.playback),
                                    supportingText = context.resources.getString(R.string.playback_supporting_text),
                                    icon = Icons.Rounded.MusicNote,
                                    onClick = {
                                        navController.navigate(SettingsRoutes.Playback)
                                    }
                                ),
                                SettingsItem(
                                    title = context.resources.getString(R.string.theme),
                                    supportingText = context.resources.getString(R.string.theme_supporting_text),
                                    icon = Icons.Rounded.ColorLens,
                                    onClick = {
                                        navController.navigate(SettingsRoutes.Theme)
                                    }
                                ),
                                SettingsItem(
                                    title = context.resources.getString(R.string.lyrics),
                                    supportingText = context.resources.getString(R.string.lyrics_supporting_text),
                                    icon = Icons.Rounded.Lyrics,
                                    onClick = {
                                        navController.navigate(SettingsRoutes.Lyrics)
                                    }
                                )
                            )
                        }

                        SettingsGroup(
                            items = settings
                        )
                    }
                }

                composable<SettingsRoutes.Playback> {
                    PlaybackSettings(
                        settings = state.settings,
                        onBackClick = {
                            navController.navigateUp()
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }

                composable<SettingsRoutes.Theme> {
                    ThemeSettings(
                        settings = state.settings,
                        onBackClick = {
                            navController.navigateUp()
                        },
                        dominantColorState = dominantColorState,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                composable<SettingsRoutes.Lyrics> {
                    LyricsSettings(
                        settings = state.settings,
                        onBackClick = {
                            navController.navigateUp()
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Serializable
sealed interface SettingsRoutes {
    @Serializable
    data object Main : SettingsRoutes

    @Serializable
    data object Playback : SettingsRoutes

    @Serializable
    data object Theme : SettingsRoutes

    @Serializable
    data object Lyrics: SettingsRoutes
}