import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.File;
import java.io.IOException;

/**
 * Created by Nikhil on 21/02/17.
 */
public class Frog implements GLEventListener {
    private GLU glu = new GLU();
    private GLUquadric qobj;

    private float length;
    private float width;
    private float thickness;

    private float midLength;
    private float midWidth;

    private float armLength;
    private float handLength;
    private float palmLength;
    private float armThickness;

    private float legLength;
    private float thighLength;
    private float footLength;
    private float legThickness;

    private FrogState state;
    private FrogState diff;

    private int frame;
    private int texture;

    public Frog() {
        this.length = 0.4f;
        this.width = 0.2f;
        this.thickness = 0.07f;

        final float lengthRatio = 0.75f;
        final float widthRatio = 0.7f;

        this.midLength = lengthRatio * this.length;
        this.midWidth = widthRatio * this.width;

        this.armLength = 0.0895f;
        this.handLength = 0.05f;
        this.palmLength = 0.05f;
        this.armThickness = 0.03f;

        this.legLength = 0.2f;
        this.thighLength = 0.15f;
        this.footLength = 0.1f;
        this.legThickness = 0.04f;

        this.state = new FrogState(0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.diff = new FrogState(1.0f, 0.5f, 0.5f, 300, 0);

        this.frame = 0;


    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
        gl.glLoadIdentity();
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        //glu.gluLookAt(-9.0f, 0.5f, 0.75f, -8.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        //glu.gluLookAt(-0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        //gl.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        glu.gluLookAt(1.0f, 0.0f, 3.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);

        gl.glPushMatrix();
            gl.glTranslatef(state.posX, state.posY, state.posZ);
            gl.glRotatef(state.bodyAngle, 0.0f, 0.0f, 1.0f);
            body(gl);

            gl.glPushMatrix();
                gl.glTranslatef(-midLength/2, 0.0f, midWidth/2);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(state.legAngle, 1.0f, 0.0f, 0.0f);
                limb(gl, legLength, legThickness, 0.5f);
                gl.glPushMatrix();
                    gl.glTranslatef(0.0f, 0.0f, legLength);
                    gl.glRotatef(180.0f-state.thighAngle, 1.0f, 0.0f, 0.0f);
                    limb(gl, thighLength, legThickness/2, 1.0f);
                    gl.glPushMatrix();
                        gl.glTranslatef(0.0f, 0.0f, thighLength);
                        gl.glRotatef(state.footAngle-180.0f, 1.0f, 0.0f, 0.0f);
                        appendage(gl, footLength, legThickness/2, 3*legThickness/2);
                    gl.glPopMatrix();
                gl.glPopMatrix();
            gl.glPopMatrix();

            gl.glPushMatrix();
                gl.glTranslatef(-midLength/2, 0.0f, -midWidth/2);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(state.legAngle, 1.0f, 0.0f, 0.0f);
                limb(gl, legLength, legThickness, 0.5f);
                gl.glPushMatrix();
                    gl.glTranslatef(0.0f, 0.0f, legLength);
                    gl.glRotatef(180.0f-state.thighAngle, 1.0f, 0.0f, 0.0f);
                    limb(gl, thighLength, legThickness/2, 1.0f);
                    gl.glPushMatrix();
                        gl.glTranslatef(0.0f, 0.0f, thighLength);
                        gl.glRotatef(state.footAngle-180.0f, 1.0f, 0.0f, 0.0f);
                        appendage(gl, footLength, legThickness/2, 3*legThickness/2);
                    gl.glPopMatrix();
                gl.glPopMatrix();
            gl.glPopMatrix();

            gl.glPushMatrix();
                gl.glTranslatef(midLength/2, 0.0f, midWidth/4);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(180.0f-state.armAngle, 1.0f, 0.0f, 0.0f);
                limb(gl, armLength, armThickness, 0.5f);
                gl.glPushMatrix();
                    gl.glTranslatef(0.0f, 0.0f, armLength);
                    gl.glRotatef(state.handAngle-180.0f, 1.0f, 0.0f, 0.0f);
                    limb(gl, handLength, armThickness/2, 1.0f);
                    gl.glPushMatrix();
                        gl.glTranslatef(0.0f, 0.0f, handLength);
                        gl.glRotatef(state.palmAngle-180.0f, 1.0f, 0.0f, 0.0f);
                        appendage(gl, palmLength, armThickness/2, 3*armThickness/2);
                    gl.glPopMatrix();
                gl.glPopMatrix();
            gl.glPopMatrix();

            gl.glPushMatrix();
                gl.glTranslatef(midLength/2, 0.0f, -midWidth/4);
                gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                gl.glRotatef(180.0f-state.armAngle, 1.0f, 0.0f, 0.0f);
                limb(gl, armLength, armThickness, 0.5f);
                gl.glPushMatrix();
                    gl.glTranslatef(0.0f, 0.0f, armLength);
                    gl.glRotatef(state.handAngle-180.0f, 1.0f, 0.0f, 0.0f);
                    limb(gl, handLength, armThickness/2, 1.0f);
                    gl.glPushMatrix();
                        gl.glTranslatef(0.0f, 0.0f, handLength);
                        gl.glRotatef(state.palmAngle-180.0f, 1.0f, 0.0f, 0.0f);
                        appendage(gl, palmLength, armThickness/2, 3*armThickness/2);
                    gl.glPopMatrix();
                gl.glPopMatrix();
            gl.glPopMatrix();

        gl.glPopMatrix();

        this.frame += 1;
        if (this.frame <= 150)
            this.state.add(this.diff, this.frame, 150);
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

        gl.glEnable(GL2.GL_TEXTURE_2D);
        try{

            File im = new File("C:\\2016-17 Second Sem\\COL781\\frog-insect\\reptiles_texture817.jpg");
            Texture t = TextureIO.newTexture(im, true);
            texture= t.getTextureObject(gl);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void body(GL2 gl) {
        gl.glColor3f(0.0f, 1.0f, 0.0f);

        gl.glBegin(GL2.GL_POLYGON);
        gl.glTexCoord2f(0.25f,0.0f);gl.glVertex3f(length/2, thickness/2, 0.0f);
        gl.glTexCoord2f(0.075f,0.125f);gl.glVertex3f(midLength/2, thickness/2, midWidth/2);
        gl.glTexCoord2f(0.0f,0.875f);gl.glVertex3f(-midLength/2, thickness/2, width/2);
        gl.glTexCoord2f(0.25f,1.0f);gl.glVertex3f(-length/2, thickness/2, 0.0f);
        gl.glTexCoord2f(0.5f,0.875f);gl.glVertex3f(-midLength/2, thickness/2, -width/2);
        gl.glTexCoord2f(0.425f,0.125f);gl.glVertex3f(midLength/2, thickness/2, -midWidth/2);
        gl.glEnd();

        gl.glBegin(GL2.GL_POLYGON);
        gl.glTexCoord2f(0.25f,0.0f);gl.glVertex3f(length/2, -thickness/2, 0.0f);
        gl.glTexCoord2f(0.075f,0.125f);gl.glVertex3f(midLength/2, -thickness/2, midWidth/2);
        gl.glTexCoord2f(0.0f,0.875f);gl.glVertex3f(-midLength/2, -thickness/2, width/2);
        gl.glTexCoord2f(0.25f,1.0f);gl.glVertex3f(-length/2, -thickness/2, 0.0f);
        gl.glTexCoord2f(0.5f,0.875f);gl.glVertex3f(-midLength/2, -thickness/2, -width/2);
        gl.glTexCoord2f(0.425f,0.125f);gl.glVertex3f(midLength/2, -thickness/2, -midWidth/2);
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
        gl.glColor3f(0.0f, 0.225f, 0.0f);
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
