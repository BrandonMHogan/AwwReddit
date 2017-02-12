package com.brandonhogan.kotlintest

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.brandonhogan.kotlintest.commons.BusStation
import com.brandonhogan.kotlintest.commons.RedditAwwItem
import com.brandonhogan.kotlintest.features.aww.AwwItemDetailFragment
import com.brandonhogan.kotlintest.features.aww.AwwListFragment
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            changeFragment(AwwListFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        BusStation.bus.register(this)
    }

    override fun onPause() {
        super.onPause()
        BusStation.bus.unregister(this)
    }

    fun setActionBarTitle(title : String) {
        toolbar.title = title
    }

    fun changeFragment(f: Fragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }

        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.addToBackStack(f.tag)

        if (fragmentManager.findFragmentByTag(f.tag) != null) {
            ft.show(f)
        }
        else {
            ft.add(R.id.activity_base_content, f, f.tag)
        }


        ft.commit()
    }

    fun clearBackStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    /**
     * Finish activity when reaching the last fragment.
     */
    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            setActionBarTitle(getString(R.string.app_name))
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    @Subscribe
    fun getRedditAwwItem(awwItem : RedditAwwItem) {
        changeFragment(AwwItemDetailFragment.newInstance(awwItem))
    }
}
