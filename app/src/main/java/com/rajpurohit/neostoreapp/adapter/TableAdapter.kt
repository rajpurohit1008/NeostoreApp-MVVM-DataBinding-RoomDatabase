package com.rajpurohit.neostoreapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.model.Datum
import com.rajpurohit.neostoreapp.viewactivity.ProductdetailActivity

class TableAdapter(val context: Context, val tablelist: List<Datum>,val accessT:String?):RecyclerView.Adapter<TableAdapter.TableListVH>() {
    //val tablelist:List<Datum> =ArrayList<Datum>()
  //lateinit var  tablelist:Datum
 // val tablelist=Datum(name = "Madan Rajpurohit",description = "hgchgchgfhfgxgfxgfxghxgfxgfxgf",
 //  cost = 300,rating = 3,productImages = "${R.drawable.tableicon}")
    //val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableListVH {
    val  view = LayoutInflater.from(parent.context).inflate(R.layout.table_list, parent, false)

    return TableListVH(view)
    }


    override fun onBindViewHolder(holder: TableListVH, position: Int) {
        val currenttable = tablelist[position]
        holder.titletext.text = currenttable.name
        holder.subtext.text = currenttable.description
        holder.amounttext.text = "Rs ${currenttable.cost.toString()}"
        holder.ratingbar.rating = currenttable.rating!!.toFloat()
        val s1: String = java.lang.String.valueOf(currenttable.id)
        Glide.with(context).load(currenttable.productImages).centerCrop().into(holder.tableimage)
        holder.itemView.setOnClickListener(){
        val intent = Intent(context, ProductdetailActivity::class.java)
            intent.putExtra("product_id", s1)
            intent.putExtra("access_tokentable",accessT)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

    }

    override fun getItemCount():Int= tablelist.size


    inner class TableListVH(itemView: View):RecyclerView.ViewHolder(itemView){
        val tableimage:ImageView = itemView.findViewById(R.id.tableimage)
        val titletext:TextView = itemView.findViewById(R.id.nametextview)
        val subtext:TextView = itemView.findViewById(R.id.desctextview)
        val amounttext:TextView = itemView.findViewById(R.id.pricetxtview)
        val ratingbar:RatingBar = itemView.findViewById(R.id.ratingbar)

    }

}