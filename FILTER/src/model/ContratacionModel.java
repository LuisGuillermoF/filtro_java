package model;

import database.CRUD;
import database.ConfigDB;
import entity.CoderEntity;
import entity.ContratacionEntity;
import entity.EmpresaEntity;
import entity.VacanteEntity;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel implements CRUD {
    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConection();

        List<Object> listCOntrato = new ArrayList<>();

        try {

            String sql = "SELECT * INTO contratacion " +
                    "INNER JOIN coder ON coder.id = contratacion.id_coder " +
                    "INNER JOIN vacante ON vacante.id = contratacion.id_vacante " +
                    "INNER JOIN empresa ON empresa.id = vacante.id_empresa ";


            PreparedStatement objprepared = objConnection.prepareStatement(sql);

            ResultSet objResult = objprepared.executeQuery();

            while (objResult.next()){
                ContratacionEntity objContrato = new ContratacionEntity();
                VacanteEntity objVacante = new VacanteEntity();
                EmpresaEntity objEmpresa = new EmpresaEntity();
                CoderEntity objCoder = new CoderEntity();

                objContrato.setId(objResult.getInt("id"));
                objContrato.setEstado(objResult.getString("estado"));
                objContrato.setFecha(objResult.getString("fecha_aplication"));
                objContrato.setSalario(objResult.getDouble("salario"));
                objContrato.setObjCoder((CoderEntity) objResult.getObject(objContrato.getObjCoder().getNombre()));
                objContrato.setObjVacante((VacanteEntity) objResult.getObject(objContrato.getObjVacante().getTitulo()));

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setDni(objResult.getString("documento"));
                objCoder.setCohote(objResult.getInt("cohorte"));
                objCoder.setHoja_de_vida(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));


                objVacante.setId(objResult.getInt("id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("description"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setTegnologia(objResult.getString("tegnoligia"));
                objVacante.setEstado(objResult.getString("estado"));

                objEmpresa.setId(objResult.getInt("id"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setContacto(objResult.getString("contacto"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));

                objVacante.setObjEmpresa(objEmpresa);
                objContrato.setObjCoder(objCoder);
                objContrato.setObjVacante(objVacante);

                listCOntrato.add(objContrato);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return listCOntrato;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConection();

        ContratacionEntity objContrato = (ContratacionEntity) obj;

        try {

            String sql = "UPDATE contratacion SET estado=?,salario=? WHERE id=?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1,objContrato.getEstado());
            objPrepared.setDouble(2,objContrato.getSalario());
            objPrepared.setInt(3,objContrato.getId());

            int totalrows = objPrepared.executeUpdate();

            if (totalrows > 0){
                JOptionPane.showMessageDialog(null,"The update was successful");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return false;
        }finally {
            ConfigDB.closeConnection();
        }


        return true;    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConection();

        ContratacionEntity objContrato = (ContratacionEntity) obj;

        try {

            String sql = "DELETE FROM contratacion WHERE id =?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1,objContrato.getId());

            int totalrowsafected = objPrepared.executeUpdate();

            if (totalrowsafected > 0){
                JOptionPane.showMessageDialog(null,"The update was successful");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return false;
        }finally {
            ConfigDB.closeConnection();
        }

        return true;    }

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConection();

        ContratacionEntity objContrato = (ContratacionEntity) obj;

        try {

            String sql = "INSERT INTO contratacion (estado,salario) VALUES (?,?);";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepared.setString(1,objContrato.getEstado());
            objPrepared.setDouble(2,objContrato.getSalario());


            objPrepared.execute();

            ResultSet objResult = objPrepared.getGeneratedKeys();

            while (objResult.next()){
                objContrato.setId(objResult.getInt(1));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objContrato;
    }
    public ContratacionEntity findById(int id) {
        //1. abrimos la conexion

        Connection objConection = ConfigDB.openConection();

        //2 Crear el coder que vamos a retornar
        ContratacionEntity objContrato = null;

        try {

            //3. Sentendia sql
            String sql = "SELECT * FROM contratacion WHERE id = ?;";

            //4. preparamos el prepare
            PreparedStatement objprepare = objConection.prepareStatement(sql);

            //5. Darle valor al parametrod al Query
            objprepare.setInt(1, id);

            //6. Executamos el Query
            ResultSet objResult = objprepare.executeQuery();
            if (objResult.next()) {
                objContrato = new ContratacionEntity();
                objContrato.setId(objResult.getInt("id"));
                objContrato.setEstado(objResult.getString("estado"));
                objContrato.setFecha(objResult.getString("fecha_aplication"));
                objContrato.setSalario(objResult.getDouble("salario"));
                objContrato.setObjCoder((CoderEntity) objResult.getObject(objContrato.getObjCoder().getNombre()));
                objContrato.setObjVacante((VacanteEntity) objResult.getObject(objContrato.getObjVacante().getTitulo()));

            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7 Cerramos la conexion
        ConfigDB.closeConnection();

        return objContrato;
    }


}
