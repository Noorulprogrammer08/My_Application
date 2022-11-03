package com.mallowtech.myapplication.recyclerview

import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mallowtech.myapplication.R
import com.mallowtech.myapplication.api.ApiManager.Companion.AUTH
import com.mallowtech.myapplication.databinding.ActivityJobListScreenBinding

class JobListScreen : AppCompatActivity() {
    lateinit var binding: ActivityJobListScreenBinding
    private val jobListScreenViewModel: JobListScreenViewModel by lazy {
        ViewModelProvider(this)[JobListScreenViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list_screen)
        initializers()
        observeViewModel()
        listeners()
        adapters()
    }

    private fun observeViewModel() {
        with(jobListScreenViewModel) {
            errorMessage.observe(this@JobListScreen) { error ->
                AlertDialog.Builder(this@JobListScreen).setTitle("Error:").setMessage(error)
                    .create().show()
            }
            passIntent.observe(this@JobListScreen) { passIntentOk ->
                if (passIntentOk) {
                    itemListener()
                }

            }
        }
    }

    private fun itemListener() {
        jobListAdapter.onItemClicked = {
            val bundle = Bundle().apply {
                putInt("id", it.id)
                putString("jobname", it.jobname)
                putString("location",it.location)
                putString("phone",it.phone)
                putString("task description",it.taskDescription)
            }
            dialog.arguments = bundle
            dialog.show(supportFragmentManager, "customDialog")
        }
    }

    private fun initializers() {
        AUTH = "Basic " + Base64.encodeToString(
            "${pref.email}:${pref.password}".toByteArray(),
            Base64.NO_WRAP
        )
        binding.rvMain.layoutManager =
            GridLayoutManager(this@JobListScreen, 2, LinearLayoutManager.VERTICAL, false)
    }

    private fun listeners() {
        with(binding) {
            tvUserName.text = email
            fabNewTask.setOnClickListener {
                dialog.show(supportFragmentManager, "customDialog")
            }
        }
    }

    private fun adapters() {
        binding.rvMain.adapter = jobAdapter
    }

}