package com.uvezem.domain

import com.uvezem.data.BidsRepository
import com.uvezem.model.Deliveries
import io.reactivex.Single

interface BidsInteractor {

    fun loadFreeBids(): Single<Deliveries>
}

class BidsInteractorImpl(private val bidsRepository: BidsRepository): BidsInteractor {
    override fun loadFreeBids(): Single<Deliveries> =
            bidsRepository.loadFreeBids()
}