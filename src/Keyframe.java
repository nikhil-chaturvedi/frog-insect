import java.util.ArrayList;

/**
 * Created by Nikhil on 07/03/17.
 */
public class Keyframe {
    private int frames;

    private class Change {
        private float diff;
        private float rate;

        Change(float diff, float rate) {
            this.diff = diff;
            this.rate = rate;
        }

        float changeValue(int frame) {
            return diff * (float)((Math.pow(frame, rate) - Math.pow(frame-1, rate))/Math.pow(frames, rate));
        }
    }

    private Change dist;
    private Change height;

    private Change bodyAngle;

    private Change legAngle;
    private Change thighAngle;
    private Change footAngle;

    private Change armAngle;
    private Change handAngle;
    private Change palmAngle;

    public Keyframe(int frames,
                    float diffDist, float diffHeight, float diffBody,
                    float rateDist, float rateHeight, float rateBody,
                    float diffLeg, float diffThigh, float diffFoot,
                    float rateLeg, float rateThigh, float rateFoot,
                    float diffArm, float diffHand, float diffPalm,
                    float rateArm, float rateHand, float ratePalm
    ) {
        this.frames = frames;

        this.dist = new Change(diffDist, rateDist);
        this.height = new Change(diffHeight, rateHeight);

        this.bodyAngle = new Change(diffBody, rateBody);

        this.legAngle = new Change(diffLeg, rateLeg);
        this.thighAngle = new Change(diffThigh, rateThigh);
        this.footAngle = new Change(diffFoot, rateFoot);

        this.armAngle = new Change(diffArm, rateArm);
        this.handAngle = new Change(diffHand, rateHand);
        this.palmAngle = new Change(diffPalm, ratePalm);
    }

    public static ArrayList<Keyframe> populateKeyframes(float dist, float height) {
        ArrayList<Keyframe> keyframes = new ArrayList<>();

        keyframes.add(new Keyframe(
                100,
                dist/2, height, 10.0f,
                1.0f, 2.0f, 1.0f,
                120.0f, 140.0f, 150.0f,
                1.0f, 1.0f, 1.0f,
                -40.0f, 60.0f, 45.0f,
                1.0f, 1.0f, 1.0f
        ));

        keyframes.add(new Keyframe(
                100,
                dist/2, -height, -10.0f,
                1.0f, 2.0f, 1.0f,
                -120.0f, -140.0f, -150.0f,
                1.0f, 1.0f, 1.0f,
                40.0f, -60.0f, -45.0f,
                1.0f, 1.0f, 1.0f
        ));

        return keyframes;
    }

    public void changeState(FrogState state, int frame) {
        state.posX += state.rotX * dist.changeValue(frame);
        state.posZ += state.rotZ * dist.changeValue(frame);
        state.posY += height.changeValue(frame);

        state.bodyAngle += this.bodyAngle.changeValue(frame);

        state.legAngle += this.legAngle.changeValue(frame);
        state.thighAngle += this.thighAngle.changeValue(frame);
        state.footAngle += this.footAngle.changeValue(frame);

        state.armAngle += this.armAngle.changeValue(frame);
        state.handAngle += this.handAngle.changeValue(frame);
        state.palmAngle += this.palmAngle.changeValue(frame);
    }

    public int getFrames() {
        return frames;
    }
}
