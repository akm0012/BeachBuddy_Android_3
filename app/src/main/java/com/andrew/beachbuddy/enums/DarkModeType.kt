package com.andrew.beachbuddy.enums

enum class DarkModeType() {
    AUTO,
    ON,
    OFF;

    override fun toString(): String {
        return when (this) {
            AUTO -> "Dark Mode: Auto"
            ON -> "Dark Mode: On"
            OFF -> "Dark Mode: Off"
        }
    }
}