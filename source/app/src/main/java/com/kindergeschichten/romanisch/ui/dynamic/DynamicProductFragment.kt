package com.kindergeschichten.romanisch.ui.dynamic


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aluisderagisch.vocabulary.other_products.OtherProduct
import com.aluisderagisch.vocabulary.other_products.OtherProductAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kindergeschichten.romanisch.R
import com.kindergeschichten.romanisch.databinding.FragmentDynamicProductBinding
import com.kindergeschichten.romanisch.tools.openInPlayStore
import com.kindergeschichten.romanisch.tools.readJsonAsset

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val TARGET_LIST = "TARGET_LIST"
private const val ENABLE_HEADER = "ENABLE_HEADER"


class DynamicProductFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var targetList: String? = null
    private var enableHeader: Boolean? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            targetList = it.getString(TARGET_LIST)
            enableHeader = it.getBoolean(ENABLE_HEADER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dynamic_product, container, false)
    }

    lateinit var binding: FragmentDynamicProductBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDynamicProductBinding.bind(view)
        setUpRecyclerView()
        loadTargetList()
    }

    private fun loadTargetList() {
        try {
            val gson = Gson()
            val itemType = object : TypeToken<List<OtherProduct>>() {}.type
            val itemList = gson.fromJson<List<OtherProduct>>(requireActivity()?.readJsonAsset("other_products/${targetList!!}"), itemType)
            val filter = itemList.filter { it.display=="yes" }
            val sorted = itemList.sortedBy { it.sortorder }
            adapter?.submitList(sorted)
        }catch (ex:Exception){
            Log.e("ex",ex.localizedMessage)
        }

    }


    var adapter: OtherProductAdapter? = null
    private fun setUpRecyclerView() {
        adapter = OtherProductAdapter(requireActivity())
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        binding.rc?.layoutManager = layoutManager
        binding.rc.setHasFixedSize(true)
        binding.rc.requestDisallowInterceptTouchEvent(true)
        binding.rc?.adapter = adapter

        adapter?.setOnItemClickListener(object : OtherProductAdapter.OnItemClickListener {
            override fun OnItemClicked(product: OtherProduct) {
                context?.openInPlayStore(product.appid)
            }
        })

        if(enableHeader==true)
            binding.tvHeader.visibility= View.VISIBLE

    }

    companion object {

        @JvmStatic
        fun newInstance(targetList: String,enableHeader:Boolean) =
            DynamicProductFragment().apply {
                arguments = Bundle().apply {
                    putString(TARGET_LIST, targetList)
                    putBoolean(ENABLE_HEADER,enableHeader)
                }
            }
    }
}