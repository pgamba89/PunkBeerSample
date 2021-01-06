package com.example.punkBeerSample.ui.beersList

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.punkBeerSample.R
import com.example.punkBeerSample.databinding.FragmentRecyclerviewListBinding
import com.example.punkBeerSample.utils.Constants.Companion.DEFAULT_QUERY
import com.example.punkBeerSample.utils.Constants.Companion.LAST_SEARCH_QUERY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeersListFragment : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel: BeersModelView by viewModels()

    private lateinit var binding: FragmentRecyclerviewListBinding
    private lateinit var adapter: RecyclerViewAdapter

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val item = menu.findItem(R.id.action_search)
        searchView = item.actionView as SearchView
        searchView!!.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_recyclerview_list, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter = RecyclerViewAdapter(ListItemListener {
            view?.findNavController()
                ?.navigate(
                    BeersListFragmentDirections.actionBeersListFragmentToBeerDetailFragment(it)
                )
        })

        binding.recyclerviewlist.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        initAdapter()
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        viewModel.getBeersList(query)
        subscribeObservers()

        return binding.root
    }

    private fun initAdapter() {
        binding.recyclerviewlist.adapter = adapter
        binding.recyclerviewlist.adapter = adapter.withLoadStateHeader(
            header = RecyclerLoadStateAdapter()
        )
    }

    private fun subscribeObservers() {
        viewModel.currentList?.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            viewModel.getBeersList(query)
            binding.recyclerviewlist.scrollToPosition(0)
            subscribeObservers()
            searchView?.clearFocus()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            viewModel.getBeersList(newText)
            binding.recyclerviewlist.scrollToPosition(0)
            subscribeObservers()
        }
        return true
    }
}