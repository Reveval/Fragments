package ru.startandroid.develop.p1121dynamicactionbar.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import ru.startandroid.develop.p1121dynamicactionbar.R

class Fragment1 : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment1, null)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment1_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}