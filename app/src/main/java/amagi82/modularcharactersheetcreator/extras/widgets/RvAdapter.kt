package amagi82.modularcharactersheetcreator.extras.widgets

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.layoutInflater


class RvAdapter<T>(val ctx: Context, val set: MutableSet<T>, val layout: Int, val f: (v: View, pos: Int) -> Unit) : RecyclerView.Adapter<RvAdapter.Vh>() {

    fun add(item: T){
        if(set.contains(item)){
            update(item)
            return
        }
        set.add(item)
        notifyItemInserted(set.indexOf(item))
    }

    fun update(item: T){
        set.add(item)
        notifyItemChanged(set.indexOf(item))
    }

    fun remove(item: T){
        val index = set.indexOf(item)
        if(index == -1) return
        set.remove(item)
        notifyItemRemoved(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Vh(ctx.layoutInflater.inflate(layout, parent))

    override fun onBindViewHolder(holder: Vh, pos: Int) = f(holder.itemView, pos)

    override fun getItemCount() = set.size

    class Vh(v: View) : RecyclerView.ViewHolder(v)
}

fun RecyclerView.simpleAdapter(set: MutableSet<Any>, layout: Int, f: (v: View, pos: Int) -> Unit) {
    adapter = RvAdapter(context, set, layout, f)
}