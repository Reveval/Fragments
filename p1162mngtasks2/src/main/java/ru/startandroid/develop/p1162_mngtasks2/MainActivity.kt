package ru.startandroid.develop.p1162_mngtasks2

import android.app.ActivityManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

const val LOG_TAG = "myLogs"

class MainActivity : AppCompatActivity() {
    private lateinit var list: List<ActivityManager.RunningTaskInfo>
    private lateinit var am: ActivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = resources.getString(R.string.app_name) + ":" + localClassName
        am = getSystemService(ACTIVITY_SERVICE) as ActivityManager
    }

    //В onClick идет вызов ActivityC из первого приложения.
    fun onClick(view: View) {
        startActivity(Intent("mngtasks1_activity_c"))
    }

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