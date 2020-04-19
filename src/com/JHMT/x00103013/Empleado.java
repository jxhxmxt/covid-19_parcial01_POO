package com.JHMT.x00103013;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Empleado {
    protected String nombre,puesto;
    protected ArrayList<Documento> documentos;
    protected double salario;

    public Empleado(String nombre, String puesto, double salario) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.documentos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public ArrayList<Documento> getDocumentos() {
        return documentos;
    }

    public void addDcomuneto(Documento doc)  {
        boolean existe = false;
        try{
            for(Documento d : documentos){
                if(d.getNumero()==doc.getNumero())
                    existe = true;
            }
            if(existe)
                throw new AlreadyExistDocument("Ese documento ya fue ingresado,numero incorrecto.");

            documentos.add(doc);
        } catch (AlreadyExistDocument ex){
            System.out.println(ex.getMessage());

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void removeDocumento(String nombre){
        try{
            Documento aux = null;

            for(Documento documento : documentos){
                if(documento.getNombre().equals(nombre))
                    aux = documento;
            }

            if(aux != null) {
                documentos.remove(aux);
            }
            else
                throw new AlreadyExistDocument("Documento no encontrado.");

        }catch (AlreadyExistDocument excep){
            JOptionPane.showMessageDialog(null, excep.getMessage());

        }catch(Exception excep){
            JOptionPane.showMessageDialog(null, excep.getMessage());
        }

    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}

