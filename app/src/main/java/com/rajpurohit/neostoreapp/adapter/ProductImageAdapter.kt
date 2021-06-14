package com.rajpurohit.neostoreapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.model.ProductImage
import com.rajpurohit.neostoreapp.viewactivity.ProductdetailActivity

class ProductImageAdapter(val context: Context,val imageList:List<ProductImage>):RecyclerView.Adapter<ProductImageAdapter.ProductImageVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.productdeatilimage,parent,false)
        return ProductImageVH(view)
    }

    override fun onBindViewHolder(holder: ProductImageVH, position: Int) {
        Glide.with(context).load(imageList[position].image).centerCrop().into(holder.productImage)
    }

    override fun getItemCount(): Int =imageList.size
    inner class ProductImageVH(itemView: View):RecyclerView.ViewHolder(itemView){
        val productImage :ImageView = itemView.findViewById(R.id.productimageView)
    }

}