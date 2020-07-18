package com.example.nnvlib

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.findViewTreeLifecycleOwner

@BindingAdapter("nnvViewModel")
fun bindingAdapterNnvViewModel(view: MapView, viewModel: NnvViewModel) {
    view.setNnvViewModel(viewModel)
}

open class MapView : SurfaceView, SurfaceHolder.Callback {

    private lateinit var viewModel: NnvViewModel

    init {
        holder.addCallback(this)
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    public fun setNnvViewModel(viewModel: NnvViewModel) {
        this.viewModel = viewModel
        findViewTreeLifecycleOwner()?.let {
            this.viewModel.mapHandle.observe(it, Observer { map ->
                map?.getContentIfNotHandled()?.let {
                    val bmp = BitmapFactory.decodeResource(resources, R.drawable.map)
                    val canvas = holder.lockCanvas()
                    canvas.drawBitmap(bmp, 0.toFloat(), 0.toFloat(), Paint())
                    holder.unlockCanvasAndPost(canvas)
                }
            })
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        holder?.let {
            viewModel.mapInitialize(holder, this.width, this.height)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        viewModel.updateMapHandle(null)
    }

}