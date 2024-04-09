package controller;

import entity.CoderEntity;
import model.CoderModel;

import javax.swing.*;
import java.util.List;

public class CoderController {


    public static void getAll(){

        CoderModel objModel = new CoderModel();

        String listCoders = "LIST CODERS \n:";

        for (Object coder : objModel.findAll()){
            CoderEntity objCoder = (CoderEntity) coder;
            listCoders += objCoder.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listCoders);

    }

    public static String getAllString(){

        CoderModel objModel = new CoderModel();

        String listCoders = "LIST CODERS \n:";

        for (Object coder : objModel.findAll()){
            CoderEntity objCoder = (CoderEntity) coder;
            listCoders += objCoder.toString()+"\n";
        }
        return listCoders;
    }


    public static void insert(){

        CoderModel objModel = new CoderModel();
        CoderEntity objCoder = new CoderEntity();

        String name = JOptionPane.showInputDialog("Ingrese el nuevo nombre"+objCoder.getNombre());
        String clan = JOptionPane.showInputDialog("Ingrese el nuevo clan"+objCoder.getClan());
        String dni = JOptionPane.showInputDialog("Ingrese el nuevo dni"+objCoder.getDni());
        String apellido = JOptionPane.showInputDialog("Ingrese el nuevo apellido"+objCoder.getApellido());
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingresar el numero de cohorte"+objCoder.getCohote()));
        String cv = JOptionPane.showInputDialog("Ingresar el nuevo cv"+objCoder.getHoja_de_vida());


        objCoder.setNombre(name);
        objCoder.setApellido(apellido);
        objCoder.setCohote(cohorte);
        objCoder.setClan(clan);
        objCoder.setDni(dni);
        objCoder.setHoja_de_vida(cv);

        objCoder = (CoderEntity) objModel.insert(objCoder);

        JOptionPane.showMessageDialog(null,objCoder.toString());
    }


    public static void delete (){

        CoderModel objModel = new CoderModel();

        String listCoder = getAllString();

        int delete = Integer.parseInt(JOptionPane.showInputDialog("Ingresar el id a eliminar"+listCoder));

        CoderEntity objCoder = (CoderEntity) objModel.findById(delete);

        if (objCoder == null){
            JOptionPane.showMessageDialog(null, "Coder not found");
        } else {
            int  confirm = JOptionPane.showConfirmDialog(null,"estas seguro de eliminar el coder ?\n"+objCoder.toString());
            if (confirm == 0){
                objModel.delete(objCoder);
            }
        }
    }


    public static  void update(){

        CoderModel objModel = new CoderModel();

        String listCoders = getAllString();

        int update = Integer.parseInt(JOptionPane.showInputDialog(listCoders+"ingrese el id a modificar"));

        CoderEntity  objCoder = objModel.findById(update);

        if (objCoder == null){
            JOptionPane.showMessageDialog(null, "Coder not found");
        }else {
            String name = JOptionPane.showInputDialog("Ingrese el nuevo nombre"+objCoder.getNombre());
            String clan = JOptionPane.showInputDialog("Ingrese el nuevo clan"+objCoder.getClan());
            String dni = JOptionPane.showInputDialog("Ingrese el nuevo dni"+objCoder.getDni());
            String apellido = JOptionPane.showInputDialog("Ingrese el nuevo apellido"+objCoder.getApellido());
            int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingresar el numero de cohorte"+objCoder.getCohote()));
            String cv = JOptionPane.showInputDialog("Ingresar el nuevo cv"+objCoder.getHoja_de_vida());

            objCoder.setNombre(name);
            objCoder.setApellido(apellido);
            objCoder.setCohote(cohorte);
            objCoder.setClan(clan);
            objCoder.setDni(dni);
            objCoder.setHoja_de_vida(cv);

            objModel.update(objCoder);

        }

    }

    public  static void getClan(){

        CoderModel objModel = new CoderModel();

        String clan = JOptionPane.showInputDialog("ingresa el clan a buscar");

        String listCoders = "LIST CODERS \n:";

        for(Object coder : objModel.findByClan(clan)){
            CoderEntity objCoder = (CoderEntity) coder;
            listCoders += objCoder.toString()+"\n";
        }

        JOptionPane.showMessageDialog(null,listCoders);

    }

    public  static void getCohorte(){

        CoderModel objModel = new CoderModel();

        int clan = Integer.parseInt(JOptionPane.showInputDialog("ingresa la cohorte a buscar"));

        String listCoders = "LIST CODERS \n:";

        for(Object coder : objModel.findByCohorte(clan)){
            CoderEntity objCoder = (CoderEntity) coder;
            listCoders += objCoder.toString()+"\n";
        }

        JOptionPane.showMessageDialog(null,listCoders);

    }

    public  static void getCv(){

        CoderModel objModel = new CoderModel();

        String cv = JOptionPane.showInputDialog("ingresa el clan a buscar");

        String listCoders = "LIST CODERS \n:";

        for(Object coder : objModel.findByTecnology(cv)){
            CoderEntity objCoder = (CoderEntity) coder;
            listCoders += objCoder.toString()+"\n";
        }

        JOptionPane.showMessageDialog(null,listCoders);

    }


}
