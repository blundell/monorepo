package com.blundell

data class SemVersion(val major: Int, val minor: Int, val patch: Int, val build: Int) {

    /**
     * 'nnn.nnn.nnn' (leading zero's dropped)
     */
    fun asCode(): Int {
        return (major * 10_000 + minor * 1000 + patch * 100) + build
    }

    fun asName(): String {
        return "$major.$minor.$patch"
    }
}
