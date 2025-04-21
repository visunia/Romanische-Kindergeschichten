package com.kindergeschichten.romanisch.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.viewpager2.widget.ViewPager2
import com.kindergeschichten.romanisch.R
import com.kindergeschichten.romanisch.data.Story
import com.kindergeschichten.romanisch.databinding.ActivityStoryDetailsActiivtyBinding
import com.kindergeschichten.romanisch.tools.showExitDialog
import com.kindergeschichten.romanisch.ui.adapter.StoryPagerAdapter
import com.kindergeschichten.romanisch.ui.base.VBActivity

class StoryDetailsActivity : VBActivity<ActivityStoryDetailsActiivtyBinding>() {

    override fun getViewBinding(): ActivityStoryDetailsActiivtyBinding {
      return  ActivityStoryDetailsActiivtyBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        disableBottomMenu()
        loadData()

    }
    var  storyType = ""
    private fun loadData() {
        val storyId = intent.getIntExtra(STORY_ID,1)
        storyType = intent.getStringExtra(STORY_TYPE)!!
        viewModel.getStoryPages(storyId)?.observe(this){
            it?.apply {
                setAdapter(this,storyType) }
        }

//        binding.imgSpeak.setOnClickListener{
//            tryPlay(binding.viewPager.currentItem)
//        }

        binding.imgClose.setOnClickListener{
            showExitDialog(R.string.exit_story,{
                finish()
            }){
                enableEdgeToEdge()
                disableBottomMenu()
            }
        }
    }

    var storiesCache:List<Story>? = null
    lateinit var adapter:StoryPagerAdapter
    private fun setAdapter(stories: List<Story>, storyType: String) {
        this.storiesCache = stories
        adapter = StoryPagerAdapter(this,stories,storyType){
            tryPlay(it)
        }
        binding.viewPager.adapter = adapter
        updateArrows(0)
        binding.viewPager.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tryPlay(position)
                updateArrows(position)
            }
        })

        binding.arrowLeft.setOnClickListener {
            if (binding.viewPager.currentItem > 0) {
                binding.viewPager.currentItem -= 1
            }
        }

        binding.arrowRight.setOnClickListener {
            if (binding.viewPager.currentItem < stories.size - 1) {
                binding.viewPager.currentItem += 1
            }
        }
    }

    private fun updateArrows(position: Int) {
        binding.arrowLeft.visibility = if (position > 0) View.VISIBLE else View.GONE
        binding.arrowRight.visibility = if (position < adapter.itemCount - 1) View.VISIBLE else View.GONE
    }

    private fun tryPlay(position: Int) {
        try {
            val story = storiesCache?.get(position)
            story?.getStorySound(storyType)?.apply {
                assetManager.play(this)
            }
        }catch (ex:Exception){
            Log.e("error",ex.message!!)
        }
    }

    private fun tryPlay(story: Story) {
        try {
            story?.getStorySound(storyType)?.apply {
                assetManager.play(this)
            }
        }catch (ex:Exception){}
    }

    override fun onPause() {
        super.onPause()
        assetManager.stop()
    }

    companion object{
        val STORY_ID = "STORY_ID"
        val STORY_TYPE = "STORY_TYPE"
        fun start(context: Context, storyId: Int, storyType: String)
        {
            context.startActivity(Intent(context,StoryDetailsActivity::class.java).putExtra(STORY_ID,storyId).putExtra(
                STORY_TYPE,storyType))
        }
    }
}