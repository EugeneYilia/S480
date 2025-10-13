import kotlin.math.abs

fun calcVariance(nums: IntArray): Double {
    var n = 0
    var mean = 0.0
    var variance = 0.0

    for (num in nums){
        n+=1
        val delta = num - mean
        mean += delta / n
        variance += delta * (num - mean)
    }

    return variance
}

fun main() {
    // 定义一个浮点比较误差容忍度
    val eps = 1e-9

    // 1️⃣ 基础测试
    assert(abs(calcVariance(intArrayOf(1, 2, 3)) - 1.0) < eps)  // 方差 = 1
    assert(abs(calcVariance(intArrayOf(4, 6, 8)) - 4.0) < eps)  // 方差 = 4

    // 2️⃣ 所有数相等（方差应为 0）
    assert(abs(calcVariance(intArrayOf(5, 5, 5, 5)) - 0.0) < eps)

    // 3️⃣ 只有一个元素（定义上方差为 0）
    assert(abs(calcVariance(intArrayOf(42)) - 0.0) < eps)

    // 4️⃣ 含负数的情况
    // 均值 = -1.0, 方差 = ((-3+1)^2 + (-1+1)^2 + (1+1)^2)/2 = (4+0+4)/2 = 4
    assert(abs(calcVariance(intArrayOf(-3, -1, 1)) - 4.0) < eps)

    // 5️⃣ 含大数
    assert(abs(calcVariance(intArrayOf(1000000, 1000001, 999999)) - 1.0) < eps)

    // 6️⃣ 方差非整数的情况
    // nums = [1,2,3,4]; 均值=2.5, 方差=((1.5)^2+(0.5)^2+(0.5)^2+(1.5)^2)/3 = 1.666...
    assert(abs(calcVariance(intArrayOf(1, 2, 3, 4)) - 1.6666666667) < 1e-6)

    // 7️⃣ 空数组（返回0）
    assert(abs(calcVariance(intArrayOf()) - 0.0) < eps)

    // 8️⃣ 含重复值但非全相等
    // nums = [2, 2, 3, 3]; 均值=2.5, 方差=((0.5)^2+(0.5)^2+(0.5)^2+(0.5)^2)/3=0.3333
    assert(abs(calcVariance(intArrayOf(2, 2, 3, 3)) - 0.3333333333) < 1e-6)

    // 9️⃣ 大数组稳定性测试（不崩溃）
    val largeArray = IntArray(1_000_000) { it % 10 }
    val varLarge = calcVariance(largeArray)
    assert(varLarge >= 0)

    println("✅ 所有测试通过！")
}