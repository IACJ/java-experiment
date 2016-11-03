package pac;

public class Sushu {
	
	/**
	 *  ����ɸ�����������n�������ĸ�������������������
	 * @param n
	 * @return maxSushu ��������
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
		System.out.println("������"+n+"���������: "+maxSushu);
		System.out.println("��������: "+ count);
		return maxSushu;
	}
	
	/** �ж� x�ǲ�������
	 * @param xӦΪ����2������
	 * @return x�ǲ�������
	 */
	public boolean isSushu(int x) {
		for (int i = 2; i <= x / 2; i++) {
			if (x % i == 0)
				return false;
		}
		return true;
	}
}
