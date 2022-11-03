package com.mallowtech.myapplication.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mallowtech.myapplication.databinding.ActivityJobCardsBinding
import com.mallowtech.myapplication.model.jobList.JobListResponse

class JobListAdapter(private var jobList: List<JobListResponse>) :
    RecyclerView.Adapter<JobListAdapter.TodoViewHolder>() {
    lateinit var binding : ActivityJobCardsBinding

    var onItemClicked: ((JobListResponse) -> Unit)? = null

    class TodoViewHolder(val binding: ActivityJobCardsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ActivityJobCardsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    fun setData(todos: List<JobListResponse>) {
        jobList = jobList
//    list=todoTasks
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentPosition = jobList[position]
        holder.binding.apply {
           tvRole.text = currentPosition.name
            tvExperience.text = currentPosition.description
            tvLocation.text = currentPosition.description
            tvPhone.text = currentPosition.description
            tvGreetings.text = currentPosition.description

        }
        holder.itemView.setOnClickListener {
            onItemClicked?.invoke(currentPosition)
        }
    }

    override fun getItemCount(): Int {
        return jobList.size
    }
}