
// https://school.programmers.co.kr/learn/courses/30/lessons/42888

data class Activity(
    val command: String,
    val userId: String,
    val nickname: String?,
)

class SolutionKakaoChatRoom {
    fun solution(record: Array<String>): Array<String> {
        val nicknameMap = hashMapOf<String, String>()
        return record
            .map {
                val activity = it.toActivity()
                when (activity.command) {
                    "Enter", "Change" -> nicknameMap[activity.userId] = activity.nickname ?: ""
                }
                activity
            }
            .asSequence()
            .filter { it.command != "Change" }
            .map {
                when (it.command) {
                    "Enter" -> "${nicknameMap[it.userId]}님이 들어왔습니다."
                    "Leave" -> "${nicknameMap[it.userId]}님이 나갔습니다."
                    else -> ""
                }
            }
            .toList()
            .toTypedArray()
    }

    private fun String.toActivity(): Activity {
        return this.split(" ").let {
            val command = it[0]
            val userId = it[1]
            val nickname = try {
                it[2]
            } catch (e: IndexOutOfBoundsException) {
                null
            }

            Activity(
                command = command,
                userId = userId,
                nickname = nickname,
            )
        }
    }
}