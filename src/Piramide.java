import biblioteca_matrizes.Vector3;

public class Piramide extends Object {
    public Piramide() {
        points = new Vector3[] {
            new Vector3(0, 0, 0),     // Ponto 0 (vértice superior)
            new Vector3(-1, -1, -1),  // Ponto 1 (base da pirâmide)
            new Vector3(1, -1, -1),   // Ponto 2 (base da pirâmide)
            new Vector3(1, -1, 1),    // Ponto 3 (base da pirâmide)
            new Vector3(-1, -1, 1),   // Ponto 4 (base da pirâmide)
        };

        vertexOrder = new int[][]{
            {0, 1, 2, 3, 4, 1},  // Face frontal
            {0, 1, 2, 0},       // Face traseira (triângulo)
            {0, 1, 3},           // Conexões entre a face frontal e a base
            {0, 3, 4},           // Conexões entre a face frontal e a base
            {0, 4, 1},           // Conexões entre a face frontal e a base
            {1, 2, 3},           // Base
            {1, 3, 4},           // Base
        };
    }
}
