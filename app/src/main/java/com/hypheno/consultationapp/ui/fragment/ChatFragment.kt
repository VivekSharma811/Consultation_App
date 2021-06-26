package com.hypheno.consultationapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hypheno.consultationapp.R
import com.hypheno.consultationapp.databinding.FragmentChatBinding
import com.hypheno.consultationapp.model.dataclass.ChatData
import com.hypheno.consultationapp.model.network.ApiService
import com.hypheno.consultationapp.ui.adapter.MessageAdapter
import com.hypheno.consultationapp.ui.vm.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : BaseFragment() {

    private val disposable = CompositeDisposable()
    var adapter: MessageAdapter? = null
    private lateinit var dataBinding: FragmentChatBinding

    private val viewModel: ChatViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.stackFromEnd = true
        rvChat.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }

        getMessages()


        disposable.add(
            ApiService().getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ChatData>() {
                    override fun onSuccess(t: ChatData) {
                        Log.d("success", t.toString())
                        adapter = context?.let {
                            MessageAdapter(
                                it,
                                t.messages
                            )
                        }
                        rvChat.adapter = adapter
                        dataBinding.doctor = t.consultation_request
                    }

                    override fun onError(e: Throwable) {
                        Log.e("error", e.localizedMessage!!)
                    }

                })
        )
    }

    private fun getMessages() = launch {
        viewModel.fetchMessages()
        viewModel.messages.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                adapter = context?.let {
                    MessageAdapter(
                        it,
                        data.messages
                    )
                }
                rvChat.adapter = adapter
                dataBinding.doctor = data.consultation_request
            }
        })
    }
}