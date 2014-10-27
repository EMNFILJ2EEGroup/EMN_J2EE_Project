
public class DiagUtils {
	
	
//	public static final int RAY = 250;
//		/**
//		 * Returns the angle  360/nbClass in radian
//		 * @param nbClass
//		 * @return angle in radiant
//		 */
//		public static double getRadianAngleBase(int nbClass) {
//			return  Math.toRadians(360/nbClass);
//	}
		
	/**
	 * Calculate  coordinate x-axis on a circle 
	 * @param coordXOrigin position x of the center of the circle
	 * @param ray ray of the circle
	 * @param angleClassRadian angle in degrees
	 * @return
	 */
	public static double getCoordonateX(double coordXOrigin,double ray, double angleClassDegrees){
		return coordXOrigin + ray * Math.cos(Math.toRadians(angleClassDegrees)); 
	}
	
	/**
	 * Calculate  coordinate y-axis on a circle 
	 * @param coordYOrigin position y of the center of the circle
	 * @param ray ray of the circle
	 * @param angleClassRadian angle in degrees
	 * @return
	 */
	public static double getCoordonateY(double coordYOrigin,double ray, double angleClassDegrees){
		return coordYOrigin - ray * Math.sin(Math.toRadians(angleClassDegrees)); 
	}
	
//	public static double toRadians(double angleInDegrees){
//		return Math.toRadians(angleInDegrees); 
//	}
}
