package edu.ksu.cs.mdc.androidspring2021

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.location.LocationManagerCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.LocationServices

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        // Grab location once the app has the permission
        val mTextView = view.findViewById<TextView>(R.id.textview_second)
        val locationManager: LocationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // Check if location is enabled globally. Even if the app has the permission, it won't get
        // anything if the global location setting isn't enabled.
        val isEnabled = LocationManagerCompat.isLocationEnabled(locationManager)
        // Grab the fused location provider (from Google Play Services)
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity().applicationContext)

        if(isEnabled) {
            // Make sure to move strings to strings.xml in production
            mTextView.text = "Location enabled."
            // Grab location from the fused location provider
            fusedLocationClient.lastLocation.addOnSuccessListener {
                location ->
                    if (location != null) {
                        mTextView.text =
                            "Longitude = ${location.longitude}/nLatitude = ${location.latitude}"
                    }
                    else {
                        mTextView.text = "Location = null"
                    }
            }
        }
        else {
            mTextView.text = "Location not enabled"
        }
    }
}