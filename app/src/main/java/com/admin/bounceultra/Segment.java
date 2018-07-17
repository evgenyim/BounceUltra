package com.admin.bounceultra;


import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class Segment {
    Point a;
    Point b;
    float eps = (float) 1e-4;

    static float angle(Segment segment1, Segment segment2) {
        Vector vec1 = new Vector(segment1);
        Vector vec2 = new Vector(segment2);
        float alfa =  vec1.angle(vec2);
        if (alfa > Math.PI / 2) {
            alfa = (float) Math.abs(alfa - Math.PI);
        }
        return alfa;
    }
    static Vector normal(Segment segment) {
        Vector vec = new Vector(segment);
        return Vector.normal(vec);
    }
    float length(){
        Vector vec = new Vector(this);
        return vec.length();
    }
    static boolean segments_intersect(Segment seg1, Segment seg2) {
        Point p1 = seg1.a;
        Point p2 = seg1.b;
        Point p3 = seg2.a;
        Point p4 = seg2.b;
        //сначала расставим точки по порядку, т.е. чтобы было p1.x <= p2.x
        if (p2.x < p1.x) {
            Point tmp = p1;
            p1 = p2;
            p2 = tmp;
        }
        //и p3.x <= p4.x
        if (p4.x < p3.x) {
            Point tmp = p3;
            p3 = p4;
            p4 = tmp;
        }
        //проверим существование потенциального интервала для точки пересечения отрезков
        if (p2.x < p3.x) {
            return false; //ибо у отрезков нету взаимной абсциссы
        }
        //если оба отрезка вертикальные
        if((p1.x - p2.x == 0) && (p3.x - p4.x == 0)) {
            //если они лежат на одном X
            if(p1.x == p3.x) {
                //проверим пересекаются ли они, т.е. есть ли у них общий Y
                //для этого возьмём отрицание от случая, когда они НЕ пересекаются
                if (!((Math.max(p1.y, p2.y) < Math.min(p3.y, p4.y)) ||
                        (Math.min(p1.y, p2.y) > Math.max(p3.y, p4.y)))) {
                    return true;
                }
            }
            return false;
        }
        //найдём коэффициенты уравнений, содержащих отрезки
        //f1(x) = A1*x + b1 = y
        //f2(x) = A2*x + b2 = y
        //если первый отрезок вертикальный
        if (p1.x - p2.x == 0) {
            //найдём Xa, Ya - точки пересечения двух прямых
            double Xa = p1.x;
            double A2 = (p3.y - p4.y) / (p3.x - p4.x);
            double b2 = p3.y - A2 * p3.x;
            double Ya = A2 * Xa + b2;
            if (p3.x <= Xa && p4.x >= Xa && Math.min(p1.y, p2.y) <= Ya &&
                    Math.max(p1.y, p2.y) >= Ya) {
                return true;
            }
            return false;
        }
        //если второй отрезок вертикальный
        if (p3.x - p4.x == 0) {
            //найдём Xa, Ya - точки пересечения двух прямых
            double Xa = p3.x;
            double A1 = (p1.y - p2.y) / (p1.x - p2.x);
            double b1 = p1.y - A1 * p1.x;
            double Ya = A1 * Xa + b1;
            if (p1.x <= Xa && p2.x >= Xa && Math.min(p3.y, p4.y) <= Ya &&
                    Math.max(p3.y, p4.y) >= Ya) {
                return true;
            }
            return false;
        }
        //оба отрезка невертикальные
        double A1 = (p1.y - p2.y) / (p1.x - p2.x);
        double A2 = (p3.y - p4.y) / (p3.x - p4.x);
        double b1 = p1.y - A1 * p1.x;
        double b2 = p3.y - A2 * p3.x;
        if (A1 == A2) {
            return false; //отрезки параллельны
        }
        //Xa - абсцисса точки пересечения двух прямых
        double Xa = (b2 - b1) / (A1 - A2);
        if ((Xa < Math.max(p1.x, p3.x)) || (Xa > Math.min( p2.x, p4.x))) {
            return false; //точка Xa находится вне пересечения проекций отрезков на ось X
        }
        else {
            return true;
        }
    }
    boolean intersect_ball(Ball ball) {
        float dist = Vector.dPointSegment(a, b, ball.centre());
        if (dist > ball.r) {
            return false;
        }
        return true;
    }
    void draw(Canvas canvas, Paint paint) {
        canvas.drawLine(a.x,a.y,b.x,b.y,paint);
    }
    void comunicate(Ball ball, int intersected_seg_ind, Segment cur_seg, ArrayList<GameObject> ObjectList, int intersected_obst_ind, float min_d, boolean draft){

    }

    Segment(Point a, Point b) {
        this.a = a;
        this.b = b;
    }
    Segment(float x1, float y1, float x2, float y2) {
        this.a = new Point(x1, y1);
        this.b = new Point(x2, y2);
    }
    Segment(){}
}
