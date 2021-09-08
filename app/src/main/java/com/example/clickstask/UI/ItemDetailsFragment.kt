package com.example.clickstask.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.clickstask.R
import kotlinx.android.synthetic.main.fragment_item_details.view.*

class ItemDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding  = inflater.inflate(R.layout.fragment_item_details, container, false)

        //retrieve the data sent from the previous fragment
        binding.item_details_title.text = arguments?.getString("title")
        //if this piece of news doesn't have a description, notify the user.
        if((arguments?.getString("des").isNullOrEmpty())){
                binding.item_details_description.text = "لا يوجد المزيد من التفاصيل."
            }
        else
            binding.item_details_description.text = arguments?.getString("des")

        //handling missing images.
        if ((arguments?.getString("imageURL")).isNullOrEmpty()){
            binding.item_details_image.setImageResource(R.drawable.no_image_available)
        }
        else
            Glide.with(binding.item_details_image)
                .load(arguments?.getString("imageURL"))
                .centerCrop()
                .into(binding.item_details_image)


        return binding
    }



}