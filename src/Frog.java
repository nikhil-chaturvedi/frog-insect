import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

/**
 * Created by Nikhil on 21/02/17.
 */


public class Frog implements GLEventListener {
    private GLU glu = new GLU();
    private GLUquadric qobj;

    private float length;
    private float width;
    private float thickness;

    private float lengthRatio;
    private float widthRatio;

    private float midLength;
    private float midWidth;

    Frog() {
        this.length = 0.4f;
        this.width = 0.2f;
        this.thickness = 0.07f;

        this.lengthRatio = 0.75f;
        this.widthRatio = 0.7f;

        this.midLength = this.lengthRatio * this.length;
        this.midWidth = this.widthRatio * this.width;
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
        gl.glLoadIdentity();
        glu.gluLookAt(-0.5f, 0.25f, 0.75f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        //gl.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);

        gl.glPushMatrix();
            body(gl);

            gl.glPushMatrix();
                gl.glTranslatef(-midLength/2, 0.0f, midWidth/2);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(15.0f, 1.0f, 0.0f, 0.0f);
                limb(gl, 0.2f, 0.04f, 0.5f);
                gl.glPushMatrix();
                    gl.glTranslatef(0.0f, 0.0f, 0.2f);
                    gl.glRotatef(150.0f, 1.0f, 0.0f, 0.0f);
                    limb(gl, 0.15f, 0.02f, 1.0f);
                    gl.glPushMatrix();
                        gl.glTranslatef(0.0f, 0.0f, 0.15f);
                        gl.glRotatef(-165.0f, 1.0f, 0.0f, 0.0f);
                        appendage(gl, 0.1f, 0.02f, 0.06f);
                    gl.glPopMatrix();
                gl.glPopMatrix();
            gl.glPopMatrix();

            gl.glPushMatrix();
                gl.glTranslatef(-midLength/2, 0.0f, -midWidth/2);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(15.0f, 1.0f, 0.0f, 0.0f);
                limb(gl, 0.2f, 0.04f, 0.5f);
                gl.glPushMatrix();
                    gl.glTranslatef(0.0f, 0.0f, 0.2f);
                    gl.glRotatef(150.0f, 1.0f, 0.0f, 0.0f);
                    limb(gl, 0.15f, 0.02f, 1.0f);
                    gl.glPushMatrix();
                        gl.glTranslatef(0.0f, 0.0f, 0.15f);
                        gl.glRotatef(-165.0f, 1.0f, 0.0f, 0.0f);
                        appendage(gl, 0.1f, 0.02f, 0.06f);
                    gl.glPopMatrix();
                gl.glPopMatrix();
            gl.glPopMatrix();

            gl.glPushMatrix();
                gl.glTranslatef(midLength/2, 0.0f, midWidth/4);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(135.0f, 1.0f, 0.0f, 0.0f);
                limb(gl, 0.1f, 0.03f, 0.5f);
                gl.glPushMatrix();
                    gl.glTranslatef(0.0f, 0.0f, 0.1f);
                    gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                    limb(gl, 0.05f, 0.015f, 1.0f);
                    gl.glPushMatrix();
                        gl.glTranslatef(0.0f, 0.0f, 0.05f);
                        gl.glRotatef(-45.0f, 1.0f, 0.0f, 0.0f);
                        appendage(gl, 0.05f, 0.015f, 0.045f);
                    gl.glPopMatrix();
                gl.glPopMatrix();
            gl.glPopMatrix();

            gl.glPushMatrix();
                gl.glTranslatef(midLength/2, 0.0f, -midWidth/4);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(135.0f, 1.0f, 0.0f, 0.0f);
                limb(gl, 0.1f, 0.03f, 0.5f);
                gl.glPushMatrix();
                    gl.glTranslatef(0.0f, 0.0f, 0.1f);
                    gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                    limb(gl, 0.05f, 0.015f, 1.0f);
                    gl.glPushMatrix();
                        gl.glTranslatef(0.0f, 0.0f, 0.05f);
                        gl.glRotatef(-45.0f, 1.0f, 0.0f, 0.0f);
                        appendage(gl, 0.05f, 0.015f, 0.045f);
                    gl.glPopMatrix();
                gl.glPopMatrix();
            gl.glPopMatrix();

        gl.glPopMatrix();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        glu.gluDeleteQuadric(qobj);
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

        qobj = glu.gluNewQuadric();
        glu.gluQuadricNormals(qobj, GLU.GLU_SMOOTH);
    }

    private void body(GL2 gl) {
        gl.glColor3f(0.0f, 1.0f, 0.0f);

        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex3f(length/2, thickness/2, 0.0f);
        gl.glVertex3f(midLength/2, thickness/2, midWidth/2);
        gl.glVertex3f(-midLength/2, thickness/2, width/2);
        gl.glVertex3f(-length/2, thickness/2, 0.0f);
        gl.glVertex3f(-midLength/2, thickness/2, -width/2);
        gl.glVertex3f(midLength/2, thickness/2, -midWidth/2);
        gl.glEnd();

        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex3f(length/2, -thickness/2, 0.0f);
        gl.glVertex3f(midLength/2, -thickness/2, midWidth/2);
        gl.glVertex3f(-midLength/2, -thickness/2, width/2);
        gl.glVertex3f(-length/2, -thickness/2, 0.0f);
        gl.glVertex3f(-midLength/2, -thickness/2, -width/2);
        gl.glVertex3f(midLength/2, -thickness/2, -midWidth/2);
        gl.glEnd();

        gl.glColor3f(0.0f, 0.5f, 0.0f);

        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex3f(length/2, thickness/2, 0.0f);
        gl.glVertex3f(length/2, -thickness/2, 0.0f);
        gl.glVertex3f(midLength/2, -thickness/2, midWidth/2);
        gl.glVertex3f(midLength/2, thickness/2, midWidth/2);
        gl.glEnd();

        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex3f(midLength/2, thickness/2, midWidth/2);
        gl.glVertex3f(midLength/2, -thickness/2, midWidth/2);
        gl.glVertex3f(-midLength/2, -thickness/2, width/2);
        gl.glVertex3f(-midLength/2, thickness/2, width/2);
        gl.glEnd();

        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex3f(-midLength/2, thickness/2, width/2);
        gl.glVertex3f(-midLength/2, -thickness/2, width/2);
        gl.glVertex3f(-length/2, -thickness/2, 0.0f);
        gl.glVertex3f(-length/2, thickness/2, 0.0f);
        gl.glEnd();

        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex3f(-length/2, thickness/2, 0.0f);
        gl.glVertex3f(-length/2, -thickness/2, 0.0f);
        gl.glVertex3f(-midLength/2, -thickness/2, -width/2);
        gl.glVertex3f(-midLength/2, thickness/2, -width/2);
        gl.glEnd();

        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex3f(-midLength/2, thickness/2, -width/2);
        gl.glVertex3f(-midLength/2, -thickness/2, -width/2);
        gl.glVertex3f(midLength/2, -thickness/2, -midWidth/2);
        gl.glVertex3f(midLength/2, thickness/2, -midWidth/2);
        gl.glEnd();

        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex3f(midLength/2, thickness/2, -midWidth/2);
        gl.glVertex3f(midLength/2, -thickness/2, -midWidth/2);
        gl.glVertex3f(length/2, -thickness/2, 0.0f);
        gl.glVertex3f(length/2, thickness/2, 0.0f);
        gl.glEnd();
    }

    private void limb(GL2 gl, float height, float thickness, float ratio) {
        gl.glColor3f(0.0f, 0.75f, 0.0f);
        glu.gluCylinder(qobj, thickness/2, (ratio*thickness)/2, height, 40, 40);
    }

    private void appendage(GL2 gl, float height, float width1, float width2) {
        gl.glColor3f(0.0f, 1.0f, 0.0f);

        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex3f(width1/2, 0.0f, 0.0f);
        gl.glVertex3f(-width1/2, 0.0f, 0.0f);
        gl.glVertex3f(-width2/2, 0.0f, height);
        gl.glVertex3f(width2/2, 0.0f, height);
        gl.glEnd();
    }
}
