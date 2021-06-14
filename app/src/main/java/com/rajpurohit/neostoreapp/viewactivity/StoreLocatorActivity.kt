package com.rajpurohit.neostoreapp.viewactivity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.adapter.StoreLocatorAdapter

class StoreLocatorActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_locator)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        val storeRecyclerView :RecyclerView= findViewById(R.id.store_recycler)
        storeRecyclerView.layoutManager = LinearLayoutManager(this)
        storeRecyclerView.adapter = StoreLocatorAdapter(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
       val mumbai = LatLng(19.2337028, 72.8621114)
        mMap.addMarker(MarkerOptions().position(mumbai).title("A to Z Furnishing"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mumbai,8f))
    }

    fun backstore(view: View) {
        onBackPressed()
    }
}