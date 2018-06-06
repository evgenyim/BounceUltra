package com.admin.bounceultra;

public class Line {
    float a;
    float b;
    float c;

    public static Point intersect(Line n, Line m) {
        double d = m.a * n.b - m.b * n.a;
        double d1 = -m.c * n.b + m.b * n.c;
        double d2 = -n.c * m.a + n.a * m.c;
        if (d == 0)
            return null;
        return new Point((float) (d1 / d), (float) (d2 / d));
    }

    Line(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    Line(Segment segment) {
        this.a = segment.a.y - segment.b.y;
        this.b = segment.b.x - segment.a.x;
        this.c = segment.a.x * segment.b.y - segment.b.x * segment.a.y;
    }
    Line(Point A, Vector vector) {
        Point B = new Point(A.x + vector.x, A.y + vector.y);
        Segment segment = new Segment(A, B);
        Line L = new Line(segment);
        this.a = L.a;
        this.b = L.b;
        this.c = L.c;
    }
}
