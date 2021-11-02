package ru.startandroid.develop.p1061fragmentactivity.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.startandroid.develop.p1061fragmentactivity.R

const val LOG_TAG = "myLogs"

class Fragment1 : Fragment() {
    /*
        У фрагмента нет привычного для нас метода findViewById для поиска компонентов с экрана.
            Поэтому вызываем этот метод для View, которое будет содержимым фрагмента. В методе
            onCreateView мы создаем View и сразу же находим в нем кнопку и ставим ей обработчик.
            Затем отдаем View системе.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment1, null)
        val button = v.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            Log.d(LOG_TAG, "Button Click in Fragment1")
            activity?.findViewById<Button>(R.id.btnFind)
                ?.text = "Access from Fragment1"
        }
        return v
    }
}