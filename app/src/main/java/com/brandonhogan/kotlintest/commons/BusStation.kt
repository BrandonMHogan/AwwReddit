package com.brandonhogan.kotlintest.commons

import com.squareup.otto.Bus

/**
 * Created by Brandon on 2/5/2017.
 * Description :
 */

class BusStation {
    companion object {
        @JvmStatic var bus : Bus = Bus(com.squareup.otto.ThreadEnforcer.MAIN)
    }
}
