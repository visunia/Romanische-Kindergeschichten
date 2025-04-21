package com.kindergeschichten.romanisch.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kindergeschichten.romanisch.R
import com.kindergeschichten.romanisch.databinding.FragmentShareBinding
import com.kindergeschichten.romanisch.tools.shareByEmail

class FragmentShare: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_share, container, false)

        return view
    }

    lateinit var binding: FragmentShareBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShareBinding.bind(view)
        binding.btnShare.setOnClickListener{
            requireActivity()?.shareByEmail()
        }

        requireActivity()?.shareByEmail()
    }
}