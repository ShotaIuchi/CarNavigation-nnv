package com.example.nnvlib

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.databinding.BindingAdapter

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