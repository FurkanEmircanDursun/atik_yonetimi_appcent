package com.beran_setaou.atik_yonetimi_appcent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(FirebaseAuth.getInstance().currentUser!=null){
            var intent =Intent(this,AnaSayfaActivity::class.java)
            startActivity(intent)
            finish()
        }
         var emailText=findViewById<EditText>(R.id.emailText_main)
         var parolaText=findViewById<EditText>(R.id.parolaText_main)
          var kaydolButton=findViewById<Button>(R.id.kaydolButton)
            var girisButton=findViewById<Button>(R.id.girisYapButton)




        kaydolButton.setOnClickListener(View.OnClickListener {
            var intent= Intent(this,KaydolActivity::class.java)
                startActivity(intent)

        })

    girisButton.setOnClickListener {
        var auth=FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(emailText.text.toString(),parolaText.text.toString())
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                var intent =Intent(this,AnaSayfaActivity::class.java)
                startActivity(intent)
                finish()
        }

    }

    }

}

}
