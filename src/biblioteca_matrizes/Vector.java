package biblioteca_matrizes;
public class Vector {
    private int dimensao;
    private double[] elementos;

    public int getDimensao(){
        return dimensao;
    }
    
    public double[] getElementos(){
        return elementos;
    }

    public Vector(int dimensao, double[] elementos){
        this.dimensao = dimensao;
        elementos = new double[dimensao];
    }

    public double GetValue(int dimensao){
        return elementos[dimensao];
    }

    public double SetValue(int dimensao){
        return elementos[dimensao];
    }

    public Vector(Vector vector){
        dimensao = vector.getDimensao();
        elementos = new double[dimensao];
        for( int i = 0; i<dimensao; i++){
            elementos[i] = vector.GetValue(i);
        }
    }

    public Vector(Matriz matriz) {
        dimensao = matriz.GetLinhas();
        elementos = new double[dimensao];
        for( int i = 0; i<dimensao; i++){
            elementos[i] = matriz.GetValue(i, 0);
        }
    }
}
