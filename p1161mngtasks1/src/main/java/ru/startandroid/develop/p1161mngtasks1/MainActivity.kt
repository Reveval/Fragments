package ru.startandroid.develop.p1161mngtasks1

import android.app.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

const val LOG_TAG = "myLogs"

abstract class MainActivity : AppCompatActivity() {
    lateinit var list: List<ActivityManager.RunningTaskInfo>
    lateinit var am: ActivityManager

    /*
        В onCreate мы в заголовок помещаем название приложения и имя класса Activity, чтобы на
            экране видеть какое Activity какого приложения сейчас активно.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(LOG_TAG, "onCreate")

        title = resources.getString(R.string.app_name) + " : " + localClassName
        am = getSystemService(ACTIVITY_SERVICE) as ActivityManager
    }

    /*
        Этот класс будет предком для 4-х следующих. Он содержит абстрактный метод onClick, который
            нам надо будет реализовать в наследниках. Этот метод будет вызываться по нажатию
            на кнопку Start.
     */
    abstract fun onClick(view: View)

    /*
        В onInfoClick используем ActivityManager. Метод getRunningTasks вернет нам список текущих
            работающих тасков. Последние активные таски будут в начале списка. На всякий случай
            будем получать первые 10. Среди них точно окажутся два наших. Далее мы получаем
            информацию о таске: кол-во Activity (numActivities), корневое Activity (baseActivity),
            верхнее Activity (topActivity). И отсеиваем свои таски по имени пакета
            корневого Activity.
     */
    fun onInfoClick(view: View) {
        list = am.getRunningTasks(10)
        for (task in list) {
            if (task.baseActivity?.flattenToShortString()
                    ?.startsWith("ru.startandroid.develop.p116")!!) {
                Log.d(LOG_TAG, "------------------")
                Log.d(LOG_TAG, "Count: ${task.numActivities}")
                Log.d(LOG_TAG, "Root: ${task.baseActivity?.flattenToShortString()}")
                Log.d(LOG_TAG, "Top: ${task.topActivity?.flattenToShortString()}")
            }
        }
    }
}