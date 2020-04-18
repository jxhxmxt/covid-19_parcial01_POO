import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //Declaracion de escaner global
    static Scanner in = new Scanner(System.in);
    static Empresa unaEmpresa;

    public static void main(String[] args) {
        //Creando una empresa
        System.out.print("Introduzca el nombre de la emprea");
        String nombreEmpresa = in.nextLine();
        unaEmpresa = new Empresa(nombreEmpresa);

        int op;
        do{
            JOptionPane.showMessageDialog(null, MenuPrincipal());
            op = in.nextByte(); in.nextLine();

            switch (op){
                case 1:
                    MenuAddEmpleado();
                    break;
                case 2:
                    try{
                        String nombreEmpleado = JOptionPane.showInputDialog(null, "Nombre del empleado: ");
                        unaEmpresa.quitEmpleado(nombreEmpleado);
                    }

                    catch (Exception ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                    break;
                case 3:
                    VerEmpleados(unaEmpresa.getPlanilla());
                    break;
                case 4:
                    String nombreEmpleado = JOptionPane.showInputDialog(null, "Nombre del empleado: ");
                    for(Empleado e : unaEmpresa.getPlanilla()){
                        try{
                            if(e.getNombre().equalsIgnoreCase(nombreEmpleado))
                                JOptionPane.showMessageDialog(null, "Sueldo liquido a pagar: " + CalculadoraImpuestos.calcularPago(e));
                            else
                                JOptionPane.showMessageDialog(null, "Empleado no se encontro en la planilla");
                        }
                        catch (Exception ex){
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    }
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, CalculadoraImpuestos.mostarTotales());
                    break;
            }
        }while (op != 0);
    }

    public static String MenuPrincipal(){
        return  "1. Agregar empleado\n" +
                "2. Despedir empleado\n" +
                "3. Ver lista de empleados\n" +
                "4. Calcular sueldo\n" +
                "5. Mostrar totales\n" +
                "0. Salir";
    }

    public static void MenuAddEmpleado(){
        try{
            String nombreEmpleado = JOptionPane.showInputDialog(null, "Nombre del empleado: ");
            String puesto = JOptionPane.showInputDialog(null, "Puesto asignado: ");
            int salario = Integer.parseInt(JOptionPane.showInputDialog(null, "Salario menual: "));
            int tipoContrato = Integer.parseInt(JOptionPane.showInputDialog(null, "1. Plaza fija \n2. Servicio Profesional"));
            String documento = JOptionPane.showInputDialog(null, "Documento: ");
            String numero = JOptionPane.showInputDialog(null, "Numero: ");
            if(tipoContrato == 1){
                int extension = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero de extension: "));
                Empleado unEmpleado = new PlazaFija(nombreEmpleado, puesto, salario, extension);
                unEmpleado.addDcomuneto(new Documento(documento, numero));
                unaEmpresa.addEmpleado(unEmpleado);
            }
            else if(tipoContrato == 2){
                int mesesContrato = Integer.parseInt(JOptionPane.showInputDialog(null, "Meses del contrato: "));
                Empleado unEmpleado = new ServicioProfesional(nombreEmpleado, puesto, salario, mesesContrato);
                unEmpleado.addDcomuneto(new Documento(documento, numero));
                unaEmpresa.addEmpleado(unEmpleado);                        }
        }

        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static void VerEmpleados(ArrayList<Empleado> empleados){
        for (Empleado e: empleados) {
            String format = "Nombre: " + e.getNombre() +
                    "\nPuesto: " + e.getPuesto() +
                    "\nSalario: " + e.getSalario();
            if(e instanceof ServicioProfesional)
                format += "\nMeses contrato: " + ((ServicioProfesional) e).getMesescontrato();
            else if(e instanceof PlazaFija)
                format += "\nExtension: " + ((PlazaFija) e).getExtension();
            for(Documento d : e.getDocumentos()){
                format += "\n Documento: " + d.getNombre();
                format += "\nNumero" + d.getNumero();
            }
            JOptionPane.showMessageDialog(null, format);
        }
    }
}
