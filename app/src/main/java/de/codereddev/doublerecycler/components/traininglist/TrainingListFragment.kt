package de.codereddev.doublerecycler.components.traininglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.codereddev.doublerecycler.R
import de.codereddev.doublerecycler.adapter.TrainingAdapter
import de.codereddev.doublerecycler.adapter.TrainingSetAdapter

class TrainingListFragment : Fragment(), TrainingAdapter.TrainingClickListener {

    private val trainingAdapter = TrainingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        trainingAdapter.setOnClickListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_training_list, container, false)

        rootView.findViewById<RecyclerView>(R.id.trainingRv).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = trainingAdapter
        }

        rootView.findViewById<FloatingActionButton>(R.id.addTrainingFab).setOnClickListener {

        }

        return rootView
    }

    override fun onClick(uuid: String, editText: EditText, adapter: TrainingSetAdapter) {
        // TODO: BLABLABLA
    }
}