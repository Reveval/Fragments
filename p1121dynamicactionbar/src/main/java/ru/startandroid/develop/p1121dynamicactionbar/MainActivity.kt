package ru.startandroid.develop.p1121dynamicactionbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import ru.startandroid.develop.p1121dynamicactionbar.fragments.Fragment1
import ru.startandroid.develop.p1121dynamicactionbar.fragments.Fragment2

/*
    Создадим следующее приложение. Фрагмент может реализовать в себе метод onCreateOptionsMenu и,
        тем самым, создать свои элементы для ActionBar. Как только фрагмент будет добавлен на
        экран, эти элементы добавятся в ActionBar. А когда фрагмент с экрана уберут, элементы
        исчезнут.
 */

const val MENU_ID = 1

class MainActivity : AppCompatActivity() {
    lateinit var chbAddDel: CheckBox
    lateinit var chbVisible: CheckBox

    lateinit var frag1: Fragment
    lateinit var frag2: Fragment
    lateinit var frag: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chbAddDel = findViewById(R.id.chbAddDel)
        chbVisible = findViewById(R.id.chbVisible)

        frag = Fragment1()
        frag1 = frag
        frag2 = Fragment2()
    }

    /*
        В onCreateOptionsMenu настраиваем видимость группы groupVsbl в зависимости от значения
            чекбокса chbVisible. В зависимости от значения чекбокса chbAddDel создаем или удаляем
            элемент.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        menu.apply {
            if (this != null) {
                setGroupVisible(R.id.groupVsbl, chbVisible.isChecked)
                if (chbAddDel.isChecked) {
                    add(0, MENU_ID, 0, R.string.menu_item1)
                        .setIcon(android.R.drawable.ic_delete)
                        .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS or
                                MenuItem.SHOW_AS_ACTION_WITH_TEXT)
                } else {
                    removeItem(MENU_ID)
                }
            }
        }
        return true
    }

    /*
        В onСlick для чекбоксов вызываем метод invalidateOptionsMenu - перерисовка меню/ActionBar.
            А по нажатию на кнопку поочередно выводим на экран Fragment1 или Fragment2.
     */
    fun onClick(view: View) {
        when(view.id) {
            R.id.chbAddDel, R.id.chbVisible -> invalidateOptionsMenu()
            R.id.btnFrag -> {
                frag = if(frag == frag1) frag2 else frag1
                supportFragmentManager.beginTransaction().replace(R.id.cont, frag).commit()
            }
        }
    }
}