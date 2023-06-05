package com.senpro.ulamsae.ui.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.senpro.ulamsae.R
import com.senpro.ulamsae.databinding.FragmentHistoryBinding
import com.senpro.ulamsae.ui.ViewModelFactory
import com.senpro.ulamsae.ui.profile.ProfileViewModel

class HistoryFragment : Fragment() {

    private lateinit var viewModel: HistoryViewModel
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val adapter: MarketAdapter by lazy { MarketAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupAction()
        onBackPressed()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(requireActivity()))[HistoryViewModel::class.java]
    }

    private fun setupAction() {
        binding.apply {
            rvMarket.layoutManager = LinearLayoutManager(requireActivity())
            rvMarket.setHasFixedSize(true)
            rvMarket.adapter = adapter
        }

        viewModel.listMarket.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }
}