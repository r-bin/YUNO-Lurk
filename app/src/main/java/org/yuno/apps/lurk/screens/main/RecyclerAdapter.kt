package org.yuno.apps.lurk.screens.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.yuno.apps.lurk.R
import org.yuno.apps.lurk.screens.main.model.Stream

class RecyclerAdapter(var photos: List<Stream>?) :
    RecyclerView.Adapter<RecyclerAdapter.StreamHolder>() {

    override fun getItemCount() = photos?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerAdapter.StreamHolder, position: Int) {
        val itemPhoto = photos!![position]
        holder.bindPhoto(itemPhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamHolder {
        val inflatedView = parent.inflate(R.layout.stream_list_entry, false)
        return StreamHolder(inflatedView)
    }

    class StreamHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var stream: Stream? = null

        init {
            v.setOnClickListener(this)
        }

        fun bindPhoto(stream: Stream) {
            this.stream = stream

            Picasso.get().load(stream.channel.logo).into(view.findViewById<ImageView>(R.id.imageView))
            Picasso.get().load(stream.preview.large).into(view.findViewById<ImageView>(R.id.preview))

            view.findViewById<TextView>(R.id.textView).text = "${stream.channel.display_name} (${stream.viewers})"
        }

        override fun onClick(v: View) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("${stream?.channel?.url}"))
            view.context.startActivity(browserIntent)
        }
    }
}

private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
