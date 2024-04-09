package model;

import database.ConfigDB;
import entity.EmpresaEntity;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpresaModel  {

    public List<Object> findAll() {

        Connection objConnection = ConfigDB.openConection();

        List<Object> listEmpresas = new ArrayList<>();

        try {

            String sql = "SELECT * INTO empresa;";

            PreparedStatement objpreared = objConnection.prepareCall(sql);

            ResultSet objResult = objpreared.executeQuery();

            while (objResult.next()){
                EmpresaEntity objEmpresa = new EmpresaEntity();

                objEmpresa.setId(objResult.getInt("id"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setContacto(objResult.getString("contacto"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));

                listEmpresas.add(objEmpresa);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        return listEmpresas;
    }

    public EmpresaEntity findByIdEmpresa(int id) {

        Connection objConection = ConfigDB.openConection();

        EmpresaEntity objEmpresa = new EmpresaEntity();

        try {

            String sql = "SELECT * FROM empresa WHERE id = ?;";

            PreparedStatement objprepare = objConection.prepareStatement(sql);

            objprepare.setInt(1, id);

            ResultSet objResult = objprepare.executeQuery();
            if (objResult.next()) {

                objEmpresa.setId(objResult.getInt("id"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setContacto(objResult.getString("contacto"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));

            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objEmpresa;
    }
}
