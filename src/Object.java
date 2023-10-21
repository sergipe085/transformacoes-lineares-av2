import java.awt.Graphics;

import biblioteca_matrizes.*;

public class Object {
    public Transform transform;

    public void Draw(Graphics g) {
        Vector3 vector = new Vector3(0, 0, 0);
        Matriz transformationMatrix = transform.GetTransformationMatriz();
    }
}
