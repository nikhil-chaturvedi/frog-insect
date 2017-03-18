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

        this.bodyAngle = 15.0f;

        this.armAngle = 45.0f;
        this.handAngle = 105.0f;
        this.palmAngle = 135.0f;

        this.legAngle = 30.0f;
        this.thighAngle = 30.0f;
        this.footAngle = 15.0f;
    }
}
