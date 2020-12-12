package com.jcodonuts.app.utils

import android.content.res.Resources
import android.webkit.WebView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.jcodonuts.app.R


@BindingAdapter(value=["imgUrl", "roundCorner"], requireAll = false)
fun AppCompatImageView.setImgUrl(imgUrl:String?, roundCorner:Int=0){
    if(imgUrl!=null){
        if(roundCorner>0){
            Glide.with(context)
                    .load(imgUrl)
                    .placeholder(R.drawable.img_placeholder_donut)
                    .error(R.drawable.img_placeholder_donut)
                    .transform(CenterInside(), RoundedCorners(dpToPx(roundCorner)))
                    .into(this)
        }else{
            Glide.with(context)
                    .load(imgUrl)
                    .placeholder(R.drawable.img_placeholder_donut)
                    .error(R.drawable.img_placeholder_donut)
                    .into(this)
        }
    }else{
        setImageResource(R.drawable.img_placeholder_donut)
    }
}

@BindingAdapter("imgSVG")
fun AppCompatImageView.bindImgSVG(url:String){
    SvgLoader.fetchSvg(context, url, this)
}

@BindingAdapter("src")
fun ImageView.bindSrc(img:Int){
    this.setImageResource(img)
}

@BindingAdapter("selected")
fun LinearLayout.bindSelected(selected:Boolean){
    this.isSelected = selected
}

@BindingAdapter("selected")
fun TextView.bindSelected(selected:Boolean){
    this.isSelected = selected
}

@BindingAdapter("loadWeb")
fun WebView.loadWeb(url:String){
    this.loadUrl(url)
}

//@BindingAdapter("setWebViewClient")
//fun WebView.setWebViewClient(client: WebViewClient?) {
//    this.webViewClient = client
//}

fun dpToPx(dp:Int):Int{
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}