package amagi82.modularcharactersheetcreator.ui._base

import amagi82.modularcharactersheetcreator.extras.utils.Otto
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

abstract class BaseActivity: RxAppCompatActivity() {

    override fun onStart() {
        super.onStart()
        Otto.BUS.get().register(this)
    }

    override fun onStop() {
        super.onStop()
        Otto.BUS.get().unregister(this)
    }
}
