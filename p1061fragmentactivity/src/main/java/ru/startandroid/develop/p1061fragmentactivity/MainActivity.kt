package ru.startandroid.develop.p1061fragmentactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.startandroid.develop.p1061fragmentactivity.fragments.Fragment2

class MainActivity : AppCompatActivity(), Fragment2.onSomeEventListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment2 = Fragment2()
        supportFragmentManager.beginTransaction().run {
            add(R.id.fragment2, fragment2)
            commit()
        }
    }

    /*
         Чтобы получить доступ к фрагменту из Activity, у FragmentManager вызываем метод
            findFragmentById, который на вход принимает id компонента fragment (если фрагмент
            статический) или id контейнера (если динамический).
     */
    fun onClick(view: View) {
        val frag1 = supportFragmentManager.findFragmentById(R.id.fragment1)
        frag1?.view?.findViewById<TextView>(R.id.textView)
            ?.text = "Access Fragment 1 from Activity"

        val frag2 = supportFragmentManager.findFragmentById(R.id.fragment2)
        frag2?.view?.findViewById<TextView>(R.id.textView)
            ?.text = "Access Fragment 2 from Activity"
    }

    override fun someEvent(s: String) {
        supportFragmentManager.findFragmentById(R.id.fragment1)
            ?.view?.findViewById<TextView>(R.id.textView)?.text = "Text from Fragment2: $s"
    }
}