package com.example.homeworkskotlin

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworkskotlin.recycler.CityRecAdapter
import com.example.homeworkskotlin.response.WeatherResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*

@Suppress("LateinitUsage")
class MainActivity : LocationListener, AppCompatActivity(), CoroutineScope by MainScope() {


    private lateinit var service: WeatherService

    private lateinit var lm: LocationManager

    // The minimum distance to change Updates in meters
    private val minDistanceChangeForUpdates: Float = 10.toFloat() // 10 meters


    // The minimum time between updates in milliseconds
    private val minTimeBwUpdates = 60000  // 1 minute
        .toLong()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var location: Location? = null
        checkAndAskLocationPermissions()
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            lm.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                minTimeBwUpdates,
                minDistanceChangeForUpdates,
                this
            )
            location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
//            Toast.makeText(this, "location realisation block accessed", Toast.LENGTH_SHORT).show()
        }
        service = ApiFactory.weatherService

        launch {
            val response = withContext(Dispatchers.IO) {
                service.weatherInNearbyCities(
                    location?.longitude ?: 0.0,
                    location?.latitude ?: 0.0,
                    20
                )
            }
            if (response.isSuccessful) {
                //Toast.makeText(this@MainActivity,"#TestMsg CoroutineBlockAccessed",Toast.LENGTH_LONG).show()
                recycler.adapter =
                    CityRecAdapter(response.body()?.list ?: LinkedList<WeatherResponse>())
                    { wr -> startActivity(DetailedActivity.createIntent(this@MainActivity, wr.id)) }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //https://www.geeksforgeeks.org/android-searchview-with-example/
        //https://spin.atomicobject.com/2019/11/11/how-to-create-a-searchview-with-suggestions-in-kotlin/
        menuInflater.inflate(R.menu.menu, menu)
        val searchBarItem = menu?.findItem(R.id.search_bar)
        val searchBar = searchBarItem?.actionView as SearchView

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                launch {
                    lateinit var response: WeatherResponse

                    try {
                        response = withContext(Dispatchers.IO) {
                            service.weatherByName(query ?: "")

                        }

                        val cityId = response.id

                        startActivity(DetailedActivity.createIntent(this@MainActivity, cityId))
                    } catch (e: Exception) {
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            "Cannot find this city",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }


                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }

    private fun checkLocationPermissions(): Boolean {
        return !(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
    }

    private fun checkAndAskLocationPermissions() {
        if (!checkLocationPermissions()
        ) {
            val permissions = arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            this.requestPermissions(permissions, 0)
        }
    }

    override fun onLocationChanged(location: Location?) {

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }
}
