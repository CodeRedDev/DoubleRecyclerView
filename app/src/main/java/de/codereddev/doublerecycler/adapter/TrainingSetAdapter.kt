package de.codereddev.doublerecycler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.codereddev.doublerecycler.R
import de.codereddev.doublerecycler.model.TrainingSet

class TrainingSetAdapter(
    private val trainingSetList: MutableList<TrainingSet> = mutableListOf()
) : RecyclerView.Adapter<TrainingSetAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.child_rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return trainingSetList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        trainingSetList[position].let {
            holder.textView.text = it.text
        }
    }

    fun swapData(data: List<TrainingSet>) {
        trainingSetList.clear()
        trainingSetList.addAll(data)
        notifyDataSetChanged()
    }

    fun addTrainingSet(set: TrainingSet) {
        trainingSetList.add(set)
        notifyItemInserted(itemCount - 1)
    }
}