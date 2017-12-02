package really.game;

public final class Utils {
    
    private static final double HALF_PI = Math.PI/2;
    private static final double PI = Math.PI;
    private static final double ONE_HALF_PI = 3*Math.PI/2;
    private static final double TWO_PI = 2*Math.PI;
    
                                                   //1/3!   1/5!     1/7!      1/9!        1/11!         1/13!
    private static final double[] INV_FACTORIALS = { 1/6.0, 1/120.0, 1/5040.0, 1/362880.0, 1/39916800.0, 1/6227020800.0 };
    
    public static double cos(double theta) {
        return sin(HALF_PI - theta);
    }
    
    public static double sin(double theta) {
        theta %= TWO_PI;
        if (theta <= HALF_PI)
            return  taylorSeriesSin(theta);
        else if (theta <= PI)
            return  taylorSeriesSin(PI - theta);
        else if (theta <= ONE_HALF_PI)
            return -taylorSeriesSin(theta - PI);
        else
            return -taylorSeriesSin(TWO_PI - theta);
    }
        
    private static double taylorSeriesSin(double x) {
        double x2 = x * x;
        return x*(1 - x2*(INV_FACTORIALS[0] - x2*(INV_FACTORIALS[1] - x2*(INV_FACTORIALS[2]
                    - x2*(INV_FACTORIALS[3] - x2*(INV_FACTORIALS[4] - x2*INV_FACTORIALS[5]))))));
    }
    
    public static float invSqrt(float x) {
    	float xhalf = 0.5f * x;
    	int i = Float.floatToIntBits(x);
    	i = 0x5f3759df - (i >> 1);
    	x = Float.intBitsToFloat(i);
    	x = x*(1.5f - xhalf*x*x);
    	x = x*(1.5f - xhalf*x*x);
    	return x;
    }
    		
    
}