package com.example.segundaprova.fragments.home

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NovoRecycleViewClickListener(context : Context, recyclerView: RecyclerView, listener : OnItemClickListener) : RecyclerView.OnItemTouchListener {

    var myGestureDetector: GestureDetector

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    init {
        myGestureDetector =
            GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(motionEvent: MotionEvent): Boolean {
                    super.onSingleTapUp(motionEvent)
                    val childView = recyclerView.findChildViewUnder(motionEvent.x, motionEvent.y)
                    if (childView != null) {
                        listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView))
                        Log.i("Teste", "onSingleTapUp ")
                    }
                    return true
                }
                override fun onLongPress(motionEvent: MotionEvent) {
                    super.onLongPress(motionEvent)
                    val childView = recyclerView.findChildViewUnder(motionEvent.x, motionEvent.y)
                    if (childView != null) {
                        listener.onItemLongClick(
                            childView,
                            recyclerView.getChildAdapterPosition(childView)
                        )
                        Log.i("Teste", "onLongPress")
                    }
                }
            })
    }



    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        myGestureDetector.onTouchEvent(e)
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        TODO("Not yet implemented")
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        TODO("Not yet implemented")
    }
}