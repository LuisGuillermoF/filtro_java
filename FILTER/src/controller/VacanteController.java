package controller;

import entity.EmpresaEntity;
import entity.VacanteEntity;
import model.EmpresaModel;
import model.VacanteModel;
import org.w3c.dom.html.HTMLObjectElement;

import javax.swing.*;
import java.awt.*;

public class VacanteController {

    public static void getAll() {

        VacanteModel objModel = new VacanteModel();

        String listVacancy = "List Vacancys\n";

        for (Object vacante : objModel.findAll()) {
            VacanteEntity objVacancy = (VacanteEntity) vacante;
            listVacancy += objVacancy.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listVacancy);
    }

    public static String getAllString() {

        VacanteModel objModel = new VacanteModel();

        String listVacancy = "List Vacancys\n";

        for (Object vacante : objModel.findAll()) {
            VacanteEntity objVacancy = (VacanteEntity) vacante;
            listVacancy += objVacancy.toString() + "\n";
        }
        return listVacancy;
    }

    public static void getTitle() {

        VacanteModel objModel = new VacanteModel();

        String title = JOptionPane.showInputDialog("Ingrese el tiutlo a buscar");

        String listTitles = "List titles\n";

        for (Object vancacy : objModel.findBytitle(title)) {
            VacanteEntity objVacante = (VacanteEntity) vancacy;
            listTitles += objVacante.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listTitles);

    }

    public static void getTegnoligy() {

        VacanteModel objModel = new VacanteModel();

        String tag = JOptionPane.showInputDialog("ingresar la tegnologia");

        String listteg = "List titles \n";

        for (Object vancacy : objModel.findByTegnologi(tag)) {
            VacanteEntity objVacante = (VacanteEntity) vancacy;
            listteg += objVacante.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listteg);

    }

    public static void Insert() {

        VacanteModel objModel = new VacanteModel();
        VacanteEntity objVacante = new VacanteEntity();
        EmpresaController objEmpresa = new EmpresaController();



        String listEmpresas = objEmpresa.getallStrin();
        JOptionPane.showMessageDialog(null,objModel.findByVacantesActivas()+"vacantes disponibles");
        String title = JOptionPane.showInputDialog("Ingresa el titulo");
        String description = JOptionPane.showInputDialog("Ingresar la descripcion");
        String duration = JOptionPane.showInputDialog("ingresar la duracion");
        String estado = JOptionPane.showInputDialog("Ingrese la duracion " + "ingrese los valores\n" + "Activa o desactivo");
        String tecnologia = JOptionPane.showInputDialog("Ingresar la tegnologia a buscar");
        int id_empresa = Integer.parseInt(JOptionPane.showInputDialog(listEmpresas + "ingresar el id de la empresa"));


        objVacante.setTitulo(title);
        objVacante.setEstado(estado);
        objVacante.setDescripcion(description);
        objVacante.setDuracion(duration);
        objVacante.setTegnologia(tecnologia);
        objVacante.setId_empresa(id_empresa);


        objVacante = (VacanteEntity) objModel.insert(objVacante);
        objModel.changeEstadoInactivo(objVacante.getId());
    }

    public static void delete() {

        VacanteModel objModel = new VacanteModel();

        String listVacancy = getAllString();

        int delete = Integer.parseInt(JOptionPane.showInputDialog("ingrese el id a eliminar" + listVacancy));

        VacanteEntity objVacante = (VacanteEntity) objModel.findById(delete);

        if (objVacante == null) {
            JOptionPane.showMessageDialog(null, "la vacante no fue encontrada");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Esyas seguro de eliminar esta vacante ?");
            if (confirm == 0) {
                objModel.delete(objVacante);
                objModel.changeEstadoActivo(objVacante.getId());
            }
        }

    }

    public static void update() {

        VacanteModel objModel = new VacanteModel();
        EmpresaController objEmpresas = new EmpresaController();

        String listEmpresas = objEmpresas.getallStrin();

        String listVacancy = getAllString();


        int delete = Integer.parseInt(JOptionPane.showInputDialog("ingrese el id a eliminar" + listVacancy));

        VacanteEntity objVacante = (VacanteEntity) objModel.findById(delete);

        if (objVacante == null) {
            JOptionPane.showMessageDialog(null, "la vacante no fue encontrada");
        } else {
            String title = JOptionPane.showInputDialog("Ingresa el titulo");
            String description = JOptionPane.showInputDialog("Ingresar la descripcion");
            String duration = JOptionPane.showInputDialog("ingresar la duracion");
            String estado = JOptionPane.showInputDialog("Ingrese la duracion\n" + "ingrese los valores\n" + "Activa o desactivo");
            String tecnologia = JOptionPane.showInputDialog("Ingresar la tegnologia a buscar");
            int id_empresa = Integer.parseInt(JOptionPane.showInputDialog(listEmpresas + "ingrese la nueva id de la empresa"));


            objVacante.setTitulo(title);
            objVacante.setEstado(estado);
            objVacante.setDescripcion(description);
            objVacante.setDuracion(duration);
            objVacante.setTegnologia(tecnologia);
            objVacante.setId_empresa(id_empresa);

            objModel.update(objVacante);
        }

    }

}
