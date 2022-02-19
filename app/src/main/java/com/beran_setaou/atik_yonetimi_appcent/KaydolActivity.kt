package com.beran_setaou.atik_yonetimi_appcent

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.PackageManagerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import android.Manifest
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import android.widget.TextView
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import java.util.*
import kotlin.collections.ArrayList
private lateinit var auth: FirebaseAuth
lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
val locationRequestId = 100
class KaydolActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kaydol)
        auth = Firebase.auth
        var adSoyadText = findViewById<EditText>(R.id.adSoyadText)
        var parolaText = findViewById<EditText>(R.id.parolaText)
        var eMailText = findViewById<EditText>(R.id.eMailText)
        var konumAl=findViewById<Button>(R.id.konumAlButton)


        var kaydolButton = findViewById<Button>(R.id.kaydolButton)
        kaydolButton.setOnClickListener(View.OnClickListener {
            auth.createUserWithEmailAndPassword(
                eMailText.text.toString(),
                parolaText.text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        var tv_add = findViewById<TextView>(R.id.tv_add)

                        val user = auth.currentUser
                        val person = hashMapOf(
                            "nameAndSurname" to adSoyadText.text.toString(),
                            "password" to parolaText.text.toString(),
                            "email" to eMailText.text.toString(),
                            "adres" to tv_add.text.toString(),
                            "puan" to 100,

                        )

                        FirebaseFirestore.getInstance().collection("person").document(user!!.uid)
                            .set(person)
                        var intent = Intent(this, AnaSayfaActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {

                    }
                }


        })
        konumAl.setOnClickListener {

            getLocation()



        }


    }


    fun getLocation() {

        if (checkForLocationPermission()) {
            updateLocation()
        } else {
            askLocationPermission()
        }
    }

    fun updateLocation() {
        var locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000

        mFusedLocationProviderClient = FusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mFusedLocationProviderClient.requestLocationUpdates(
            locationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }


    var mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {

            var location: Location = p0.lastLocation

            updateAddressUI(location)

        }
    }

    fun updateAddressUI(location: Location) {

        var geocoder: Geocoder
        var addressList = ArrayList<Address>()

        geocoder = Geocoder(applicationContext, Locale.getDefault())

        addressList = geocoder.getFromLocation(
            location.latitude,
            location.longitude,
            1
        ) as ArrayList<Address>
        var tv_add = findViewById<TextView>(R.id.tv_add)
        tv_add.text = addressList.get(0).getAddressLine(0)


    }


    fun checkForLocationPermission(): Boolean {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
            return true

        return false

    }


    fun askLocationPermission() {

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            locationRequestId
        )

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == locationRequestId) {

            if (grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                getLocation()
            }
        }

    }

        }



