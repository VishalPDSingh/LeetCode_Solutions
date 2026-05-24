class Solution {
    public int hammingWeight(int n) {
        
        String binaryNum = Integer.toBinaryString(n);
       int count = 0;
       for(int i=0; i<binaryNum.length(); i++)
       {
        if(binaryNum.charAt(i)=='1')
        count++;
       }
       return count;
    }
}