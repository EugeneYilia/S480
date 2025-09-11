//package yichen
//
////默认题目描述
////
////输入：
////
////board =
////
////[['X','X','X','X'],['X','O','O','X'],['X','X','O','X'],['X','O','X','X']]
////
////输出：
////
////[['X','X','X','X'],['X','X','X','X'],['X','X','X','X'],['X','O','X','X']]
//
//
//fun main(args: Array<String>) {
//
//}
//
//fun updateConnectedAreas(src: Array<Int>, area1: Int, area2: Int){
//    if(src[area2] == area2){
//        src[area1] = area2
//    } else {
//        var areaId = getConnectedArea(src, src[area2])
//        src[area2] = areaId
//        src[area1] = areaId
//    }
//}
//
//fun getConnectedArea(src: Array<Int>, area1: Int): Int{
//    if(src[area1] == area1){
//        return area1
//    } else {
//        return getConnectedArea(src, src[area1])
//    }
//}
//
//public class Region{
//    public var head: Int = -1
//    public var elements: HashSet<String> = HashSet()
//    public var isLive = false
//}
//
//fun calcRemovePart(src: ArrayList<ArrayList<String>>)
//{
//    val row = src.size;
//    val column = src[0].size;
//
//    if(row < 2 || column < 2){
//        return
//    }
//
//    var connectedAreas: Array<Int> = Array<Int>(row+column, ()-> 0)
//    for(i in 0..<row+column){
//        connectedAreas[i] = i
//    }
//
//    var regions : ArrayList<Region> = ArrayList<Region>()
//
//    for(rowIndex in 1..row-2){
//        for(columnIndex in 1..column-2){
//            if(src[rowIndex][columnIndex] == "O"){
//                updateConnectedAreas(connectedAreas, rowIndex, columnIndex)
//            }
//        }
//    }
//
//    for(rowIndex in 1..row-2){
//        for(columnIndex in 1..column-2){
//            if(src[rowIndex][columnIndex] == "O"){
//                var regionIndex = getConnectedArea(connectedAreas, columnIndex);
//                var region = regions.where(region => region.head == regionIndex).firstOrDefault();
//                if(region is null){
//                    region = Region()
//                    region.head = regionIndex;
//                    region.elements.add(src[rowIndex][columnIndex]);
//                    regions.add(region);
//                } else {
//                    region.elements.add(src[rowIndex][columnIndex]);
//                }
//
//                if(rowIndex == 1){
//                    if(src[0][columnIndex] == "O"){
//                        region.isLive = true
//                        continue
//                    }
//                }
//
//                if(rowIndex == row-2){
//                    if(src[row-1][columnIndex] == "0"){
//                        region.isLive = true
//                        continue
//                    }
//                }
//
//                if(columnIndex == 1){
//                    if(src[rowIndex][0] == "0"){
//                        region.isLive = true
//                        continue
//                    }
//                }
//
//                if(columnIndex == column - 2){
//                    if()
//                }
//            }
//        }
//    }
//}