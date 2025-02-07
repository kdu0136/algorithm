// https://school.programmers.co.kr/learn/courses/30/lessons/176962

data class Plan(
    val name: String,
    val startTime: Int, // 시:분을 분으로 계산
    var playtime: Int,
)

class SolutionAssignmentPlan {
    fun solution(plans: Array<Array<String>>): Array<String> {
        // step1. 시간순으로 과제 정렬
        val sortedPlans = plans
            .map { plan ->
                Plan(
                    name = plan[0],
                    startTime = timeToMinutes(time = plan[1]),
                    playtime = plan[2].toInt(),
                )
            }
            .sortedBy { it.startTime }

        val stopPlans = ArrayList<Plan>()
        val finishPlans = ArrayList<String>()
        for (i in sortedPlans.indices) {
            val plan = sortedPlans[i]
            plan.playtime = if (i == sortedPlans.size - 1) { // step5. 마지막 과제 진행
                0
            } else { // step2. 과제를 하나씩 다음 과제 시작 전까지 진행
                plan.playtime - (sortedPlans[i+1].startTime - plan.startTime)
            }

            // step3-2. 과제를 끝내면 완료 과제로 저장
            if (plan.playtime <= 0) {
                finishPlans.add(plan.name)
            }

            // step3-1. 현재 과제가 끝나지 않았으면 중단 과제로 따로 저장한다.
            if (plan.playtime > 0) {
                stopPlans.add(0, plan)
            } else if (plan.playtime < 0) {
                // step3-2. 과제를 끝내고 시간이 남았으므로 가장 최근 중단 과제를 순서대로 진행
                var remainTime = plan.playtime * -1
                for (stopPlan in stopPlans) {
                    stopPlan.playtime -= remainTime
                    remainTime = 0
                    // 중단 과제가 끝났으면 완료 과제로 저장
                    if (stopPlan.playtime <= 0) {
                        finishPlans.add(stopPlan.name)
                        remainTime += stopPlan.playtime * -1
                    }
                    if (remainTime == 0) {
                        plan.playtime = 0
                        break
                    }
                }
                stopPlans.removeIf { it.playtime <= 0 }
            }
        }

        // 가장 최근 중단 과제부터 하나씩 완료
        for (stopPlan in stopPlans) {
            finishPlans.add(stopPlan.name)
        }

        return finishPlans.toTypedArray()
    }

    private fun timeToMinutes(time: String): Int {
        return time.split(":").let {
            it.first().toInt() * 60 + it.last().toInt()
        }
    }
}