    package com.beran_setaou.atik_yonetimi_appcent.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.beran_setaou.atik_yonetimi_appcent.MainActivity
import com.beran_setaou.atik_yonetimi_appcent.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


    class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        var emailText = view.findViewById<TextView>(R.id.eMailAnaSayfaText)
        var adSoyadText = view.findViewById<TextView>(R.id.adSoyadAnaSayfaText)
        var adresText =  view.findViewById<TextView>(R.id.adresAnaSayfaText)
        var puanText = view.findViewById<TextView>(R.id.puanText)
        var CikisText=view.findViewById<TextView>(R.id.cikis_Yap)
        CikisText.setOnClickListener(View.OnClickListener {


            FirebaseAuth.getInstance().signOut()
            var intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        })


        var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        firestore.collection("person")
            .document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {


                emailText.setText(it.data?.get("email").toString())
                adSoyadText.setText(it.data?.get("nameAndSurname").toString())
                adresText.setText(it.data?.get("adres").toString())
                puanText.setText(it.data?.get("puan").toString()+" puan")
            }
        return view
    }



}