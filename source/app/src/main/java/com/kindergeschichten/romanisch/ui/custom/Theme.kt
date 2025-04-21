package com.kindergeschichten.romanisch.ui.custom

enum class Theme(val value: Int) {
    Dark(0),
    Light(1),
    FollowSystem(2);

    companion object {
        fun fromInt(value: Int): Theme {
            return when (value) {
                0 -> Dark
                1 -> Light
                else -> FollowSystem

            }
        }

        fun toInt(theme: Theme): Int {
            return theme.value
        }
    }
}