package yichen

fun main(){
//    println(MaximumCount().maximumCount.getMaximumCount(arrayOf(1,1,1,0,0,0,1,1,1,1,0), 2))
//    println(MaximumCount().maximumCount.getMaximumCount2(arrayOf(1,1,1,0,0,0,1,1,1,1,0), 2))

    val maximumCount = MaximumCount()
    
    val nums0 = arrayOf(-7, -2, 2, -6, -1, 7, -10, 3, -9, 7, 3, 0, 10, -1, 9, 3, 10, -6, 1, 9, 6, 2, -6, 3, -4, 10, 2)
    val k0 = 9
    val res0_1 = maximumCount.getMaximumCount(nums0, k0)
    val res0_2 = maximumCount.getMaximumCount2(nums0, k0)
    println("Test case 0: match = ${res0_1 == res0_2}, result1 = ${res0_1}, result2 = ${res0_2}")

    val nums1 = arrayOf(4, -3, -2, 1, -9, 7, -3, -3, 10, -10)
    val k1 = 7
    val res1_1 = maximumCount.getMaximumCount(nums1, k1)
    val res1_2 = maximumCount.getMaximumCount2(nums1, k1)
    println("Test case 1: match = ${res1_1 == res1_2}, result1 = ${res1_1}, result2 = ${res1_2}")

    val nums2 = arrayOf(1, 6, 5, 7, -8, -5, -6, 2, 6, 6)
    val k2 = -5
    val res2_1 = maximumCount.getMaximumCount(nums2, k2)
    val res2_2 = maximumCount.getMaximumCount2(nums2, k2)
    println("Test case 2: match = ${res2_1 == res2_2}, result1 = ${res2_1}, result2 = ${res2_2}")

    val nums3 = arrayOf(2, -4, -8, 2, 7, 7, 6, -1, 10, 0, 0, 9, 1, -7, -9, -8, -9, 7, 5)
    val k3 = 5
    val res3_1 = maximumCount.getMaximumCount(nums3, k3)
    val res3_2 = maximumCount.getMaximumCount2(nums3, k3)
    println("Test case 3: match = ${res3_1 == res3_2}, result1 = ${res3_1}, result2 = ${res3_2}")

    val nums4 = arrayOf(-9, 1, -7, 3, 10, -8, 5, 5, -8, -1, 10, 9, -9, 10, 7, 8, 8, -6, 4, -6, 4, 9, 9, -10, 9, 7, -9, -5, -7, 2, -7, 8)
    val k4 = 10
    val res4_1 = maximumCount.getMaximumCount(nums4, k4)
    val res4_2 = maximumCount.getMaximumCount2(nums4, k4)
    println("Test case 4: match = ${res4_1 == res4_2}, result1 = ${res4_1}, result2 = ${res4_2}")

    val nums5 = arrayOf(4, -3, -10, -3, -7, -10, -1, 6, -4, -10, -3, -7, 10, 4, 0, -6, 0, -4, 3, 2, 10, 4, -9, -2, 8, 9, 6, -5, -9, 0, 7, 1, -7, -8, -4, 2, -9, 4, 10, 3, 8, -9, 9, 9, -5, 5, -6, -7, 6)
    val k5 = 2
    val res5_1 = maximumCount.getMaximumCount(nums5, k5)
    val res5_2 = maximumCount.getMaximumCount2(nums5, k5)
    println("Test case 5: match = ${res5_1 == res5_2}, result1 = ${res5_1}, result2 = ${res5_2}")

    val nums6 = arrayOf(5, -6, -9, 0, 10, -5, 6, -8, -2, -7, 4, -6, 7, -5, 6, -5, 10, -6, -6, -4, -6, -9, 9, -1, -3, -8, 1, -7, -5, -4, -8, 0, 7, -9, 4, 10, 0, -2, 2, -1)
    val k6 = -6
    val res6_1 = maximumCount.getMaximumCount(nums6, k6)
    val res6_2 = maximumCount.getMaximumCount2(nums6, k6)
    println("Test case 6: match = ${res6_1 == res6_2}, result1 = ${res6_1}, result2 = ${res6_2}")

    val nums7 = arrayOf(-9, 9, 5, 9, 2, -10, 5, 0, 7, -9, 3, -9, 8, -1, -2, 9, 6, 3, 9, -2, 5, 10, -3, 3, 4, -2)
    val k7 = 9
    val res7_1 = maximumCount.getMaximumCount(nums7, k7)
    val res7_2 = maximumCount.getMaximumCount2(nums7, k7)
    println("Test case 7: match = ${res7_1 == res7_2}, result1 = ${res7_1}, result2 = ${res7_2}")

    val nums8 = arrayOf(6, 1, 9, 2, -10, -5, -6, 7, 3, 5, -8, -1, 3, -1, 1, 1, -9, 3, 7, -10, 9, 2, 8, 0, -9, 7, 3, 10, -9, 2, 6, 9, -9, 4)
    val k8 = 5
    val res8_1 = maximumCount.getMaximumCount(nums8, k8)
    val res8_2 = maximumCount.getMaximumCount2(nums8, k8)
    println("Test case 8: match = ${res8_1 == res8_2}, result1 = ${res8_1}, result2 = ${res8_2}")

    val nums9 = arrayOf(-2, -2, -9, 3, -2, 10, 5, -8, -1, -4, 2, -5, -4, -2, -1, -2, -4, -7, -5, 2, 7, -9, 5, -8, 3, 10, 6, -8, -2, 6, -5, 6, 3, 6, 7, 5, 9, 2, -5, 0, 3, 5, 0, 0, 8, -5)
    val k9 = 7
    val res9_1 = maximumCount.getMaximumCount(nums9, k9)
    val res9_2 = maximumCount.getMaximumCount2(nums9, k9)
    println("Test case 9: match = ${res9_1 == res9_2}, result1 = ${res9_1}, result2 = ${res9_2}")

    val nums10 = arrayOf(-4, 1, 7, -5, -8)
    val k10 = -4
    val res10_1 = maximumCount.getMaximumCount(nums10, k10)
    val res10_2 = maximumCount.getMaximumCount2(nums10, k10)
    println("Test case 10: match = ${res10_1 == res10_2}, result1 = ${res10_1}, result2 = ${res10_2}")

    val nums11 = arrayOf(-9, 4, 10, 1, -3, 8, -7, -2, -10, -2, 6, -7, 7, 6, 8, -8, 7, 2, -1, -9, 0, 2)
    val k11 = 0
    val res11_1 = maximumCount.getMaximumCount(nums11, k11)
    val res11_2 = maximumCount.getMaximumCount2(nums11, k11)
    println("Test case 11: match = ${res11_1 == res11_2}, result1 = ${res11_1}, result2 = ${res11_2}")

    val nums12 = arrayOf(8, -5, -6, 5, 1, -5, 6, -9)
    val k12 = 5
    val res12_1 = maximumCount.getMaximumCount(nums12, k12)
    val res12_2 = maximumCount.getMaximumCount2(nums12, k12)
    println("Test case 12: match = ${res12_1 == res12_2}, result1 = ${res12_1}, result2 = ${res12_2}")

    val nums13 = arrayOf(0, 2, 6, -9, 7, 5, -1, 8, -10, 2, 7, 4, 5, -5, 1, 4, 3, 9, 5, 2, 5, 9, 4, -8, 1, 8, -3, -7, 7)
    val k13 = -9
    val res13_1 = maximumCount.getMaximumCount(nums13, k13)
    val res13_2 = maximumCount.getMaximumCount2(nums13, k13)
    println("Test case 13: match = ${res13_1 == res13_2}, result1 = ${res13_1}, result2 = ${res13_2}")

    val nums14 = arrayOf(10, -7, 2, -6, 5, 5, -10, -1, 9, 2, -2, -2, -7, 3, -4, -3, 1, -9, -3, -9, -1, 6, 7, -9, -4, 7, 1, 5, 6, 2, 5, -10, -10, 0, -3, -9, 4)
    val k14 = 0
    val res14_1 = maximumCount.getMaximumCount(nums14, k14)
    val res14_2 = maximumCount.getMaximumCount2(nums14, k14)
    println("Test case 14: match = ${res14_1 == res14_2}, result1 = ${res14_1}, result2 = ${res14_2}")

    val nums15 = arrayOf(6, 1, 4, 2, 6, 7, 9, -4, 8, -3, 8, -4, -9, -3, 9, 0, 2, 2, 8, 4)
    val k15 = -3
    val res15_1 = maximumCount.getMaximumCount(nums15, k15)
    val res15_2 = maximumCount.getMaximumCount2(nums15, k15)
    println("Test case 15: match = ${res15_1 == res15_2}, result1 = ${res15_1}, result2 = ${res15_2}")

    val nums16 = arrayOf(3, -6, 5, 6, -9, 9)
    val k16 = -6
    val res16_1 = maximumCount.getMaximumCount(nums16, k16)
    val res16_2 = maximumCount.getMaximumCount2(nums16, k16)
    println("Test case 16: match = ${res16_1 == res16_2}, result1 = ${res16_1}, result2 = ${res16_2}")

    val nums17 = arrayOf(7, -8, 6, 7, -1, 1, -1, 0, -5, -5, 7, 0, 7, 6, -9)
    val k17 = 1
    val res17_1 = maximumCount.getMaximumCount(nums17, k17)
    val res17_2 = maximumCount.getMaximumCount2(nums17, k17)
    println("Test case 17: match = ${res17_1 == res17_2}, result1 = ${res17_1}, result2 = ${res17_2}")

    val nums18 = arrayOf(-6, 3, 1, 1, 0, -3, -8)
    val k18 = 0
    val res18_1 = maximumCount.getMaximumCount(nums18, k18)
    val res18_2 = maximumCount.getMaximumCount2(nums18, k18)
    println("Test case 18: match = ${res18_1 == res18_2}, result1 = ${res18_1}, result2 = ${res18_2}")

    val nums19 = arrayOf(6, -6, 7, -5, -6)
    val k19 = 6
    val res19_1 = maximumCount.getMaximumCount(nums19, k19)
    val res19_2 = maximumCount.getMaximumCount2(nums19, k19)
    println("Test case 19: match = ${res19_1 == res19_2}, result1 = ${res19_1}, result2 = ${res19_2}")

    val nums20 = arrayOf(2, 10, -7, -2, 3, 9, -9, 1, -4, -1, -7, 2, -3, -7, 6, 8, -5, 8, -8, -3, -10, 10, 8, -10, -7, -4, 5, -5, -8, -3, 5, 8, -1, 9, 4, -7, 10, -9, -6, 5, -10, -6, -8, 5, -4)
    val k20 = 8
    val res20_1 = maximumCount.getMaximumCount(nums20, k20)
    val res20_2 = maximumCount.getMaximumCount2(nums20, k20)
    println("Test case 20: match = ${res20_1 == res20_2}, result1 = ${res20_1}, result2 = ${res20_2}")

    val nums21 = arrayOf(0, 0, -1, -6, 4, -5, 5, 9, -6, -4, 2, 1, -4, 3, 7, -6, -5)
    val k21 = 1
    val res21_1 = maximumCount.getMaximumCount(nums21, k21)
    val res21_2 = maximumCount.getMaximumCount2(nums21, k21)
    println("Test case 21: match = ${res21_1 == res21_2}, result1 = ${res21_1}, result2 = ${res21_2}")

    val nums22 = arrayOf(-2, 5, 5, -1, -4)
    val k22 = -2
    val res22_1 = maximumCount.getMaximumCount(nums22, k22)
    val res22_2 = maximumCount.getMaximumCount2(nums22, k22)
    println("Test case 22: match = ${res22_1 == res22_2}, result1 = ${res22_1}, result2 = ${res22_2}")

    val nums23 = arrayOf(6, -6, 2, 1, 7, -6, 0, 0, -6, -9, 7, 10, -6, -2, 2, -4, -4, -3, 4, 10, -9, 5, -10, 0, 6, 6, -6, -7)
    val k23 = -3
    val res23_1 = maximumCount.getMaximumCount(nums23, k23)
    val res23_2 = maximumCount.getMaximumCount2(nums23, k23)
    println("Test case 23: match = ${res23_1 == res23_2}, result1 = ${res23_1}, result2 = ${res23_2}")

    val nums24 = arrayOf(10, -1, 8, 9, 0, -10, 8, 6, 3, -6, 1, -8, -8, 10, -5, -10, -10, 6, -1, -10, 3, -6, -2, 3, 8, -2, -6, -9, -7, -5)
    val k24 = -7
    val res24_1 = maximumCount.getMaximumCount(nums24, k24)
    val res24_2 = maximumCount.getMaximumCount2(nums24, k24)
    println("Test case 24: match = ${res24_1 == res24_2}, result1 = ${res24_1}, result2 = ${res24_2}")

    val nums25 = arrayOf(3, 8, 5, 9, 7, 4, 8, 1, -6, -4, 10, -7, -8, 7, -9, -1, 3, 6, 7, -7, 2, -10, 7, 3, -6, 4, -2, -3, 10, -5, -6, 5)
    val k25 = -6
    val res25_1 = maximumCount.getMaximumCount(nums25, k25)
    val res25_2 = maximumCount.getMaximumCount2(nums25, k25)
    println("Test case 25: match = ${res25_1 == res25_2}, result1 = ${res25_1}, result2 = ${res25_2}")

    val nums26 = arrayOf(0, 4, 3, 5, 10, 4, -5, 5, -5, 6, -4, 6, 0, -9, -2, 1, 2, 4, 8, 9, 4, -6, 3, -3, -10, -3, 7, -6, -1, 1, 0, 2, -2, -5, -4)
    val k26 = 10
    val res26_1 = maximumCount.getMaximumCount(nums26, k26)
    val res26_2 = maximumCount.getMaximumCount2(nums26, k26)
    println("Test case 26: match = ${res26_1 == res26_2}, result1 = ${res26_1}, result2 = ${res26_2}")

    val nums27 = arrayOf(3, 10, -4, 7, 3, 0, 1, 5, 4, 4, -3, -6, -10, 5, -3, -6, 3, 0, 10, -2, -4, -7, 10, 5, 7, 5, 0, -5, 10, -7, 8, -6, -5, 2, -2, 3, 5, -6, 5, 4, -7, -6, 1, -6, -7, 3, 10)
    val k27 = 7
    val res27_1 = maximumCount.getMaximumCount(nums27, k27)
    val res27_2 = maximumCount.getMaximumCount2(nums27, k27)
    println("Test case 27: match = ${res27_1 == res27_2}, result1 = ${res27_1}, result2 = ${res27_2}")

    val nums28 = arrayOf(-7, 10, -8, -4, 0, 2, 8, -4, 0, 6, 3, -6, 3, 3, 10, 8)
    val k28 = -6
    val res28_1 = maximumCount.getMaximumCount(nums28, k28)
    val res28_2 = maximumCount.getMaximumCount2(nums28, k28)
    println("Test case 28: match = ${res28_1 == res28_2}, result1 = ${res28_1}, result2 = ${res28_2}")

    val nums29 = arrayOf(-3, 6, -3, 10, 6, 10, 5, 9, 8, -7, -4)
    val k29 = 8
    val res29_1 = maximumCount.getMaximumCount(nums29, k29)
    val res29_2 = maximumCount.getMaximumCount2(nums29, k29)
    println("Test case 29: match = ${res29_1 == res29_2}, result1 = ${res29_1}, result2 = ${res29_2}")

    val nums30 = arrayOf(-9, 4, 0, -1, 2, 10, -5, -9, -9, -10, -8, -1, -5, 1, 9, -6, -1, -1, -1, 9, -8, 6, 8, 1, 9, -3, -5, 0, -5, 2, 6, -8, 9, -6, 5, 1, 10, 7, -1)
    val k30 = -1
    val res30_1 = maximumCount.getMaximumCount(nums30, k30)
    val res30_2 = maximumCount.getMaximumCount2(nums30, k30)
    println("Test case 30: match = ${res30_1 == res30_2}, result1 = ${res30_1}, result2 = ${res30_2}")

    val nums31 = arrayOf(1, 2, -6, -2, -7, -6, 6, -6, 2, 8, -4, -3, -6, -7, 5, -4, 9, -2, -9, -1, -5, -3, 9, 4, -7, -2, -10, 5, 7, 1, -4, 7, 1, 9, -8, 8, 6, -10, -10, 10, 4, 3, 4, 4, -2, 6, -7, 6)
    val k31 = 3
    val res31_1 = maximumCount.getMaximumCount(nums31, k31)
    val res31_2 = maximumCount.getMaximumCount2(nums31, k31)
    println("Test case 31: match = ${res31_1 == res31_2}, result1 = ${res31_1}, result2 = ${res31_2}")

    val nums32 = arrayOf(-7, -2, 0, 6, 4, -4, 8, 10, 1, -3, -1, -6, -4, -10, 7, -9, 2, 3, -5, 2, 8, -8, -9, 6)
    val k32 = -7
    val res32_1 = maximumCount.getMaximumCount(nums32, k32)
    val res32_2 = maximumCount.getMaximumCount2(nums32, k32)
    println("Test case 32: match = ${res32_1 == res32_2}, result1 = ${res32_1}, result2 = ${res32_2}")

    val nums33 = arrayOf(-1, 1, 0, 8, 9, -9, 6, 5, -8, -4, -7, 3, -6, 8, 5, 8, -8, 3, 8, -5, 4, -7, 5, 6, 3, -9, 3, -5, -9, 7, -4)
    val k33 = 0
    val res33_1 = maximumCount.getMaximumCount(nums33, k33)
    val res33_2 = maximumCount.getMaximumCount2(nums33, k33)
    println("Test case 33: match = ${res33_1 == res33_2}, result1 = ${res33_1}, result2 = ${res33_2}")

    val nums34 = arrayOf(-4, 1, -5, -4, -4, -10, 0, -4, 7, -3, -9, 6, 5, -4, -8, 9, 7, -5, 1, 10, 10, 8, 7, -4, 7, 10, -7, 3, -2, 10, 5, -6, 0, -9, -5, 4, -4, -1, 6, -4, 8, -4, 6, -8, -7, 0, 2)
    val k34 = 2
    val res34_1 = maximumCount.getMaximumCount(nums34, k34)
    val res34_2 = maximumCount.getMaximumCount2(nums34, k34)
    println("Test case 34: match = ${res34_1 == res34_2}, result1 = ${res34_1}, result2 = ${res34_2}")

    val nums35 = arrayOf(-10, -1, -4, 7, -9, -2, -9, 10, -7, -6, -9, -3, 5, -10, -5, -7, -9, 5, -10, 4, 8, -10, 10, -4, -10, 3, 6, 8, -8, 3, -2, 7, -9, -5, 6, -4, -6, 2, -8, -2, -1, -10, -1, -5, 3, 0)
    val k35 = 6
    val res35_1 = maximumCount.getMaximumCount(nums35, k35)
    val res35_2 = maximumCount.getMaximumCount2(nums35, k35)
    println("Test case 35: match = ${res35_1 == res35_2}, result1 = ${res35_1}, result2 = ${res35_2}")

    val nums36 = arrayOf(-7, 2, 8, 5, -1, -3, -4, -9, 9, -10, 3, 2, 1, -7, -3, 6, 0, -3, 9, 9, -9, 9, 9, 2, -1, 1, 6, 7, 3, 2, 3, -5, -4, -10, -6, 6, 1, 0, -7, -3, -2, 7)
    val k36 = -4
    val res36_1 = maximumCount.getMaximumCount(nums36, k36)
    val res36_2 = maximumCount.getMaximumCount2(nums36, k36)
    println("Test case 36: match = ${res36_1 == res36_2}, result1 = ${res36_1}, result2 = ${res36_2}")

    val nums37 = arrayOf(9, -4, -9, -2, -4, -9, 5, 2, 1, -4, -5, 0, -7, -3, 5, -4, -6, -4, -8, -10, -9, 10, -5, 0, 9)
    val k37 = 9
    val res37_1 = maximumCount.getMaximumCount(nums37, k37)
    val res37_2 = maximumCount.getMaximumCount2(nums37, k37)
    println("Test case 37: match = ${res37_1 == res37_2}, result1 = ${res37_1}, result2 = ${res37_2}")

    val nums38 = arrayOf(-4, 4, 8, -2, 2, 7, -3, 1, 1, -8, 9)
    val k38 = 2
    val res38_1 = maximumCount.getMaximumCount(nums38, k38)
    val res38_2 = maximumCount.getMaximumCount2(nums38, k38)
    println("Test case 38: match = ${res38_1 == res38_2}, result1 = ${res38_1}, result2 = ${res38_2}")

    val nums39 = arrayOf(-8, 2, 3, -4, -6, 4, 5, 5, 5, 10, 8, 5, -4, -9, 8, -10, 1, -10, -5, -9, 7, -6, -1, -5, 0, -5, 10, 7, -3, 2, 7, -4, 5, -3, -9, -7, 5, 8, 5)
    val k39 = 7
    val res39_1 = maximumCount.getMaximumCount(nums39, k39)
    val res39_2 = maximumCount.getMaximumCount2(nums39, k39)
    println("Test case 39: match = ${res39_1 == res39_2}, result1 = ${res39_1}, result2 = ${res39_2}")

    val nums40 = arrayOf(6, -6, 10, -7, 0)
    val k40 = 6
    val res40_1 = maximumCount.getMaximumCount(nums40, k40)
    val res40_2 = maximumCount.getMaximumCount2(nums40, k40)
    println("Test case 40: match = ${res40_1 == res40_2}, result1 = ${res40_1}, result2 = ${res40_2}")

    val nums41 = arrayOf(-10, 5, 7, 5, 2, -5, 0, -6, 10, 10, 1, 4, 7, 7, 7, 3, -6, 2, -4, 6, -1, -2, 4)
    val k41 = 0
    val res41_1 = maximumCount.getMaximumCount(nums41, k41)
    val res41_2 = maximumCount.getMaximumCount2(nums41, k41)
    println("Test case 41: match = ${res41_1 == res41_2}, result1 = ${res41_1}, result2 = ${res41_2}")

    val nums42 = arrayOf(10, -4, -3, -8, 1, 6, -5, -2, -5)
    val k42 = 6
    val res42_1 = maximumCount.getMaximumCount(nums42, k42)
    val res42_2 = maximumCount.getMaximumCount2(nums42, k42)
    println("Test case 42: match = ${res42_1 == res42_2}, result1 = ${res42_1}, result2 = ${res42_2}")

    val nums43 = arrayOf(2, -6, -5, 10, 9, 3, 3, -5, 2, 4, -2, -9, 8, -8, 10, 0, 3, 8, 4, 4, 8, -7, 1, -3, 6, -3, -8, 9)
    val k43 = 4
    val res43_1 = maximumCount.getMaximumCount(nums43, k43)
    val res43_2 = maximumCount.getMaximumCount2(nums43, k43)
    println("Test case 43: match = ${res43_1 == res43_2}, result1 = ${res43_1}, result2 = ${res43_2}")

    val nums44 = arrayOf(-6, 1, -10, 6, 6, 8, 2, -5, -9, 3, 3, -7, 0, 1, 0, -10, 1, -4, -10, -1, 9, -1, -1, 1, -3, -7, -7)
    val k44 = 0
    val res44_1 = maximumCount.getMaximumCount(nums44, k44)
    val res44_2 = maximumCount.getMaximumCount2(nums44, k44)
    println("Test case 44: match = ${res44_1 == res44_2}, result1 = ${res44_1}, result2 = ${res44_2}")

    val nums45 = arrayOf(10, 6, -5, 2, 7, 10, -10, -3, -7, 9, 9, -1, 6, -2, -9, 10, 1, -6, -7, 2, -7, -1, 5, -10, 1, 8, -7, -10, 5, -4, 2, 9, -7, -10, 5, 4, -6, 7, -5, 5, 4, 7, 6, 6, -3, 2, 0, 4)
    val k45 = -3
    val res45_1 = maximumCount.getMaximumCount(nums45, k45)
    val res45_2 = maximumCount.getMaximumCount2(nums45, k45)
    println("Test case 45: match = ${res45_1 == res45_2}, result1 = ${res45_1}, result2 = ${res45_2}")

    val nums46 = arrayOf(-6, -7, -3, 0, -10, 6, 1, 1, -3, 7, -5, 8, 4, -10, -2, 1)
    val k46 = -10
    val res46_1 = maximumCount.getMaximumCount(nums46, k46)
    val res46_2 = maximumCount.getMaximumCount2(nums46, k46)
    println("Test case 46: match = ${res46_1 == res46_2}, result1 = ${res46_1}, result2 = ${res46_2}")

    val nums47 = arrayOf(-9, -2, 0, -6, 2, 1, 2, 10, 0, -1, -1)
    val k47 = 0
    val res47_1 = maximumCount.getMaximumCount(nums47, k47)
    val res47_2 = maximumCount.getMaximumCount2(nums47, k47)
    println("Test case 47: match = ${res47_1 == res47_2}, result1 = ${res47_1}, result2 = ${res47_2}")

    val nums48 = arrayOf(3, 6, -2, -3, 1, 4, -3, 0, 2, 0, -10, -6, 3, 4, 9, -4, 10, -2)
    val k48 = -6
    val res48_1 = maximumCount.getMaximumCount(nums48, k48)
    val res48_2 = maximumCount.getMaximumCount2(nums48, k48)
    println("Test case 48: match = ${res48_1 == res48_2}, result1 = ${res48_1}, result2 = ${res48_2}")

    val nums49 = arrayOf(5, 6, 2, 8, -5, 1, 1, 5, -1, 0, -9, 9, 10, 9, -2, -7, 7, -9, -10, 6, -7, -1, 5, -2, 7, 6, 0, -5, -9, -10, -9, -2, -10, 7, 8, 0, -2, 10, 10, -2)
    val k49 = 7
    val res49_1 = maximumCount.getMaximumCount(nums49, k49)
    val res49_2 = maximumCount.getMaximumCount2(nums49, k49)
    println("Test case 49: match = ${res49_1 == res49_2}, result1 = ${res49_1}, result2 = ${res49_2}")
}


