
// https://school.programmers.co.kr/learn/courses/30/lessons/340213

class SolutionVideoPlayer {
    fun solution(video_len: String, pos: String, op_start: String, op_end: String, commands: Array<String>): String {
        val videoLengthSec = video_len.toSeconds()
        val posSec = pos.toSeconds()
        val opStartSec = op_start.toSeconds()
        val opEndSec = op_end.toSeconds()

        var currentPosSec = posSec

        commands.forEach { command ->
            currentPosSec = runCommand(
                command = command,
                posSec = currentPosSec,
                videoLength = videoLengthSec,
                opStart = opStartSec,
                opEnd = opEndSec,
            )
        }

        return currentPosSec.toTime()
    }

    private fun String.toSeconds(): Int {
        val (min, sec) = this.split(":").let {
            it.first().toInt() to it.last().toInt()
        }
        return min * 60 + sec
    }

    private fun Int.toTime(): String {
        val min = this / 60
        val second = this % 60

        return min.toString().padStart(2, '0') + ":" + second.toString().padStart(2, '0')
    }

    private fun runCommand(command: String, posSec: Int, videoLength: Int, opStart: Int, opEnd: Int): Int {
        var newPosSec = if (posSec in opStart..opEnd) opEnd else posSec

        newPosSec += when (command) {
            "next" -> 10
            "prev" -> -10
            else -> posSec
        }

        return when {
            newPosSec < 0 -> 0
            newPosSec > videoLength -> videoLength
            newPosSec in opStart..opEnd -> opEnd
            else -> newPosSec
        }
    }
}
