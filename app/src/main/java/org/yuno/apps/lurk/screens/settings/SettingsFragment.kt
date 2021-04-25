package org.yuno.apps.lurk.screens.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.yuno.apps.lurk.R
import org.yuno.apps.lurk.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val binding: SettingsFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.settings_fragment, container, false)


        return binding.root
    }
}