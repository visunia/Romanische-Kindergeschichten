package com.kindergeschichten.romanisch.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.kindergeschichten.romanisch.R
import com.kindergeschichten.romanisch.databinding.FragmentSettingsBinding
import com.kindergeschichten.romanisch.tools.PreferenceManager
import com.kindergeschichten.romanisch.tools.handleDarkTheme
import com.kindergeschichten.romanisch.ui.custom.Theme

class FragmentSettings: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)

        return view
    }

    lateinit var binding:FragmentSettingsBinding
    lateinit var preference_Manager: PreferenceManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
        preference_Manager = PreferenceManager.getInstance(requireContext())

        if (preference_Manager.themeMode === Theme.Dark) binding.radioNight.setChecked(true)
        else if (preference_Manager.themeMode === Theme.Light) binding.radioDay.setChecked(true)
        else binding.radioSystem.setChecked(true)

        binding.radioGroupDark.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, checkedId ->
            if (checkedId == R.id.radioNight) preference_Manager.themeMode = (Theme.Dark)
            else if (checkedId == R.id.radioDay) preference_Manager.themeMode=(Theme.Light)
            else preference_Manager.themeMode = (Theme.FollowSystem)
        })

        binding.applyAndSave.setOnClickListener{
            requireActivity()?.handleDarkTheme()
        }

    }
}