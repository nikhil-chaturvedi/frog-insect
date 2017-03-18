import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

/**
 * Created by Nikhil on 02/03/17.
 */
public class Floor implements GLEventListener {
    private GLU glu = new GLU();

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
        gl.glLoadIdentity();
        //glu.gluLookAt(-1.0f, 0.25f, 0.75f, 0.0f, 0.25f, 0.0f, 0.0f, 1.0f, 0.0f);
        glu.gluLookAt(-15.0f, 6.0f, 15.0f, 10.0f, -6.0f, -10.0f, 0.0f, 1.0f, 0.0f);

        gl.glColor3f(0.8f, 0.8f, 0.8f);

        float y = 0.0f;

        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex3f(-10.0f, y, -10.0f);
        gl.glVertex3f(-10.0f, y, 10.0f);
        gl.glVertex3f(10.0f, y, 10.0f);
        gl.glVertex3f(10.0f, y, -10.0f);
        gl.glEnd();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        if( height <= 0 )
            height = 1;

        final float h = ( float ) width / ( float ) height;
        gl.glViewport( 0, 0, width, height );
        gl.glMatrixMode( GL2.GL_PROJECTION );
        gl.glLoadIdentity();

        glu.gluPerspective( 45.0f, h, 0.1, 100.0 );
        gl.glMatrixMode( GL2.GL_MODELVIEW );
        gl.glLoadIdentity();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel( GL2.GL_SMOOTH );
        gl.glClearColor( 0f, 0f, 0f, 0f );
        gl.glClearDepth( 1.0f );
        gl.glEnable( GL2.GL_DEPTH_TEST );
        gl.glDepthFunc( GL2.GL_LEQUAL );
        gl.glHint( GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST );
    }
}
