/**
 * Created by Nikhil on 07/03/17.
 */
public class Keyframe {
    private int frames;

    private class Change {
        public float diff;
        public float rate;

        public Change(float diff, float rate) {
            this.diff = diff;
            this.rate = rate;
        }
    }

    private Change posX;
    private Change posY;
    private Change posZ;

    private Change legAngle;
    private Change thighAngle;
    private Change footAngle;

    private Change armAngle;
    private Change handAngle;
    private Change palmAngle;

    public Keyframe(int frames,
                    float diffX, float diffY, float diffZ,
                    float rateX, float rateY, float rateZ,
                    float diffLeg, float diffThigh, float diffFoot,
                    float rateLeg, float rateThigh, float rateFoot,
                    float diffArm, float diffHand, float diffPalm,
                    float rateArm, float rateHand, float ratePalm
    ) {
        this.frames = frames;

        this.posX = new Change(diffX, rateX);
        this.posY = new Change(diffY, rateY);
        this.posZ = new Change(diffZ, rateZ);

        this.legAngle = new Change(diffLeg, rateLeg);
        this.thighAngle = new Change(diffThigh, rateThigh);
        this.footAngle = new Change(diffFoot, rateFoot);

        this.armAngle = new Change(diffArm, rateArm);
        this.handAngle = new Change(diffHand, rateHand);
        this.palmAngle = new Change(diffPalm, ratePalm);
    }

    
}
