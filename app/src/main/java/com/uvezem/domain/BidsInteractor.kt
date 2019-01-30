package com.uvezem.domain

import com.uvezem.data.BidsRepository
import com.uvezem.model.Deliveries
import com.uvezem.model.DeliveriesItem
import com.uvezem.model.SimpleId
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface BidsInteractor {

    fun loadFreeBids(): Single<Deliveries>

    fun loadMyBids(): Single<Deliveries>

    fun loadBid(bidId: Int): Single<DeliveriesItem>

    fun cancelOrder(orderId: Int): Completable

    fun loadArchiveBids(): Single<Deliveries>
}

class BidsInteractorImpl(private val bidsRepository: BidsRepository) : BidsInteractor {
    override fun loadFreeBids(): Single<Deliveries> =
        bidsRepository.loadFreeBids()
            .subscribeOn(Schedulers.io())

    override fun loadMyBids(): Single<Deliveries> =
        bidsRepository.loadMyBids()
            .subscribeOn(Schedulers.io())

    override fun loadBid(bidId: Int): Single<DeliveriesItem> =
        bidsRepository.loadBid(bidId)
            .subscribeOn(Schedulers.io())

    override fun cancelOrder(orderId: Int): Completable =
        bidsRepository.cancelOrder(SimpleId(orderId))
            .subscribeOn(Schedulers.io())

    override fun loadArchiveBids(): Single<Deliveries> =
        bidsRepository.loadMyBids()
            .subscribeOn(Schedulers.io())
}