package de.codereddev.doublerecycler

import android.app.Application
import de.codereddev.doublerecycler.realm.DoubleRecyclerRealm
import io.realm.Realm

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        DoubleRecyclerRealm.initRealmConfig()
    }
}