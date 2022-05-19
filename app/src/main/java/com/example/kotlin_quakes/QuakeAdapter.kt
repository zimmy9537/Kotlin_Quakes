package com.example.kotlin_quakes

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import retrofit2.http.Url


class QuakeAdapter(quakeList: ArrayList<Earthquake>, context: Context) :
    RecyclerView.Adapter<QuakeAdapter.QuakeViewHolder>() {

    private var quakeList: ArrayList<Earthquake>
    private var context: Context

    init {
        this.quakeList = quakeList
        this.context = context
    }

    fun getMagnitudeColor(Magnitude: String): Int {
        val magnitudeColorResourceId: Int
        val magnitudeFloor = Magnitude.toDouble().toInt()
        magnitudeColorResourceId = when (magnitudeFloor) {
            0, 1 -> R.color.magnitude1
            2 -> R.color.magnitude2
            3 -> R.color.magnitude3
            4 -> R.color.magnitude4
            5 -> R.color.magnitude5
            6 -> R.color.magnitude6
            7 -> R.color.magnitude7
            8 -> R.color.magnitude8
            9 -> R.color.magnitude9
            else -> R.color.magnitude10plus
        }
        return ContextCompat.getColor(context, magnitudeColorResourceId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuakeViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return QuakeViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuakeViewHolder, position: Int) {
        holder.magnitude.setText(quakeList.get(position).mMagnitude)
        locationParser(quakeList.get(position), holder)
        holder.time.setText(quakeList.get(position).mTime)
        holder.date.setText(quakeList.get(position).mDate)
        val magnitudeColor = getMagnitudeColor(quakeList.get(position).mMagnitude)
        // Set the color on the magnitude circle

        val magnitudeView = holder.magnitude
        magnitudeView.setBackgroundColor(magnitudeColor)

        holder.list_item.setOnClickListener {
            val url = quakeList.get(position).mUrl
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(url))
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return quakeList.size
    }

    fun locationParser(quake: Earthquake, holder: QuakeViewHolder) {
        val raw: String = quake.mLocation
        if (raw.contains(" of")) {
            var a: String
            var b: String
            val i = raw.indexOf(" of")
            a = raw.substring(0, i)
            a = "$a OF"
            b = raw.substring(
                i + 4,
                raw.length
            ) //? 3 =1+1 2 for "of" other one for the space left after "of"
            b = " $b"
            holder.offset_location.text = a
            holder.primary_location.text = b
        } else {
            holder.primary_location.text = raw
        }
    }


    class QuakeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var magnitude: TextView
        var offset_location: TextView
        var primary_location: TextView
        var date: TextView
        var time: TextView
        var list_item: LinearLayout

        init {
            list_item = itemView.findViewById(R.id.list_item)
            magnitude = itemView.findViewById(R.id.magnitude_text_view)
            offset_location = itemView.findViewById(R.id.offset_location_text_view)
            primary_location = itemView.findViewById(R.id.primary_location_text_view)
            date = itemView.findViewById(R.id.date_text_view)
            time = itemView.findViewById(R.id.time_text_view)
        }
    }
}