package pac;

public class Sushu {
	
	/**
	 *  运用筛法求出不大于n的素数的个数，并返回最大的素数
	 * @param n
	 * @return maxSushu 最大的素数
	 */
	public int shaiFa(int n){
		boolean[] arr = new boolean[n+1];
		int count = 0;
		int maxSushu = -2;
		for (int i = 2;i <= n; i++){
			arr[i] = true;
		}
		
		for (int i = 2;i <= n; i++){
			if (arr[i] == false){
				continue;
			}
			count ++;
			maxSushu = i;
			//System.out.print(i + " \t");
			int j = 2*i;
			while (j<=n){
				arr[j] = false;
				j += i;
			}
		}
		System.out.println();
		System.out.println("不大于"+n+"的最大素数: "+maxSushu);
		System.out.println("素数个数: "+ count);
		return maxSushu;
	}
	
	/** 判断 x是不是素数
	 * @param x应为大于2的整数
	 * @return x是不是素数
	 */
	public boolean isSushu(int x) {
		for (int i = 2; i <= x / 2; i++) {
			if (x % i == 0)
				return false;
		}
		return true;
	}
}
