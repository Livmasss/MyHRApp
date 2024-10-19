package com.example.coreui.navigation.bar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.coreui.R
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavDest {
    abstract val route: String
    @get:DrawableRes
    abstract val iconResourceId: Int
    @get:StringRes
    abstract val label: Int
    protected abstract val navbarPosition: Int

    companion object {
        fun getObjectInstances(): List<BottomNavDest> {
            val subclasses = BottomNavDest::class.sealedSubclasses
            return subclasses.mapNotNull {
                it.objectInstance
            }.sortedBy { it.navbarPosition }
        }
    }

    @Serializable data object Search : BottomNavDest() {
        override val route: String = Search::class.qualifiedName.toString()
        override val iconResourceId: Int = R.drawable.ic_search_outline
        override val label: Int = R.string.label_search
        override val navbarPosition: Int = 0
    }

    @Serializable data object Favorite : BottomNavDest() {
        override val route: String = Favorite::class.qualifiedName.toString()
        override val iconResourceId: Int = R.drawable.ic_like_outline
        override val label: Int = R.string.label_favorite
        override val navbarPosition: Int = 1
    }

    @Serializable data object Responds : BottomNavDest() {
        override val route: String = Responds::class.qualifiedName.toString()
        override val iconResourceId: Int = R.drawable.ic_responds_outline
        override val label: Int = R.string.label_responds
        override val navbarPosition: Int = 2
    }

    @Serializable data object Messages : BottomNavDest() {
        override val route: String = Messages::class.qualifiedName.toString()
        override val iconResourceId: Int = R.drawable.ic_messages_outline
        override val label: Int = R.string.label_messages
        override val navbarPosition: Int = 3
    }

    @Serializable data object Profile : BottomNavDest() {
        override val route: String = Profile::class.qualifiedName.toString()
        override val iconResourceId: Int = R.drawable.ic_profile_outline
        override val label: Int = R.string.label_profile
        override val navbarPosition: Int = 4
    }
}