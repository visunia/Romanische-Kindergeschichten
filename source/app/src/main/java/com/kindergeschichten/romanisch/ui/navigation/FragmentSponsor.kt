package com.kindergeschichten.romanisch.ui.navigation

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kindergeschichten.romanisch.R
import com.kindergeschichten.romanisch.databinding.FragmentSponsorBinding
import com.kindergeschichten.romanisch.databinding.FragmentUeberunsBinding
import com.kindergeschichten.romanisch.tools.openUrl

class FragmentSponsor  // private OnFragmentInteractionListener mListener;
    : Fragment(), View.OnClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sponsor, container, false)
    }

    lateinit var binding: FragmentSponsorBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSponsorBinding.bind(view)
        binding.abt1.setOnClickListener(this)
        binding.abt2.setOnClickListener(this)
        binding.abt3.setOnClickListener(this)
        binding.abt4.setOnClickListener(this)
        binding.abt5.setOnClickListener(this)
        binding.abt6.setOnClickListener(this)
        binding.abt7.setOnClickListener(this)

        binding.abtContri4.setOnClickListener(this)


        binding.abt1.paintFlags = binding.abt1.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.abt2.paintFlags = binding.abt2.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.abt3.paintFlags = binding.abt3.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.abt4.paintFlags = binding.abt4.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.abt5.paintFlags = binding.abt5.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.abt6.paintFlags = binding.abt6.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.abt7.paintFlags = binding.abt7.paintFlags or Paint.UNDERLINE_TEXT_FLAG


        binding.abtContri4.paintFlags = binding.abt7.paintFlags or Paint.UNDERLINE_TEXT_FLAG

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.abt_1 -> requireContext()?.openUrl( R.string.about_us_bullet_1_url)
            R.id.abt_2 -> requireContext()?.openUrl( R.string.about_us_bullet_2_url)
            R.id.abt_3 -> requireContext()?.openUrl( R.string.about_us_bullet_3_url)
            R.id.abt_4 -> requireContext()?.openUrl( R.string.about_us_bullet_4_url)
            R.id.abt_5 -> requireContext()?.openUrl( R.string.about_us_bullet_5_url)
            R.id.abt_6 -> requireContext()?.openUrl( R.string.about_us_bullet_6_url)
            R.id.abt_7 -> requireContext()?.openUrl( R.string.about_us_bullet_7_url)

            R.id.abt_contri_4 -> requireContext()?.openUrl( R.string.about_us_contri_bullet_4_url)
        }
    } // TODO: Rename method, update argument and hook method into UI event
    //    public void onButtonPressed(Uri uri) {
    //        if (mListener != null) {
    //            mListener.onFragmentInteraction(uri);
    //        }
    //    }
    //    @Override
    //    public void onAttach(Context context) {
    //        super.onAttach(context);
    //        if (context instanceof OnFragmentInteractionListener) {
    //            mListener = (OnFragmentInteractionListener) context;
    //        } else {
    //            throw new RuntimeException(context.toString()
    //                    + " must implement OnFragmentInteractionListener");
    //        }
    //    }
    //
    //    @Override
    //    public void onDetach() {
    //        super.onDetach();
    //        mListener = null;
    //    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training Lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    //    public interface OnFragmentInteractionListener {
    //        // TODO: Update argument type and Name
    //        void onFragmentInteraction(Uri uri);
    //    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UeberunsFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): UeberunsFragment {
            val fragment = UeberunsFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
