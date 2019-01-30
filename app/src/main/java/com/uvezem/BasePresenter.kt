package com.uvezem

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    fun dispose() {
        disposable.dispose()
    }
}