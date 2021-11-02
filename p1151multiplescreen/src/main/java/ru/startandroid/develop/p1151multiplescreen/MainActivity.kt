package ru.startandroid.develop.p1151multiplescreen

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import ru.startandroid.develop.p1151multiplescreen.fragments.DetailsFragment
import ru.startandroid.develop.p1151multiplescreen.fragments.TitlesFragment

/*
    Activity наследует интерфейс onItemClickListener, чтобы получать оповещения о выбранных
        элементах от фрагмента со списком заголовков. Поле position будет хранить последний
        выбранный элемент. Это поле сохраняем (onSaveInstanceState) и читаем (savedInstanceState
        в onCreate) при пересоздании Activity.
 */

class MainActivity : FragmentActivity(), TitlesFragment.OnItemClickListener {
    var position = 0
    /*
        Добавляется поле withDetails, которое будет сообщать нам: показывает Activity заголовки с
            содержимым или без. В нашем случае это будет совпадать соответственно с горизонтальной
            и вертикальной ориентацией.
     */
    var withDetails = true

    //В onCreate вызываем метод, который покажет последнюю выбранную запись.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) position = savedInstanceState.getInt("position")
        /*
            В onCreate мы задаем значение withDetails с помощью проверки наличия контейнера для
                фрагмента с содержимым. Если у нас должно отображаться содержимое, то вызываем
                метод showDetails и передаем ему позицию. Если содержимое не должно отображаться,
                то ничего не делаем, показываем только заголовки.
         */
        withDetails = (findViewById<FrameLayout>(R.id.cont) != null)
        if (withDetails) showDetails(position)
    }

    /*
        Метод showDetails получает на вход позицию, ищет DetailsFragment. Если не находит или
            находит но, отображающий данные по другой позиции, то создает фрагмент заново,
            передает ему нужную позицию и размещает в контейнер.
     */
    private fun showDetails(pos: Int) {
        /*
            В методе showDetails мы смотрим withDetails. Если содержимое должно быть показано здесь
                же, то работает старый алгоритм, мы создаем фрагмент. Если же содержимое должно
                быть показано в отдельном Activity, то вызываем DetailsActivity и передаем ему
                позицию.
         */
        if (withDetails) {
            var details = supportFragmentManager.findFragmentById(R.id.cont) as DetailsFragment?
            if (details == null || details.position != pos) {
                details = DetailsFragment.newInstance(pos)
                supportFragmentManager.beginTransaction().replace(R.id.cont, details).commit()
            }
        } else {
            startActivity(Intent(this, DetailsActivity::class.java)
                .putExtra("position", position))
        }
    }

    /*
        itemClick – метод, вызываемый из фрагмента со списком заголовков. В нем мы получаем позицию
            выбранного элемента в списке. Пишем ее в поле position и вызываем showDetails, который
            отобразит нужные данные на экране.
     */
    override fun itemClick(position: Int) {
        this.position = position
        showDetails(position)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("position", position)
    }
}