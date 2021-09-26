package com.example.simpleexample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleexample.R
import com.example.simpleexample.adapter.Adapter
import com.example.simpleexample.model.RecyclerListModel
import com.example.simpleexample.viewmodel.MainActivityViewModel
import com.example.simpleexample.databinding.FragmentRecyclerListBinding


class RecyclerListFragment : Fragment() {
    private lateinit var binding: FragmentRecyclerListBinding
    private lateinit var rvAdapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recycler_list, container, false)

        val view: View = binding.getRoot()
        initViewModel()
        itemUpdate()
        return view;
    }

    private fun initViewModel() {
        val rvList = binding.rv;
        rvList.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        rvList.addItemDecoration(decoration)
        rvAdapter = Adapter(requireContext())
        rvList.adapter = rvAdapter
    }

    private fun itemUpdate() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<RecyclerListModel> {
            if (it != null) {
                rvAdapter.setUpdatedData(it.items)
            } else {
                Toast.makeText(context, "Hata alındı", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.makeApiCall()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RecyclerListFragment()
    }

}