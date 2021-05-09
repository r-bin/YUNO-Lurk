package org.yuno.apps.lurk.screens.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.yuno.apps.lurk.R
import java.util.*
import kotlin.concurrent.timerTask

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var adapter: RecyclerAdapter

    lateinit var message: TextView

    lateinit var streams_list: RecyclerView

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.editable_fragment, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            val action = MainFragmentDirections.actionMainFragmentToSettingsFragment()
            NavHostFragment.findNavController(this).navigate(action)

            true
        } else -> {
            super.onOptionsItemSelected(item)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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