import java.awt.*;
import java.util.*;
import java.util.List;

public class Player {

    int x, y, dx = 0, dy = 0, nx, ny, playernumber, turnnumber, checkpoint=0;
    Color farve = Color.BLACK;
    boolean dead = false, playing = true;
    ArrayList<int[]> coordhis = new ArrayList<>();


    public void setVec(int p) {

        switch (p) {

            case 1:

                dx -= 1;
                dy -= 1;

                break;

            case 2:

                dy -= 1;

                break;

            case 3:

                dx += 1;
                dy -= 1;

                break;

            case 4:

                dx -= 1;

                break;

            case 5:

                break;

            case 6:

                dx += 1;

                break;

            case 7:

                dx -= 1;
                dy += 1;

                break;

            case 8:

                dy += 1;

                break;

            case 9:

                dx += 1;
                dy += 1;
        }


    }

    public void setCord(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public void revive(int x,int y, int dx,int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.dead=false;

    }

    public void setPos() {

        nx = x+dx;
        ny = y+dy;
        StdDraw.line(x,y,nx,ny);

        x = x + dx;
        y = y + dy;


    }

    public int[] giveCord() {

        int[] cord = new int[2];

        cord[0] = x; cord[1] = y;

        return cord;

    }

}
