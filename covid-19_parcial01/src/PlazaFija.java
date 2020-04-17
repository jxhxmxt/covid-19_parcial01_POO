import java.util.ArrayList;

public class PlazaFija extends Empleado {
    private int extension;

    public PlazaFija(String nombre, String puesto, double salario, int extension) {
        super(nombre, puesto, salario);
        this.extension = extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    public int getExtension() {
        return extension;
    }
}
