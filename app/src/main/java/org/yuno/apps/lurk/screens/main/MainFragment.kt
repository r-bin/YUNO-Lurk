package org.yuno.apps.lurk.screens.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.yuno.apps.lurk.R
import java.util.*
import kotlin.concurrent.timerTask

class MainFragment : Fragment() {
    private lateinit var adapter: RecyclerAdapter

    lateinit var message: TextView

    lateinit var streams_list: RecyclerView

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        message = view?.findViewById<TextView>(R.id.message)!!

        streams_list = view?.findViewById<RecyclerView>(R.id.streams_list)!!
        streams_list.layoutManager = LinearLayoutManager(context)
        adapter = RecyclerAdapter(null)
        streams_list.adapter = adapter

        updateList()
    }

    private fun updateList() {
        var game = listOf<String>("Super Mario World").random()
        Log.i("test", "game=$game")

        viewModel.getStreamList(game, callback = {
            message.text = game

            adapter.photos = it
            adapter.notifyDataSetChanged()
        })

        Timer().schedule(timerTask { updateList() }, 5_000)
    }

}