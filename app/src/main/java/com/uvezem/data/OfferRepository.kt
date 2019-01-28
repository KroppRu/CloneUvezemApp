package com.uvezem.data

import com.uvezem.data.network.ApiRetrofit
import com.uvezem.data.prefs.Preference
import com.uvezem.model.*
import io.reactivex.Completable
import io.reactivex.Single

class OfferRepository(
    private val apiRetrofit: ApiRetrofit,
    private val preference: Preference
) {

    fun loadCompanyByUser(): Single<List<Company>> {
        return apiRetrofit.loadCompanyData(preference.getUserAuthToken())
    }

    fun createOffer(offer: Offer): Single<Order> {
        return apiRetrofit.createOffer(preference.getUserAuthToken(), offer)
    }

    fun loadCompanyDetails(companyId: Int): Single<CompanyDetail> {
        return apiRetrofit.loadCompanyDetails(preference.getUserAuthToken(), companyId)
    }

    fun attachInfo(offerInfo:OfferInfo): Completable {
        return apiRetrofit.attachInfo(preference.getUserAuthToken(), offerInfo)
    }
}