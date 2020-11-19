package com.jcodonuts.app.utils

import android.webkit.WebView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.jcodonuts.app.R


@BindingAdapter("imgUrl")
fun AppCompatImageView.bindImgUrl(url:String?){
    if(url!=null){
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.img_placeholder_news)
            .error(R.drawable.img_placeholder_news)
            .into(this)
    }else{
        setImageResource(R.drawable.img_placeholder_news)
    }
}

@BindingAdapter("imgSVG")
fun AppCompatImageView.bindImgSVG(url:String){
    SvgLoader.fetchSvg(context, url, this);
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