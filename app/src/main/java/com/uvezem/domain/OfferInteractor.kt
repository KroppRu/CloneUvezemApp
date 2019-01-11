package com.uvezem.domain

import com.uvezem.data.OfferRepository
import com.uvezem.model.Company
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface OfferInteractor {

    fun loadCompanyData(): Single<List<Company>>
}

class OfferInteractorImpl(private val offerRepository: OfferRepository): OfferInteractor {

    override fun loadCompanyData(): Single<List<Company>> =
        offerRepository.loadCompanyData()
            .subscribeOn(Schedulers.io())
}