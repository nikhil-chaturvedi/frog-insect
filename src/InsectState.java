/**
 * Created by Nikhil on 14/04/17.
 */
public class InsectState {
    float posX;
    float posZ;

    float rotX;
    float rotZ;

    public InsectState(float posX, float posZ) {
        this.posX = posX;
        this.posZ = posZ;

        this.rotX = 0.0f;
        this.rotZ = 1.0f;
    }
}
