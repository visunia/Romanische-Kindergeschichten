package com.kindergeschichten.romanisch.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.kindergeschichten.romanisch.R
import com.kindergeschichten.romanisch.data.StoryType
import com.kindergeschichten.romanisch.databinding.ActivityStorySelectionBinding
import com.kindergeschichten.romanisch.ui.base.VBActivity
import com.kindergeschichten.romanisch.ui.fragment.StoriesFragment

class StorySelectionActivity : VBActivity<ActivityStorySelectionBinding>() {

    override fun getViewBinding(): ActivityStorySelectionBinding {
        return ActivityStorySelectionBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val storyType = StoryType.fromArgument(intent.getStringExtra(STORY_TYPE)!!)
        val fragment = when(storyType)
        {
            StoryType.DEUTSCH  -> StoriesFragment.newInstance(StoryType.DEUTSCH)
            else  -> StoriesFragment.newInstance(StoryType.ROMANISCH)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.main, fragment)
            .commit()

    }

    companion object{
        val STORY_TYPE = "STORY_TYPE"
        fun start(context: Context,storyType:StoryType) {
            context.startActivity(Intent(context,StorySelectionActivity::class.java).putExtra(
                STORY_TYPE,storyType.toArgument()))
        }
    }
}