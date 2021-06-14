package com.rajpurohit.neostoreapp.viewactivity

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.adapter.ViewPagerAdapter
import com.rajpurohit.neostoreapp.model.fetchaccount.FetchDetailModel
import com.rajpurohit.neostoreapp.network.ApiService
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeActivity : AppCompatActivity() {
  lateinit var images:IntArray
  lateinit var  mviewpager : ViewPager
  lateinit var toggle: ActionBarDrawerToggle
   lateinit var drawlayout:DrawerLayout
   var ACCESS_TOKENHOME : String? = null
    lateinit var tv_counter:TextView
    val PICK_IMAGE = 1
    lateinit var currentProfile:CircleImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
         images = intArrayOf(
                 R.drawable.slider_img1,
                 R.drawable.slider_img2,
                 R.drawable.slider_img3,
                 R.drawable.slider_img4
         )
         mviewpager = findViewById<ViewPager>(R.id.viewpagerm)
       val  viewPagerAdapter = ViewPagerAdapter(this, images)
        mviewpager.setAdapter(viewPagerAdapter)
       val tabLayout = findViewById<TabLayout>(R.id.tablayout)
        tabLayout.setupWithViewPager(mviewpager, true)

        Timer().scheduleAtFixedRate(SliderTimer(mviewpager, images), 2000, 4000)


        //navigation
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.registertool)
        setSupportActionBar(toolbar)
         drawlayout =findViewById(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(this, drawlayout, R.string.open, R.string.close)
        drawlayout.addDrawerListener(toggle)
        toggle.drawerArrowDrawable.color = resources.getColor(R.color.white)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navview :NavigationView = findViewById(R.id.navview)
        navview.bringToFront()
        navview.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.chairs -> Toast.makeText(applicationContext, "check item chair", Toast.LENGTH_SHORT).show()
                R.id.logout -> {
                    finish()
                }
                R.id.cupboards -> Toast.makeText(this, "check item cupboards", Toast.LENGTH_SHORT).show()
                R.id.storelocator -> {  val intentStorelocator = Intent(this, StoreLocatorActivity::class.java)
                        intentStorelocator.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intentStorelocator)}
                R.id.sofas -> Toast.makeText(this, "check item sofas", Toast.LENGTH_SHORT).show()
                R.id.tables -> {
                    val intentTable = Intent(this, TableActivity::class.java)
                    intentTable.putExtra("tokenhome", ACCESS_TOKENHOME)
                    intentTable.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intentTable)
                }
                R.id.myorders -> {
                    val intentMyorder = Intent(this, MyorderActivity::class.java)
                    intentMyorder.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intentMyorder)
                }
                R.id.mycart -> {
                    val intentMycart = Intent(this, MycartActivity::class.java)
                    intentMycart.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intentMycart)
                }
                R.id.myaccount -> {
                    val intentMyaccount = Intent(this, MyaccountActivity::class.java)
                    intentMyaccount.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intentMyaccount)
                }
            }
            true
        }

        ACCESS_TOKENHOME  = intent.getStringExtra("Access_TokenLogin")
        val sharedPreferences = getSharedPreferences("My_Token", MODE_PRIVATE)
       val sharedToken = sharedPreferences.edit()
        sharedToken.putString("Access_Token", ACCESS_TOKENHOME)
        sharedToken.commit()
       val naviView = navview.getHeaderView(0)
       val naviMenuItem = navview.menu
        val currentname:TextView =naviView.findViewById(R.id.currentname)
        val currentemail:TextView = naviView.findViewById(R.id.currentemail)
        currentProfile  = naviView.findViewById(R.id.homeimage_profile)

        currentProfile.setOnClickListener {
            val i = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(i,PICK_IMAGE)
           //val intent = Intent(Intent.ACTION_GET_CONTENT)
           // intent.type = "image/*"
           // startActivityForResult(Intent.createChooser(intent,"select picture"),PICK_IMAGE)
        }
        val li = LayoutInflater.from(this)
        tv_counter = li.inflate(R.layout.menu_counter, null) as TextView
       val mycartNotification = naviMenuItem.findItem(R.id.mycart)
        mycartNotification.actionView = tv_counter
        //Toast.makeText(this, "AccessToken is $ACCESS_TOKENHOME", Toast.LENGTH_SHORT).show()
        val callFetchuser = ApiService.registerUserInfo().getfetchAccountDetail(ACCESS_TOKENHOME!!)
        callFetchuser.enqueue(object : Callback<FetchDetailModel> {
            override fun onResponse(call: Call<FetchDetailModel>, response: Response<FetchDetailModel>) {
               // Toast.makeText(this@HomeActivity, "Wel Come  ${response.body()?.data?.userData?.username}", Toast.LENGTH_LONG).show()
                currentname.text = response.body()?.data?.userData?.username
                currentemail.text = response.body()?.data?.userData?.email
                tv_counter.text = "${response.body()?.data?.totalCarts}"

            }

            override fun onFailure(call: Call<FetchDetailModel>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "something wrong on homeActivity", Toast.LENGTH_SHORT).show()
            }
        })


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    class SliderTimer(val mviewpager: ViewPager, val images: IntArray) : TimerTask() {
        override fun run() {
            GlobalScope.launch(Dispatchers.Main){
                if (mviewpager.getCurrentItem() < images.size - 1) {
                    mviewpager.setCurrentItem(mviewpager.getCurrentItem() + 1)
                } else {
                    mviewpager.setCurrentItem(0)
                }
            }
        }
    }

    fun onclickTable(view: View) {
        val intentTable = Intent(this, TableActivity::class.java)
        startActivity(intentTable)
    }
    override fun onBackPressed() {
        if (drawlayout.isDrawerOpen(GravityCompat.START)) {
            drawlayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imagedata: Intent?) {
        super.onActivityResult(requestCode, resultCode, imagedata)
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            val imageUrl : Uri? = imagedata?.data
            val bitmap:Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUrl)
            currentProfile .setImageBitmap(bitmap)
        }
    }
}