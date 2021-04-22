import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.*;
import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import javax.swing.Timer;

class Vec {
    public double x, y;
 
    public Vec() {}
 
    public Vec(double x, double y) {
        this.x = x;
        this.y = y;
    }
 
    public void add(Vec v) {
        x += v.x;
        y += v.y;
    }
 
    public void sub(Vec v) {
        x -= v.x;
        y -= v.y;
    }
 
    public void div(double val) {
        x /= val;
        y /= val;
    }
 
    public void mult(double val) {
        x *= val;
        y *= val;
    }
 
    public double mag() {
        return sqrt(pow(x, 2) + pow(y, 2));
    }
 
    public double dot(Vec v) {
        return this.x * v.x + this.y * v.y;
    }
 
    public void normalize() {
        double mag = mag();
        if (mag != 0) {
            x /= mag;
            y /= mag;
        }
    }

    public double heading() {
        return atan2(y, x);
    }
 
    public Vec sub(Vec v, Vec v2) {
        return new Vec(v.x - v2.x, v.y - v2.y);
    }
 
    public double dist(Vec v) {
        return sqrt(pow(v.x - this.x, 2) + pow(v.y - this.y, 2));
    }
 
    public double angleBetween(Vec v) {
        return acos(this.dot(v) / (this.mag() * v.mag()));
    }
    
    public String toString() {
		return "("+x+", "+y+")";
	}
		
}
