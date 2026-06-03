class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        long minLandFinish = Long.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            minLandFinish = Math.min(minLandFinish,
                    (long) landStartTime[i] + landDuration[i]);
        }

        long minWaterFinish = Long.MAX_VALUE;
        for (int i = 0; i < waterStartTime.length; i++) {
            minWaterFinish = Math.min(minWaterFinish,
                    (long) waterStartTime[i] + waterDuration[i]);
        }

        long ans = Long.MAX_VALUE;

        for (int i = 0; i < waterStartTime.length; i++) {
            ans = Math.min(ans,
                    Math.max((long) waterStartTime[i], minLandFinish)
                            + waterDuration[i]);
        }

        for (int i = 0; i < landStartTime.length; i++) {
            ans = Math.min(ans,
                    Math.max((long) landStartTime[i], minWaterFinish)
                            + landDuration[i]);
        }

        return (int) ans;
    }
}