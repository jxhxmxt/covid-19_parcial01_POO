import javax.swing.*;
import java.util.ArrayList;

public class Empresa {
    private String nombre;
    private ArrayList<Empleado> planilla;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.planilla = new ArrayList<>();
        planilla=new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Empleado> getPlanilla() {
        return planilla;
    }

    public void addEmpleado(Empleado e) {
        boolean existe = false;
        try{
            for(Empleado empleado : planilla){
				if(empleado.getNombre().equalsIgnoreCase(e.getNombre()))
                    existe = true;
            }
            if(existe)
                throw new AlreadyExistEmployeeException("Ya esta ese empleado en la planilla, ingrese otro nombre.");

            planilla.add(e);
        }catch(AlreadyExistEmployeeException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void quitEmpleado(String nombre) {
        try{
            Empleado aux = null;

            for(Empleado empleado : planilla){
                if(empleado.getNombre().equals(nombre))
                    aux = empleado;
            }

            if(aux != null) {
                planilla.remove(aux);
            }
            else
                throw new AlreadyExistEmployeeException("Empleado no ecnontrado en la planilla, no es posible despedir");

        }catch (AlreadyExistEmployeeException excep){
            JOptionPane.showMessageDialog(null, excep.getMessage());

        }catch(Exception excep){
            JOptionPane.showMessageDialog(null, excep.getMessage());
        }
    }
}
