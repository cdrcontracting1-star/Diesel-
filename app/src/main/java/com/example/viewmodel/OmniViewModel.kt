package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.SampleData
import com.example.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

enum class AppScreen {
    SHOP,
    PRICE_COMPARE,
    SUBSCRIPTIONS,
    CHATBOT,
    LOYALTY_HUB,
    ACCOUNT
}

class OmniViewModel : ViewModel() {

    // Navigation & Screen Stack
    private val _currentScreen = MutableStateFlow(AppScreen.SHOP)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct.asStateFlow()

    private val _showCartSheet = MutableStateFlow(false)
    val showCartSheet: StateFlow<Boolean> = _showCartSheet.asStateFlow()

    private val _activeOrderTracking = MutableStateFlow<Order?>(null)
    val activeOrderTracking: StateFlow<Order?> = _activeOrderTracking.asStateFlow()

    // Catalog & Filtering
    private val _allProducts = MutableStateFlow(SampleData.products)
    val allProducts: StateFlow<List<Product>> = _allProducts.asStateFlow()

    private val _selectedCategory = MutableStateFlow(ProductCategory.ALL)
    val selectedCategory: StateFlow<ProductCategory> = _selectedCategory.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _brandFilter = MutableStateFlow("All")
    val brandFilter: StateFlow<String> = _brandFilter.asStateFlow()

    // Cart & Checkout
    private val _cartItems = MutableStateFlow<List<CartItem>>(
        listOf(
            CartItem(SampleData.products.first { it.id == "prod_anker_prime_100w" }, 1, "Space Gray"),
            CartItem(SampleData.products.first { it.id == "prod_nomad_240w" }, 1, "Black Kevlar")
        )
    )
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    private val _selectedShipping = MutableStateFlow(SampleData.shippingOptions[1]) // Next-Day default
    val selectedShipping: StateFlow<ShippingOption> = _selectedShipping.asStateFlow()

    private val _appliedReward = MutableStateFlow<LoyaltyReward?>(null)
    val appliedReward: StateFlow<LoyaltyReward?> = _appliedReward.asStateFlow()

    // Loyalty Program
    private val _loyaltyProfile = MutableStateFlow(SampleData.initialLoyaltyProfile)
    val loyaltyProfile: StateFlow<LoyaltyProfile> = _loyaltyProfile.asStateFlow()

    // Orders History
    private val _orders = MutableStateFlow<List<Order>>(
        listOf(
            Order(
                id = "OMNI-98214",
                items = listOf(
                    CartItem(SampleData.products.first { it.id == "prod_s24u" }, 1, "Titanium Gray")
                ),
                subtotal = 1199.99,
                shippingOption = SampleData.shippingOptions[0],
                discountAmount = 0.0,
                total = 1209.98,
                orderDate = "Oct 31, 2026",
                status = "Out for Delivery",
                trackingSteps = listOf(
                    OrderTrackingStep("Order Placed", "11:30 AM", isCompleted = true, isCurrent = false, "OmniMobile Express Hub"),
                    OrderTrackingStep("Quality Verified & Packed", "12:15 PM", isCompleted = true, isCurrent = false, "Metro Logistics Center"),
                    OrderTrackingStep("Dispatched via Local Courier", "01:05 PM", isCompleted = true, isCurrent = false, "Van #402 - Driver Dave"),
                    OrderTrackingStep("Out for Delivery", "02:40 PM", isCompleted = true, isCurrent = true, "Within 3 miles of your address"),
                    OrderTrackingStep("Delivered to Doorstep", "Estimated 3:45 PM", isCompleted = false, isCurrent = false, "Front Porch / Secure Lockbox")
                ),
                pointsEarned = 1800
            )
        )
    )
    val orders: StateFlow<List<Order>> = _orders.asStateFlow()

    // Subscriptions
    private val _activeCarePlan = MutableStateFlow<DeviceCarePlan?>(SampleData.deviceCarePlans[1]) // Pro Shield active
    val activeCarePlan: StateFlow<DeviceCarePlan?> = _activeCarePlan.asStateFlow()

