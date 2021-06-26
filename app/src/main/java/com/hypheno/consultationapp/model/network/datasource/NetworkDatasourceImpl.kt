package com.hypheno.consultationapp.model.network.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hypheno.consultationapp.model.dataclass.ChatData
import com.hypheno.consultationapp.model.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class NetworkDatasourceImpl : NetworkDatasource {

    private val disposable = CompositeDisposable()

    private val _messages = MutableLiveData<ChatData>()
    override val messages: LiveData<ChatData>
        get() = _messages

    override fun fetchMessages() {
        disposable.add(
            ApiService().getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ChatData>() {
                    override fun onSuccess(t: ChatData) {
                        _messages.value = t
                    }

                    override fun onError(e: Throwable) {
                        Log.e("error", e.localizedMessage!!)
                    }
                })
        )
    }
}