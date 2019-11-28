package app.cntandroidver2.workshop.utils

import android.util.Log
import android.widget.ImageView
import app.cntandroidver2.workshop.R
import com.bumptech.glide.Glide


fun ImageView.loadImageWithGlide(imgPath: String?) {

    val validPath = toValidUrl(imgPath)

//    Log.d("print", "loadImageWithGlide = " + validPath)

    Glide.with(this)
        .load(validPath) // image url
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .fitCenter()
//        .override(200, 200); // resizing
        .centerCrop()
        .into(this);
}

fun toValidUrl(imgPath: String?): String? {

    return imgPath?.replace("http://http://".toRegex(), "http://")

}
