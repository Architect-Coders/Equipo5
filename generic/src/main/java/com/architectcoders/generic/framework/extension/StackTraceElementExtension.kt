package com.architectcoders.generic.framework.extension

fun StackTraceElement.generateTag() = className.substringAfterLast(".")

fun StackTraceElement.generateMessage(msg: Any) = "$methodName() $msg"
