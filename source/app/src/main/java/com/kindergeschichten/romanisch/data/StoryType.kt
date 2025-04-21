package com.kindergeschichten.romanisch.data

// Enum class for Story types
enum class StoryType {
    ROMANISCH,
    DEUTSCH;

    // Utility function to convert enum to a string for arguments
    fun toArgument(): String = this.name

    companion object {
        // Function to parse a string back to an enum
        fun fromArgument(argument: String): StoryType {
            return valueOf(argument)
        }
    }
}