package com.kindergeschichten.romanisch.data

data class Story(
    val storyId: Int,
    val storyName: String,
    val storyTitlePictureThumb: String,
    val storyTitlePicture: String,
    val storyPicture: String,
    val sursilvanStoryName: String,
    val sursilvanStoryIntroText: String,
    val sursilvanStoryIntroSound: String?,
    val sursilvanStoryText: String,
    val sursilvanStorySound: String?,
    val sutsilvanStoryName: String,
    val sutsilvanStoryIntroText: String,
    val sutsilvanStoryIntroSound: String?,
    val sutsilvanStoryText: String,
    val sutsilvanStorySound: String?,
    val surmiranStoryName: String,
    val surmiranStoryIntroText: String,
    val surmiranStoryIntroSound: String?,
    val surmiranStoryText: String,
    val surmiranStorySound: String?,
    val puterStoryName: String,
    val puterStoryIntroText: String,
    val puterStoryIntroSound: String?,
    val puterStoryText: String,
    val puterStorySound: String?,
    val valladerStoryName: String,
    val valladerStoryIntroText: String,
    val valladerStoryIntroSound: String?,
    val valladerStoryText: String,
    val valladerStorySound: String?,
    val deutschStoryName: String,
    val deutschStoryIntroText: String,
    val deutschStoryIntroSound: String?,
    val deutschStoryText: String,
    val deutschStorySound: String?,
    val author: String,
    val writtenBy: String?,
    val translatedBy: String?,
    val illustrator: String?,
    val originalStory: String?,
    val publishedBy: String?,
    val photographer: String?,
    val translatedByGerman: String?,


    val sursilvanSpokenBy: String?,
    val sutsilvanSpokenBy: String?,
    val surmiranSpokenBy: String?,
    val puterSpokenBy: String?,
    val valladerSpokenBy: String?,
    val deutschSpokenBy: String?

){
    fun getStoryName(category: String): String? {
        return when (category.lowercase()) {
            "sursilvan" -> sursilvanStoryName
            "sutsilvan" -> sutsilvanStoryName
            "surmiran" -> surmiranStoryName
            "puter" -> puterStoryName
            "vallader" -> valladerStoryName
            "deutsch" -> deutschStoryName
            else -> null
        }
    }

    fun getStorySpeakerName(category: String): String? {
        return when (category.lowercase()) {
            "sursilvan" -> sursilvanSpokenBy
            "sutsilvan" -> sutsilvanSpokenBy
            "surmiran" -> surmiranSpokenBy
            "puter" -> puterSpokenBy
            "vallader" -> valladerSpokenBy
            "deutsch" -> deutschSpokenBy
            else -> null
        }
    }

    fun getStoryIntroText(category: String): String? {
        return when (category.lowercase()) {
            "sursilvan" -> sursilvanStoryIntroText
            "sutsilvan" -> sutsilvanStoryIntroText
            "surmiran" -> surmiranStoryIntroText
            "puter" -> puterStoryIntroText
            "vallader" -> valladerStoryIntroText
            "deutsch" -> deutschStoryIntroText
            else -> null
        }
    }

    fun getStoryIntroSound(category: String): String? {
        return when (category.lowercase()) {
            "sursilvan" -> sursilvanStoryIntroSound
            "sutsilvan" -> sutsilvanStoryIntroSound
            "surmiran" -> surmiranStoryIntroSound
            "puter" -> puterStoryIntroSound
            "vallader" -> valladerStoryIntroSound
            "deutsch" -> deutschStoryIntroSound
            else -> null
        }
    }

    fun getStoryText(category: String): String? {
        return when (category.lowercase()) {
            "sursilvan" -> sursilvanStoryText
            "sutsilvan" -> sutsilvanStoryText
            "surmiran" -> surmiranStoryText
            "puter" -> puterStoryText
            "vallader" -> valladerStoryText
            "deutsch" -> deutschStoryText
            else -> null
        }
    }

    fun getStorySound(category: String): String? {
        return when (category.lowercase()) {
            "sursilvan" -> sursilvanStorySound
            "sutsilvan" -> sutsilvanStorySound
            "surmiran" -> surmiranStorySound
            "puter" -> puterStorySound
            "vallader" -> valladerStorySound
            "deutsch" -> deutschStorySound
            else -> null
        }
    }
}
