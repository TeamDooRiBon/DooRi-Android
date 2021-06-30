package kr.co.dooribon.utils

import kotlin.reflect.KProperty

/**
 * CustomView Delegation
 */
class OnChangeProp<T>(private var field: T, private val block: (value: T) -> Unit) {
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        field = value
        block(value)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return field
    }
}