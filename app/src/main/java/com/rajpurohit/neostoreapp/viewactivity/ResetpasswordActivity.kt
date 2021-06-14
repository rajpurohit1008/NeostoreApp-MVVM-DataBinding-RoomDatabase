package com.rajpurohit.neostoreapp.viewactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.model.LoginModel
import com.rajpurohit.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResetpasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpassword)
        val currentpass: EditText = findViewById(R.id.rstcurrentpass)
        val newpass: EditText = findViewById(R.id.rstnewpass)
        val confpass: EditText = findViewById(R.id.rstconfrmpass)
        val resetpassbtn: Button = findViewById(R.id.resetpassbutton)

        resetpassbtn.setOnClickListener {
            val curnt = currentpass.text.toString()
            val newp = newpass.text.toString()
            val confp = confpass.text.toString()
            if (currentpass.getText().toString().isEmpty() || newpass.getText().toString()
                    .isEmpty() ||
                confpass.getText().toString()
                    .isEmpty() || newp == curnt || newp != confp || newpass.getText()
                            .toString().isNotEmpty() && newpass.getText().toString().length < 6
            ) {
                if (curnt.isEmpty()) {
                    currentpass.requestFocus()
                    currentpass.setError("Please Enter Current Password")
                }
                if (newp.isEmpty()) {
                    newpass.requestFocus()
                    newpass.setError("Please Enter New Password")
                }
                if (confp.isEmpty()) {
                    confpass.requestFocus()
                    confpass.setError("Please Enter Confirm Password")
                }
                if (newp == curnt) {
                    newpass.requestFocus()
                    newpass.setError("Password is same as old password")
                }
                if (newp != confp) {
                    confpass.requestFocus()
                    confpass.setError("Confirm Password is not same as Password")
                }
                if (newpass.getText().toString().length > 0 && newpass.getText().toString().length < 6) {
                    newpass.requestFocus()
                    newpass.setError("Minimum Length of Password Should be 6")
                }
            } else {
                val getsharedToken = getSharedPreferences("My_Token", MODE_PRIVATE)
                val changepwToken  = getsharedToken.getString("Access_Token","not Fount")
                Toast.makeText(this, "$changepwToken \n$curnt \n $newp \n $confp", Toast.LENGTH_SHORT).show()
                val callResetpw = ApiService.registerUserInfo().changePassword(changepwToken,curnt,newp,confp)
                callResetpw.enqueue(object : Callback<LoginModel> {
                    override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                        Toast.makeText(this@ResetpasswordActivity, "${response.body()?.userMsg}", Toast.LENGTH_SHORT).show()
                        if (response.isSuccessful){
                            intent = Intent(this@ResetpasswordActivity,MyaccountActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                        Toast.makeText(this@ResetpasswordActivity, "changepassword $t", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }


    }
    fun backbutton(view: View) {
        onBackPressed()
    }
}