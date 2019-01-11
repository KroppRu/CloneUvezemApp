package com.uvezem.data

import com.uvezem.data.network.ApiRetrofit
import com.uvezem.data.prefs.Preference
import com.uvezem.model.Company
import io.reactivex.Single

class OfferRepository(
    private val apiRetrofit: ApiRetrofit,
    private val preference: Preference
) {

    fun loadCompanyData(): Single<List<Company>> {
        val userApp = preference.getUserApp()
        return apiRetrofit.loadCompanyData("Bearer ${userApp?.token}")
    }
}