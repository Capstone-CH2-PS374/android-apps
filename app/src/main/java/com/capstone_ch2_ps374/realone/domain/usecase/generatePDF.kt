package com.capstone_ch2_ps374.realone.domain.usecase

import android.content.Context
import android.graphics.Canvas
import android.graphics.Bitmap
import android.graphics.Typeface
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.pdf.PdfDocument
import androidx.core.content.ContextCompat
import android.graphics.pdf.PdfDocument.PageInfo
import android.os.Environment
import android.widget.Toast
import com.capstone_ch2_ps374.realone.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun generatePDF(context: Context) {
    val pageHeight = 3700
    val pageWidth = 5200
    val pdfDocument = PdfDocument()
    val paint = Paint()
    val title = Paint()
    val myPageInfo = PageInfo.Builder(pageWidth, pageHeight, 1).create()
    val myPage = pdfDocument.startPage(myPageInfo)
    val canvas: Canvas = myPage.canvas
    val bitmap: Bitmap? = context.getDrawable(R.drawable.ic_accept)
        ?.let { drawableToBitmap(it) }
    val bitmapBackground: Bitmap? = context.getDrawable(R.drawable.background_certificate)
        ?.let { drawableToBitmap(it) }
    val scaleBitmap: Bitmap? = Bitmap.createScaledBitmap(bitmap!!, 31, 23, true)
    canvas.drawBitmap(bitmapBackground!!, 0f, 0f, paint)
//    title.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
//    title.textSize = 15f
//    title.color = ContextCompat.getColor(context, R.color.purple_200)
//    canvas.drawText("Jetpack Compose", 400f, 100f, title)
//    canvas.drawText("Make it Easy", 400f, 80f, title)
//    title.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
//    title.color = ContextCompat.getColor(context, R.color.purple_200)
//    title.textSize = 15f
//    title.textAlign = Paint.Align.CENTER
//    canvas.drawText("This is sample document which we have created.", 396f, 560f, title)
    pdfDocument.finishPage(myPage)
    val file = File("${Environment.getExternalStorageDirectory()}/${Environment.DIRECTORY_DOWNLOADS}", "serti.pdf")

    try {
        pdfDocument.writeTo(FileOutputStream(file))
        Toast.makeText(context, "PDF file generated successfylly", Toast.LENGTH_SHORT).show()
    } catch (ex: IOException) {
        ex.printStackTrace()
    }
    pdfDocument.close()
}

fun drawableToBitmap(drawable: Drawable): Bitmap? {
    if (drawable is BitmapDrawable) {
        return drawable.bitmap
    }
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}