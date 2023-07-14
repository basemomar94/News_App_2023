package com.example.newsapp.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsSourceBinding
import com.example.newsapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsSourceFragment : BaseFragment<FragmentNewsSourceBinding>(), View.OnClickListener {
    private val TAG = this::class.java.simpleName

    @Inject
    lateinit var pref: SharedPreferences
    private var selectedSource: String? = null

    override

    fun inflateViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentNewsSourceBinding {
        return FragmentNewsSourceBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinnerAdapter()
        setupSpinnerClick()
        binding.btnSave.setOnClickListener(this)
    }

    private fun setupSpinnerClick() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedSource = parent?.getItemAtPosition(position).toString()
                Log.d(TAG, "selected item is $selectedSource")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    private fun setupSpinnerAdapter() {
        val adapter =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                Constants.newsSources
            )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnSave.id -> {
                Log.d(TAG,"clicked on save")
                pref.edit().putString(Constants.NEWS_SOURCE, selectedSource).apply()
                findNavController().navigate(R.id.action_newsSourceFragment_to_homeFragment)
            }
        }
    }
}