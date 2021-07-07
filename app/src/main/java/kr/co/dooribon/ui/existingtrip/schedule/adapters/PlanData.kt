package kr.co.dooribon.ui.existingtrip.schedule.adapters

data class PlanData(
    val time: String,
    val mainTodo: String,
    val subTodo: String,
    val viewType: Int
) {
    companion object {
        const val FIRST_DATE_PLAN = 0
        const val MIDDLE_DATE_PLAN = 1
        const val LAST_DATE_PLAN = 2
    }
}

