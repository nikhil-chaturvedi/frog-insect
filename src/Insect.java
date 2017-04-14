import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

/**
 * Created by Nikhil on 11/03/17.
 */
public class Insect implements GLEventListener {
    private GLU glu = new GLU();
    private GLUquadric qobj;

    private float size;

    private InsectState state;
    private Unproject unproject;

    public Insect(float size, InsectState state, Unproject unproject) {
        this.size = size;
        this.state = state;
        this.unproject = unproject;
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        gl.glPushMatrix();
            gl.glTranslatef(state.posX, size, state.posZ);
            gl.glColor3f(0.0f, 0.0f, 0.0f);
            glu.gluSphere(qobj, size, 36, 18);
        gl.glPopMatrix();

        unproject.changeInsect(state);
        updateState();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        glu.gluDeleteQuadric(qobj);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }

    @Override
    public void init(GLAutoDrawable drawable) {
        qobj = glu.gluNewQuadric();
        glu.gluQuadricNormals(qobj, GLU.GLU_SMOOTH);
    }

    private void updateState() {
        if (Float.isNaN(state.rotX) || Float.isNaN(state.rotZ))
            return;
        float distance = 0.05f;
        state.posX += distance * state.rotX;
        state.posZ += distance * state.rotZ;
    }
}
