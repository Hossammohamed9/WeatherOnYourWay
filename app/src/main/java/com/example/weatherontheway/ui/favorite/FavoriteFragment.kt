package com.example.weatherontheway.ui.favorite

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherontheway.R
import com.example.weatherontheway.adapters.FavoriteAdapter
import com.example.weatherontheway.databinding.FragmentFavoriteBinding
import com.example.weatherontheway.models.FavData
import com.example.weatherontheway.ui.mainactivity.MainActivity
import com.example.weatherontheway.ui.map.MapActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private lateinit var adapter: FavoriteAdapter
    private lateinit var dataList : List<FavData>
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var binding: FragmentFavoriteBinding

    // This property is only valid between onCreateView and
    // onDestroyView.

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        favoriteViewModel= ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[FavoriteViewModel::class.java]
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        adapter= FavoriteAdapter(favoriteViewModel, MainActivity.units)

        binding.favFloatingAddButtonId.setOnClickListener{
            if (favoriteViewModel.getOnline(activity!!)) {
                val intent = Intent(activity, MapActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(requireActivity(),getString(R.string.you_are_offline), Toast.LENGTH_SHORT).show()
            }
        }

        favoriteViewModel.getFavDataBase().observe(this,{
            if (it.isNotEmpty()){
                binding.favRecyclerViewId.visibility=View.VISIBLE
                loadFavourite(it)
                dataList=it
            }else{
                binding.favRecyclerViewId.visibility=View.GONE
            }
        })

        favoriteViewModel.getIntent().observe(this,{
            val intent = Intent(activity, FavoriteDetailsActivity::class.java)
            intent.putExtra("lat", "${dataList[it].lat}")
            intent.putExtra("lon", "${dataList[it].lon}")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            startActivity(intent)
        })
        favoriteViewModel.getAlertDialogLiveData().observe(this,{
            if (it!=null){
                showAlarm(it.lat.toString(),it.lon.toString())
            }
        })



        return binding.root
    }

    private fun loadFavourite(it: List<FavData>) {
        val lay : RecyclerView.LayoutManager= LinearLayoutManager(activity)
        binding.favRecyclerViewId.layoutManager=lay
        adapter.models=it
        binding.favRecyclerViewId.adapter=adapter
    }

    private fun showAlarm(lat : String,lon: String) {
        val alertDialogBuilder = AlertDialog.Builder(activity!!)
        alertDialogBuilder.setTitle("Are you Sure")
        alertDialogBuilder.setMessage("you want to delete this city")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                favoriteViewModel.deleteOneFav(lat,lon)
            }
        }
        alertDialogBuilder.setNegativeButton("No") { _, _ ->

        }
        alertDialogBuilder.show()
    }


}