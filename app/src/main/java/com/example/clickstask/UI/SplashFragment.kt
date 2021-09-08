package com.example.clickstask.UI

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.clickstask.R
import kotlinx.android.synthetic.main.fragment_splash.*



class SplashFragment : Fragment() {
    lateinit var mContext:Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // handle swipe down to refresh
        itemsswipetorefresh.setOnRefreshListener {
           SwitchUponStatus()
            itemsswipetorefresh.isRefreshing = false
        }

        SwitchUponStatus()



    }

    //check the internet connection
    fun SwitchUponStatus(){
        var handler: Handler = Handler();
        var runnable =  Runnable{
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }
        if((activity as MainActivity).isOnline(mContext)){
            handler.postDelayed(runnable,2000)
        }else{
            textView2.visibility = View.INVISIBLE
            textView3.visibility = View.INVISIBLE
            splashImage.setImageResource(R.drawable.no_connection)
        }
    }

}