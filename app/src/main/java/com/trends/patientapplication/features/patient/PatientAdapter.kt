package com.trends.patientapplication.features.patient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trends.patientapplication.R
import com.trends.patientapplication.databinding.PatientItemBinding
import com.trends.patientapplication.domain.model.patient.PatientRemoteModel

class PatientAdapter(private val list: List<PatientRemoteModel>) :
    RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    var lastSelected = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.patient_item,parent,false)
        return PatientViewHolder(view)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val currentPatient = list[position]
        holder.binding.model = currentPatient
        holder.itemView.setOnClickListener{
            if(position != lastSelected){
                if(lastSelected != -1){
                    list[lastSelected].selected = false
                    notifyItemChanged(lastSelected)
                }
                lastSelected = position
                currentPatient.selected = true
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    inner class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = PatientItemBinding.bind(itemView)
    }
}
