package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.OmniTopBar
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.AppScreen
import com.example.viewmodel.OmniViewModel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                OmniApp()
            }
        }
    }
}

@Composable
fun OmniApp(viewModel: OmniViewModel = viewModel()) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val selectedProduct by viewModel.selectedProduct.collectAsState()
    val showCartSheet by viewModel.showCartSheet.collectAsState()
    val activeOrderTracking by viewModel.activeOrderTracking.collectAsState()
    val feedbackMessage by viewModel.feedbackMessage.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(feedbackMessage) {
        feedbackMessage?.let { msg ->
            snackbarHostState.showSnackbar(
                message = msg,
                duration = SnackbarDuration.Short
            )
            viewModel.clearFeedback()
        }
    }

    // Predictive Back Handling
    BackHandler(enabled = activeOrderTracking != null || showCartSheet || selectedProduct != null || currentScreen != AppScreen.SHOP) {
        when {
            activeOrderTracking != null -> viewModel.closeOrderTracking()
            showCartSheet -> viewModel.closeCart()
            selectedProduct != null -> viewModel.closeProduct()
            currentScreen != AppScreen.SHOP -> viewModel.navigateTo(AppScreen.SHOP)
        }
    }

    Scaffold(
        topBar = {
            if (activeOrderTracking == null && !showCartSheet && selectedProduct == null && currentScreen != AppScreen.LOYALTY_HUB) {
                OmniTopBar(viewModel = viewModel)
            }
        },
        bottomBar = {
            if (activeOrderTracking == null && !showCartSheet && selectedProduct == null) {
                NavigationBar(
                    modifier = Modifier.testTag("bottom_nav_bar")
                ) {
                    NavigationBarItem(
                        selected = currentScreen == AppScreen.SHOP,
                        onClick = { viewModel.navigateTo(AppScreen.SHOP) },
                        icon = { Icon(Icons.Default.Storefront, contentDescription = "Shop") },
                        label = { Text("Shop") },
                        modifier = Modifier.testTag("nav_item_shop")
                    )

                    NavigationBarItem(
                        selected = currentScreen == AppScreen.PRICE_COMPARE,
                        onClick = { viewModel.navigateTo(AppScreen.PRICE_COMPARE) },
                        icon = { Icon(Icons.Default.CompareArrows, contentDescription = "Price Compare") },
                        label = { Text("Compare") },
                        modifier = Modifier.testTag("nav_item_compare")
                    )

                    NavigationBarItem(
                        selected = currentScreen == AppScreen.SUBSCRIPTIONS,
                        onClick = { viewModel.navigateTo(AppScreen.SUBSCRIPTIONS) },
                        icon = { Icon(Icons.Default.Security, contentDescription = "DeviceCare+") },
                        label = { Text("Care+") },
                        modifier = Modifier.testTag("nav_item_care")
                    )

                    NavigationBarItem(
                        selected = currentScreen == AppScreen.CHATBOT,
                        onClick = { viewModel.navigateTo(AppScreen.CHATBOT) },
                        icon = { Icon(Icons.Default.SmartToy, contentDescription = "24/7 Chatbot") },
                        label = { Text("Support") },
                        modifier = Modifier.testTag("nav_item_chatbot")
                    )

                    NavigationBarItem(
                        selected = currentScreen == AppScreen.ACCOUNT || currentScreen == AppScreen.LOYALTY_HUB,
                        onClick = { viewModel.navigateTo(AppScreen.ACCOUNT) },
                        icon = { Icon(Icons.Default.Person, contentDescription = "Account") },
                        label = { Text("Account") },
                        modifier = Modifier.testTag("nav_item_account")
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                activeOrderTracking != null -> {
                    OrderTrackingScreen(order = activeOrderTracking!!, viewModel = viewModel)
                }
                showCartSheet -> {
                    CartCheckoutScreen(viewModel = viewModel)
                }
                selectedProduct != null -> {
                    ProductDetailScreen(product = selectedProduct!!, viewModel = viewModel)
                }
                else -> {
                    when (currentScreen) {
                        AppScreen.SHOP -> HomeScreen(viewModel = viewModel)
                        AppScreen.PRICE_COMPARE -> PriceCompareScreen(viewModel = viewModel)
                        AppScreen.SUBSCRIPTIONS -> SubscriptionScreen(viewModel = viewModel)
                        AppScreen.CHATBOT -> SupportChatScreen(viewModel = viewModel)
                        AppScreen.LOYALTY_HUB -> LoyaltyScreen(viewModel = viewModel)
                        AppScreen.ACCOUNT -> AccountScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}
