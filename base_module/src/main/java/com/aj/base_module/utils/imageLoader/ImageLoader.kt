package com.aj.base_module.utils.imageLoader

import android.content.Context
import android.widget.ImageView

object  ImageLoader {

    var glideImageLoader : GlideImageLoader = GlideImageLoader();

    fun loadImage(ctx: Context?, iv: ImageView, url: String) {
        glideImageLoader.loadImage(ctx, ImageConfig(imageView = iv,url = url))
    }
}