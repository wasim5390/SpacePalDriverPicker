package com.spacepal.internal.app

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.annotations.Expose

class AnnotationExclusionStrategy : ExclusionStrategy {
    override fun shouldSkipField(f: FieldAttributes): Boolean {
        val expose = f.getAnnotation(Expose::class.java)
        return expose != null && !expose.serialize
    }

    override fun shouldSkipClass(clazz: Class<*>): Boolean {
        return false
    }
}