class MaximumCount {

    // O(n * n)
    fun getMaximumCount(nums: Array<Int>, k: Int): Int {
        val size = nums.size
        val baseCount = nums.count {it == k}
        var maxCount = baseCount

        for (l in 0 until size){
            val freq = mutableMapOf<Int, Int>()
            var rangeKCount = 0

            for (r in l until size) {
                if(nums[r] == k) {
                    rangeKCount++
                }

                freq[nums[r]] = freq.getOrDefault(nums[r],0) + 1

                maxCount = maxOf(maxCount, baseCount - rangeKCount + freq[nums[r]]!!)
            }
        }

        return maxCount
    }

    // O(n * d)
    fun getMaximumCount2(nums: Array<Int>, k: Int): Int {
        val baseCount = nums.count { it == k }
        val unique = nums.toSet() - k
        var maxGain = 0

        for (x in unique) {
            var gain = 0
            var bestGain = 0

            for (num in nums) {
                if (num == x) gain += 1
                else if (num == k) gain -= 1

                // 如果 gain 变负数就重置（意味着前面贡献变差）
                if (gain < 0) gain = 0
                bestGain = maxOf(bestGain, gain)
            }

            maxGain = maxOf(maxGain, bestGain)
        }

        return baseCount + maxGain
    }
}