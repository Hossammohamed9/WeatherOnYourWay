package com.example.weatherontheway.ui.settings

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherontheway.R
import com.example.weatherontheway.database.sharedPreference.SettingsModel
import com.example.weatherontheway.databinding.FragmentSettingsBinding
import com.example.weatherontheway.ui.mainactivity.MainActivity
import com.example.weatherontheway.ui.map.MapActivity

class SettingsFragment : Fragment() {


    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        settingsViewModel =  ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(
            requireActivity().application
        ))[SettingsViewModel::class.java]


        binding.settingsSaveBtnId.setOnClickListener{
            savedata()
        }


        binding.autoCompleteId.setOnClickListener{
            val intent = Intent(activity, MapActivity::class.java)
            intent.putExtra("type","setting")
            startActivity(intent)
        }
        getSetting()

        return binding.root
    }

    fun getSetting() {
        settingsViewModel.getSetting().observe(this, {
            val units: String = it.units
            val lang: String = it.lang
            val location: String = it.location
            Log.d("TAG",it.location)
            if (units == "standard") {
                binding.radioGroup2.check(R.id.standardUnitId)
            } else if (units == "imperial") {
                binding.radioGroup2.check(R.id.imperialUnitId)
            } else {
                binding.radioGroup2.check(R.id.metricUnitId)
            }

            if (lang == "en") {
                binding.radioGroup.check(R.id.radioButtonEnglishId)
            } else {
                binding.radioGroup.check(R.id.radioButtonArabicId)
            }

            if (location == "add") {
                binding.radioGroup3.check(R.id.autoCompleteId)
            } else {
                binding.radioGroup3.check(R.id.chooseFromMapId)
            }
        })
    }
    var langS:String="en"
    private fun savedata() {
        val units: String = when (binding.radioGroup2.checkedRadioButtonId) {
            R.id.standardUnitId -> {
                "standard"
            }
            R.id.imperialUnitId -> {
                "imperial"
            }
            else -> {
                "metric"
            }
        }


        val lang: String = if (binding.radioGroup.checkedRadioButtonId == R.id.radioButtonEnglishId) {
            "en"
        } else {
            "ar"
        }
        langS=lang



        val location: String = if (binding.radioGroup3.checkedRadioButtonId == R.id.chooseFromMapId) {
            "gps"
        } else {
            "add"
        }

        settingsViewModel.setSetting(SettingsModel(units, lang, location))
        settingsViewModel.setLocale(requireActivity(),langS)
        startActivity(Intent(requireContext(), MainActivity::class.java))
        requireActivity().finish()
    }

}