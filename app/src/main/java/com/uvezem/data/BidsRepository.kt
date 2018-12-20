package com.uvezem.data

import com.uvezem.features.network.ApiRetrofit
import com.uvezem.features.prefs.Preference
import com.uvezem.model.Deliveries
import io.reactivex.Single

class BidsRepository(
    private val apiRetrofit: ApiRetrofit,
    private val preference: Preference
) {

    fun loadFreeBids(): Single<Deliveries> {
        val userApp = preference.getUserApp()
        return apiRetrofit.loadFreeBids("Bearer ${userApp?.token}")
    }
}