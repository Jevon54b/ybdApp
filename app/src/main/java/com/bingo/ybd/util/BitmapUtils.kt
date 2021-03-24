package com.bingo.ybd.util

import android.graphics.Bitmap
import android.graphics.Matrix

object BitmapUtils{
    public fun zoomImg(bm: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
    // 获得图片的宽高
        val width = bm.getWidth()
        val height = bm.getHeight()
        // 计算缩放比例
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat()/ height;
        // 取得想要缩放的matrix参数
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        // 得到新的图片
        val newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm
    }
}