package lib;

public class Vector2D {
    public float x;
    public float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(){
        this.x = 0;
        this.y = 0;
    }

    public Vector2D setVector2D(float x, float y){
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2D addThis(float x , float y){
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2D subtract(float x, float y) {
        return new Vector2D(this.x - x, this.y - y);
    }
    public Vector2D subtractThis(float x , float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public float getlength() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2D setLength(float length) {
        if (this.getlength() == 0){
            return this;
        }
        float rate = length / this.getlength();
        return new Vector2D(this.x * rate, this.y * rate);
    }

    public Vector2D setLengthThis(float length) {
        if (this.getlength() == 0){
            return this;
        }
        float rate = length / this.getlength();
        this.x = this.x *rate;
        this.y = this.y *rate;
        return this;
    }

    public double getAngle() {
        return Math.toDegrees(Math.atan(this.x / this.y));
    }

    public double getAngleVsOx() {
        return Math.toDegrees(Math.acos(this.y / this.getlength()));
    }

    public Vector2D setAngle(double angle) {
        double rads = Math.toRadians(angle);
        float length = this.getlength();
        return new Vector2D(length * (float) Math.cos(rads), length * (float) Math.sin(rads));
    }

    public Vector2D setAngleThis(double angle) {
        double rads = Math.toRadians(angle);
        float length = this.getlength();
        this.x = length * (float) Math.cos(rads);
        this.y = length * (float) Math.sin(rads);
        return this;
    }
}
