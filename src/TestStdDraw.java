public class TestStdDraw {
    public static void main(String[] args) {
        StdDraw.setXscale(-1, 1);
        StdDraw.setYscale(-1, 1);
        StdDraw.setPenRadius(2.0/1000);
        int n = 100;
        for (int i = 0; i < n*2*Math.PI; i++) {
            StdDraw.point(Math.cos(i/(double) n), Math.sin(i/(double) n));
        }
    }
}
