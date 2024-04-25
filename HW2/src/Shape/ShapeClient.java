import java.util.Scanner;

class ShapeClient {
    public static void main(String[] args) {
        //Point[] means an array, where each ith element contains two components x and y
        Point[] a = createPoints("0 0 0 1 1 1 1 0");
        Point[] b = createPoints("10 10 10 11 11 11 11 10");
        Point[] c = createPoints("0.5 0.5 0.5 -10 1.5 0");
        Point[] d = createPoints("0.5 0.5 0.75 0.75 0.75 0.2");

        System.out.println("a crosses b: " + crosses(a, b));
        System.out.println("a crosses c: " + crosses(a, c));
        System.out.println("a crosses d: " + crosses(a, d));
        System.out.println("a encircles b: " + encircles(a, b));
        System.out.println("a encircles c: " + encircles(a, c));
        System.out.println("a encircles d: " + encircles(a, d));
    }

    public static Point[] createPoints(String input) {
        // input is the string that needs to be split
        //split("\\s+") means split when encountering a space
        String[] tokens = input.split("\\s+");

        int numPoints = tokens.length / 2;
        Point[] points = new Point[numPoints];

        int index = 0;
        for (int i = 0; i < numPoints; i++) {
            double x = Double.parseDouble(tokens[index]);
            double y = Double.parseDouble(tokens[index + 1]);
            points[i] = new Point(x, y);
            index += 2;
        }
        return points;
    }


    public static double dis(double x1, double y1, double x2, double y2) {
        double x = x1 - x2;
        double y = y1 - y2;
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
    public static double FindR(Point[] points, double xCenter, double yCenter){
        double R = 99999;
        FindCenter center = new FindCenter(points);
        for (int i = 0; i < points.length; i++) {
            double distance = dis(xCenter,yCenter,points[i].getX(),points[i].getY());
            R = Math.min(R,distance);
        }
        return R;
    }

    public static boolean crosses(Point[] pointsA, Point[] pointsB){
        FindCenter centerB = new FindCenter(pointsB);
        double xCenterB = centerB.getX();
        double yCenterB = centerB.getY();
        double R = FindR(pointsB,xCenterB,yCenterB);
        int in = 0;
        int out = 0;
        for (int i = 0; i < pointsA.length; i++) {
            double distance = dis(xCenterB,yCenterB,pointsA[i].getX(),pointsA[i].getY());
            if(distance < R) in ++;
            else if(distance >R ) out ++;
            if(out*in != 0) return true;
        }
        return  false;
    }
    public static int encircles(Point[] pointsA, Point[] pointsB){
        FindCenter centerA = new FindCenter(pointsA);
        double xCenterA = centerA.getX();
        double yCenterA = centerA.getY();
        FindCenter centerB = new FindCenter(pointsB);
        double xCenterB = centerB.getX();
        double yCenterB = centerB.getY();
        double Ra = FindR(pointsA,xCenterA,yCenterA);
        double Rb = FindR(pointsB,xCenterB,yCenterB);
        double distance = dis(xCenterB,yCenterB,xCenterA,yCenterA);
        if(distance < Ra) return 2;
        if(Math.abs(Ra - Rb) < distance && distance < Ra + Rb) return 1;
        return 0;
    }
}
