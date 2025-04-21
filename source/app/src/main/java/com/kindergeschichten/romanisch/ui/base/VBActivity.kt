package com.kindergeschichten.romanisch.ui.base

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.kindergeschichten.romanisch.tools.PreferenceManager
import com.kindergeschichten.romanisch.viewmodel.ViewModel
import com.us.babyeducation.assets.AssetsManager

abstract class VBActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    abstract fun getViewBinding(): VB
    lateinit var viewModel: ViewModel
    lateinit var preferenceManager: PreferenceManager
    lateinit var assetManager: AssetsManager
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        assetManager = AssetsManager.getInstance(this)
        _binding = getViewBinding()
        setContentView(binding.root)
        preferenceManager = PreferenceManager.getInstance(this)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun disableBottomMenu() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let { controller ->
                controller.hide(WindowInsets.Type.systemBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            hideSystemUI()
        }
    }

    private fun hideSystemUI() {
        // For devices below Android 11
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }


    fun enableBottomMenu() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.show(WindowInsets.Type.systemBars())
        }
    }

}