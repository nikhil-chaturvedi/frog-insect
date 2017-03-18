/**
 * Created by Kartikeya Sharma on 11-03-2017.
 */
import java.awt.event.*;

import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import javax.swing.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.GLCapabilities;

/**
 * When the left mouse button is pressed, this program reads the mouse position
 * and determines two 3D points from which it was transformed. Very little is
 * displayed.
 *
 */
public class Unproject
        implements GLEventListener, KeyListener, MouseMotionListener
{
    private GLU glu;
    //private GLCapabilities caps;
    //private GLCanvas canvas;
    private MouseEvent mouse;
    private int viewport[];
    private double mvmatrix[];
    private double projmatrix[];
    private int realy;// GL y coord pos
    private double wcoord0[];// wx, wy, wz;// returned xyz coords
    private double wcoord1[];

    public Unproject()
    {


        viewport = new int[4];
        mvmatrix = new double[16];
        projmatrix = new double[16];
        wcoord0 = new double[4];
        wcoord1 = new double[4];
        realy=0;
    }

    public void init(GLAutoDrawable drawable)
    {

        glu = new GLU();
    }

    public void changeRotation(FrogState state) {
        double[] raySrc = wcoord0;
        double[] rayDir = new double[]{wcoord1[0]-wcoord0[0],wcoord1[1]-wcoord0[1],wcoord1[2]-wcoord0[2]};
        double param = -1.0 * (raySrc[1]/rayDir[1]);
        double intersectX = raySrc[0] + param * rayDir[0];
        double intersectZ = raySrc[2] + param * rayDir[2];

        System.out.println(intersectX + ", " + intersectZ);

        float diffX = (float)intersectX - state.posX;
        float diffZ = (float)intersectZ - state.posZ;

        float norm = (float)Math.sqrt(diffX*diffX + diffZ*diffZ);

        state.rotX = diffZ/norm;
        state.rotZ = diffX/norm;
    }

    public void display(GLAutoDrawable drawable)
    {
        GL2 gl = drawable.getGL().getGL2();

        if (mouse != null)
        {
            int x = mouse.getX(), y = mouse.getY();
            gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);
            gl.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, mvmatrix, 0);
            gl.glGetDoublev(GL2.GL_PROJECTION_MATRIX, projmatrix, 0);
  /* note viewport[3] is height of window in pixels */
            realy = viewport[3] - (int) y - 1;
            //System.out.println("Coordinates at cursor are (" + x + ", " + realy);
            glu.gluUnProject((double) x, (double) realy, 0.0, //
                    mvmatrix, 0,
                    projmatrix, 0,
                    viewport, 0,
                    wcoord0, 0);
            /*System.out.println("World coords at z=0.0 are ( " //
                    + wcoord[0] + ", " + wcoord[1] + ", " + wcoord[2]
                    + ")");*/
            glu.gluUnProject((double) x, (double) realy, 1, //
                    mvmatrix, 0,
                    projmatrix, 0,
                    viewport, 0,
                    wcoord1, 0);
        }
    }

    /* Change these values for a different transformation */
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
    {

    }

    public void dispose(GLAutoDrawable autoDrawable){}

    public void keyTyped(KeyEvent key)
    {
    }

    public void keyPressed(KeyEvent key)
    {

        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;

            default:
                break;
        }

    }

    public void keyReleased(KeyEvent key)
    {
    }

    public void mousePressed(MouseEvent e)
    {
        mouse = e;
       // canvas.display();
    }
    public void mouseMoved(MouseEvent e) {
        mouse = e;
    }

    public void mouseDragged(MouseEvent e) {

    }

}
