package com.uvezem.data

import com.uvezem.data.network.ApiRetrofit
import com.uvezem.data.prefs.Preference
import com.uvezem.model.Deliveries
import com.uvezem.model.DeliveriesItem
import io.reactivex.Single

class BidsRepository(
    private val apiRetrofit: ApiRetrofit,
    private val preference: Preference
) {

    fun loadFreeBids(): Single<Deliveries> {
        val userApp = preference.getUserApp()
        return apiRetrofit.loadFreeBids("Bearer ${userApp?.token}")
    }

    fun loadBid(bidId: Int): Single<DeliveriesItem> {
        val userApp = preference.getUserApp()
        return apiRetrofit.loadFreeBid("Bearer ${userApp?.token}", bidId)
    }

}