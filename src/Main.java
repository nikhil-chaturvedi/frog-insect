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

        Frog frog = new Frog();
        glcanvas.addGLEventListener(frog);

        Floor floor = new Floor();
        glcanvas.addGLEventListener(floor);

        glcanvas.setSize(400, 400);

        final JFrame frame = new JFrame ("Frog-Insect");

        frame.getContentPane().add(glcanvas);

        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);

        final FPSAnimator animator = new FPSAnimator(glcanvas, 300,true);
        animator.start();
    }
}
