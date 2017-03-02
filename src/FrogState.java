/**
 * Created by Nikhil on 01/03/17.
 */
public class FrogState {
    public float posX;
    public float posY;
    public float posZ;

    public float rotX;
    public float rotZ;

    public float bodyAngle;

    public float armAngle;
    public float handAngle;
    public float palmAngle;

    public float legAngle;
    public float thighAngle;
    public float footAngle;

    public FrogState(float posX, float posY, float posZ, float rotX, float rotZ) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.rotX = rotX;
        this.rotZ = rotZ;

        this.bodyAngle = 10.0f;

        this.armAngle = 50.0f;
        this.handAngle = 105.0f;
        this.palmAngle = 135.0f;

        this.legAngle = 20.0f;
        this.thighAngle = 20.0f;
        this.footAngle = 10.0f;
    }

    public FrogState(float dist, float height, float time, int fps, int state) {
        float frames = time * fps;
        this.posX = dist/frames;
        this.posY = height;
        this.posZ = 0.0f;
        this.rotX = 0.0f;
        this.rotZ = 0.0f;

        this.bodyAngle = -10.0f/frames;

        this.armAngle = -40.0f/frames;
        this.handAngle = 60.0f/frames;
        this.palmAngle = 45.0f/frames;

        this.legAngle = 140.0f/frames;
        this.thighAngle = 140.0f/frames;
        this.footAngle = 150.0f/frames;
    }

    public void add(FrogState diff, int frame, int frames) {
        this.posX += diff.posX;
        this.posY += diff.posY * ((Math.sqrt(frame) - Math.sqrt(frame-1))/Math.sqrt(frames));
        //System.out.println(diff.posY * ((Math.sqrt(frame) - Math.sqrt(frame-1))/Math.sqrt(frames)));
        this.posZ += diff.posZ;

        this.bodyAngle += diff.bodyAngle;

        this.armAngle += diff.armAngle;
        this.handAngle += diff.handAngle;
        this.palmAngle += diff.palmAngle;

        this.legAngle += diff.legAngle;
        this.thighAngle += diff.thighAngle;
        this.footAngle += diff.footAngle;
    }
}
