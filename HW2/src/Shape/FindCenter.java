class FindCenter{
    private double x;
    private double y;
    public FindCenter(Point[] points){
        double xCenter = 0, yCenter = 0;
        for (int i = 0; i < points.length; i++) {
            xCenter += points[i].getX()/points.length;
            yCenter += points[i].getY()/points.length;
        }
        x = xCenter;
        y = yCenter;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}