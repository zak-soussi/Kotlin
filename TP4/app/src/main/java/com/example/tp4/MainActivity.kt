package com.example.tp4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4.database.BusScheduleApplication
import com.example.tp4.viewmodels.BusScheduleViewModel
import com.example.tp4.viewmodels.BusScheduleViewModelFactory
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var busStopAdapter: BusStopAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: BusScheduleViewModel by viewModels() {
        BusScheduleViewModelFactory(
            (application as
                    BusScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        busStopAdapter = BusStopAdapter(emptyList()) { schedule ->

            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("stopName", schedule.stop_name)
            startActivity(intent)

        }
        recyclerView.adapter = busStopAdapter
        viewModel.fullSchedule().observe(this) {
            busStopAdapter.updateList(it)
        }

    }
}