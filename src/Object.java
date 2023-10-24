import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import biblioteca_matrizes.*;

public class Object {
    public Transform transform;
    public Vector3[] points;

    public int[][] vertexOrder;

    public Vector3[] GetTransformedPoints(Vector3[] points) {
        Vector3[] transformedPoints = new Vector3[points.length];
        for (int i = 0; i < points.length; i++) {
            Vector3 point = points[i];
            
            transformedPoints[i] = LinearAlgebra.Dot(transform.GetTransformationMatriz(),point);
        }

        return transformedPoints;
    }

    public Vector3[] GetReflectedPoints(Vector3[] points) {
        Vector3[] transformedPoints = new Vector3[points.length];
        for (int i = 0; i < points.length; i++) {
            Vector3 point = points[i];
            transformedPoints[i] = LinearAlgebra.Dot3(transform.GetReflectionMatrix(new Vector3(0, 1,0)),point);
        }

        return transformedPoints;
    }

    public Vector3[] GetPerspectivePoints(Vector3[] points, Camera camera) {
        Vector3[] perspectivePoints = new Vector3[points.length];
        for (int i = 0; i < points.length; i++) {
            Vector3 point = points[i];


            perspectivePoints[i] = LinearAlgebra.Dot(camera.GetPerspectiveMatrix(),point);
        }

        return perspectivePoints;
    }

    public Vector3[] Get_X_ProjectedPoints(Vector3[] points) {
        Vector3[] projectedPoints = new Vector3[points.length];
        for (int i = 0; i < points.length; i++) {
            Vector3 point = points[i];


            projectedPoints[i] = LinearAlgebra.Dot(transform.Get_X_ProjectionMatrix(), point);
        }

        return projectedPoints;
    }

    public Vector3[] Get_Y_ProjectedPoints(Vector3[] points) {
        Vector3[] projectedPoints = new Vector3[points.length];
        for (int i = 0; i < points.length; i++) {
            Vector3 point = points[i];


            projectedPoints[i] = LinearAlgebra.Dot(transform.Get_Y_ProjectionMatrix(), point);
        }

        return projectedPoints;
    }

    public Vector3[] Get_Z_ProjectedPoints(Vector3[] points) {
        Vector3[] projectedPoints = new Vector3[points.length];
        for (int i = 0; i < points.length; i++) {
            Vector3 point = points[i];


            projectedPoints[i] = LinearAlgebra.Dot(transform.Get_Z_ProjectionMatrix(), point);
        }

        return projectedPoints;
    }

    public Object() {
        transform = new Transform();
        transform.position = new Vector3(0, 0, 0);
        transform.rotation = new Vector3(0, 0, 0);
        transform.scale = new Vector3(1, 1, 1);
        transform.shear = new Vector3(0, 0, 0);
    }

    public void Move() {
        transform.position.x += 0.01f;
    }

    public void DrawObject(Graphics g) {
        Camera camera = new Camera();
        System.out.println("Drawing");
        Vector3[] transformedPoints = GetTransformedPoints(this.points);
        Vector3[] perspectivedPoints = GetPerspectivePoints(transformedPoints, camera);
        
        Vector3[] pointsToDraw = perspectivedPoints;
    
        DrawPoints(g, pointsToDraw, vertexOrder);
    }

    public void Draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(8));
        
        DrawObject(g);


        g2.setStroke(new BasicStroke(2));
        g.setColor(Color.MAGENTA);
        DrawReflection(g);

        g2.setStroke(new BasicStroke(8));
        g.setColor(Color.RED);
        Draw_X_Projection(g);
        Draw_Y_Projection(g);
        Draw_Z_Projection(g);

    }

    public void DrawPoints(Graphics g, Vector3[] pointsToDraw, int[][] vertexOrder) {
        for (int[] face : vertexOrder) {
            for (int i = 0; i < face.length - 1; i++) {
                Vector3 from = pointsToDraw[face[i]];
                Vector3 to = pointsToDraw[face[i + 1]];
                g.drawLine((int) from.x, (int) from.y, (int) to.x, (int) to.y);
            }
        }
    }

    public void DrawReflection(Graphics g) {
        Camera camera = new Camera();
        Vector3[] reflectedPoints = GetReflectedPoints(this.points);
        Vector3[] transformedPoints = GetTransformedPoints(reflectedPoints);
        Vector3[] perspectivedPoints = GetPerspectivePoints(transformedPoints, camera);
        
        Vector3[] pointsToDraw = perspectivedPoints;
    
        DrawPoints(g, pointsToDraw, vertexOrder);
    }

    public void Draw_X_Projection(Graphics g) {
        Camera camera = new Camera();
        Vector3[] transformedPoints = GetTransformedPoints(this.points);
        Vector3[] projectedPoints = Get_X_ProjectedPoints(transformedPoints);
        Vector3[] perspectivedPoints = GetPerspectivePoints(projectedPoints, camera);
        
        Vector3[] pointsToDraw = perspectivedPoints;
    
        DrawPoints(g, pointsToDraw, vertexOrder);
    }

    public void Draw_Y_Projection(Graphics g) {
        Camera camera = new Camera();
        Vector3[] transformedPoints = GetTransformedPoints(this.points);
        Vector3[] projectedPoints = Get_Y_ProjectedPoints(transformedPoints);
        Vector3[] perspectivedPoints = GetPerspectivePoints(projectedPoints, camera);
        
        Vector3[] pointsToDraw = perspectivedPoints;
    
        DrawPoints(g, pointsToDraw, vertexOrder);
    }

    public void Draw_Z_Projection(Graphics g) {
        Camera camera = new Camera();
        Vector3[] transformedPoints = GetTransformedPoints(this.points);
        Vector3[] projectedPoints = Get_Z_ProjectedPoints(transformedPoints);
        Vector3[] perspectivedPoints = GetPerspectivePoints(projectedPoints, camera);
        
        Vector3[] pointsToDraw = perspectivedPoints;
    
        DrawPoints(g, pointsToDraw, vertexOrder);
    }
}
