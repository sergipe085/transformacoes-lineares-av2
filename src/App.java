import biblioteca_matrizes.Vector3;

public class App {
    public static void main(String[] args) throws Exception {


        Cube objectTest = new Cube();
        objectTest.transform.position = new Vector3(512, 388, 0);
        objectTest.transform.rotation = new Vector3(0, 20, 0);
        objectTest.transform.scale = new Vector3(150 , 150 , 150 );
        objectTest.transform.shear = new Vector3(0,0,0);


        Object[] objects = new Object[] {
            objectTest
        };
        Drawing drawing = new Drawing(objects);
        drawing.OpenWindow();
    }
}
