package com.trends.patientapplication.presentation.feature.patient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.trends.patientapplication.domain.model.patient.PatientRemoteModel
import com.trends.patientapplication.presentation.R
import com.trends.patientapplication.presentation.databinding.PatientItemBinding

class PatientAdapter(private var list: List<PatientRemoteModel>? = null) :
    RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    var lastSelected = -1

    fun setData(newList: List<PatientRemoteModel>?) {
        val diffResult = DiffUtil.calculateDiff(PatientDiffUtil(list, newList))
        list = newList
        return diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.patient_item, parent, false)
        return PatientViewHolder(view)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val currentPatient = list?.get(position)
        holder.binding.model = currentPatient
        holder.itemView.setOnClickListener {
            if (position != lastSelected) {
                if (lastSelected != -1) {
                    list?.get(lastSelected)?.selected = false
                    notifyItemChanged(lastSelected)
                }
                lastSelected = position
                currentPatient?.selected = true
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = list?.size ?: 0

    inner class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = PatientItemBinding.bind(itemView)
    }
}

class PatientDiffUtil(
    private val oldItem: List<PatientRemoteModel>?,
    private val newItem: List<PatientRemoteModel>?
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItem?.size  ?: 0

    override fun getNewListSize(): Int = newItem?.size ?: 0

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem?.get(oldItemPosition)?.id == newItem?.get(newItemPosition)?.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}
