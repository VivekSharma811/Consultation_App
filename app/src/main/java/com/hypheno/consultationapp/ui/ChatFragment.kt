package com.hypheno.consultationapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hypheno.consultationapp.R
import com.hypheno.consultationapp.model.dataclass.ChatData
import com.hypheno.consultationapp.model.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment() {

    private val disposable = CompositeDisposable()
    var adapter: MessageAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.stackFromEnd = true
        rvChat.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }


        disposable.add(
            ApiService().getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ChatData>() {
                    override fun onSuccess(t: ChatData) {
                        Log.d("success", t.toString())
                        adapter = context?.let { MessageAdapter(it, t.messages) }
                        rvChat.adapter = adapter
                    }

                    override fun onError(e: Throwable) {
                        Log.e("error", e.localizedMessage!!)
                    }

                })
        )
    }
}