    private val _maintenanceBookings = MutableStateFlow<List<MaintenanceBooking>>(
        listOf(
            MaintenanceBooking(
                id = "MB-3921",
                planName = "Pro Shield+",
                serviceType = "Quarterly Deep Clean & Port Sanitization",
                deviceModel = "Samsung Galaxy S24 Ultra",
                scheduledDate = "Nov 05, 2026 at 2:00 PM",
                status = "Confirmed",
                notes = "Ultrasonic speaker grill clearing & USB-C lint extraction."
            )
        )
    )
    val maintenanceBookings: StateFlow<List<MaintenanceBooking>> = _maintenanceBookings.asStateFlow()

    // Product Reviews
    private val _reviews = MutableStateFlow(SampleData.sampleReviews)
    val reviews: StateFlow<List<Review>> = _reviews.asStateFlow()

    // 24/7 Chatbot Messages
    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(
        listOf(
            ChatMessage(
                id = "bot_init",
                text = "Hello! I'm OmniBot, your 24/7 mobile tech specialist. How can I help you today? Ask me about device compatibility, orders, device maintenance, or our Unbeatable Price Match guarantee!",
                isUser = false,
                timestamp = "Just now",
                actionChips = listOf("Track my order", "Recommend a fast charger", "Explain DeviceCare+", "How do I earn loyalty points?", "Claim Price Match")
            )
        )
    )
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()

    private val _isBotTyping = MutableStateFlow(false)
    val isBotTyping: StateFlow<Boolean> = _isBotTyping.asStateFlow()

    // Snackbars / Feedback message
    private val _feedbackMessage = MutableStateFlow<String?>(null)
    val feedbackMessage: StateFlow<String?> = _feedbackMessage.asStateFlow()

    fun clearFeedback() {
        _feedbackMessage.value = null
    }

