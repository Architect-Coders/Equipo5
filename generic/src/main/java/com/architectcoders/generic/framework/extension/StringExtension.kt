package com.architectcoders.generic.framework.extension

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

const val EMPTY: String = ""

@UseExperimental(ExperimentalContracts::class)
fun String?.isFilled(): Boolean {
    contract {
        returns(true) implies (this@isFilled != null)
    }
    return !this.isNullOrEmpty()
}
