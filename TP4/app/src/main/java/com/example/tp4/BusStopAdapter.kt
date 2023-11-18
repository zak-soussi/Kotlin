package com.example.tp4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4.database.entities.Schedule
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class BusStopAdapter(
    private var busStops: List<Schedule>,
    private val onItemClick: (Schedule) -> Unit
) :
    RecyclerView.Adapter<BusStopAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stopName: TextView = itemView.findViewById(R.id.stopstation)
        val arrivalTime: TextView = itemView.findViewById(R.id.arrivalTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.bus_stop, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentBusStop = busStops[position]

        holder.stopName.text = currentBusStop.stop_name
        val date = Date(currentBusStop.arrival_time * 1000L)
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        holder.arrivalTime.text = sdf.format(date)

        holder.itemView.setOnClickListener {
            onItemClick(currentBusStop)
        }

    }

    override fun getItemCount(): Int {
        return busStops.size
    }

    fun updateList(newList: List<Schedule>) {
        busStops = newList
        notifyDataSetChanged()
    }


}