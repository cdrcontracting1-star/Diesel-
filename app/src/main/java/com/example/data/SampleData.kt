package com.example.data

import com.example.model.*

object SampleData {

    val shippingOptions = listOf(
        ShippingOption(
            id = "ship_same_day",
            name = "Same-Day Express Courier",
            estimatedDelivery = "Today by 7:00 PM",
            price = 9.99,
            badge = "LIGHTNING FAST",
            description = "Hand-delivered from local fulfillment hub within 4 hours."
        ),
        ShippingOption(
            id = "ship_next_day",
            name = "Next-Day Priority",
            estimatedDelivery = "Tomorrow by 10:30 AM",
            price = 4.99,
            badge = "POPULAR",
            description = "Guaranteed morning delivery with temperature-controlled transit."
        ),
        ShippingOption(
            id = "ship_standard",
            name = "Standard Eco Saver",
            estimatedDelivery = "2-3 Business Days",
            price = 0.0,
            badge = "FREE OVER $35",
            description = "Carbon-neutral certified delivery with full live GPS tracking."
        )
    )

    val deviceCarePlans = listOf(
        DeviceCarePlan(
            id = "plan_essential",
            name = "Care Essential",
            monthlyPrice = 4.99,
            tagline = "Essential preventative care & accessories discount",
            features = listOf(
                "Free 2x Tempered Glass Screen Protectors/yr",
                "Annual Device Battery & Port Health Audit",
                "15% Off all chargers, cables & adapters",
                "Standard email & chat support",
                "Zero deductible on diagnosis"
            ),
            isPopular = false
        ),
        DeviceCarePlan(
            id = "plan_pro",
            name = "Pro Shield+",
            monthlyPrice = 9.99,
            tagline = "Complete peace of mind for daily mobile power-users",
            features = listOf(
                "Unlimited accidental screen repair ($29 deductible)",
                "Free quarterly deep cleaning & speaker grill polish",
                "Free overnight loaner device during repairs",
                "25% Off all accessories & wireless audio",
                "Priority 24/7 VIP Chatbot & Technician hotline",
                "Free Next-Day shipping on all shop orders"
            ),
            isPopular = true
        ),
        DeviceCarePlan(
            id = "plan_ultimate",
            name = "Ultimate Slate & Phone",
            monthlyPrice = 14.99,
            tagline = "Maximum coverage for flagship phones and tablets",
            features = listOf(
                "Full theft, loss, liquid and drop replacement",
                "Free annual battery replacement (<80% health)",
                "Annual $100 trade-in upgrade credit bonus",
                "Coverage for 1 phone AND 1 tablet bundled",
                "Same-Day courier repair pickup & return",
                "Free Same-Day shipping on all shop purchases"
            ),
            isPopular = false
        )
    )

    val availableLoyaltyRewards = listOf(
        LoyaltyReward(
            id = "rew_5off",
            title = "$5 Off Any Accessory",
            description = "Redeem on chargers, earbuds, cables or adapters (min. $20 purchase).",
            pointsCost = 500,
            discountValue = 5.0,
            type = RewardType.DOLLAR_OFF,
            couponCode = "OMNI5ACC"
        ),
        LoyaltyReward(
            id = "rew_10off",
            title = "$10 Off Entire Cart",
            description = "Take $10 off any order above $40 store-wide.",
            pointsCost = 1000,
            discountValue = 10.0,
            type = RewardType.DOLLAR_OFF,
            couponCode = "OMNI10CART"
        ),
        LoyaltyReward(
            id = "rew_25off",
            title = "$25 Flagship Device Voucher",
            description = "Save $25 on any smartphone or tablet purchase.",
            pointsCost = 2500,
            discountValue = 25.0,
            type = RewardType.DOLLAR_OFF,
            couponCode = "DEVICE25VIP"
        ),
        LoyaltyReward(
            id = "rew_free_charger",
            title = "Free Anker 30W GaN Charger",
            description = "Get an Anker 30W Ultra-Compact Fast Charger 100% free with any order.",
            pointsCost = 1500,
            discountValue = 22.99,
            type = RewardType.FREE_PRODUCT,
            couponCode = "FREE30WGAN"
        ),
        LoyaltyReward(
            id = "rew_care_month",
            title = "1 Month Free Pro Shield+",
            description = "Try 30 days of comprehensive device maintenance & screen coverage.",
            pointsCost = 800,
            discountValue = 9.99,
            type = RewardType.FREE_MAINTENANCE,
            couponCode = "CAREPRO1MO"
        )
    )

