package kr.co.dooribon.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AutoClearBinding<T : Any> : ReadWriteProperty<Fragment, T>, LifecycleObserver {
    private var _binding: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
        _binding ?: throw IllegalArgumentException("you can't getValue outside of Lifecycle")

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        thisRef.viewLifecycleOwner.lifecycle.removeObserver(this)
        _binding = value
        thisRef.viewLifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        _binding = null
    }
}