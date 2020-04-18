import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //Declaracion de escaner global
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        //Creando una empresa
        System.out.print("Introduzca el nombre de la emprea");
        String nombreEmpresa = in.nextLine();
        Empresa unaEmpresa = new Empresa(nombreEmpresa);

        int op;
        do{
            System.out.print(menuPrincipal());
            op = in.nextByte(); in.nextLine();

            switch (op){
                case 1:
                    try{
                        System.out.print("Nombre del empleado: ");
                        String nombreEmpleado = in.nextLine();
                        System.out.print("Puesto asignado: ");
                        String puesto = in.nextLine();
                        System.out.print("Salario mensual: ");
                        int salario = in.nextByte(); in.nextLine();
                        System.out.print("1. Plaza fija \n2. Servicio Profesional");
                        int tipoContrato = in.nextByte(); in.nextLine();

                        if(tipoContrato == 1){
                            int extension = in.nextInt(); in.nextLine();
                            unaEmpresa.addEmpleado(new PlazaFija(nombreEmpleado, puesto, salario, extension));
                        }
                        else if(tipoContrato == 2){
                            int mesesContrato = in.nextInt(); in.nextLine();
                            unaEmpresa.addEmpleado(new ServicioProfesional(nombreEmpleado, puesto, salario, mesesContrato));
                        }
                    }

                    catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    try{
                        System.out.print("Nombre del empleado: ");
                        String nombreEmpleado = in.nextLine();

                        unaEmpresa.quitEmpleado(nombreEmpleado);
                    }

                    catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    for(Empleado e : unaEmpresa.getPlanilla()){
                        System.out.println(e.getNombre());
                    }
            }
        }while (op != 0);
    }

    public static String menuPrincipal(){
        return  "1. Agregar empleado\n" +
                "2. Despedir empleado\n" +
                "3. Ver lista de empleados\n" +
                "4. Calcular sueldo\n" +
                "5. Mostrar totales\n" +
                "0. Salir";
    }
}
