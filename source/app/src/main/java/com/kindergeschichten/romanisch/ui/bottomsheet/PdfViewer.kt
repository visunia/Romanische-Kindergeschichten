package com.kindergeschichten.romanisch.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import com.kindergeschichten.romanisch.R
import com.kindergeschichten.romanisch.databinding.DialogPdfViewBinding


class PdfViewer(val documentType: DocumentType):BaseBottomSheet(true,false) {

    constructor() : this(DocumentType.PRIVACY)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contextThemeWrapper = ContextThemeWrapper(activity, R.style.AppTheme) // your app theme here
        return inflater.cloneInContext(contextThemeWrapper).inflate(R.layout.dialog_pdf_view, container, false)
        //return inflater.inflate(R.layout.dialog_in_app, container, false)
    }

    lateinit var binding: DialogPdfViewBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogPdfViewBinding.bind(view)


        val assetType = if(documentType==DocumentType.PRIVACY) "privacy_policy.pdf"
        else "terms_of_use.pdf"
        assetType.apply {
            binding.pdfView.fromAsset(this).load()
        }
        binding.imgBack.setOnClickListener{
            dismiss()
        }

    }

    enum class DocumentType{
        TERMS,PRIVACY
    }
}