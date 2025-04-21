package com.kindergeschichten.romanisch.ui

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kindergeschichten.romanisch.R
import com.kindergeschichten.romanisch.databinding.ActivitySplashScreenBinding
import com.kindergeschichten.romanisch.tools.PreferenceManager
import com.kindergeschichten.romanisch.tools.handleDarkTheme
import com.kindergeschichten.romanisch.tools.openPdfDocument
import com.kindergeschichten.romanisch.ui.base.VBActivity
import com.kindergeschichten.romanisch.ui.bottomsheet.PdfViewer
import com.kindergeschichten.romanisch.ui.languageselection.LanguageSelectionActivity

class SplashScreen : VBActivity<ActivitySplashScreenBinding>() {



    override fun getViewBinding(): ActivitySplashScreenBinding {
       return ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleDarkTheme()
        viewModel.loadStories()
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val preferenceManager = PreferenceManager.getInstance(this)
        if(preferenceManager.firstOpen) {
            binding.groupContinue.visibility = View.VISIBLE
            setListeners()
            preferenceManager.firstOpen = false
        }
        else {
            MainActivity.start(this)
        }
    }

    private fun setListeners() {
        binding.tvPrivacy.paintFlags = binding.tvPrivacy.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.tvTerms.paintFlags = binding.tvTerms.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        binding.tvPrivacy.setOnClickListener {
            openPdfDocument(PdfViewer.DocumentType.PRIVACY)
        }

        binding.tvTerms.setOnClickListener {
            openPdfDocument(PdfViewer.DocumentType.TERMS)
        }

        binding.btnContinue.setOnClickListener {
            LanguageSelectionActivity.start(this)
        }
    }


}