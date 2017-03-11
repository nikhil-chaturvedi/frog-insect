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
    int viewport[];
    double mvmatrix[];
    double projmatrix[];
    int realy;// GL y coord pos
    double wcoord[];// wx, wy, wz;// returned xyz coords


    public Unproject()
    {


        viewport = new int[4];
        mvmatrix = new double[16];
        projmatrix = new double[16];
        wcoord = new double[4];
        realy=0;
    }
    public double getWorldX(){
        return wcoord[0];
    }
    public double getWorldY(){
        return wcoord[1];
    }
    public double getWorldZ(){
        return wcoord[2];
    }

    public void init(GLAutoDrawable drawable)
    {

        glu = new GLU();
    }

    public void display(GLAutoDrawable drawable)
    {
        GL2 gl = drawable.getGL().getGL2();

        //gl.glClear(GL.GL_COLOR_BUFFER_BIT);



        if (mouse != null)
        {
            int x = mouse.getX(), y = mouse.getY();
            //switch (mouse.getButton()) {
              //  case MouseEvent.BUTTON1:
                    gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);
                    gl.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, mvmatrix, 0);
                    gl.glGetDoublev(GL2.GL_PROJECTION_MATRIX, projmatrix, 0);
          /* note viewport[3] is height of window in pixels */
                    realy = viewport[3] - (int) y - 1;
                    System.out.println("Coordinates at cursor are (" + x + ", " + realy);
                    glu.gluUnProject((double) x, (double) realy, 0.0, //
                            mvmatrix, 0,
                            projmatrix, 0,
                            viewport, 0,
                            wcoord, 0);
                    System.out.println("World coords at z=0.0 are ( " //
                            + wcoord[0] + ", " + wcoord[1] + ", " + wcoord[2]
                            + ")");
                    glu.gluUnProject((double) x, (double) realy, 1.0, //
                            mvmatrix, 0,
                            projmatrix, 0,
                            viewport, 0,
                            wcoord, 0);
                    System.out.println("World coords at z=1.0 are (" //
                            + wcoord[0] + ", " + wcoord[1] + ", " + wcoord[2]
                            + ")");
                //    break;
                //case MouseEvent.BUTTON2:
                 //   break;
                //default:
                  //  break;
            //}
        }

        //gl.glFlush();
    }

    /* Change these values for a different transformation */
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
    {

    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
                               boolean deviceChanged)
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

    public void mouseClicked(MouseEvent e)
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
    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

}
