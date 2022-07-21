package android.devhubservice.utils

import android.raywenderlich.devhubservice.R
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class FragmentActions(
    val view: View,
    val fragment: Fragment
) {

    fun redirect(){
        val activity = view.context as AppCompatActivity
        activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view_tag, fragment)
            .commitNow()
    }

}