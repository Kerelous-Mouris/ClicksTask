package com.example.clickstask.UI

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clickstask.R
import com.example.clickstask.pojo.NewsModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var mContext: Context
    lateinit var newsVM : NewsVM
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>? = null
    lateinit var owner: ViewModelStoreOwner
    private var listHolder: ArrayList<NewsModel>? = ArrayList()
    private var searchedListHolder: ArrayList<NewsModel>? = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        owner = this
        val binding = inflater.inflate(R.layout.fragment_home, container, false)


        //listen to any change in the search edit text
        binding.search_txt.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //search the list with the user input
                searchedListHolder!!.clear()
                listHolder!!.forEach {
                    if (it.sourceData.name.contains(p0!!,true)){
                        searchedListHolder!!.add(it)

                    }
                }
                //update the adapter with the new list
                adapter = NewsAdapter(requireActivity(),searchedListHolder as MutableList<NewsModel>)
                news_rec_view.adapter = adapter
            }

            override fun afterTextChanged(p0: Editable?) {
                //restore the adapter with default data if there is no input from the user
                if (binding.search_txt.text.isNullOrEmpty())
                {
                    searchedListHolder!!.clear()
                    runBlocking {
                        setAdapter()
                    }
            }}


        })


        return binding
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            //Call the coroutines method
            runBlocking {
                setAdapter()
            }



        }

    //attach the adapter to the RecyclerView
    private suspend fun setAdapter(){
        //start a background thread to handle loading data
        GlobalScope.launch(Dispatchers.IO) {
            //initialize the ViewModel
            newsVM = ViewModelProvider(owner)[NewsVM::class.java]
            newsVM.initialize(mContext)
            //ask the ViewModel to load the data
            newsVM.getNews()
            //update the UI with the data retrieved
            withContext(Dispatchers.Main){
                newsVM.newsMutableLiveData.observe(viewLifecycleOwner, {
                        t->
                    listHolder = (t as ArrayList<NewsModel>)
                    layoutManager = LinearLayoutManager(activity)
                    adapter = NewsAdapter(requireActivity(),t as MutableList<NewsModel>)

                    news_rec_view.adapter = adapter
                    news_rec_view.layoutManager = layoutManager
                })}
    }
    }

}