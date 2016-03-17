package tool.Graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 13/10/2015.
 */
//Arrow graphic object class
public class Arrow {
    private final Canvas c;

    /*arrow constructor, makes an arrow
    parameters: x,y - first coordinates, z,a - second coordinates
    returns: null
     */
    public Arrow(int x,int y, int z, int a) {
        this.c=canvasSetUp(x,y,z,a);
        drawArrow(x,y,z,a);


    }

    /*drawArrow, draws the arrow on screen
    parameters: x,y - first coordinates, z,a - second coordinates
    returns: null
     */
    private void drawArrow(int x, int y, int z, int a){
        GraphicsContext gc = c.getGraphicsContext2D();
        gc.setLineWidth(3);
        gc.strokeLine(x,y,z,a);

        //following code altered from :http://stackoverflow.com/questions/3010803/draw-arrow-on-line-algorithm
        int arrowLength=25;
        int dx = z - x;
        int dy = a - y;

        double theta = Math.atan2(dy, dx);

        double rad = Math.toRadians(40);
        double endX = z - arrowLength * Math.cos(theta + rad);
        double endY = a - arrowLength * Math.sin(theta + rad);

        double phi2 = Math.toRadians(-40);
        double x2 = z - arrowLength * Math.cos(theta + phi2);
        double y2 = a - arrowLength * Math.sin(theta + phi2);

        double[] arrowYs = new double[3];
        arrowYs[0] = a;
        arrowYs[1] = endY;
        arrowYs[2] = y2;

        double[] arrowXs = new double[3];
        arrowXs[0] = z;
        arrowXs[1] = endX;
        arrowXs[2] = x2;

        gc.fillPolygon(arrowXs, arrowYs, 3);


    }

    //getter
    public Canvas getC() {
        return c;
    }

    /*canvasSetUp, makes the canvas a set size based on coordinates
    parameters: x,y - first coordinates, z,a - second coordinates
    returns: the setup canvas
     */
    private Canvas canvasSetUp(int x, int y, int z, int a){
        if (z==x){
            return new Canvas(z*2,a);
        }
            else if(y==a) {
            return new Canvas(z,2*a);

        }
        return new Canvas(1.25*z,1.25*a);

    }


}
