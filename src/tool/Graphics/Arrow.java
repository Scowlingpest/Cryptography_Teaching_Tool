package tool.Graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Line;

/**
 * Created by Phillipa on 13/10/2015.
 */
public class Arrow {
    int startX,endX;
    int startY,endY;
    Canvas c;

    public Arrow(int x,int y, int z, int a) {
        this.c=new Canvas(z-x,a-y);
        drawArrow(x,y,z,a);


    }

    public void drawArrow(int x,int y, int z, int a){
        GraphicsContext gc = c.getGraphicsContext2D();
        gc.strokeLine(x,y,z,a);
        double angle =Math.toDegrees(Math.atan2(a-y,z-a));
        int topX= (int) (z+((z-x)/4)*Math.sin(angle+45));
        int topY=(int) (a+((a-y)/4)*Math.cos(angle+45));

        int bottomX= (int) (z+((z-x)/4)*Math.sin(angle-45));
        int bottomY=(int) (a+((a-y)/4)*Math.cos(angle-45));

        gc.strokeLine(z,a,topX,topY);
        gc.strokeLine(z,a,bottomX,bottomY);

    }

    public Canvas getC() {
        return c;
    }
}
