import java.awt.Graphics;

import biblioteca_matrizes.*;

public class Object {
    public Transform transform;
    public Vector3[] points = new Vector3[] {
        new Vector3(0, 0, 0),        
        new Vector3(50, 0, 0),
        new Vector3(50, 50, 0),
        new Vector3(0, 50, 0),        

    };

    public Vector3[] GetTransformedPoints() {
        Vector3[] transformedPoints = new Vector3[points.length];
        for (int i = 0; i < points.length; i++) {
            Vector3 point = points[i];
            System.out.println(transform.GetTransformationMatriz());
            transformedPoints[i] = LinearAlgebra.Dot(transform.GetTransformationMatriz(),point);
        }

        return transformedPoints;
    }

    public Object() {
        transform = new Transform();
        transform.position = new Vector3(50, 50, 0);
        transform.rotation = new Vector3(0, 0, 0);
        transform.scale = new Vector3(0, 0, 0);
    }

    public void Move() {
        transform.position.x += 0.0001f;
    }

    public void Draw(Graphics g) {
        Vector3[] _points = GetTransformedPoints();
        for (int i = 0; i < _points.length; i++) {
            Vector3 from = _points[i];
            int toIndex = i + 1;
            if (toIndex >= _points.length) {
                toIndex = 0;
            }

            Vector3 to = _points[toIndex];
            System.out.println(to.x);
            g.drawLine((int)from.x, (int)from.y, (int)to.x, (int)to.y);
        }
    }
}
