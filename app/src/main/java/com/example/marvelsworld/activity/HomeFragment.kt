package com.example.marvelsworld.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marvelsworld.R
import com.example.marvelsworld.Extensions.bind
import com.example.marvelsworld.Extensions.subscribe
import com.example.marvelsworld.characterList.CharacterListAdapter
import com.example.marvelsworld.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val adapter: CharacterListAdapter = CharacterListAdapter()
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.adapter = adapter

        bindViews()
        viewModel.init()
        subscribeEvents()
    }

    private fun bindViews() {
        bind(viewModel.progressBarVisibility, user_list_progress_bar::setVisibility)
        bind(viewModel.recyclerVisibility, recyclerView::setVisibility)
        bind(viewModel.recyclerVisibility, dividerRecycler::setVisibility)
        bind(viewModel.recyclerVisibility, mainTitle::setVisibility)
    }

    private fun subscribeEvents() {
        viewModel.error.subscribe(this) {
            // Make anything
        }

        viewModel.success.subscribe(this) {
            adapter.users = this.data
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
