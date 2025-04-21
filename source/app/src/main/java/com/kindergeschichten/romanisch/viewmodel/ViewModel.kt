package com.kindergeschichten.romanisch.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kindergeschichten.romanisch.data.Story
import com.kindergeschichten.romanisch.repo.Repository
import kotlinx.coroutines.launch

class ViewModel(val context:Application):AndroidViewModel(context) {
    val repo = Repository.getInstance(context)

    fun loadStories() {
      viewModelScope.launch {
          repo.loadStories()
      }
    }

    fun getRomanStoires(): MutableLiveData<List<Story>> {
        val stories = MutableLiveData<List<Story>>()
        viewModelScope.launch {
           stories.postValue( repo.getRomanStories())
        }
        return stories
    }

    fun getDeutschStoires(): MutableLiveData<List<Story>> {
        val stories = MutableLiveData<List<Story>>()
        viewModelScope.launch {
            stories.postValue( repo.getDuetschStories())
        }
        return stories
    }

    fun getStoryPages(storyId: Int): MutableLiveData<List<Story>> {
        val storyPages = MutableLiveData<List<Story>>()
        viewModelScope.launch {
            storyPages.postValue(repo.getStoryPages(storyId))
        }
        return storyPages
    }
}