package com.example.ta3


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentKelasGuru :Fragment(){
    companion object {
        fun newInstance(): FragmentKelasGuru {
            var fragmentKelasGuru = FragmentKelasGuru()
            var args = Bundle()
            fragmentKelasGuru.arguments = args
            return fragmentKelasGuru
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_kelas_guru, container, false)
        return rootView
    }
}