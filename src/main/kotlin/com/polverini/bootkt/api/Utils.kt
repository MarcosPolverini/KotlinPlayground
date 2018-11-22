/**
Example script =)
Add this annotations to work with java

https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-name/index.html
Basically add a class name
@file:JvmName("Utils")

https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-multifile-class/index.html
Put all scripts into an unique "Class" for java
@file:JvmMultifileClass

More annotations to use in classes for java
@JvmField
@JvmStatic
@get:JvmName("x")
@set:JvmName("changeX")
@JvmOverloads
@Throws(IOException::class)
@JvmSuppressWildcards
 **/
package com.polverini.bootkt.api

import org.springframework.http.HttpStatus

/**
const val JSON_UTF8: String = MediaType.APPLICATION_JSON_UTF8_VALUE
 **/

const val RESPONSE_OK : Int = 200

const val RESPONSE_CREATED : Int = 201