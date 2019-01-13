package com.uvezem.domain

import com.uvezem.data.BidsRepository
import com.uvezem.model.Deliveries
import com.uvezem.model.DeliveriesItem
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface BidsInteractor {

    fun loadFreeBids(): Single<Deliveries>

    fun loadBid(bidId: Int): Single<DeliveriesItem>
}

class BidsInteractorImpl(private val bidsRepository: BidsRepository) : BidsInteractor {
    override fun loadFreeBids(): Single<Deliveries> =
        bidsRepository.loadFreeBids()
            .subscribeOn(Schedulers.io())

    override fun loadBid(bidId: Int): Single<DeliveriesItem> =
        bidsRepository.loadBid(bidId)
            .subscribeOn(Schedulers.io())
}