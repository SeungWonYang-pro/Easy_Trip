package edu.skku.cs.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginActivity : AppCompatActivity() {
    companion object{
        const val EXT_NAME = "USERNAME!!"
    }
    var auth: FirebaseAuth? = null
    val GOOGLE_REQUEST_CODE =99
    val TAG = "google Login"
    lateinit var googleSingInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var Btn = findViewById<Button>(R.id.button)
        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSingInClient= GoogleSignIn.getClient(this,gso)
        Btn.setOnClickListener {
            signin()
        }


    }
    fun signin(){
        val signinIntent = googleSingInClient.signInIntent
        startActivityForResult(signinIntent,GOOGLE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==GOOGLE_REQUEST_CODE)
        {
            val task  =GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG,"FirebaseAuth with Google " + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            }
            catch (e: ApiException)
            {
                Log.w(TAG,"SIGNINFAILED!!!!!!!!!!", e)
                Toast.makeText(this,"로그인 실패",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken,null)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener(this){
                task ->
                if(task.isSuccessful)
                {
                    Log.d(TAG,"로그인성공")
                    val user = auth!!.currentUser
                    val intent = Intent(this,MainPageActivity::class.java).apply {
                        putExtra(EXT_NAME,user?.displayName)
                    }
                    startActivity(intent)
                }
                else
                {
                    Log.w(TAG,"SIGN IN FAIL",task.exception)
                }


            }
    }


}