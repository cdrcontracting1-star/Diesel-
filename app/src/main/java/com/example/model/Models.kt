package com.example.model

enum class ProductCategory(val label: String) {
    ALL("All Products"),
    PHONES("Smartphones"),
    TABLETS("Tablets"),
    CHARGERS("Fast Chargers"),
    EARBUDS("Earbuds & Audio"),
    ADAPTERS("Adapters & Hubs"),
    CASES_PROTECTION("Cases & Glass"),
    CABLES_POWERBANKS("Cables & Power")
}

data class CompetitorPrice(
    val competitorName: String,
    val price: Double,
    val shippingDays: String,
    val inStock: Boolean = true
)

data class Review(
    val id: String,
    val productId: String,
    val author: String,
    val rating: Int,
    val date: String,
    val title: String,
    val comment: String,
    val verifiedBuyer: Boolean = true,
    val helpfulCount: Int = 0
)

data class Product(
    val id: String,
    val name: String,
    val brand: String,
    val category: ProductCategory,
    val price: Double,
    val originalPrice: Double,
    val rating: Double,
    val reviewCount: Int,
    val description: String,
    val keySpecs: Map<String, String>,
    val inStock: Boolean = true,
    val competitorPrices: List<CompetitorPrice>,
    val isFeatured: Boolean = false,
    val isDealOfTheDay: Boolean = false,
    val availableColors: List<String> = listOf("Space Gray", "Midnight Blue", "Silver Titanium")
) {
    val discountPercent: Int
        get() = if (originalPrice > price) (((originalPrice - price) / originalPrice) * 100).toInt() else 0

    val lowestCompetitorPrice: Double?
        get() = competitorPrices.minOfOrNull { it.price }

    val omniSavingsVsLowestCompetitor: Double
        get() = lowestCompetitorPrice?.let { maxOf(0.0, it - price) } ?: 0.0
}

data class CartItem(
    val product: Product,
    var quantity: Int,
    val selectedColor: String = "Default"
) {
    val subtotal: Double
        get() = product.price * quantity
}

data class ShippingOption(
    val id: String,
    val name: String,
    val estimatedDelivery: String,
    val price: Double,
    val badge: String? = null,
    val description: String
)

data class OrderTrackingStep(
    val title: String,
    val timestamp: String,
    val isCompleted: Boolean,
    val isCurrent: Boolean,
    val location: String
)

data class Order(
    val id: String,
    val items: List<CartItem>,
    val subtotal: Double,
    val shippingOption: ShippingOption,
    val discountAmount: Double,
    val total: Double,
    val orderDate: String,
    val status: String,
    val trackingSteps: List<OrderTrackingStep>,
    val pointsEarned: Int
)

enum class LoyaltyTier(
    val displayName: String,
    val pointsThreshold: Int,
    val multiplier: Double,
    val benefits: List<String>
) {
    BRONZE("Bronze Member", 0, 1.0, listOf("1x Points on all orders", "Exclusive Member Deals", "Standard Shipping")),
    SILVER("Silver VIP", 1000, 1.5, listOf("1.5x Points on orders", "Free Standard Shipping", "Priority Dispatch", "Birthday Bonus 100 pts")),
    GOLD("Gold Elite", 3000, 2.0, listOf("2x Points on all purchases", "Free Next-Day Priority Shipping", "Early Access to Drops", "15% DeviceCare+ Discount")),
    PLATINUM("Platinum TechClub", 6000, 3.0, listOf("3x Points on everything", "Free Same-Day Delivery", "Dedicated VIP Concierge", "Free Annual Device Diagnostic"))
}

enum class RewardType {
    DOLLAR_OFF,
    PERCENT_OFF,
    FREE_PRODUCT,
    FREE_MAINTENANCE
}

data class LoyaltyReward(
    val id: String,
    val title: String,
    val description: String,
    val pointsCost: Int,
    val discountValue: Double,
    val type: RewardType,
    val couponCode: String,
    var isRedeemed: Boolean = false
)

data class LoyaltyTransaction(
    val id: String,
    val date: String,
    val description: String,
    val pointsChange: Int,
    val isCredit: Boolean
)

data class LoyaltyProfile(
    val currentPoints: Int,
    val lifetimePoints: Int,
    val tier: LoyaltyTier,
    val streakDays: Int,
    val lastCheckInDate: String,
    val availableRewards: List<LoyaltyReward>,
    val redeemedCoupons: List<LoyaltyReward>,
    val transactionHistory: List<LoyaltyTransaction>
) {
    val dollarEquivalent: Double
        get() = currentPoints * 0.01

    val nextTier: LoyaltyTier?
        get() = when (tier) {
            LoyaltyTier.BRONZE -> LoyaltyTier.SILVER
            LoyaltyTier.SILVER -> LoyaltyTier.GOLD
            LoyaltyTier.GOLD -> LoyaltyTier.PLATINUM
            LoyaltyTier.PLATINUM -> null
        }

    val pointsToNextTier: Int
        get() = nextTier?.let { maxOf(0, it.pointsThreshold - lifetimePoints) } ?: 0

    val progressToNextTier: Float
        get() = nextTier?.let {
            val prevThreshold = tier.pointsThreshold
            val span = it.pointsThreshold - prevThreshold
            val current = lifetimePoints - prevThreshold
            (current.toFloat() / span.toFloat()).coerceIn(0f, 1f)
        } ?: 1f
}

data class DeviceCarePlan(
    val id: String,
    val name: String,
    val monthlyPrice: Double,
    val tagline: String,
    val features: List<String>,
    val isPopular: Boolean = false
)

data class MaintenanceBooking(
    val id: String,
    val planName: String,
    val serviceType: String,
    val deviceModel: String,
    val scheduledDate: String,
    val status: String, // "Confirmed", "In Progress", "Completed"
    val notes: String
)

data class ChatMessage(
    val id: String,
    val text: String,
    val isUser: Boolean,
    val timestamp: String,
    val actionChips: List<String> = emptyList(),
    val linkedProductId: String? = null
)
