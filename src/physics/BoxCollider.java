package physics;
import lib.Vector2D;

public class BoxCollider {
    Vector2D position;
    public int width , height; // chieu rong va cao cua box

    public BoxCollider(Vector2D position, int width, int height){
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public float top() {
        return this.position.y - (float) this.height / 2;
    }

    public float bot() {
        return this.top() + this.height;
    }

    public float left() {
        return this.position.x - (float) this.width / 2;
    }

    public float right() {
        return this.left() + this.width;
    }

    public boolean intersects(BoxCollider other) {
        return this.bot() >= other.top()
                && this.top() <= other.bot()
                && this.left() <= other.right()
                && this.right() >= other.left();

    }
}
