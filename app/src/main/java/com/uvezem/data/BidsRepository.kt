package com.uvezem.data

import com.uvezem.data.network.ApiRetrofit
import com.uvezem.data.prefs.Preference
import com.uvezem.model.Deliveries
import com.uvezem.model.DeliveriesItem
import com.uvezem.model.SimpleId
import io.reactivex.Completable
import io.reactivex.Single

class BidsRepository(
    private val apiRetrofit: ApiRetrofit,
    private val preference: Preference
) {

    fun loadFreeBids(): Single<Deliveries> {
        return apiRetrofit.loadFreeBids(preference.getUserAuthToken())
    }

    fun loadBid(bidId: Int): Single<DeliveriesItem> {
        return apiRetrofit.loadFreeBid(preference.getUserAuthToken(), bidId)
    }

    fun cancelOrder(simpleId: SimpleId): Completable {
        return apiRetrofit.cancelOrder(preference.getUserAuthToken(), simpleId)
    }

}