package com.example.myapplication.utility

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R
import com.example.myapplication.presentation.view.fragments.ArticleListFragment
import java.time.Instant
import java.time.ZoneId

object CommonUtility {

    fun switchFragment(fragmentManager: FragmentManager,fragment: Fragment,addToBackStackRequired:Boolean=false){
        if (addToBackStackRequired){
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }else{
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }
    }

    fun popFragment(fragmentManager: FragmentManager){
        fragmentManager.popBackStack()
    }

    fun dateFormat(input:String?):String?{
        try {
            val instant = Instant.parse(input)
            val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
            return date.toString()
        }catch (exception:Exception){
            return input
        }


    }

    fun Int.dpToPx(context: Context): Int =
        (this * context.resources.displayMetrics.density).toInt()
}