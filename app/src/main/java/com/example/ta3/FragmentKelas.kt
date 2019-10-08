package com.example.ta3


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentKelas :Fragment(){
    companion object {
        fun newInstance(): FragmentKelas {
            var fragmentKelas = FragmentKelas()
            var args = Bundle()
            fragmentKelas.arguments = args
            return fragmentKelas
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_kelas, container, false)
        return rootView
    }
}