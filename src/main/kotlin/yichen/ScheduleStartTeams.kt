package yichen

import java.time.LocalDateTime
import java.time.LocalTime
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

import com.sun.jna.Library
import com.sun.jna.Native
import java.awt.Robot
import java.awt.MouseInfo
import java.util.Timer
import java.util.TimerTask

interface Kernel32 : Library {
    fun SetThreadExecutionState(esFlags: Int): Int
}

val kernel32 = Native.load("kernel32", Kernel32::class.java)

// 防止系统休眠、显示器关闭、系统空闲状态
fun preventSleep() {
    val ES_CONTINUOUS = 0x80000000.toInt()
    val ES_SYSTEM_REQUIRED = 0x00000001
    val ES_DISPLAY_REQUIRED = 0x00000002

    val flags = ES_CONTINUOUS or ES_SYSTEM_REQUIRED or ES_DISPLAY_REQUIRED
    val result = kernel32.SetThreadExecutionState(flags)

    if (result == 0) {
        println("⚠ 无法设置系统防息屏状态")
    } else {
        println("✅ 系统防息屏模式已启用")
    }
}


fun main() {
    preventSleep()

    val scheduler = Executors.newScheduledThreadPool(2)
    val exitLatch = CountDownLatch(1) // 等待全部任务完成后退出

    val now = LocalDateTime.now()
    var next10am = now.withHour(10).withMinute(0).withSecond(0).withNano(0)
    if (now.isAfter(next10am)) {
        next10am = next10am.plusDays(1)
    }

    keepMouseMovingEvery10Seconds()

// ⏰ 正确的 10 点启动延迟
    val delaySeconds = java.time.Duration.between(now, next10am).seconds
    println("计划启动 Teams 时间：$next10am（距离现在 ${delaySeconds} 秒）")

    scheduler.schedule({
        val started = startTeams()
        if (started) {
            println("✅ Teams 启动成功，当前时间：${LocalTime.now()}")
        } else {
            println("❌ 无法启动 Teams，当前时间：${LocalTime.now()}")
        }

        // 延迟 5 秒执行测试任务
        scheduler.schedule({
            println("✅ 测试任务执行成功，当前时间：${LocalTime.now()}")
            exitLatch.countDown()  // 通知主线程任务已完成
        }, 3, TimeUnit.SECONDS)

    }, delaySeconds, TimeUnit.SECONDS)

    println("调度器已启动，等待任务...")
    exitLatch.await() // 阻塞主线程，直到测试任务完成
}

fun startTeams(): Boolean {
    try {
        // 1. 启动 Microsoft Teams（Store App）
        Runtime.getRuntime().exec("cmd /c start shell:AppsFolder\\MSTeams_8wekyb3d8bbwe!MSTeams")
        println("已启动 Teams 应用")

        // 2. 等待 Teams 启动
//        Thread.sleep(5000)

        // 3. 激活 Teams 窗口（使用 NirCmd 工具）
//        val nircmdPath = "E:\\CapitalEugene\\x64tools\\nircmd.exe"
//        val activateCmd = arrayOf(nircmdPath, "win", "activate", "title", "Microsoft Teams")
//        Runtime.getRuntime().exec(activateCmd)
//        println("✅ 已尝试通过 NirCmd 激活 Teams 窗口")
        return true

    } catch (e: Exception) {
        println("❌ 启动或激活 Teams 失败")
        e.printStackTrace()
        return false
    }
}



fun keepMouseMovingEvery10Seconds() {
    val robot = Robot()
    val timer = Timer()
    println("✅ 已启用缓慢鼠标移动防息屏")

    timer.scheduleAtFixedRate(object : TimerTask() {
        var toggle = true
        override fun run() {
            try {
                val location = MouseInfo.getPointerInfo().location
                val x = location.x
                val y = location.y
                val dx = if (toggle) 200 else -200
                toggle = !toggle

                // 缓慢移动过去
                smoothMoveMouse(robot, x, y, x + dx, y, durationMs = 600)
                // 缓慢移动回来
                smoothMoveMouse(robot, x + dx, y, x, y, durationMs = 600)

                println("🖱️ 模拟鼠标缓慢移动一次 at (${x + dx}, $y)")
            } catch (e: Exception) {
                println("⚠️ 鼠标移动失败: ${e.message}")
            }
        }
    }, 0, 10_000)
}


fun smoothMoveMouse(robot: Robot, fromX: Int, fromY: Int, toX: Int, toY: Int, durationMs: Long = 500) {
    val steps = 50
    val sleepTime = durationMs / steps
    for (i in 1..steps) {
        val t = i / steps.toDouble()
        val x = (fromX + (toX - fromX) * t).toInt()
        val y = (fromY + (toY - fromY) * t).toInt()
        robot.mouseMove(x, y)
        Thread.sleep(sleepTime)
    }
}
