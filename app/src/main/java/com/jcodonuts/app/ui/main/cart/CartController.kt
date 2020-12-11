package com.jcodonuts.app.ui.main.cart

import android.content.Context
import android.view.View
import android.widget.Toast
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.stickyheader.StickyHeaderCallbacks
import com.jcodonuts.app.data.local.Divider16
import com.jcodonuts.app.itemCart
import com.jcodonuts.app.utils.BindingClick

/**
 * Showcases [EpoxyController] with sticky header support
 */
class CartController(
    private val listeners: ControllerListener
) : TypedEpoxyController<List<Divider16>>(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
), StickyHeaderCallbacks {

    override fun buildModels(data: List<Divider16>?) {
        data?.forEachIndexed() { index, cellData ->
            itemCart {
                id("view holder $index")
                data(cellData)
                index(index)
                listener(listeners)
//                click(View.OnClickListener {
//                    listeners.onClick(cellData)
//                })
            }
        }


//        for (i in 0 until 100) {
//            when {
//                i % 5 == 0 -> stickyItemEpoxyHolder {
//                    id("sticky-header $i")
//                    title("Sticky header $i")
//                    listener {
//                        Toast.makeText(context, "clicked", Toast.LENGTH_LONG).show()
//                    }
//                }
//                else -> itemEpoxyHolder {
//                    id("view holder $i")
//                    title("this is a View Holder item")
//                    listener {
//                        Toast.makeText(context, "clicked", Toast.LENGTH_LONG)
//                            .show()
//                    }
//                }
//            }
//        }
    }

}
