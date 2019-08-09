package de.codereddev.doublerecycler.realm

import io.realm.Realm
import io.realm.RealmConfiguration

object DoubleRecyclerRealm {
    const val REALM_FILE_NAME = "doublerecycler.realm"
    const val REALM_SCHEMA_VERSION = 1L

    lateinit var config: RealmConfiguration

    fun initRealmConfig() {
        config = RealmConfiguration.Builder()
            .name(REALM_FILE_NAME)
            .modules(Module())
            .schemaVersion(REALM_SCHEMA_VERSION)
            .build()

        Realm.setDefaultConfiguration(config)
    }

    fun newRealmInstance(): Realm {
        return Realm.getInstance(config)
    }
}