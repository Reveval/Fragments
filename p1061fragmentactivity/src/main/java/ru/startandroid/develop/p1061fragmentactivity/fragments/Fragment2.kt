package ru.startandroid.develop.p1061fragmentactivity.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.startandroid.develop.p1061fragmentactivity.R
import java.lang.ClassCastException

class Fragment2 : Fragment() {
    interface onSomeEventListener {
        fun someEvent(s: String)
    }

    lateinit var someEventListener: onSomeEventListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            someEventListener = activity as onSomeEventListener
        } catch (ex: ClassCastException) {
            throw ClassCastException("$activity must implement onSomeEventListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment2, null)
        return v.also {
            it.findViewById<Button>(R.id.button).setOnClickListener {
                Log.d(LOG_TAG, "Button Click in Fragment2")
                someEventListener.someEvent("Test text to Fragment1")
            }
        }
    }
}