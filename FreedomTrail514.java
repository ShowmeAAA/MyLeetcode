package cn.hello;


/**
 * 
 * @author С��
 *˼·�������� 
 *��̬�滮��
 *����ʽԭ��˼·��������һ�е���Ϣ�õ���һ��
 *ֻ�е�ring[idxRing] = key[idxKey]ʱ���ſ���ƥ��
 *������һ�������в�ΪNOT_MATCH����������dp[idxKey][idxRing]
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
    	
    	//��ʼ��
    	final int NOT_MATCH = 999999;
    	for (int i= 0; i < key.length(); i ++)
    		for (int k = 0; k < ring.length(); k ++)
    			dp[i][k] = NOT_MATCH;
    	
    	for (int idxKey = 0; idxKey < key.length(); idxKey ++) {
    		for (int idxRing = 0; idxRing < ring.length(); idxRing ++) {
    			if (ring.charAt(idxRing) == key.charAt(idxKey) ) {
    				//ring�п��ܻ��ж���ַ�ƥ�� ����Ҫ�������±�ΪidxRing
    				
    				if (idxKey == 0) //����key�е�һ���ַ�
    					dp[idxKey][idxRing] = getMinSteps(0, idxRing);
    				else {//����key�е�һ���ַ� ����Ҫ������һ��
    					for (int k = 0; k < ring.length(); k ++) {
    						//������һ��
    						if (dp[idxKey - 1][k] != NOT_MATCH) {
    							//˵��ring[k]��key[idxKey - 1]ƥ��
    							//minest(��kת��idxRing����Сת�� + dp[idxKey - 1][k])
    							dp[idxKey][idxRing] = 
    								Math.min(dp[idxKey - 1][k] + getMinSteps(k, idxRing), dp[idxKey][idxRing]);
    						}//sec if
    					}//thr for ����������һ�е�ÿһ��
    				}//else
    			}//fst if ��ǰ�е���һ�ж�Ӧ��ring�ַ��뵱ǰ�ж�Ӧ��key�ַ�ƥ��
    		}//sec for ����������ǰ�е�ÿһ��
    	}//fst for ��������ÿһ��
    	
    	//��dp��ά�������һ�з�NOT_MATCH����Сֵ
    	int minDP = 99999;
    	for (int j = 0; j < ring.length(); j ++) {
    		if (ring.charAt(j) == key.charAt(key.length() - 1) &&
    				dp[key.length() - 1][j] < minDP)
    			minDP = dp[key.length() - 1][j];
    	}
    	//minDPΪ-1 what the f??
    	return key.length() + minDP;
    }//method findRotateSteps
    
    //��ring��idxStartת��idxEnd�������С����
    //���˼���bug
    private int getMinSteps(int idxStart, int idxEnd) {
    	System.out.println(idxStart + "ת��" + idxEnd + "��Ҫ" +
    			Math.min(Math.abs(idxEnd - idxStart), ring.length() - Math.abs(idxEnd - idxStart) ) );
    	
    	return Math.min(Math.abs(idxEnd - idxStart), ring.length() - Math.abs(idxEnd - idxStart) );
    }
    
}//class Solution2
