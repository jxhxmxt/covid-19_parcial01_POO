import java.util.ArrayList;

public class ServicioProfesional extends Empleado {
    private int mesescontrato;

    public ServicioProfesional(String nombre, String puesto, double salario, int mesescontrato) {
        super(nombre, puesto, salario);
        this.mesescontrato = mesescontrato;
    }

    public int getMesescontrato() {
        return mesescontrato;
    }

    public void setMesescontrato(int mesescontrato) {
        this.mesescontrato = mesescontrato;
    }
}
