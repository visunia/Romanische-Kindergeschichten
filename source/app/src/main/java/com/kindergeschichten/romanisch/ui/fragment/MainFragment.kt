package com.kindergeschichten.romanisch.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kindergeschichten.romanisch.R
import com.kindergeschichten.romanisch.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    lateinit var binding:FragmentMainBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.imgRoman.setOnClickListener{
            listener?.bottomMenuClicked(BottomMenu.Roman)
        }

        binding.imgDeutcsh.setOnClickListener{
            listener?.bottomMenuClicked(BottomMenu.Deutsch)
        }

        binding.imgSelectDialect.setOnClickListener{
            listener?.bottomMenuClicked(BottomMenu.SelectDialect)
        }

        binding.imgParents.setOnClickListener{
            listener?.bottomMenuClicked(BottomMenu.Parents)
        }
    }

    enum class BottomMenu{
        Roman,Deutsch,SelectDialect,Parents
    }
    interface BottomMenuListener{
        fun bottomMenuClicked(menuItem:BottomMenu)
    }

    var listener:BottomMenuListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.listener = context as? BottomMenuListener
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.listener = activity as? BottomMenuListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}