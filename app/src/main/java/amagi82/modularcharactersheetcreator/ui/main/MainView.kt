package amagi82.modularcharactersheetcreator.ui.main

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.extras.circleImageView
import amagi82.modularcharactersheetcreator.extras.lparams
import amagi82.modularcharactersheetcreator.extras.tintResource
import amagi82.modularcharactersheetcreator.models.GameCharacter
import android.support.design.widget.AppBarLayout
import android.support.v7.util.SortedList
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.util.SortedListAdapterCallback
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainView: AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui){
        coordinatorLayout {
            fitsSystemWindows = true

            appBarLayout {

                toolbar {

                    popupTheme = R.style.ThemeOverlay_AppCompat_Light

                }.lparams(width = matchParent)  {
                    val tv = TypedValue()
                    height = if(ui.owner.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics) else dip(56)
                }

            }.lparams(width = matchParent)

            recyclerView {
                layoutManager = LinearLayoutManager(ctx)
                //adapter = adapt
//                app:itemView = @{mainViewModel.itemView}
//                app:items = @{mainViewModel.list}
            }.lparams(width = matchParent, height = matchParent){ behavior = AppBarLayout.ScrollingViewBehavior() }

            floatingActionButton {
                onClick{}
                imageResource = R.drawable.ic_person_add_24dp
                tintResource = R.color.white
                visibility = View.INVISIBLE
            }.lparams(width = matchParent){
                margin = ctx.dimen(R.dimen.fab_margin)
                gravity = Gravity.BOTTOM or Gravity.RIGHT
            }
        }
    }

    //val character: GameCharacter

    class Adapter: RecyclerView.Adapter<Holder>() {
        val characters= SortedList<GameCharacter>(GameCharacter::class.java, object : SortedListAdapterCallback<GameCharacter>(this@Adapter) {
            override fun areContentsTheSame(oldItem: GameCharacter, newItem: GameCharacter) = oldItem == newItem
            override fun areItemsTheSame(item1: GameCharacter, item2: GameCharacter) = item1.entityId == item2.entityId
            override fun compare(o1: GameCharacter, o2: GameCharacter) = o1.timestamp.compareTo(o2.timestamp)
        })

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(RelativeLayout(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(matchParent, dip(72))
            onClick {  }

            circleImageView{
                id = 1


            }.lparams(width = dip(40), height = dip(40)){

            }

            textView{

            }.lparams {
            }
        })

        override fun onBindViewHolder(holder: Holder, position: Int) {

        }

        override fun getItemCount() = characters.size()


    }

    class Holder(v: View): RecyclerView.ViewHolder(v)
}
