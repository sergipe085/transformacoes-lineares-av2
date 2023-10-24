import biblioteca_matrizes.Vector3;

public class Cube extends Object {
    public Cube() {
        points = new Vector3[] {
            new Vector3(0, 0, 0),  // Ponto 0
            new Vector3(-1, 0, 0),   // Ponto 1
            new Vector3(-1, -1, 0),    // Ponto 2
            new Vector3(0, -1, 0),   // Ponto 3

            // Back face
            new Vector3(0, 0, -1),   // Ponto 4
            new Vector3(-1, 0, -1),    // Ponto 5
            new Vector3(-1, -1, -1),     // Ponto 6
            new Vector3(0, -1, -1),    // Ponto 7

            // Conexões entre as faces
            new Vector3(0, 0, 0),  // Ponto 8 (para conectar com Ponto 4)
            new Vector3(1, 0, 0),   // Ponto 9 (para conectar com Ponto 5)
            new Vector3(0, 1, 0),   // Ponto 10 (para conectar com Ponto 7)
            new Vector3(1, 1, 0),    // Ponto 11 (para conectar com Ponto 6)
        };

        vertexOrder = new int[][]{
            {0, 1, 2, 3, 0},  // Face frontal
            {4, 5, 6, 7, 4},  // Face traseira
            {0, 4},           // Conexões entre as faces
            {1, 5},
            {2, 6},
            {3, 7}
        };
    }
}
