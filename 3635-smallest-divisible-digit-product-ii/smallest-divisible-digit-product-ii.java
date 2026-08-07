public class Solution {

    // Digit factor contributions for digits 0..9
    private static final int[] d2 = {0, 0, 1, 0, 2, 0, 1, 0, 3, 0};
    private static final int[] d3 = {0, 0, 0, 1, 0, 0, 1, 0, 0, 2};
    private static final int[] d5 = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
    private static final int[] d7 = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0};

    public String smallestNumber(String num, long t) {
        int target2 = 0, target3 = 0, target5 = 0, target7 = 0;

        // Factorize t into prime factors 2, 3, 5, 7
        while (t % 2 == 0) { target2++; t /= 2; }
        while (t % 3 == 0) { target3++; t /= 3; }
        while (t % 5 == 0) { target5++; t /= 5; }
        while (t % 7 == 0) { target7++; t /= 7; }

        // If t has prime factors other than 2, 3, 5, 7, it's impossible
        if (t > 1) return "-1";

        int n = num.length();

        // Any digit '0' invalidates the rest of the prefix
        int zeroIdx = num.indexOf('0');
        int maxPrefix = (zeroIdx == -1) ? n : zeroIdx;

        // Calculate factors provided by prefix num[0 ... maxPrefix-1]
        int cur2 = 0, cur3 = 0, cur5 = 0, cur7 = 0;
        for (int i = 0; i < maxPrefix; i++) {
            int d = num.charAt(i) - '0';
            cur2 += d2[d];
            cur3 += d3[d];
            cur5 += d5[d];
            cur7 += d7[d];
        }

        // Try matching prefix of length L from maxPrefix down to 0
        for (int L = maxPrefix; L >= 0; L--) {
            if (L == n) {
                // Check if exact num works
                int req2 = Math.max(0, target2 - cur2);
                int req3 = Math.max(0, target3 - cur3);
                int req5 = Math.max(0, target5 - cur5);
                int req7 = Math.max(0, target7 - cur7);

                if (minLenRequired(req2, req3, req5, req7) == 0) {
                    return num;
                }
            } else {
                int startD = num.charAt(L) - '0' + 1;
                for (int d = startD; d <= 9; d++) {
                    int req2 = Math.max(0, target2 - cur2 - d2[d]);
                    int req3 = Math.max(0, target3 - cur3 - d3[d]);
                    int req5 = Math.max(0, target5 - cur5 - d5[d]);
                    int req7 = Math.max(0, target7 - cur7 - d7[d]);

                    int remLen = n - 1 - L;
                    if (minLenRequired(req2, req3, req5, req7) <= remLen) {
                        return num.substring(0, L) + d + fillSuffix(remLen, req2, req3, req5, req7);
                    }
                }
            }

            // O(1) backtrack: remove contributions of num[L-1] when moving to L-1
            if (L > 0) {
                int prevD = num.charAt(L - 1) - '0';
                cur2 -= d2[prevD];
                cur3 -= d3[prevD];
                cur5 -= d5[prevD];
                cur7 -= d7[prevD];
            }
        }

        // If no number of length n works, find smallest valid number of length >= n + 1
        int reqLen = minLenRequired(target2, target3, target5, target7);
        int targetLen = Math.max(n + 1, reqLen);

        return fillSuffix(targetLen, target2, target3, target5, target7);
    }

    // Calculates minimum digits needed to satisfy remaining prime factor counts
    private int minLenRequired(int c2, int c3, int c5, int c7) {
        int len = c5 + c7;

        int n9 = c3 / 2;
        c3 %= 2;

        int n8 = c2 / 3;
        c2 %= 3;

        if (c2 == 1 && c3 == 1) {
            len += 1; // digit 6
            c2 = 0; c3 = 0;
        } else if (c2 == 2 && c3 == 1) {
            len += 2; // e.g., digits 6 and 4
            c2 = 0; c3 = 0;
        }

        if (c2 > 0) len += 1; // digit 4 or 2
        if (c3 > 0) len += 1; // digit 3

        return len + n9 + n8;
    }

    // Fills remaining suffix greedily with digits 1..9 using primitives
    private String fillSuffix(int remLen, int req2, int req3, int req5, int req7) {
        StringBuilder sb = new StringBuilder(remLen);
        for (int i = 0; i < remLen; i++) {
            for (int d = 1; d <= 9; d++) {
                int next2 = Math.max(0, req2 - d2[d]);
                int next3 = Math.max(0, req3 - d3[d]);
                int next5 = Math.max(0, req5 - d5[d]);
                int next7 = Math.max(0, req7 - d7[d]);

                if (minLenRequired(next2, next3, next5, next7) <= remLen - 1 - i) {
                    sb.append(d);
                    req2 = next2;
                    req3 = next3;
                    req5 = next5;
                    req7 = next7;
                    break;
                }
            }
        }
        return sb.toString();
    }
}