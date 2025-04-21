package com.kindergeschichten.romanisch.ui.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kindergeschichten.romanisch.BuildConfig
import com.kindergeschichten.romanisch.R
import com.kindergeschichten.romanisch.databinding.FragmentsNavImpressumBinding
import com.kindergeschichten.romanisch.tools.openPdfDocument
import com.kindergeschichten.romanisch.ui.bottomsheet.PdfViewer

class ImpressumFragment  //    private OnFragmentInteractionListener mListener;
    : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragments_nav_impressum, container, false)

        return view
    }

    lateinit var binding:FragmentsNavImpressumBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentsNavImpressumBinding.bind(view)

        underLineText()
        setUpData()

    }

    private fun setUpData() {
        binding.tvAppVersion!!.text =
            resources.getString(R.string.app_version) + " " + BuildConfig.VERSION_NAME

        binding.tvEmail.setOnClickListener{
            email()
        }

        binding.tvWebsite.setOnClickListener{
            openWebsite()
        }

        binding.tvPrivacyPolicy.setOnClickListener {
            activity?.openPdfDocument(PdfViewer.DocumentType.PRIVACY)
        }

        binding.tvTermsOfUse.setOnClickListener {
           activity?.openPdfDocument(PdfViewer.DocumentType.TERMS)
        }

    }


    private fun underLineText() {
        binding.tvWebsite.setPaintFlags(binding.tvWebsite.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        binding.tvTermsOfUse.setPaintFlags(binding.tvTermsOfUse.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        binding.tvPrivacyPolicy.setPaintFlags(binding.tvPrivacyPolicy.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
    }

    fun email() {
        val TO = arrayOf("info@visunia.com")
        val uri = Uri.parse("mailto:info@romontsch.ch")
            .buildUpon()
            .build()
        val emailIntent = Intent(Intent.ACTION_SENDTO, uri)
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
        startActivity(Intent.createChooser(emailIntent, "Send mail..."))
    }

    fun openWebsite() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.romontsch.ch"))
        startActivity(browserIntent)
    }


    var activity: AppCompatActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as AppCompatActivity
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.activity = activity as AppCompatActivity
    }

    override fun onDetach() {
        super.onDetach()
        this.activity = null
    }


}