import java.util.ArrayList;

public class Empresa {
    private String nombre;
    private ArrayList<Empleado> planilla;

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public void addEmpleado(Empleado e) throws AlreadyExistEmployeeException {
        boolean existe = false;
        try{
            for(Empleado empleado : planilla){
                if(e.getNombre().equalsIgnoreCase(e.getNombre()))
                    existe = true;
            }
            if(existe)
                throw new AlreadyExistEmployeeException("Ya esta ese empleado en la planilla, ingrese otro nombre.");

            planilla.add(e);
        }catch(AlreadyExistEmployeeException ex){
            System.out.println(ex.getMessage());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void quitEmpleado(String nombre) throws AlreadyExistEmployeeException {
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
            System.out.println(excep.getMessage());

        }catch(Exception excep){
            System.out.println(excep.getMessage());

        }
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Empleado> getPlanilla() {
        return planilla;
    }
}
