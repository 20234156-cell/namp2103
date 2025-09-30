package com.example.game8386

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

// Data class for leaderboard row
data class LeaderboardItem(
    val rank: Int,
    val name: String,
    val score: Int,
    val time: Int,
    val order: Int
)

class LeaderboardAdapter(private val context: Context, private val items: List<LeaderboardItem>) : BaseAdapter() {
    override fun getCount(): Int = items.size
    override fun getItem(position: Int): Any = items[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.leaderboard_row, parent, false)
        val item = items[position]
        view.findViewById<TextView>(R.id.tvRank).text = item.rank.toString()
        view.findViewById<TextView>(R.id.tvName).text = item.name
        view.findViewById<TextView>(R.id.tvScore).text = item.score.toString()
        val min = item.time / 60
        val sec = item.time % 60
        view.findViewById<TextView>(R.id.tvTime).text = "%02d:%02d".format(min, sec)
        view.findViewById<TextView>(R.id.tvOrder).text = item.order.toString()
        return view
    }
}

