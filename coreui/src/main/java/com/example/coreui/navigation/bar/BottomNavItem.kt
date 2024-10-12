package com.example.coreui.navigation.bar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.coreui.R
import kotlinx.serialization.Serializable


sealed class BottomNavItem {
    abstract val route: String
    @get:DrawableRes
    abstract val iconResourceId: Int
    @get:StringRes
    abstract val label: Int

    companion object {
        fun getObjectInstances(): List<BottomNavItem> {
            return BottomNavItem::class.sealedSubclasses.mapNotNull {
                it.objectInstance as BottomNavItem
            }
        }
    }

    @Serializable data object Search : BottomNavItem() {
        override val route: String = Search::class.qualifiedName.toString()
        override val iconResourceId: Int = R.drawable.ic_search
        override val label: Int = R.string.label_search
    }

    @Serializable data object Favorite : BottomNavItem() {
        override val route: String = Favorite::class.qualifiedName.toString()
        override val iconResourceId: Int = R.drawable.ic_like
        override val label: Int = R.string.label_favorite
    }

    @Serializable data object Responds : BottomNavItem() {
        override val route: String = Responds::class.qualifiedName.toString()
        override val iconResourceId: Int = R.drawable.ic_responds
        override val label: Int = R.string.label_responds
    }

    @Serializable data object Messages : BottomNavItem() {
        override val route: String = Messages::class.qualifiedName.toString()
        override val iconResourceId: Int = R.drawable.ic_messages
        override val label: Int = R.string.label_messages
    }

    @Serializable data object Profile : BottomNavItem() {
        override val route: String = Profile::class.qualifiedName.toString()
        override val iconResourceId: Int = R.drawable.ic_profile
        override val label: Int = R.string.label_profile
    }
}