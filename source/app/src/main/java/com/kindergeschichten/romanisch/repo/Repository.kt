package com.kindergeschichten.romanisch.repo

import android.content.Context
import com.kindergeschichten.romanisch.data.Story
import com.kindergeschichten.romanisch.tools.SingletonHolder
import com.kindergeschichten.romanisch.tools.loadStoriesFromAssets
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class Repository(val context: Context) : CoroutineScope {

    companion object : SingletonHolder<Repository, Context>(::Repository)

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO


    var allStories: List<Story>? = null

    suspend fun getRomanStories(): List<Story> {
        if(allStories.isNullOrEmpty())
            loadStories()

        return allStories!!.filter { it.storyName in storiesRoman }
    }


    suspend fun getDuetschStories(): List<Story> {
        if(allStories.isNullOrEmpty())
            loadStories()

        return allStories!!.filter { it.storyName in storiesDeutsch }
    }

    suspend fun loadStories() {
        allStories = context.loadStoriesFromAssets()
    }

    suspend fun getStoryPages(storyId: Int): List<Story>? {
        if(allStories.isNullOrEmpty())
            loadStories()

        return allStories!!.filter { it.storyId == storyId }
    }


    val storiesRoman = listOf(
        "The Moon and The Cap",
        "Annual Haircut Day",
        "Mouse in the House",
        "Too Much Noise",
        "The Magic Block"
    )

    val storiesDeutsch = listOf(
        "The Moon and The Cap",
        "Annual Haircut Day",
        "Ghum-Ghum Gharial's Glorious Adventure",
        "Singing in the Rain",
        "My fish! No, my fish!",
        "Singing in the Rain",
        "My fish! No, my fish!",
        "Too Many Bananas",
        "Smile Please!"
    )

}