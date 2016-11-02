package pac;

/**
 * @author ACJ
 */
public class Dichotomy {
	private double x_low = -10.0;
	private double x_high = 5.0;
	private double x_c;
	private final double delta = 0.001;
	
	 
	/**
	 * 计算 f(x) = x^3 -10 x + 23的值
	 * @param x 
	 * @return f(x)
	 */
	public double calc(double x){
		return (x*x*x - 10*x +23);
	}
	
	/**
	 * @return 二分法求得的根
	 */
	public double searchRoot() {
		do{
			x_c = (x_low + x_high) / 2;
			if (calc(x_c) * calc(x_low) <0 ){
				x_high = x_c;
			}else{
				x_low = x_c;
			}
			//System.out.println(x_c+"   "+calc(x_c));
		}while (calc(x_c) != 0 && x_high - x_low >= delta);
		return x_c;
	}
}
