package com.kindergeschichten.romanisch.ui.navigation


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kindergeschichten.romanisch.R
import com.kindergeschichten.romanisch.databinding.FragmentNavigationDrawerBinding
import com.kindergeschichten.romanisch.ui.menu.MenuAction
import com.kindergeschichten.romanisch.ui.menu.MenuAdapter
import com.kindergeschichten.romanisch.ui.menu.MenuHelper


class NavigationDrawer : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false)
    }

    lateinit var binding: FragmentNavigationDrawerBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNavigationDrawerBinding.bind(view)
        setUpAdapter()
        setupListeners()
    }


    private fun setupListeners() {
        binding.imgClose.setOnClickListener {
            menuActionListener?.onMenuAction(MenuAction.CLOSE_MENU)
        }
    }

    private fun setUpAdapter() {
        val adapter = MenuAdapter(requireContext(), MenuHelper.menuItems) {
            menuActionListener?.onMenuAction(it)
        }
        binding.rc.adapter = adapter
    }

    interface MenuActionListener {
        fun onMenuAction(menuAction: MenuAction)
    }

    var menuActionListener: MenuActionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.menuActionListener = context as? MenuActionListener
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.menuActionListener = activity as? MenuActionListener
    }

    override fun onDetach() {
        super.onDetach()
        menuActionListener = null
    }

}