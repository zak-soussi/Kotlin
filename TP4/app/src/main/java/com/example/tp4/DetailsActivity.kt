package com.example.tp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4.database.BusScheduleApplication
import com.example.tp4.databinding.ActivityDetailsBinding
import com.example.tp4.databinding.ActivityMainBinding
import com.example.tp4.viewmodels.BusScheduleViewModel
import com.example.tp4.viewmodels.BusScheduleViewModelFactory

class DetailsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var busStopAdapter: BusStopAdapter
    private lateinit var binding: ActivityDetailsBinding
    private val viewModel : BusScheduleViewModel by viewModels() {
        BusScheduleViewModelFactory(
            (application as
                    BusScheduleApplication).database.scheduleDao()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView

        val stopName = intent.getStringExtra("stopName")

        recyclerView.layoutManager = LinearLayoutManager(this)

        busStopAdapter= BusStopAdapter(emptyList()) {
            println()
        }
        recyclerView.adapter = busStopAdapter
        viewModel.scheduleForStopName(stopName!!).observe(this) {
            busStopAdapter.updateList(it)
        }

    }
}