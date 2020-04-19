import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //Declaracion de escaner global
    static Scanner in = new Scanner(System.in);
    static Empresa unaEmpresa;
    static int op = 7;
    static int op2 = 0;

    public static void main(String[] args) {
        //Creando una empresa
        String nombreEmpresa = JOptionPane.showInputDialog(null, "Introduzca el nombre de la emprea: ");
        unaEmpresa = new Empresa(nombreEmpresa);

        do{
            try{
                op = Integer.parseInt(JOptionPane.showInputDialog(null, MenuPrincipal()));
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
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
                        op = 7;
                    }
                    break;
                case 3:
                    VerEmpleados(unaEmpresa.getPlanilla());
                    break;
                case 4:
                    String nombreEmpleado = JOptionPane.showInputDialog(null, "Nombre del empleado: ");
                    boolean encontrado = false;

                    for(Empleado e : unaEmpresa.getPlanilla()){
                        try{
                            if(e.getNombre().equalsIgnoreCase(nombreEmpleado)){
                                JOptionPane.showMessageDialog(null, "Sueldo liquido a pagar: " + CalculadoraImpuestos.calcularPago(e));
                                encontrado = true;
                            }
                        }
                        catch (Exception ex){
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            op = 7;
                        }
                    }
                    if(!encontrado)
                        JOptionPane.showMessageDialog(null, "El empleado no se encontro en planilla");
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, CalculadoraImpuestos.mostarTotales());
                    break;
                case 6:
                    nombreEmpleado = JOptionPane.showInputDialog(null, "Nombre del empleado: ");
                    encontrado = false;

                    for(Empleado e : unaEmpresa.getPlanilla()){
                        try{
                            if(e.getNombre().equalsIgnoreCase(nombreEmpleado)){
                                do{
                                    op2 = Integer.parseInt(JOptionPane.showInputDialog(null, MenuEditar(e.getNombre())));
                                    switch (op2){
                                        case 1:
                                            e.setSalario(Double.parseDouble(JOptionPane.showInputDialog(null, "Introduzca el nuevo sueldo: ")));
                                            op2 = 4;
                                            break;
                                        case 2:
                                            e.removeDocumento(JOptionPane.showInputDialog(null, "Introduzca el documento a eliminar."));
                                            op2 = 4;
                                            break;
                                        case 3:
                                            if(e instanceof PlazaFija)
                                                ((PlazaFija) e).setExtension(Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca nueva extension.")));
                                            else if(e instanceof ServicioProfesional)
                                                ((ServicioProfesional) e).setMesescontrato(Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca meses de contrato.")));
                                            op2 = 4;
                                            break;
                                    }
                                }while (op2 != 0);

                                encontrado = true;
                            }
                        }
                        catch (Exception ex){
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            op = 7;
                        }
                    }

                    if(!encontrado)
                        JOptionPane.showMessageDialog(null, "El empleado no se encontro en planilla");
                    break;

            }
        }while (op != 0);
    }

    public static String MenuPrincipal(){
        return  "Bienvenido a " + unaEmpresa.getNombre() +
                "\n1. Agregar empleado\n" +
                "2. Despedir empleado\n" +
                "3. Ver lista de empleados\n" +
                "4. Calcular sueldo\n" +
                "5. Mostrar totales\n" +
                "6. Editar Empleado\n" +
                "0. Salir";
    }

    public static String MenuEditar(String nombre){
        return "Editando empleado " + nombre +
                "\n1. Editar Salario base\n" +
                "2. Eliminar documento\n"+
                "3. Otros\n"+
                "0. Salir";
    }

    public static void MenuAddEmpleado(){
        int tipoContrato = 0;
        do {
            try {
                String nombreEmpleado = JOptionPane.showInputDialog(null, "Nombre del empleado: ");
                String puesto = JOptionPane.showInputDialog(null, "Puesto asignado: ");
                double salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Salario menual: "));
                tipoContrato = Integer.parseInt(JOptionPane.showInputDialog(null, "1. Plaza fija \n2. Servicio Profesional"));
                String documento = JOptionPane.showInputDialog(null, "Documento: ");
                String numero = JOptionPane.showInputDialog(null, "Numero: ");
                if (tipoContrato == 1) {
                    int extension = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero de extension: "));
                    Empleado unEmpleado = new PlazaFija(nombreEmpleado, puesto, salario, extension);
                    unEmpleado.addDcomuneto(new Documento(documento, numero));
                    unaEmpresa.addEmpleado(unEmpleado);
                }
                else if (tipoContrato == 2) {
                    int mesesContrato = Integer.parseInt(JOptionPane.showInputDialog(null, "Meses del contrato: "));
                    Empleado unEmpleado = new ServicioProfesional(nombreEmpleado, puesto, salario, mesesContrato);
                    unEmpleado.addDcomuneto(new Documento(documento, numero));
                    unaEmpresa.addEmpleado(unEmpleado);
                }
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
                op = 7;
            }
        }while(tipoContrato != 0);
    }

    public static void VerEmpleados(ArrayList<Empleado> empleados){
        for (Empleado e: empleados) {
            String format = "Nombre: " + e.getNombre() +
                    "\nPuesto: " + e.getPuesto() +
                    "\nSalario Neto: " + e.getSalario();
            if(e instanceof ServicioProfesional)
                format += "\nMeses contrato: " + ((ServicioProfesional) e).getMesescontrato();
            else if(e instanceof PlazaFija)
                format += "\nExtension: " + ((PlazaFija) e).getExtension();
            for(Documento d : e.getDocumentos()){
                format += "\n Documento: " + d.getNombre();
                format += "\nNumero: " + d.getNumero();
            }
            JOptionPane.showMessageDialog(null, format);
        }
    }
}
