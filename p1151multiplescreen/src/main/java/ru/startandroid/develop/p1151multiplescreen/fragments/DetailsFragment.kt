package ru.startandroid.develop.p1151multiplescreen.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.startandroid.develop.p1151multiplescreen.R

//Создаем второй фрагмент, для отображения содержимого.

class DetailsFragment : Fragment() {
    //достаем из аргументов позицию.
    val position
        get() = arguments?.getInt("position", 0) ?: 0

    /*
        Метод newInstance создает экземпляр фрагмента и записывает в его атрибуты число, которое
            пришло на вход методу. Это число будет содержать позицию выбранного элемента из списка
            заголовков.
     */
    companion object {
        fun newInstance(pos: Int) : DetailsFragment {
            val details = DetailsFragment()
            return details.apply {
                val args = Bundle()
                args.putInt("position", pos)
                arguments = args
            }
        }
    }

    /*
        onCreateView создает View, находим в нем TextView, и помещает в этот TextView содержимое,
            соответствующее позиции.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.details, container, false)
        return v.also {
            it.findViewById<TextView>(R.id.tvText).text =
                resources.getStringArray(R.array.content) [position]
        }
    }
}