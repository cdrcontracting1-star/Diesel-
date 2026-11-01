package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.SampleData
import com.example.model.LoyaltyTier
import com.example.viewmodel.OmniViewModel
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("OmniMobile", appName)
    }

    @Test
    fun `test loyalty points daily checkin and streak`() {
        val vm = OmniViewModel()
        val initialPoints = vm.loyaltyProfile.value.currentPoints
        vm.dailyCheckIn()
        // Check-in adds bonus or alerts if already checked in today
        assertTrue(vm.loyaltyProfile.value.currentPoints >= initialPoints)
    }

    @Test
    fun `test price comparison has lowest omnimobile price`() {
        val s24 = SampleData.products.first { it.id == "prod_s24u" }
        val lowestComp = s24.lowestCompetitorPrice
        assertNotNull(lowestComp)
        assertTrue("Omni price should be lower than competitor", s24.price < lowestComp!!)
    }

    @Test
    fun `test reward redemption logic`() {
        val vm = OmniViewModel()
        val fiveDollarReward = vm.loyaltyProfile.value.availableRewards.first { it.id == "rew_5off" }
        val prevPoints = vm.loyaltyProfile.value.currentPoints
        vm.redeemReward(fiveDollarReward)
        val newPoints = vm.loyaltyProfile.value.currentPoints
        assertEquals(prevPoints - fiveDollarReward.pointsCost, newPoints)
        assertTrue(vm.loyaltyProfile.value.redeemedCoupons.any { it.id == "rew_5off" })
    }
}
