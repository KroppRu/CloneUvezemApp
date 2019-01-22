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
        val userApp = preference.getUserApp()
        return apiRetrofit.loadCompanyData("Bearer ${userApp?.token}")
    }

    fun createOffer(offer: Offer): Single<Order> {
        val userApp = preference.getUserApp()
        return apiRetrofit.createOffer("Bearer ${userApp?.token}", offer)
    }

    fun loadCompanyDetails(companyId: Int): Single<CompanyDetail> {
        val userApp = preference.getUserApp()
        return apiRetrofit.loadCompanyDetails("Bearer ${userApp?.token}", companyId)
    }

    fun attachInfo(offerInfo:OfferInfo): Completable {
        val userApp = preference.getUserApp()
        return apiRetrofit.attachInfo("Bearer ${userApp?.token}", offerInfo)
    }
}