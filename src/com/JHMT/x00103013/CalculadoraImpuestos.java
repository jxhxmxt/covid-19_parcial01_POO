package com.JHMT.x00103013;

public final class CalculadoraImpuestos {
    private static double totalRenta,totalISSS,totalAFP;

    private CalculadoraImpuestos() {
    }
    public static double calcularPago(Empleado e){
        if(e instanceof ServicioProfesional){
            double renta = e.getSalario() * 0.1;
            totalRenta += renta;
            return e.getSalario() - renta;
        }

        else {
            double afp = e.getSalario() * 0.0625;
            double isss = e.getSalario() * 0.03;
            totalAFP += afp;
            totalISSS += isss;
            double restante = e.getSalario() - afp - isss;
            double renta;

            if(restante < 472.0) renta = 0.0;
            else if (restante < 895.24) renta = 0.1 * (restante - 472) + 17.67;
            else if (restante < 2038.10) renta = 0.2 * (restante - 895.24) + 60;
            else  renta = 0.3 * (restante - 2038.10) + 288.57;

            totalRenta += renta;
            return restante - renta;
        }
    }

    public static String mostarTotales(){
        return "Renta: " + totalRenta +
                "\nISSS: " + totalISSS +
                "\nAFP: " + totalAFP;
    }

}