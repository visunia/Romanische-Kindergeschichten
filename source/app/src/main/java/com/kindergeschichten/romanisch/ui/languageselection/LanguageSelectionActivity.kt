package com.kindergeschichten.romanisch.ui.languageselection

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.kindergeschichten.romanisch.databinding.ActivityLanguageSelectionBinding
import com.kindergeschichten.romanisch.ui.MainActivity
import com.kindergeschichten.romanisch.ui.base.VBActivity


class LanguageSelectionActivity : VBActivity<ActivityLanguageSelectionBinding>() {

    override fun getViewBinding(): ActivityLanguageSelectionBinding {
        return ActivityLanguageSelectionBinding.inflate(layoutInflater)
    }

    var finishWhenSelected = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finishWhenSelected = intent.getBooleanExtra(FINISH_WHEN_SELECTED, false)

    }

    override fun onResume() {
        super.onResume()
        setupViewPager()
    }


    private fun setupViewPager() {
        binding.viewPager.adapter = MyViewPagerAdapter(this, languages)
        binding.dotsIndicator.attachTo(binding.viewPager)
        binding.btnStart.setOnClickListener {
            val currentLanguage = languages[binding.viewPager.currentItem]
            preferenceManager.selectedLanguage = currentLanguage.language
            if (finishWhenSelected)
                finish()
            else
                MainActivity.start(this)
        }

        binding.imgClose.setOnClickListener{
            if(finishWhenSelected)
                finish()
            else
                MainActivity.start(this)
        }
    }


    companion object {
        val FINISH_WHEN_SELECTED = "FINISH_WHEN_SELECTED"
        fun start(activity: Activity, finishWhenSelected: Boolean = false) {
            activity.startActivity(
                Intent(activity, LanguageSelectionActivity::class.java).putExtra(
                    FINISH_WHEN_SELECTED, finishWhenSelected
                )
            )
            // activity.finish()
        }
    }


}