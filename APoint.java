/**
 * Class that allows to handle a point in 2D space
 * @author Equipe pédagogique IF2A
 */ 
 
public class APoint {
    
	// Attributes
    public double x;
    public double y;
    
    /**
     * Constructor
     * @param The coordinates of the point
     */
    public APoint(double ax, double ay){
        x = ax;
        y = ay;
    }
    
    /**
     * A method to calculate the Euclidean distance with another point
     * @param A point w.r.t. to which the distance is calculated
     * @return Euclidean distance
     */        
    public double distance(APoint otherPoint ) {
        double dx = x - otherPoint.x;
        double dy = y - otherPoint.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    /**
     * Displys the coordinates of the point
     * @return the coordinates of the point in the form: [x=1.0,y=1.0]
     */
    public String toString() {
		String res ="";
        res="[x=" + x + ",y=" + y + "]";
        return res;  
    }    
}