    // Navigation
    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
        _selectedProduct.value = null
        _showCartSheet.value = false
    }

    fun openProduct(product: Product) {
        _selectedProduct.value = product
    }

    fun closeProduct() {
        _selectedProduct.value = null
    }

    fun openCart() {
        _showCartSheet.value = true
    }

    fun closeCart() {
        _showCartSheet.value = false
    }

    fun openOrderTracking(order: Order) {
        _activeOrderTracking.value = order
    }

    fun closeOrderTracking() {
        _activeOrderTracking.value = null
    }

    fun setCategory(category: ProductCategory) {
        _selectedCategory.value = category
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setBrandFilter(brand: String) {
        _brandFilter.value = brand
    }

    // Cart actions
    fun addToCart(product: Product, color: String = "Default") {
        val current = _cartItems.value.toMutableList()
        val existingIndex = current.indexOfFirst { it.product.id == product.id && it.selectedColor == color }
        if (existingIndex >= 0) {
            val item = current[existingIndex]
            current[existingIndex] = item.copy(quantity = item.quantity + 1)
        } else {
            current.add(CartItem(product = product, quantity = 1, selectedColor = color))
        }
        _cartItems.value = current
        _feedbackMessage.value = "Added ${product.name} to cart"
    }

    fun removeFromCart(cartItem: CartItem) {
        _cartItems.value = _cartItems.value.filter { it != cartItem }
    }

    fun updateQuantity(cartItem: CartItem, newQty: Int) {
        if (newQty <= 0) {
            removeFromCart(cartItem)
        } else {
            _cartItems.value = _cartItems.value.map {
                if (it == cartItem) it.copy(quantity = newQty) else it
            }
        }
    }

    fun setShippingOption(option: ShippingOption) {
        _selectedShipping.value = option
    }

    fun applyRewardToCheckout(reward: LoyaltyReward) {
        _appliedReward.value = reward
        _feedbackMessage.value = "Coupon '${reward.couponCode}' applied: -$${"%.2f".format(reward.discountValue)}"
    }

    fun removeAppliedReward() {
        _appliedReward.value = null
    }

    fun checkoutSubtotal(): Double = _cartItems.value.sumOf { it.subtotal }

    fun checkoutDiscount(): Double = _appliedReward.value?.discountValue ?: 0.0

    fun checkoutTotal(): Double {
        val sub = checkoutSubtotal()
        val disc = checkoutDiscount()
        val ship = _selectedShipping.value.price
        return maxOf(0.0, sub - disc + ship)
    }

    fun placeOrder(): Order? {
        val items = _cartItems.value
        if (items.isEmpty()) return null

        val subtotal = checkoutSubtotal()
        val discount = checkoutDiscount()
        val total = checkoutTotal()
        val shipping = _selectedShipping.value

        // Calculate points earned: 10 pts per $ on accessories, 5 pts per $ on phones/tablets * Tier multiplier
        val basePoints = items.sumOf { item ->
            val rate = if (item.product.category == ProductCategory.PHONES || item.product.category == ProductCategory.TABLETS) 5 else 10
            (item.subtotal * rate).toInt()
        }
        val pointsMultiplier = _loyaltyProfile.value.tier.multiplier
        val earnedPoints = (basePoints * pointsMultiplier).toInt()

        val orderId = "OMNI-${(10000..99999).random()}"
        val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.US)
        val todayStr = sdf.format(Date())

        val newOrder = Order(
            id = orderId,
            items = items,
            subtotal = subtotal,
            shippingOption = shipping,
            discountAmount = discount,
            total = total,
            orderDate = todayStr,
            status = "Processing",
            trackingSteps = listOf(
                OrderTrackingStep("Order Confirmed", "Just now", isCompleted = true, isCurrent = true, "OmniMobile Fulfillment Hub"),
                OrderTrackingStep("Item Inspection & Packaging", "Pending", isCompleted = false, isCurrent = false, "Metro Logistics Depot"),
                OrderTrackingStep("Courier Hand-off", "Pending", isCompleted = false, isCurrent = false, shipping.name),
                OrderTrackingStep("Delivered", shipping.estimatedDelivery, isCompleted = false, isCurrent = false, "Your Shipping Address")
            ),
            pointsEarned = earnedPoints
        )

        _orders.value = listOf(newOrder) + _orders.value
        _cartItems.value = emptyList()
        _appliedReward.value = null
        _showCartSheet.value = false

        // Award points in loyalty profile
        addLoyaltyPoints(earnedPoints, "Purchase Order #$orderId")
        _activeOrderTracking.value = newOrder
        _feedbackMessage.value = "Order placed! Earned +$earnedPoints Loyalty Points 🎉"
        return newOrder
    }

    // Loyalty Actions
    fun dailyCheckIn() {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        val profile = _loyaltyProfile.value
        if (profile.lastCheckInDate == today) {
            _feedbackMessage.value = "Already checked in today! Come back tomorrow."
            return
        }

        val bonus = 15
        val newStreak = profile.streakDays + 1
        val newBalance = profile.currentPoints + bonus
        val newLifetime = profile.lifetimePoints + bonus
        val updatedTier = calculateTier(newLifetime)

        val tx = LoyaltyTransaction(
            id = UUID.randomUUID().toString(),
            date = SimpleDateFormat("MMM dd, yyyy", Locale.US).format(Date()),
            description = "Daily Check-in Streak Day $newStreak",
            pointsChange = bonus,
            isCredit = true
        )

        _loyaltyProfile.value = profile.copy(
            currentPoints = newBalance,
            lifetimePoints = newLifetime,
            streakDays = newStreak,
            tier = updatedTier,
            lastCheckInDate = today,
            transactionHistory = listOf(tx) + profile.transactionHistory
        )
        _feedbackMessage.value = "+$bonus Loyalty Points added for Day $newStreak Streak! 🔥"
    }

    fun redeemReward(reward: LoyaltyReward) {
        val profile = _loyaltyProfile.value
        if (profile.currentPoints < reward.pointsCost) {
            _feedbackMessage.value = "Need ${reward.pointsCost - profile.currentPoints} more points to redeem this reward."
            return
        }

        val uniqueCode = "${reward.couponCode}-${(1000..9999).random()}"
        val redeemedItem = reward.copy(couponCode = uniqueCode, isRedeemed = true)

        val newBalance = profile.currentPoints - reward.pointsCost
        val tx = LoyaltyTransaction(
            id = UUID.randomUUID().toString(),
            date = SimpleDateFormat("MMM dd, yyyy", Locale.US).format(Date()),
            description = "Redeemed: ${reward.title}",
            pointsChange = reward.pointsCost,
            isCredit = false
        )

        _loyaltyProfile.value = profile.copy(
            currentPoints = newBalance,
            redeemedCoupons = listOf(redeemedItem) + profile.redeemedCoupons,
            transactionHistory = listOf(tx) + profile.transactionHistory
        )
        _feedbackMessage.value = "Reward '${reward.title}' redeemed! Code: $uniqueCode"
    }

    fun addLoyaltyPoints(points: Int, reason: String) {
        val profile = _loyaltyProfile.value
        val newBalance = profile.currentPoints + points
        val newLifetime = profile.lifetimePoints + points
        val updatedTier = calculateTier(newLifetime)

        val tx = LoyaltyTransaction(
            id = UUID.randomUUID().toString(),
            date = SimpleDateFormat("MMM dd, yyyy", Locale.US).format(Date()),
            description = reason,
            pointsChange = points,
            isCredit = true
        )

        _loyaltyProfile.value = profile.copy(
            currentPoints = newBalance,
            lifetimePoints = newLifetime,
            tier = updatedTier,
            transactionHistory = listOf(tx) + profile.transactionHistory
        )
    }

    private fun calculateTier(lifetimePoints: Int): LoyaltyTier {
        return when {
            lifetimePoints >= LoyaltyTier.PLATINUM.pointsThreshold -> LoyaltyTier.PLATINUM
            lifetimePoints >= LoyaltyTier.GOLD.pointsThreshold -> LoyaltyTier.GOLD
            lifetimePoints >= LoyaltyTier.SILVER.pointsThreshold -> LoyaltyTier.SILVER
            else -> LoyaltyTier.BRONZE
        }
    }

    // Reviews
    fun submitReview(productId: String, rating: Int, title: String, comment: String) {
        val todayStr = SimpleDateFormat("MMM dd, yyyy", Locale.US).format(Date())
        val newRev = Review(
            id = UUID.randomUUID().toString(),
            productId = productId,
            author = "You (Verified Buyer)",
            rating = rating,
            date = todayStr,
            title = title,
            comment = comment,
            verifiedBuyer = true,
            helpfulCount = 0
        )
        _reviews.value = listOf(newRev) + _reviews.value
        addLoyaltyPoints(50, "Verified Product Review Bonus")
        _feedbackMessage.value = "Review submitted! +50 Loyalty Points awarded 🌟"
    }

    // Price Match Guarantee Claim
    fun submitPriceMatchClaim(productId: String, competitorName: String, competitorPrice: Double, competitorUrl: String) {
        val product = _allProducts.value.firstOrNull { it.id == productId } ?: return
        val omniPrice = product.price
        val diff = omniPrice - competitorPrice
        val couponDiscount = if (diff > 0) diff + 5.0 else 5.0 // Beat by extra $5!

        val reward = LoyaltyReward(
            id = "pm_${UUID.randomUUID().toString().take(6)}",
            title = "Price Match Beat - ${product.name.take(18)}",
            description = "Beat $competitorName price by $5.00 extra guarantee!",
            pointsCost = 0,
            discountValue = couponDiscount,
            type = RewardType.DOLLAR_OFF,
            couponCode = "BEAT${(1000..9999).random()}",
            isRedeemed = true
        )

        val profile = _loyaltyProfile.value
        _loyaltyProfile.value = profile.copy(
            redeemedCoupons = listOf(reward) + profile.redeemedCoupons
        )
        addLoyaltyPoints(50, "Price Match Claim Guarantee Bonus")
        applyRewardToCheckout(reward)
        _feedbackMessage.value = "Price Match approved! Code ${reward.couponCode} applied (-$${"%.2f".format(couponDiscount)}) + 50 bonus pts!"
    }

    // Subscriptions
    fun subscribeToPlan(plan: DeviceCarePlan) {
        _activeCarePlan.value = plan
        addLoyaltyPoints(100, "DeviceCare+ ${plan.name} Enrollment Bonus")
        _feedbackMessage.value = "Subscribed to ${plan.name}! Device maintenance coverage is now active 🛡️"
    }

    fun cancelSubscription() {
        _activeCarePlan.value = null
        _feedbackMessage.value = "Subscription cancelled."
    }

    fun bookMaintenance(planName: String, serviceType: String, deviceModel: String, date: String, notes: String) {
        val booking = MaintenanceBooking(
            id = "MB-${(1000..9999).random()}",
            planName = planName,
            serviceType = serviceType,
            deviceModel = deviceModel,
            scheduledDate = date,
            status = "Confirmed",
            notes = notes
        )
        _maintenanceBookings.value = listOf(booking) + _maintenanceBookings.value
        _feedbackMessage.value = "Maintenance appointment booked for $date! Courier will pick up device."
    }

    // 24/7 Chatbot
    fun sendChatMessage(userText: String) {
        if (userText.isBlank()) return

        val userMsg = ChatMessage(
            id = UUID.randomUUID().toString(),
            text = userText,
            isUser = true,
            timestamp = "Just now"
        )
        _chatMessages.value = _chatMessages.value + userMsg
        _isBotTyping.value = true

        viewModelScope.launch {
            delay(550) // realistic typing delay
            val responseText: String
            val chips: List<String>

            val lower = userText.lowercase()
            when {
                lower.contains("order") || lower.contains("track") -> {
                    val latest = _orders.value.firstOrNull()
                    if (latest != null) {
                        responseText = "Your latest order #${latest.id} is currently **${latest.status}** with ${latest.shippingOption.name}. Expected delivery: ${latest.shippingOption.estimatedDelivery}. Would you like to view live tracking?"
                        chips = listOf("View live map", "Contact courier", "Return policy")
                    } else {
                        responseText = "You don't have any active orders right now. Can I help you find a new smartphone or fast charger?"
                        chips = listOf("Top Flagship Phones", "Best Fast Chargers", "View Deals")
                    }
                }
                lower.contains("charger") || lower.contains("watt") || lower.contains("gan") -> {
                    responseText = "For maximum speed and safety, we recommend the **Anker Prime 100W GaN 3-Port** ($59.99, beaten vs Amazon $74.99). It supports PD 3.0 PPS for fast-charging Galaxy S24 Ultra at 45W and iPhone 16 Pro Max at peak 27W simultaneously!"
                    chips = listOf("Add Anker 100W to cart", "Check cable compatibility", "Compare charger prices")
                }
                lower.contains("care") || lower.contains("subscription") || lower.contains("repair") || lower.contains("shield") -> {
                    responseText = "Our **DeviceCare+ Pro Shield** ($9.99/mo) covers unlimited accidental screen repairs ($29 deductible), quarterly deep cleaning and port sanitization, plus free loaner phones and free Next-Day shipping on all store orders!"
                    chips = listOf("View Care Plans", "Book Maintenance", "Check Screen Deductible")
                }
                lower.contains("point") || lower.contains("loyalty") || lower.contains("reward") || lower.contains("tier") -> {
                    val pts = _loyaltyProfile.value.currentPoints
                    val tier = _loyaltyProfile.value.tier.displayName
                    responseText = "You currently have **$pts Loyalty Points** (${_loyaltyProfile.value.tier.multiplier}x multiplier as a $tier). You earn 10 points per dollar on accessories, 5 points on devices, and +15 points daily with check-in. Redeem them anytime in your cart!"
                    chips = listOf("Daily Check-In (+15 pts)", "Redeem $5 Off Coupon", "Redeem $10 Off Coupon")
                }
                lower.contains("price match") || lower.contains("competitor") || lower.contains("cheaper") -> {
                    responseText = "We guarantee the lowest price on earth! If you find any in-stock item cheaper on Amazon, Best Buy, B&H, or carrier stores, use our **Price Match Tool** — we'll match it and give you an extra $5 off coupon + 50 loyalty points!"
                    chips = listOf("Open Price Match Tool", "Check Deal of the Day", "Why are our prices lower?")
                }
                lower.contains("shipping") || lower.contains("delivery") -> {
                    responseText = "We offer 3 ultra-fast shipping tiers: Same-Day Express Courier (within 4 hours), Next-Day Priority (guaranteed by 10:30 AM), and Free Standard Eco for orders over $35 or Silver+ loyalty members!"
                    chips = listOf("Same-Day Delivery cities", "Next-Day rates", "Track recent order")
                }
                else -> {
                    responseText = "I'm here 24/7 to assist with product specs, accessory compatibility (USB-C, MagSafe, Qi2, LDAC), price match claims, and DeviceCare support. What can I help you find?"
                    chips = listOf("Top Flagship Phones", "Compare Charger Prices", "DeviceCare Plans", "Loyalty Rewards")
                }
            }

            val botMsg = ChatMessage(
                id = UUID.randomUUID().toString(),
                text = responseText,
                isUser = false,
                timestamp = "Just now",
                actionChips = chips
            )
            _isBotTyping.value = false
            _chatMessages.value = _chatMessages.value + botMsg
        }
    }
}
