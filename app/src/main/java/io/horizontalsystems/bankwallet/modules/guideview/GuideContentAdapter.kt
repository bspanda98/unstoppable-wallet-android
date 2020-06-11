package io.horizontalsystems.bankwallet.modules.guideview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.horizontalsystems.bankwallet.R
import io.horizontalsystems.views.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_guide_block.*

class GuideContentAdapter : ListAdapter<GuideBlock, GuideContentAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.view_holder_guide_block))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<GuideBlock>() {
            override fun areItemsTheSame(oldItem: GuideBlock, newItem: GuideBlock): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GuideBlock, newItem: GuideBlock): Boolean {
                return oldItem == newItem
            }

        }
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: GuideBlock) {
            when (item) {
                is GuideBlock.Heading1 -> {
                    h1.text = item.text
                }
                is GuideBlock.Heading2 -> {
                    h2.text = item.text
                }
                is GuideBlock.Paragraph -> {
                    paragraph.text = item.text
                }
            }
        }

    }


}