    val products = listOf(
        Product(
            id = "prod_s24u",
            name = "Galaxy S24 Ultra (512GB)",
            brand = "Samsung",
            category = ProductCategory.PHONES,
            price = 1199.99,
            originalPrice = 1419.99,
            rating = 4.9,
            reviewCount = 842,
            description = "The ultimate AI flagship phone with Titanium frame, 200MP quad-camera, embedded S-Pen, and Snapdragon 8 Gen 3 for Galaxy.",
            keySpecs = mapOf(
                "Display" to "6.8\" Dynamic AMOLED 2X, 120Hz, 2600 nits",
                "Processor" to "Snapdragon 8 Gen 3",
                "Battery" to "5000 mAh (45W Super Fast 2.0)",
                "Storage" to "512GB UFS 4.0, 12GB RAM",
                "Camera" to "200MP Main + 50MP 5x Periscope"
            ),
            isFeatured = true,
            isDealOfTheDay = true,
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 1299.99, "2-Day Prime"),
                CompetitorPrice("Best Buy", 1349.99, "3 Days"),
                CompetitorPrice("Verizon Direct", 1419.99, "Standard Ground"),
                CompetitorPrice("B&H Photo", 1279.00, "2-3 Days")
            )
        ),
        Product(
            id = "prod_ip16pm",
            name = "iPhone 16 Pro Max (256GB)",
            brand = "Apple",
            category = ProductCategory.PHONES,
            price = 1149.00,
            originalPrice = 1199.00,
            rating = 4.8,
            reviewCount = 1240,
            description = "Grade 5 Titanium design with Camera Control button, 48MP Fusion camera system, A18 Pro chip, and industry-leading battery longevity.",
            keySpecs = mapOf(
                "Display" to "6.9\" Super Retina XDR OLED, ProMotion 120Hz",
                "Processor" to "Apple A18 Pro (3nm)",
                "Port" to "USB-C 3.2 (up to 10Gbps)",
                "Battery" to "Up to 33 hours video playback",
                "MagSafe" to "Qi2 & 25W MagSafe Fast Wireless"
            ),
            isFeatured = true,
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 1199.00, "1-Day Prime"),
                CompetitorPrice("Best Buy", 1199.99, "2 Days"),
                CompetitorPrice("AT&T Store", 1199.99, "Ground 3-5 Days")
            )
        ),
        Product(
            id = "prod_pixel9p",
            name = "Pixel 9 Pro XL (256GB)",
            brand = "Google",
            category = ProductCategory.PHONES,
            price = 999.00,
            originalPrice = 1199.00,
            rating = 4.7,
            reviewCount = 520,
            description = "Powered by Google Tensor G4 with 16GB RAM for on-device Gemini AI, Super Actua display, and top-tier computational photography.",
            keySpecs = mapOf(
                "Display" to "6.8\" Super Actua LTPO OLED (1-120Hz)",
                "Processor" to "Google Tensor G4 + Titan M2",
                "RAM" to "16GB LPDDR5X",
                "Charging" to "37W Wired Fast Charge (70% in 30m)",
                "OS Support" to "7 years of OS & Security updates"
            ),
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 1099.00, "2 Days"),
                CompetitorPrice("Best Buy", 1149.99, "Same-Day Pickup"),
                CompetitorPrice("Google Store", 1199.00, "3-4 Days")
            )
        ),
        Product(
            id = "prod_ipad_pro_m4",
            name = "iPad Pro 13\" OLED M4 (256GB)",
            brand = "Apple",
            category = ProductCategory.TABLETS,
            price = 1219.00,
            originalPrice = 1299.00,
            rating = 4.9,
            reviewCount = 412,
            description = "Impossibly thin 5.1mm slate with revolutionary Ultra Retina XDR Tandem OLED display and extreme M4 processing power.",
            keySpecs = mapOf(
                "Display" to "13\" Tandem OLED, 1600 nits peak, 120Hz",
                "Chip" to "Apple M4 (9-core CPU, 10-core GPU)",
                "Port" to "Thunderbolt 4 / USB 4 (40Gbps)",
                "Pencil" to "Supports Apple Pencil Pro & Magic Keyboard"
            ),
            isFeatured = true,
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 1289.00, "2 Days"),
                CompetitorPrice("Best Buy", 1299.00, "Next-Day"),
                CompetitorPrice("B&H Photo", 1279.00, "2-3 Days")
            )
        ),
        Product(
            id = "prod_tabs9_plus",
            name = "Galaxy Tab S9+ (12.4\" 256GB)",
            brand = "Samsung",
            category = ProductCategory.TABLETS,
            price = 789.99,
            originalPrice = 999.99,
            rating = 4.8,
            reviewCount = 310,
            description = "IP68 water-resistant premium tablet with included S Pen, Snapdragon 8 Gen 2, and desktop-class Samsung DeX multitasking.",
            keySpecs = mapOf(
                "Display" to "12.4\" Dynamic AMOLED 2X, 120Hz",
                "Durability" to "IP68 Water & Dust Resistant",
                "Battery" to "10,090 mAh (45W fast charge)",
                "Audio" to "Quad AKG Speakers with Dolby Atmos"
            ),
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 849.99, "2 Days"),
                CompetitorPrice("Best Buy", 899.99, "2-3 Days"),
                CompetitorPrice("Samsung Direct", 999.99, "Standard")
            )
        ),
        Product(
            id = "prod_anker_prime_100w",
            name = "Anker Prime 100W GaN 3-Port Wall Charger",
            brand = "Anker",
            category = ProductCategory.CHARGERS,
            price = 59.99,
            originalPrice = 84.99,
            rating = 4.9,
            reviewCount = 1890,
            description = "Ultra-compact Gallium Nitride fast charger with 2x USB-C and 1x USB-A. Powers laptops, tablets, and phones simultaneously with ActiveShield 2.0 safety.",
            keySpecs = mapOf(
                "Max Output" to "100W Power Delivery 3.0 & PPS",
                "Ports" to "2x USB-C (100W max), 1x USB-A (22.5W)",
                "Technology" to "GaNPrime with Dynamic Power Distribution",
                "Foldable" to "Yes, travel-friendly folding prongs"
            ),
            isDealOfTheDay = true,
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 74.99, "2 Days"),
                CompetitorPrice("Best Buy", 79.99, "3 Days"),
                CompetitorPrice("Target", 84.99, "Standard Ground")
            )
        ),
        Product(
            id = "prod_samsung_45w",
            name = "Samsung 45W Super Fast 2.0 Charger + 5A Cable",
            brand = "Samsung",
            category = ProductCategory.CHARGERS,
            price = 28.50,
            originalPrice = 49.99,
            rating = 4.7,
            reviewCount = 980,
            description = "Official Samsung Power Delivery 3.0 PPS 45W adapter. Recharges Galaxy S24 Ultra from 0 to 70% in under 30 minutes.",
            keySpecs = mapOf(
                "Output" to "45W PPS (Programmable Power Supply)",
                "Cable" to "Included 5A 1.8m Braided USB-C Cable",
                "Compatibility" to "Galaxy S24/S23, Note, Tab S9, Pixel, iPhone"
            ),
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 39.99, "2 Days"),
                CompetitorPrice("Best Buy", 44.99, "1-2 Days"),
                CompetitorPrice("Carrier Store", 49.99, "In-Store")
            )
        ),
        Product(
            id = "prod_sony_xm5",
            name = "Sony WF-1000XM5 True Wireless Earbuds",
            brand = "Sony",
            category = ProductCategory.EARBUDS,
            price = 228.00,
            originalPrice = 299.99,
            rating = 4.8,
            reviewCount = 1530,
            description = "Industry-leading noise cancellation powered by dual proprietary processors. High-resolution LDAC audio, deep punchy bass, and bone-conduction mics.",
            keySpecs = mapOf(
                "Noise Cancelling" to "Dual Processor V2 + HD Noise Cancelling QN2e",
                "Battery" to "8h earbuds + 16h Qi wireless charging case",
                "Codecs" to "LDAC, AAC, SBC, LC3",
                "Water Resistance" to "IPX4 splash proof"
            ),
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 278.00, "1-Day"),
                CompetitorPrice("Best Buy", 299.99, "2 Days"),
                CompetitorPrice("B&H Photo", 269.99, "2 Days")
            )
        ),
        Product(
            id = "prod_airpods_pro2",
            name = "Apple AirPods Pro 2 (USB-C MagSafe)",
            brand = "Apple",
            category = ProductCategory.EARBUDS,
            price = 189.99,
            originalPrice = 249.00,
            rating = 4.9,
            reviewCount = 3450,
            description = "Up to 2x more Active Noise Cancellation, Adaptive Audio, Personalized Spatial Audio with dynamic head tracking, and hearing health test support.",
            keySpecs = mapOf(
                "Chip" to "Apple H2 in buds, U1 in MagSafe Case",
                "Case" to "USB-C MagSafe with precision speaker and lanyard loop",
                "Battery" to "6h with ANC on, 30h total with case",
                "Rating" to "IP54 dust, sweat, and water resistance"
            ),
            isFeatured = true,
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 219.00, "1-Day"),
                CompetitorPrice("Best Buy", 249.99, "Same-Day"),
                CompetitorPrice("Target", 229.99, "2 Days")
            )
        ),
        Product(
            id = "prod_soundcore_liberty4",
            name = "Anker Soundcore Liberty 4 NC",
            brand = "Anker",
            category = ProductCategory.EARBUDS,
            price = 69.99,
            originalPrice = 99.99,
            rating = 4.7,
            reviewCount = 760,
            description = "High-fidelity wireless earbuds with 98.5% noise reduction, Hi-Res wireless audio with LDAC, 50-hour battery life, and 6-mic AI clear calling.",
            keySpecs = mapOf(
                "Battery" to "10h playtime (50h total with case)",
                "ANC" to "Adaptive ANC 2.0 real-time adjustment",
                "Charging" to "10 min quick charge gives 4 hours playback"
            ),
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 89.99, "2 Days"),
                CompetitorPrice("Best Buy", 99.99, "3 Days")
            )
        ),
        Product(
            id = "prod_satechi_hub",
            name = "Satechi 9-in-1 Dual USB-C Multi-Port Hub",
            brand = "Satechi",
            category = ProductCategory.ADAPTERS,
            price = 64.99,
            originalPrice = 89.99,
            rating = 4.8,
            reviewCount = 612,
            description = "Universal aerospace aluminum hub with 4K60Hz HDMI, Gigabit Ethernet, 100W PD passthrough, SD/microSD slots, and 3x USB 3.2 10Gbps ports.",
            keySpecs = mapOf(
                "Ports" to "HDMI 4K@60Hz, 1Gbps RJ45, 100W PD-in, SD 4.0, 3x USB-A",
                "Chassis" to "Sleek space gray anodized aluminum with thermal dissipation",
                "Compatibility" to "iPad Pro, Galaxy Tab, Mac, Android phones with OTG"
            ),
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 79.99, "2 Days"),
                CompetitorPrice("Best Buy", 89.99, "2 Days"),
                CompetitorPrice("B&H Photo", 84.95, "3 Days")
            )
        ),
        Product(
            id = "prod_anker_dac_adapter",
            name = "Anker USB-C to 3.5mm Hi-Res Audio DAC Adapter",
            brand = "Anker",
            category = ProductCategory.ADAPTERS,
            price = 12.99,
            originalPrice = 19.99,
            rating = 4.6,
            reviewCount = 420,
            description = "High-resolution digital-to-analog converter with 96kHz/24-bit audio sampling for lossless music, in-line mic, and volume controls.",
            keySpecs = mapOf(
                "Sampling Rate" to "96kHz / 24-bit lossless audio",
                "Build" to "Kevlar braided wire tested for 20,000+ bends",
                "Compatibility" to "iPhone 15/16, Galaxy S24, Pixel 9, iPad Pro"
            ),
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 16.99, "2 Days"),
                CompetitorPrice("Best Buy", 19.99, "Standard")
            )
        ),
        Product(
            id = "prod_maggo_10k",
            name = "Anker MagGo Qi2 10,000mAh Magnetic Power Bank",
            brand = "Anker",
            category = ProductCategory.CABLES_POWERBANKS,
            price = 69.99,
            originalPrice = 89.99,
            rating = 4.9,
            reviewCount = 880,
            description = "Qi2-certified 15W ultra-fast magnetic wireless charging with a smart digital display showing remaining battery and time-to-full.",
            keySpecs = mapOf(
                "Capacity" to "10,000 mAh (recharges iPhone twice)",
                "Wireless Output" to "Qi2 15W Ultra-Fast Magnetic Snap",
                "Wired Output" to "27W two-way USB-C Fast Charge",
                "Display" to "Full color dynamic battery percentage screen"
            ),
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 79.99, "2 Days"),
                CompetitorPrice("Best Buy", 89.99, "2-3 Days")
            )
        ),
        Product(
            id = "prod_nomad_240w",
            name = "Nomad Kevlar Braided 240W USB-C Cable (2m)",
            brand = "Nomad",
            category = ProductCategory.CABLES_POWERBANKS,
            price = 24.99,
            originalPrice = 39.99,
            rating = 4.9,
            reviewCount = 330,
            description = "Military-grade double-braided Kevlar fiber cable with custom metal alloy connector heads. Supports maximum 240W USB PD 3.1 charging.",
            keySpecs = mapOf(
                "Wattage" to "240W Extended Power Range (48V/5A)",
                "Length" to "2.0 meters (6.6 feet)",
                "Durability" to "Double Kevlar outer weave with 5-year warranty"
            ),
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 34.99, "2 Days"),
                CompetitorPrice("Best Buy", 39.99, "Standard")
            )
        ),
        Product(
            id = "prod_spigen_ultra_hybrid",
            name = "Spigen Ultra Hybrid MagFit Air Cushion Case",
            brand = "Spigen",
            category = ProductCategory.CASES_PROTECTION,
            price = 19.99,
            originalPrice = 34.99,
            rating = 4.8,
            reviewCount = 1120,
            description = "Crystal clear anti-yellowing hard back with shock-absorbing TPU bumper and built-in strong N52 neodymium magnetic ring.",
            keySpecs = mapOf(
                "Protection" to "Air Cushion Technology military-grade drop test certified",
                "Clarity" to "Infused with blue resin for long-lasting crystal transparency",
                "Magnetism" to "Built-in strong N52 MagSafe ring"
            ),
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 26.99, "2 Days"),
                CompetitorPrice("Best Buy", 34.99, "3 Days")
            )
        ),
        Product(
            id = "prod_belkin_ultraglass2",
            name = "Belkin UltraGlass 2 Privacy Screen Protector (2-Pack)",
            brand = "Belkin",
            category = ProductCategory.CASES_PROTECTION,
            price = 29.99,
            originalPrice = 44.99,
            rating = 4.7,
            reviewCount = 590,
            description = "Lithium aluminosilicate glass tested to be 2.7x stronger than tempered glass with micro-louver two-way privacy filter and easy alignment tray.",
            keySpecs = mapOf(
                "Hardness" to "9H+ double-ion exchange glass",
                "Privacy" to "Two-way side privacy filter prevents visual snooping",
                "Installation" to "Included Easy-Align tray for zero-bubble installation"
            ),
            competitorPrices = listOf(
                CompetitorPrice("Amazon", 39.99, "2 Days"),
                CompetitorPrice("Apple Store", 44.95, "Ground")
            )
        )
    )

    val sampleReviews = listOf(
        Review(
            id = "rev_1",
            productId = "prod_s24u",
            author = "Marcus Vance",
            rating = 5,
            date = "Oct 28, 2026",
            title = "Unbeatable price, arrived in under 4 hours!",
            comment = "OmniMobile had this for $1,199 when Amazon wanted $1,299. Ordered with the Same-Day delivery and it was at my front door before dinner. Screen is breathtaking and S-Pen is super handy.",
            verifiedBuyer = true,
            helpfulCount = 42
        ),
        Review(
            id = "rev_2",
            productId = "prod_s24u",
            author = "Elena Rostova",
            rating = 5,
            date = "Oct 22, 2026",
            title = "Best Android phone ever built",
            comment = "The flat screen makes applying glass protectors painless. Battery easily lasts 1.5 days of heavy gaming and camera zoom is magical. Also got 1200 loyalty points on this order!",
            verifiedBuyer = true,
            helpfulCount = 19
        ),
        Review(
            id = "rev_3",
            productId = "prod_anker_prime_100w",
            author = "Devon Cooper",
            rating = 5,
            date = "Oct 30, 2026",
            title = "One charger to rule them all",
            comment = "Replaced 3 separate power bricks in my backpack. Charges my laptop at 65W and fast charges my phone at the same time without heating up.",
            verifiedBuyer = true,
            helpfulCount = 68
        ),
        Review(
            id = "rev_4",
            productId = "prod_sony_xm5",
            author = "Samantha Hayes",
            rating = 5,
            date = "Oct 19, 2026",
            title = "ANC is spooky good on airplanes",
            comment = "Flew cross-country with these and the jet engine rumble disappeared completely. Soundstage is punchy and warm. Paired with OmniShield subscription for free loss replacement.",
            verifiedBuyer = true,
            helpfulCount = 31
        )
    )

    val initialLoyaltyProfile = LoyaltyProfile(
        currentPoints = 1450,
        lifetimePoints = 2850,
        tier = LoyaltyTier.SILVER,
        streakDays = 5,
        lastCheckInDate = "2026-10-31",
        availableRewards = availableLoyaltyRewards,
        redeemedCoupons = listOf(
            LoyaltyReward(
                id = "rew_prev_1",
                title = "$5 Off Any Accessory",
                description = "Active promo code for your next order.",
                pointsCost = 500,
                discountValue = 5.0,
                type = RewardType.DOLLAR_OFF,
                couponCode = "OMNI5ACC-7749",
                isRedeemed = true
            )
        ),
        transactionHistory = listOf(
            LoyaltyTransaction("tx_1", "Oct 31, 2026", "Daily Check-in Streak Day 5", 15, true),
            LoyaltyTransaction("tx_2", "Oct 28, 2026", "Purchase: Anker Prime 100W GaN", 600, true),
            LoyaltyTransaction("tx_3", "Oct 25, 2026", "Verified Product Review Bonus", 50, true),
            LoyaltyTransaction("tx_4", "Oct 20, 2026", "Redeemed: $5 Off Accessory Coupon", 500, false),
            LoyaltyTransaction("tx_5", "Oct 15, 2026", "DeviceCare+ Monthly Renewal", 100, true)
        )
    )
}
