package com.example.kotlin_quakes

import android.content.Context
import android.net.ConnectivityManager
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_quakes.data.Features
import com.example.kotlin_quakes.data.QuakeBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class EarthquakeActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var emptyTextView: TextView
    lateinit var progress: ProgressBar

    private val BASE_URL =
        "https://earthquake.usgs.gov/fdsnws/event/1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.earthquake_activity)

        recyclerView = findViewById(R.id.recyclerView)
        emptyTextView = findViewById(R.id.empty_list_textview)
        progress = findViewById(R.id.loading_indicator)

        progress.visibility = View.VISIBLE
        if (isConnectionAvailable(this)) {

            val quakeApi = RetrofitHelper.getInstance().create(QuakeService::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val result: QuakeBase = quakeApi.getQuakes()
                val features: ArrayList<Features> = result.features as ArrayList<Features>
                val quakes = ArrayList<Earthquake>()

                for (feat in features) {

                    val timeInMilliseconds = feat.properties.time
                    val dateObject = Date(timeInMilliseconds)
                    val dateFormatter = SimpleDateFormat("MMM dd, yyyy")
                    val dateToDisplay: String = dateFormatter.format(dateObject)
                    val timeFormat = SimpleDateFormat("h:mm a")
                    val timeToDisplay: String = timeFormat.format(dateObject)

                    val quake = Earthquake(
                        mMagnitude = feat.properties.mag.toString(),
                        mLocation = feat.properties.place,
                        mDate = dateToDisplay,
                        mTime = timeToDisplay,
                        mUrl = feat.properties.url
                    )
                    quakes.add(quake)
                }

                Log.v(EarthquakeActivity::class.java.simpleName, quakes.toString())
                runOnUiThread {
                    val quakeAdapter =
                        QuakeAdapter(quakeList = quakes, context = this@EarthquakeActivity)
                    recyclerView.layoutManager = LinearLayoutManager(this@EarthquakeActivity)
                    recyclerView.adapter = quakeAdapter
                    progress.visibility = View.GONE
                }
            }

        } else {
            emptyTextView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            progress.visibility = View.GONE
        }
    }

    fun isConnectionAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        if (netInfo != null &&
            netInfo.isConnected &&
            netInfo.isConnectedOrConnecting &&
            netInfo.isAvailable
        ) {
            return true
        }
        return false
    }
}