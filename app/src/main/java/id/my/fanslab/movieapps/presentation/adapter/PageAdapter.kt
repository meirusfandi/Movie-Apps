package id.my.fanslab.movieapps.presentation.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.my.fanslab.movieapps.presentation.fragment.MovieFragment
import id.my.fanslab.movieapps.presentation.fragment.TvShowFragment

class PageAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = MovieFragment()
            }
            1 -> {
                fragment = TvShowFragment()
            }
        }
        return fragment as Fragment
    }

}