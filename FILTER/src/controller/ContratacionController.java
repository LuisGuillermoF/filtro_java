package controller;

import entity.ContratacionEntity;
import model.CoderModel;
import model.ContratacionModel;

import javax.swing.*;

public class ContratacionController {

    public static void getAll(){

        ContratacionModel objModel = new ContratacionModel();

        String listCoders = "LIST CODERS \n:";

        for (Object contrato : objModel.findAll()){
            ContratacionEntity objContrato = (ContratacionEntity) contrato;
            listCoders += objContrato.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listCoders);

    }

    public static String getAllString(){

        ContratacionModel objModel = new ContratacionModel();

        String listCoders = "LIST CODERS \n:";

        for (Object contrato : objModel.findAll()){
            ContratacionEntity objContrato = (ContratacionEntity) contrato;
            listCoders += objContrato.toString()+"\n";
        }
        return listCoders;
    }




    public static void insert(){

        ContratacionModel objModel = new ContratacionModel();
        ContratacionEntity objContrato = new ContratacionEntity();
        CoderController objCoder = new CoderController();
        VacanteController objVacante = new VacanteController();

        String listCoder = objCoder.getAllString();
        String listVacantes = objVacante.getAllString();




        String estado = JOptionPane.showInputDialog("Ingrese el estado del contrato");
        Double salario = Double.parseDouble(JOptionPane.showInputDialog("Engrese el salario del contrato"));
        int idVacante = Integer.parseInt(JOptionPane.showInputDialog(listVacantes+"Ingrese el id de la vacante"));
        int idcoder = Integer.parseInt(JOptionPane.showInputDialog(listCoder+"Ingrese el id del coder"));

        objContrato.setEstado(estado);
        objContrato.setSalario(salario);
        objContrato.setId_coder(idcoder);
        objContrato.setId_vancante(idVacante);

        objModel.insert(objContrato);

        JOptionPane.showMessageDialog(null,objContrato.toString());
    }

    public static void update(){
        ContratacionModel objModel = new ContratacionModel();

        String listCoder = getAllString();

        int delete = Integer.parseInt(JOptionPane.showInputDialog("Ingresar el id a actualizar"+listCoder));

        ContratacionEntity objContrato = (ContratacionEntity) objModel.findById(delete);

        if (objContrato == null){
            JOptionPane.showMessageDialog(null, "Coder not found");
        } else {
            String estado = JOptionPane.showInputDialog("Ingrese el estado del contrato");
            Double salario = Double.parseDouble(JOptionPane.showInputDialog("Engrese el salario del contrato"));


            objContrato.setEstado(estado);
            objContrato.setSalario(salario);

            objModel.insert(objContrato);
        }
    }


    public static void delete (){

        ContratacionModel objModel = new ContratacionModel();

        String listCoder = getAllString();

        int delete = Integer.parseInt(JOptionPane.showInputDialog("Ingresar el id a eliminar"+listCoder));

        ContratacionEntity objContrato = (ContratacionEntity) objModel.findById(delete);

        if (objContrato == null){
            JOptionPane.showMessageDialog(null, "Coder not found");
        } else {
            int  confirm = JOptionPane.showConfirmDialog(null,"estas seguro de eliminar el contrato ?\n"+objContrato.toString());
            if (confirm == 0){
                objModel.delete(objContrato);
            }
        }
    }
}
