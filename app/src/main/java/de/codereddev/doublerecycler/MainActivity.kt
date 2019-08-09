package de.codereddev.doublerecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.codereddev.doublerecycler.adapter.TrainingSetAdapter
import de.codereddev.doublerecycler.adapter.TrainingAdapter
import de.codereddev.doublerecycler.model.TrainingSet
import de.codereddev.doublerecycler.model.Training
import de.codereddev.doublerecycler.realm.DoubleRecyclerRealm
import io.realm.Realm
import java.util.*

class MainActivity : AppCompatActivity(), TrainingAdapter.TrainingClickListener {

    private var recyclerView: RecyclerView? = null
    private val parentAdapter = TrainingAdapter(this)

    override fun onClick(uuid: String, editText: EditText, adapter: TrainingSetAdapter) {
        if (editText.text.isEmpty())
            return

        editText.text.let {
            val set = addSetToTraining(uuid, it.toString())
            editText.text.clear()
            adapter.addTrainingSet(set)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.outterRv).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = parentAdapter
        }

        findViewById<FloatingActionButton>(R.id.addFab).apply {
            setOnClickListener {
                addTraining()
                reloadData()
            }
        }

        reloadData()
    }

    fun addSetToTraining(uuid: String, text: String): TrainingSet {
        DoubleRecyclerRealm.newRealmInstance().use {
            val set = TrainingSet().apply {
                this.text = text
            }

            it.executeTransaction { realm ->
                val managedSet = realm.copyToRealm(set)
                val training: Training? = realm.where(Training::class.java).equalTo("uuid", uuid).findFirst()
                training?.sets?.add(managedSet)
            }
            return set
        }
    }

    fun addTraining() {
        DoubleRecyclerRealm.newRealmInstance().use {
            it.executeTransaction { realm ->
                realm.createObject(Training::class.java, UUID.randomUUID().toString())
            }
        }
    }

    fun addDbData() {
        DoubleRecyclerRealm.newRealmInstance().use {
            it.executeTransaction {
                val training = it.createObject(Training::class.java, UUID.randomUUID().toString())
                val setList = mutableListOf<TrainingSet>()
                for (i in 1..10) {
                    setList.add(TrainingSet(    ).apply {
                        text = "Set $i"
                    })
                }

                training.sets.addAll(it.copyToRealm(setList))
            }
        }
    }

    fun reloadData() {
        DoubleRecyclerRealm.newRealmInstance().use {
            val result = it.where(Training::class.java).findAll()
            parentAdapter.swapData(it.copyFromRealm(result))
        }
    }
}
