package com.kindergeschichten.romanisch.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.kindergeschichten.romanisch.R
import com.kindergeschichten.romanisch.data.Story
import com.kindergeschichten.romanisch.data.StoryType
import com.kindergeschichten.romanisch.databinding.FragmentStoriesBinding
import com.kindergeschichten.romanisch.tools.PreferenceManager
import com.kindergeschichten.romanisch.tools.showStoryDetails
import com.kindergeschichten.romanisch.ui.StoryDetailsActivity
import com.kindergeschichten.romanisch.ui.adapter.StoryAdapter
import com.kindergeschichten.romanisch.viewmodel.ViewModel

private const val STORY_TYPE = "storyType"

class StoriesFragment : Fragment() {



    private var storyType = StoryType.ROMANISCH

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            storyType = StoryType.fromArgument(it.getString(STORY_TYPE)!!)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stories, container, false)
    }

    lateinit var binding:FragmentStoriesBinding
    lateinit var preferenceManager: PreferenceManager

    var storyToPass = StoryType.ROMANISCH.toArgument()
    lateinit var viewModel:ViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        preferenceManager = PreferenceManager.getInstance(requireContext())

        storyToPass = if(storyType==StoryType.ROMANISCH) preferenceManager.selectedLanguage else StoryType.DEUTSCH.toArgument()
        binding = FragmentStoriesBinding.bind(view)
        setupRecyclerView()
        loadData()
    }

    var cache:List<Story>? = null
    private fun loadData() {
        val stories =if(storyType==StoryType.ROMANISCH)
        viewModel.getRomanStoires()
        else viewModel.getDeutschStoires()
        stories.observe(viewLifecycleOwner){
            cache = it
            adapter?.submitList(it)
        }

        binding.btnStart.setOnClickListener{
            cache?.apply {
                StoryDetailsActivity.start(requireContext(),this.get(binding.viewPager.currentItem).storyId,storyToPass.lowercase())
                requireActivity().finish()
            }
        }

        binding.imgClose.setOnClickListener{
            requireActivity()?.finish()
        }
    }

    var adapter:StoryAdapter? = null
    private fun setupRecyclerView() {
        adapter = StoryAdapter(requireActivity(),storyToPass.lowercase()){
            //StoryDetailsActivity.start(requireContext(),it.storyId,storyToPass.lowercase())
            requireContext()?.showStoryDetails(it,storyToPass){}
        }
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)


    }

    companion object {
        fun newInstance(storyType: StoryType) =
            StoriesFragment().apply {
                arguments = Bundle().apply {
                    putString(STORY_TYPE, storyType.toArgument())
                    }
            }
    }
}