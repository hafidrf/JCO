package com.jcodonuts.app.utils

import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.net.Uri
import android.text.Html
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import co.lujun.androidtagview.TagContainerLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.card.MaterialCardView
import com.jcodonuts.app.R
import com.jcodonuts.app.ui.main.cart.CartControllerListener
import org.sufficientlysecure.htmltextview.HtmlTextView
import org.sufficientlysecure.htmltextview.OnClickATagListener


private const val TAG = "Extension"

@BindingAdapter(value = ["imgUrl", "roundCorner", "imgPlaceholder"], requireAll = false)
fun AppCompatImageView.setImgUrl(imgUrl: String?, roundCorner: Int = 0, imgPlaceholder: Int = 0){

    var placeholder = R.drawable.img_placeholder_donut
    if(imgPlaceholder!=0){
        placeholder = imgPlaceholder
    }

    if(imgUrl!=null){
        if(roundCorner>0){
            Glide.with(context)
                    .load(imgUrl)
                    .placeholder(placeholder)
                    .error(placeholder)
                    .transform(CenterInside(), RoundedCorners(dpToPx(roundCorner)))
                    .into(this)
        }else{
            Glide.with(context)
                    .load(imgUrl)
                    .placeholder(placeholder)
                    .error(placeholder)
                    .into(this)
        }
    }else{
        setImageResource(R.drawable.img_placeholder_donut)
    }
}

@BindingAdapter("imgSVG")
fun AppCompatImageView.bindImgSVG(url: String){
    SvgLoader.fetchSvg(context, url, this)
}

@BindingAdapter("src")
fun ImageView.bindSrc(img: Int){
    this.setImageResource(img)
}

@BindingAdapter("isLocationFavorite")
fun ImageView.bindLocationFavorited(isFavorite: Boolean){
    if(isFavorite){
        ImageViewCompat.setImageTintList(
            this, ColorStateList.valueOf(
                ContextCompat.getColor(
                    context,
                    R.color.colorPrimary
                )
            )
        )
    }else{
        ImageViewCompat.setImageTintList(
            this, ColorStateList.valueOf(
                ContextCompat.getColor(
                    context,
                    R.color.c_text_secondary
                )
            )
        )
    }
}

@BindingAdapter("imgDrawable")
fun AppCompatImageView.bindImgDrawable(drawable: Int){
    this.setImageResource(drawable)
}

@BindingAdapter("html")
fun HtmlTextView.bindText(text: String?){
    text?.let {
        this.setHtml(it)
        this.setOnClickATagListener { widget, spannedText, href ->
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(href)
            context.startActivity(i)
            true
        }
    }
}

@BindingAdapter("isMenuItemFavorite")
fun AppCompatImageView.bindFavorite(isFavorite: Boolean){
    if(isFavorite){
        this.setColorFilter(
            ContextCompat.getColor(context, R.color.c_favorite_true),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }else{
        this.setColorFilter(
            ContextCompat.getColor(context, R.color.c_favorite_false),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

}

@BindingAdapter("selected")
fun LinearLayout.bindSelected(selected: Boolean){
    this.isSelected = selected
}

@BindingAdapter("selected")
fun TextView.bindSelected(selected: Boolean){
    this.isSelected = selected
}

@BindingAdapter("listTags")
fun TagContainerLayout.bindListTags(datas: List<String>){
    this.tags = datas
}

@BindingAdapter("loadWeb")
fun WebView.loadWeb(url: String){
    this.loadUrl(url)
}

@BindingAdapter("onCartChangeListener")
fun SwitchCompatEx.onCartChangeListener(listener: CartControllerListener){
    this.setOnCheckedChangeListener { _, isChecked ->
        listener.onSwitchChange(isChecked)
    }
}

@BindingAdapter("cardBackgroundColor")
fun MaterialCardView.bindCardBg(color: String){
    this.setCardBackgroundColor(Color.parseColor(color))
}

@BindingAdapter("titleActionbar")
fun TextView.bindTitle(title: Int){
    this.setText(title)
}

//@BindingAdapter("setWebViewClient")
//fun WebView.setWebViewClient(client: WebViewClient?) {
//    this.webViewClient = client
//}

fun dpToPx(dp: Int):Int{
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}