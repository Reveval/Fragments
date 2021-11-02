package ru.startandroid.develop.p1151multiplescreen.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import ru.startandroid.develop.p1151multiplescreen.R

//Создаем фрагмент, который будет отображать список заголовков

class TitlesFragment : ListFragment() {
    /*
        onItemClickListener – интерфейс, который будет наследовать Activity. Интерфейс имеет метод
            itemClick, который фрагмент будет вызывать при выборе элемента списка.
     */
    interface OnItemClickListener {
        fun itemClick(position: Int)
    }

    lateinit var listener: OnItemClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = activity?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.headers)) }
        listAdapter = adapter
    }

    /*
        В onAttach записываем Activity (к которому присоединен фрагмент) в listener. Разумеется,
            это Activity должно реализовывать интерфейс onItemClickListener.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = activity as OnItemClickListener
    }

    //В onListItemClick, мы через listener посылаем в Activity данные о выбранном элементе
    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        listener.itemClick(position)
    }
}