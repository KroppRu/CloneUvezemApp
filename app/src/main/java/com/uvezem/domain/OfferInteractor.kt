package com.uvezem.domain

import com.uvezem.data.OfferRepository
import com.uvezem.model.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface OfferInteractor {

    fun loadCompanyByUser(): Single<List<Company>>

    fun createOffer(bidId: Int, company: Company, person: Person, amount: Int, date: String): Single<Order>

    fun loadCompanyData(companyId: Int): Single<CompanyDetail>
}

class OfferInteractorImpl(private val offerRepository: OfferRepository) : OfferInteractor {

    override fun loadCompanyByUser(): Single<List<Company>> =
        offerRepository.loadCompanyByUser()
            .subscribeOn(Schedulers.io())

    override fun createOffer(bidId: Int, company: Company, person: Person, amount: Int, date: String): Single<Order> =
        offerRepository.createOffer(Offer(bidId, company.id, person.id, amount, date))
            .subscribeOn(Schedulers.io())

    override fun loadCompanyData(companyId: Int): Single<CompanyDetail> =
        offerRepository.loadCompanyDetails(companyId)
            .subscribeOn(Schedulers.io())
}