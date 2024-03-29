import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;

/**
 * Created by Nikhil on 01/03/17.
 */
public class Main {
    public static void main(String[] args) {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Unproject unproject = new Unproject();

        Floor floor = new Floor();
        glcanvas.addGLEventListener(floor);

        InsectState insectState = new InsectState(0.0f, 0.0f);
        Insect insect = new Insect(0.1f, insectState, unproject);
        glcanvas.addGLEventListener(insect);

        Frog frog = new Frog(3.0f, -9.0f, 9.0f, insectState, unproject);
        glcanvas.addGLEventListener(frog);

        //Frog frog2 = new Frog(3.0f, 9.0f, 9.0f, insectState, unproject);
        //glcanvas.addGLEventListener(frog2);

        glcanvas.addGLEventListener(unproject);
        glcanvas.addKeyListener(unproject);
        glcanvas.addMouseMotionListener(unproject);
        //glcanvas.requestFocusInWindow();


        glcanvas.setSize(400, 400);

        final JFrame frame = new JFrame ("Frog-Insect");

        frame.getContentPane().add(glcanvas);

        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);

        final FPSAnimator animator = new FPSAnimator(glcanvas, 300,true);
        animator.start();
    }
}
