import biblioteca_matrizes.*;
import javafx.scene.shape.Line;

public class Transform {
    private Matriz positionMatrix = new Matriz(4, 4);
    private Matriz rotationXMatrix;
    private Matriz rotationYMatrix;
    private Matriz rotationZMatrix;

    public Vector3 position;
    public Vector3 rotation;
    public Vector3 scale;
    public Vector3 shear;


    public Transform() {
        position = new Vector3(0, 0, 0);
        rotation = new Vector3(0, 0, 0);
        scale = new Vector3(0, 0, 0);
    }

    public Matriz GetTranslationMatrix() {
        int linhas = 4;
        int colunas = 4;
        Matriz translationMatrix = new Matriz(linhas, colunas);
        
        // montando a identidade
        for (int i = 0; i < linhas; i++) {
            translationMatrix.SetValue(i, i, 1);
        }

        translationMatrix.SetValue(0, 3, position.x);
        translationMatrix.SetValue(1, 3, position.y);
        translationMatrix.SetValue(2, 3, position.z);

        return translationMatrix;
    }

    public Matriz Get_X_RotationMatrix() {
        int linhas = 4;
        int colunas = 4;
        Matriz translationMatrix = new Matriz(linhas, colunas);

        // montando a matriz base
        translationMatrix.SetValue(0, 0, 1);
        translationMatrix.SetValue(3, 3, 1);

        double xRotation = Math.toRadians(rotation.x);
        translationMatrix.SetValue(1, 1, Math.cos(xRotation));
        translationMatrix.SetValue(1, 2, -Math.sin(xRotation));

        translationMatrix.SetValue(2, 1, Math.sin(xRotation));
        translationMatrix.SetValue(2, 2, Math.cos(xRotation));


        return translationMatrix;
    }

    public Matriz Get_Y_RotationMatrix() {
        int linhas = 4;
        int colunas = 4;
        Matriz translationMatrix = new Matriz(linhas, colunas);

        // montando a matriz base
        translationMatrix.SetValue(1, 1, 1);
        translationMatrix.SetValue(3, 3, 1);

        double yRotation = Math.toRadians(rotation.y);
        translationMatrix.SetValue(0, 0, Math.cos(yRotation));
        translationMatrix.SetValue(0, 2, Math.sin(yRotation));

        translationMatrix.SetValue(2, 0, -Math.sin(yRotation));
        translationMatrix.SetValue(2, 2, Math.cos(yRotation));


        return translationMatrix;
    }

    public Matriz Get_Z_RotationMatrix() {
        int linhas = 4;
        int colunas = 4;
        Matriz translationMatrix = new Matriz(linhas, colunas);

        // montando a matriz base
        translationMatrix.SetValue(2, 2, 1);
        translationMatrix.SetValue(3, 3, 1);

        double zRotation = Math.toRadians(rotation.z);
        translationMatrix.SetValue(0, 0, Math.cos(zRotation));
        translationMatrix.SetValue(0, 1, -Math.sin(zRotation));

        translationMatrix.SetValue(1, 0, Math.sin(zRotation));
        translationMatrix.SetValue(1, 1, Math.cos(zRotation));


        return translationMatrix;
    }

    public Matriz GetTransformationMatriz() {

        return LinearAlgebra.Dot(
            GetTranslationMatrix(), 
            LinearAlgebra.Dot(Get_Z_ShearMatrix(shear.z),
            LinearAlgebra.Dot(Get_Y_ShearMatrix(shear.y),
            LinearAlgebra.Dot(Get_X_ShearMatrix(shear.x), 
            LinearAlgebra.Dot(Get_Z_RotationMatrix(), 
            LinearAlgebra.Dot(Get_Y_RotationMatrix(), 
            LinearAlgebra.Dot(Get_X_RotationMatrix(), 
            GetScaleMatrix()
        )))))));
    }

    public Matriz GetReflectionMatrix(Vector3 eixo) {
        Matriz I = new Matriz(3, 3);
        I.FillIdentidade();

        Matriz R = LinearAlgebra.Subtracao(I, LinearAlgebra.Times(LinearAlgebra.Dot(eixo.GetMatrix(), LinearAlgebra.Tranposta(eixo.GetMatrix())), 2));
        return R;
    }

    public Matriz Get_X_ProjectionMatrix() {
        Matriz projectionMatrix = new Matriz(4, 4);
        projectionMatrix.FillZero();

        // Preencha os elementos da matriz de projeção
        projectionMatrix.SetValue(0, 0, 1);
        projectionMatrix.SetValue(3, 3, 1);

        return projectionMatrix;
    }

    public Matriz Get_Y_ProjectionMatrix() {
        Matriz projectionMatrix = new Matriz(4, 4);
        projectionMatrix.FillZero();

        // Preencha os elementos da matriz de projeção
        projectionMatrix.SetValue(1, 1, 1);
        projectionMatrix.SetValue(3, 3, 1);

        return projectionMatrix;
    }

    public Matriz Get_Z_ProjectionMatrix() {
        Matriz projectionMatrix = new Matriz(4, 4);
        projectionMatrix.FillZero();

        projectionMatrix.SetValue(2, 2, 1);
        projectionMatrix.SetValue(3, 3, 1);

        return projectionMatrix;
    }

    public Matriz Get_X_ShearMatrix(float k) {
        Matriz shear = new Matriz(4, 4);
        shear.FillIdentidade();

        shear.SetValue(0, 1, k);

        return shear;
    }

    public Matriz Get_Y_ShearMatrix(float k) {
        Matriz shear = new Matriz(4, 4);
        shear.FillIdentidade();

        shear.SetValue(1, 0, k);

        return shear;
    }

    public Matriz Get_Z_ShearMatrix(float k) {
        Matriz projectionMatrix = new Matriz(4, 4);
        projectionMatrix.FillIdentidade();

        projectionMatrix.SetValue(2, 1, 1);

        return projectionMatrix;
    }

    public Matriz GetScaleMatrix() {
        Matriz scaleMatrix = new Matriz(4, 4);
        scaleMatrix.FillZero();

        scaleMatrix.SetValue(0, 0, scale.x);
        scaleMatrix.SetValue(1, 1, scale.y);
        scaleMatrix.SetValue(2, 2, scale.z);
        scaleMatrix.SetValue(3, 3, 1); // O valor 1 na posição (3,3) é para fins de homogeneização.

        return scaleMatrix;
    }
}
