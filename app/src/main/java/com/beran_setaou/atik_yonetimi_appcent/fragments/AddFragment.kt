package com.beran_setaou.atik_yonetimi_appcent.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.getDocumentUri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.beran_setaou.atik_yonetimi_appcent.MainActivity
import com.beran_setaou.atik_yonetimi_appcent.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class AddFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {





        var view =  inflater.inflate(R.layout.fragment_add, container, false)


        return view
    }
    override fun onViewCreated(view: View,  savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var tur = view.findViewById<EditText>(R.id.atik_turu)
        var miktar= view.findViewById<EditText>(R.id.atik_miktari)


        var button= view.findViewById<Button>(R.id.talep_button)

        button.setOnClickListener {
            val istek = hashMapOf(
                "tur" to tur.text.toString(),
                "miktar" to miktar.text.toString(),
                "Uuid" to FirebaseAuth.getInstance().currentUser?.uid.toString()
            )

            FirebaseFirestore.getInstance().collection("requests").document(UUID.randomUUID().toString()).set(istek)


        }


    }




}