package ru.startandroid.develop.p1161mngtasks1

import android.content.Intent
import android.view.View

class ActivityA : MainActivity() {
    override fun onClick(view: View) {
        startActivity(Intent(this, ActivityB::class.java))
    }
}