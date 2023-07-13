package com.example.newsapp.ui

import android.R
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.newsapp.databinding.FragmentNewsSourceBinding
import com.example.newsapp.utils.Constants
import javax.inject.Inject

class NewsSourceFragment : BaseFragment<FragmentNewsSourceBinding>() {
    private val TAG = this::class.java.simpleName

    @Inject
    lateinit var pref: SharedPreferences
    override fun inflateViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentNewsSourceBinding {
        return FragmentNewsSourceBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinnerAdapter()
        setupSpinnerClick()
    }

    private fun setupSpinnerClick() {
        binding.spinner.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Log.d(TAG, "selected item is $selectedItem")
                pref.edit().putString(Constants.NEWS_SOURCE,selectedItem).apply()
            }
    }

    private fun setupSpinnerAdapter() {
        val adapter =
            ArrayAdapter(requireContext(), R.layout.simple_spinner_item, Constants.newsSources)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
    }
}