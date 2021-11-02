package ru.startandroid.develop.p1131actionmode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ListView

/*
    Рассмотренный нами в прошлых уроках ActionBar – это альтернатива обычному меню прошлых версий.
        В третьей версии Андроида появилась также альтернатива и контекстному меню - ActionMode.
        Посмотрим, как его можно использовать.
 */

const val LOG_TAG = "myLogs"

class MainActivity : AppCompatActivity() {
    private lateinit var actionMode: ActionMode
    lateinit var lvActionMode: ListView
    val data = arrayOf("one", "two", "three", "four", "five")

    /*
        В onCreate мы создаем адаптер и присваиваем его списку. Далее для списка мы включаем режим
            выбора (Урок 43) CHOICE_MODE_MULTIPLE_MODAL (появившийся в API Level 11) и
            устанавливаем объект обработчик, реализующий AbsListView.MultiChoiceModeListener.
            Методы здесь все те же, что и ранее нами рассмотренные в ActionMode.Callback, плюс
            добавляется один – onItemCheckedStateChanged, в котором мы получаем инфу о выделенных
            пунктах списка. Т.е. этот обработчик и выделение пунктов списка отслеживает и
            ActionMode контролирует.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_activated_1,
            data)
        lvActionMode = findViewById(R.id.lvActionMode)
        lvActionMode.apply {
            setAdapter(adapter)
            choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL
            setMultiChoiceModeListener(object : AbsListView.MultiChoiceModeListener {
                //В onCreateActionMode мы указываем, из какого файла брать пункты меню
                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    mode?.menuInflater?.inflate(R.menu.context, menu)
                    return true
                }

                override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    return false
                }

                /*
                    в onActionItemClicked закрываем ActionMode независимо от того, какой пункт меню
                        был выбран
                 */
                override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                    mode?.finish()
                    return false
                }


                override fun onDestroyActionMode(mode: ActionMode?) {}

                //в onItemCheckedStateChanged просто выводим в лог инфу о выбираемых пунктах списка
                override fun onItemCheckedStateChanged(
                    mode: ActionMode?,
                    position: Int,
                    id: Long,
                    checked: Boolean
                ) {
                    Log.d(LOG_TAG, "position = $position, checked = $checked")
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.context, menu)
        return true
    }

    /*
        В методе onClick мы проверяем, если ActionMode еще не был вызван, то вызываем. Иначе –
            убираем его с помощью его же метода finish.
     */

    /* ПРИМЕР ДЛЯ СЛУЧАЯ ВЫЗОВА ACTION MODE ПРИ НАЖАТИИ НА КНОПКУ
    fun onClick(view: View) {
        if (actionMode == null) {
            actionMode = startActionMode(callback)
        } else {
            actionMode?.finish()
        }
    }

    private val callback = object : ActionMode.Callback {
        /*
            onCreateActionMode – вызывается при создании ActionMode. Возвращаем true, если
                ActionMode можно создавать. Здесь мы наполняем ActionMode пунктами меню (через
                объект Menu).
         */
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.context, menu)
            return true
        }

        /*
            onPrepareActionMode – вызывается при обновлении ActionMode. Например, в случае вызова
                метода invalidate. Возвращаем true, если ActionMode можно обновить.
         */
        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        /*
            onActionItemClicked – обработка нажатия на какой-либо пункт ActionMode. Будем выводить
                в лог текст нажатого пункта.
         */
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            Log.d(LOG_TAG, "item ${item?.title}")
            return false
        }

        /*
            onDestroyActionMode – вызывается при закрытии ActionMode. Пишем лог и обнуляем
                переменную actionMode, чтобы в onClick (см.выше) у нас работала проверка
                (actionMode == null).
         */
        override fun onDestroyActionMode(mode: ActionMode?) {
            Log.d(LOG_TAG, "destroy")
            actionMode = null
        }
    }
     */
}