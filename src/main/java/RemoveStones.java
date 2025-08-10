public class RemoveStones {
    public static void main(String[] args) {
        RemoveStones sut = new RemoveStones();
//        System.out.println(sut.removeStones(new int[][] {
//                {0, 0},
//                {0, 1},
//                {1, 0},
//                {1, 2},
//                {2, 1},
//                {2, 2}
//        }) == 5);

        var result = sut.removeStones(new int[][]{
                {3, 2},
                {0, 0},
                {3, 3},
                {2, 1},
                {2, 3},
                {2, 2},
                {0, 2}
        });
        System.out.println(result);
        System.out.println(result == 6);
    }

    // 近似时间复杂度为O(n)
    public int removeStones(int[][] stones) {
        var rowCount = 10001;
        var colCount = 10001;

        int[] connectedSet = new int[rowCount + colCount];
        // 20002   init
        for (int i = 1; i < rowCount + colCount; i++) {
            connectedSet[i] = i;
        }

        // 1000    构建连通空间
        for (int[] stone : stones) {
            updateConnectedSet(connectedSet, stone[0], stone[1] + rowCount);
        }

        int[] result = new int[rowCount + colCount];

        int maxLeave = 0;

        // 1000   判断点位的联通空间归属，以及激活的连通空间数量
        for (int[] stone : stones) {
            if(result[getConnectSetId(connectedSet, stone[1] + rowCount)] == 0){
                maxLeave += 1;
                result[getConnectSetId(connectedSet, stone[1] + rowCount)] = 1;
            }
        }

        return stones.length - maxLeave;
    }

    public int updateConnectedSet(int[] connectedSet, int areaId1, int areaId2) {
        if (areaId1 <= areaId2) {
            if (connectedSet[areaId1] == areaId1) {
                connectedSet[areaId1] = areaId2;
                return areaId1;
            } else {
                connectedSet[areaId1] = updateConnectedSet(connectedSet, connectedSet[areaId1], areaId2);
                return connectedSet[areaId1];
            }
        } else {
            return updateConnectedSet(connectedSet, areaId2, areaId1);
        }
    }

    public int getConnectSetId(int[] connectedSet, int index) {
        if (connectedSet[index] == index) {
            return index;
        } else {
            connectedSet[index] = getConnectSetId(connectedSet, connectedSet[index]);
            return connectedSet[index];
        }
    }
}
