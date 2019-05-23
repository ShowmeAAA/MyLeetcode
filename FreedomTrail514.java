package cn.hello;


/**
 * 
 * @author 小奔
 *思路来自网上 
 *动态规划：
 *递推式原本思路：利用上一行的信息得到下一行
 *只有当ring[idxRing] = key[idxKey]时，才可以匹配
 *利用上一行中所有不为NOT_MATCH的数来更新dp[idxKey][idxRing]
 */
public class FreedomTrail514 {
	public static void main(String[] args) {
		System.out.println(new Solution2().findRotateSteps("pqwcx", "cpqwx"));
	}
}

class Solution2 {
	private String ring;
    public int findRotateSteps(String ring, String key) {
    	this.ring = ring;
    	int dp[][] = new int[key.length()][ring.length()];
    	
    	//初始化
    	final int NOT_MATCH = 999999;
    	for (int i= 0; i < key.length(); i ++)
    		for (int k = 0; k < ring.length(); k ++)
    			dp[i][k] = NOT_MATCH;
    	
    	for (int idxKey = 0; idxKey < key.length(); idxKey ++) {
    		for (int idxRing = 0; idxRing < ring.length(); idxRing ++) {
    			if (ring.charAt(idxRing) == key.charAt(idxKey) ) {
    				//ring中可能会有多个字符匹配 ，都要搜索，下标为idxRing
    				
    				if (idxKey == 0) //对于key中第一个字符
    					dp[idxKey][idxRing] = getMinSteps(0, idxRing);
    				else {//不是key中第一个字符 则需要根据上一行
    					for (int k = 0; k < ring.length(); k ++) {
    						//遍历上一行
    						if (dp[idxKey - 1][k] != NOT_MATCH) {
    							//说明ring[k]与key[idxKey - 1]匹配
    							//minest(从k转到idxRing的最小转数 + dp[idxKey - 1][k])
    							dp[idxKey][idxRing] = 
    								Math.min(dp[idxKey - 1][k] + getMinSteps(k, idxRing), dp[idxKey][idxRing]);
    						}//sec if
    					}//thr for 结束搜索上一行的每一列
    				}//else
    			}//fst if 当前行的这一列对应的ring字符与当前行对应的key字符匹配
    		}//sec for 结束搜索当前行的每一列
    	}//fst for 结束遍历每一行
    	
    	//找dp二维数组最后一行非NOT_MATCH的最小值
    	int minDP = 99999;
    	for (int j = 0; j < ring.length(); j ++) {
    		if (ring.charAt(j) == key.charAt(key.length() - 1) &&
    				dp[key.length() - 1][j] < minDP)
    			minDP = dp[key.length() - 1][j];
    	}
    	//minDP为-1 what the f??
    	return key.length() + minDP;
    }//method findRotateSteps
    
    //将ring从idxStart转到idxEnd所需的最小步数
    //出了几个bug
    private int getMinSteps(int idxStart, int idxEnd) {
    	System.out.println(idxStart + "转到" + idxEnd + "需要" +
    			Math.min(Math.abs(idxEnd - idxStart), ring.length() - Math.abs(idxEnd - idxStart) ) );
    	
    	return Math.min(Math.abs(idxEnd - idxStart), ring.length() - Math.abs(idxEnd - idxStart) );
    }
    
}//class Solution2
