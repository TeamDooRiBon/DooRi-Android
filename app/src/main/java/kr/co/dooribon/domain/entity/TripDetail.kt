package kr.co.dooribon.domain.entity

sealed class TripDetail{
    data class TripDetailParentItem(
        val questionNumber : Int,
        val question : String
    ) : TripDetail() {

    }

    data class TripDetailChildItem(
        val questionNumber : Int,
        val question : String,
        val resultMemberNumber : Int
    ) : TripDetail() {

    }

    companion object {
        const val PARENT_ITEM = 0
        const val CHILD_ITEM = 1
    }
}