package com.drbrosdev.flix_bags.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory

import net.glxn.qrgen.android.QRCode

object QRGenUtils {

    fun createCodeBitmap(codeContent: String, backgroundColor: Int, codeColor: Int): Bitmap {
        return try {
            val byteArray by lazy {
                QRCode
                    .from(codeContent)
                    .withSize(250, 250)
                    .withColor(codeColor, backgroundColor)
                    .stream()
                    .toByteArray()
            }
            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        } catch (e: Exception) {
            Bitmap.createBitmap(250, 250, Bitmap.Config.ARGB_8888)
        }
    }
}