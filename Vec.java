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
    double x, y;
 
    Vec() {
    }
 
    Vec(double x, double y) {
        this.x = x;
        this.y = y;
    }
 
    void add(Vec v) {
        x += v.x;
        y += v.y;
    }
 
    void sub(Vec v) {
        x -= v.x;
        y -= v.y;
    }
 
    void div(double val) {
        x /= val;
        y /= val;
    }
 
    void mult(double val) {
        x *= val;
        y *= val;
    }
 
    double mag() {
        return sqrt(pow(x, 2) + pow(y, 2));
    }
 
    double dot(Vec v) {
        return x * v.x + y * v.y;
    }
 
    void normalize() {
        double mag = mag();
        if (mag != 0) {
            x /= mag;
            y /= mag;
        }
    }

    double heading() {
        return atan2(y, x);
    }
 
    static Vec sub(Vec v, Vec v2) {
        return new Vec(v.x - v2.x, v.y - v2.y);
    }
 
    static double dist(Vec v, Vec v2) {
        return sqrt(pow(v.x - v2.x, 2) + pow(v.y - v2.y, 2));
    }
 
    static double angleBetween(Vec v, Vec v2) {
        return acos(v.dot(v2) / (v.mag() * v2.mag()));
    }
}
