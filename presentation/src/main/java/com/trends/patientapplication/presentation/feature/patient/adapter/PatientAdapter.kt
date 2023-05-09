package com.trends.patientapplication.presentation.feature.patient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trends.patientapplication.domain.model.patient.PatientResponse
import com.trends.patientapplication.presentation.R
import com.trends.patientapplication.presentation.databinding.PatientItemBinding

class PatientAdapter(
    private val onDeletePatient: (id: String) -> Unit,
    private val onClickItem: (String) -> Unit
) :
    ListAdapter<PatientResponse, PatientAdapter.PatientViewHolder>(DiffCallback) {

    var lastSelected = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.patient_item, parent, false)
        return PatientViewHolder(view)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val currentPatient = getItem(position)
        holder.binding.model = currentPatient
        holder.itemView.setOnClickListener {
            if (position != lastSelected) {
                if (lastSelected != -1) {
                    getItem(lastSelected)?.selected = false
                    notifyItemChanged(lastSelected)
                }
                lastSelected = position
                currentPatient?.selected = true
                notifyItemChanged(position)
            }
            onClickItem(currentPatient.id)
        }

        holder.binding.deleteItem.setOnClickListener {
            onDeletePatient(currentPatient?.id ?: "")
        }
    }


    inner class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = PatientItemBinding.bind(itemView)
    }

    private object DiffCallback : DiffUtil.ItemCallback<PatientResponse>() {
        override fun areItemsTheSame(
            oldItem: PatientResponse,
            newItem: PatientResponse
        ): Boolean = oldItem.id == newItem.id


        override fun areContentsTheSame(
            oldItem: PatientResponse,
            newItem: PatientResponse
        ): Boolean = oldItem == newItem

    }
}
