package com.project.biovaultapp

import android.app.Application

class MyApplication  : Application() {

    companion object {
        lateinit var preferences: PreferenceUtil

        var name = ""
        var college = ""
        var major = ""
        var studentNum = ""
    }
}