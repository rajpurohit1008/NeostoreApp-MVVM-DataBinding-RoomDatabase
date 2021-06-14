package com.rajpurohit.neostoreapp.viewactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.databinding.ActivityLoginBinding
import com.rajpurohit.neostoreapp.model.DataLogin
import com.rajpurohit.neostoreapp.model.LoginModel
import com.rajpurohit.neostoreapp.network.ApiService
import com.rajpurohit.neostoreapp.rmdatabase.UserDataBase
import com.rajpurohit.neostoreapp.viewmodel.LoginViewmodel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
  /*  lateinit var flt1: FloatingActionButton
    lateinit var login: Button
    var usern: EditText? = null
    var pass: EditText? = null
    lateinit var tv1: TextView*/
    lateinit var loginViewModel: LoginViewmodel


    //  val PREFS_NAME = "MySharedPref"
   // lateinit var login_progress: ProgressBar
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


       /* flt1 = findViewById(R.id.floatng)
        login = findViewById(R.id.login)
        usern = findViewById(R.id.username)
        pass = findViewById(R.id.passwrd)
        tv1 = findViewById(R.id.forgotTV)
        login_progress = findViewById(R.id.login_progress)
*/


        val dataBindingLogin:ActivityLoginBinding = DataBindingUtil. setContentView(this,R.layout.activity_login)
        val userEmail =  dataBindingLogin.username.text
        val userPassword =  dataBindingLogin.passwrd.text

        loginViewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(LoginViewmodel::class.java)

      loginViewModel.getLoginLivedata().observe(this, Observer{
          if (it!=null) {
                  val   intent = Intent(getApplication(), HomeActivity::class.java)
                  intent.putExtra("Access_TokenLogin",it.data?.accessToken)
                  startActivity(intent)

          }
})

      dataBindingLogin.floatng.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, 2)

        }

        dataBindingLogin.login.setOnClickListener() {
//            val ed1 = usern?.getText().toString()
//            val ed2: String = pass?.getText().toString()
            if (userEmail.isEmpty() || userPassword.isEmpty() ||
                    userPassword.length > 0 && userPassword.length < 6 ||
                    !Pattern.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+",userEmail)
            ) {
                if (userEmail.isEmpty()) {
                   dataBindingLogin.username.requestFocus()
                    dataBindingLogin.username.setError("Please Enter Email")
                } else if (!Pattern.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+",userEmail)) {
                   dataBindingLogin.username.requestFocus()
                    dataBindingLogin.username.setError("Please Enter Valid Email Id")
                }
                if (userPassword.isEmpty()) {
                   dataBindingLogin.passwrd.requestFocus()
                    dataBindingLogin.passwrd.setError("Please enter Password")
                }
                if (userPassword.length > 0 && userPassword.length < 6) {
                   dataBindingLogin.passwrd.requestFocus()
                   dataBindingLogin.passwrd.setError("Password Greater than 6 Digit")
                }
            } else {

                loginViewModel.loginUser(this,userEmail.toString(),userPassword.toString())

                //login.setVisibility(View.INVISIBLE)
                // loginViewModel.makeApiCall(ed1, ed2)
               // login_progress.setVisibility(View.VISIBLE)

                //val userlist = UserDataBase.getDatabase(this).getUserDao()
               /* Toast.makeText(this, "$userEmail $userPassword ", Toast.LENGTH_SHORT).show()
                GlobalScope.launch {
                    // val userinfo = userlist.getAllUser()

                    val loginData =  ApiService.registerUserInfo().loginUserData(userEmail.toString(),userPassword.toString())
                    loginData.enqueue(object : Callback<LoginModel> {
                        override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {

                            Toast.makeText(applicationContext, "${response.body()?.userMsg}", Toast.LENGTH_SHORT).show()
                            if (response.isSuccessful) {
                                intent = Intent(this@LoginActivity, HomeActivity::class.java)
                                intent.putExtra("Access_TokenLogin", response.body()!!.data?.accessToken)
                                startActivity(intent)
                            }
                        }

                        override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                            Toast.makeText(applicationContext, "something Wrong in login $t", Toast.LENGTH_SHORT).show()
                        }
                    })


                   *//* val userinfo = loginViewModel.getuserlist()
                    if (ed1 == userinfo.email && ed2 == userinfo.password) {
                        intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        withContext(Dispatchers.Main) {
                            displayToast()
                        }
                    }*//*
                }
*/
                //login_progress.visibility = View.GONE
                //login.visibility = View.VISIBLE
            }
        }
       dataBindingLogin.forgotTV.setOnClickListener(View.OnClickListener {
            intent = Intent(this,ForgotpasswordActivity::class.java)
            startActivity(intent)
        })
    }

    fun displayToast() {
        Toast.makeText(this@LoginActivity, "Wrong Username and Password, try again", Toast.LENGTH_SHORT).show()
    }



}





