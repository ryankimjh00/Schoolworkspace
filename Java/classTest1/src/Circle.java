package classTest1;

public class Circle {
    double radius;
    static final double PI = 3.141592;

    void setRadius(double r){
        radius = r;
    }

    double getRadius(){
        return radius;
    }

    double getArea(){
        return radius*radius*PI;
    }

}