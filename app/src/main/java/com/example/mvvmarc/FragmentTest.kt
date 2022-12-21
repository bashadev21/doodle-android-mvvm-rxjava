package com.example.mvvmarc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvmarc.databinding.FragmentTestBinding



class FragmentTest : Fragment(R.layout.fragment_test) {
private lateinit var binding:FragmentTestBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentTestBinding.bind(view)
        binding.fragText.text="helooo"
    }


}