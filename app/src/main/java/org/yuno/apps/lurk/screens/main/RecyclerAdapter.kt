package org.yuno.apps.lurk.screens.main

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import org.yuno.apps.lurk.R
import org.yuno.apps.lurk.screens.main.model.Stream


class RecyclerAdapter(var photos: List<Stream>?) :
    RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>() {

    override fun getItemCount() = photos?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerAdapter.PhotoHolder, position: Int) {
        val itemPhoto = photos!![position]
        holder.bindPhoto(itemPhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val inflatedView = parent.inflate(R.layout.stream_list_entry, false)
        return PhotoHolder(inflatedView)
    }

    class PhotoHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        private var photo: Stream? = null

        //3
        init {
            v.setOnClickListener(this)
        }

        fun bindPhoto(photo: Stream) {
            this.photo = photo

            Picasso.get().load(photo.channel.logo).into(view.findViewById<ImageView>(R.id.imageView))
            Picasso.get().load(photo.preview.large).into(view.findViewById<ImageView>(R.id.preview))

            view.findViewById<TextView>(R.id.textView).text = "${photo.channel.display_name} (${photo.viewers})"
        }

        //4
        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("${photo?.channel?.url}"))
            view.context.startActivity(browserIntent)
        }

        companion object {
            //5
            private val PHOTO_KEY = "PHOTO"
        }
    }
}

private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
