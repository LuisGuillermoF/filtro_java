package model;

import database.CRUD;
import database.ConfigDB;
import entity.CoderEntity;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {
    @Override
    public List<Object> findAll() {

        Connection objConnection = ConfigDB.openConection();

        List<Object> listCoders = new ArrayList<>();

        try {

            String sql = "SELECT * INTO coder;";

            PreparedStatement objprepared = objConnection.prepareStatement(sql);

            ResultSet objResult = objprepared.executeQuery();

            while (objResult.next()){
                CoderEntity objCoder = new CoderEntity();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setClan(objResult.getString("clan"));
                objCoder.setDni(objResult.getString("documento"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setCohote(objResult.getInt("cohorte"));
                objCoder.setHoja_de_vida(objResult.getString("cv"));

                listCoders.add(objCoder);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return listCoders;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConection();

        CoderEntity objCoder = (CoderEntity) obj;

        try {

            String sql = "UPDATE coder SET nombre=?,clan=?,documento=?,Appelido=?,cohorte=?,cv=? WHERE id=?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1,objCoder.getNombre());
            objPrepared.setString(2,objCoder.getClan());
            objPrepared.setString(3,objCoder.getDni());
            objPrepared.setString(4,objCoder.getApellido());
            objPrepared.setInt(5,objCoder.getCohote());
            objPrepared.setString(6,objCoder.getHoja_de_vida());
            objPrepared.setInt(7,objCoder.getId());

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


        return true;
    }

    @Override
    public boolean delete(Object obj) {

        Connection objConnection = ConfigDB.openConection();

        CoderEntity objCoder = (CoderEntity) obj;

        try {

            String sql = "DELETE FROM coder WHERE id =?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1,objCoder.getId());

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

        return true;
    }

    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConection();

        CoderEntity objCoder = (CoderEntity) obj;

        try {

            String sql = "INSERT INTO coder (nombre,apellido,documento,cohorte,cv,clan) VALUES (?,?,?,?,?,?);";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepared.setString(1,objCoder.getNombre());
            objPrepared.setString(6,objCoder.getClan());
            objPrepared.setString(3,objCoder.getDni());
            objPrepared.setString(2,objCoder.getApellido());
            objPrepared.setInt(4,objCoder.getCohote());
            objPrepared.setString(5,objCoder.getHoja_de_vida());

            objPrepared.execute();

            ResultSet objResult = objPrepared.getGeneratedKeys();

            while (objResult.next()){
                objCoder.setId(objResult.getInt(1));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objCoder;
    }

    public CoderEntity findById(int id){
        //1. abrimos la conexion

        Connection objConection = ConfigDB.openConection();

        //2 Crear el coder que vamos a retornar
        CoderEntity objCoder = null;

        try {

            //3. Sentendia sql
            String sql = "SELECT * FROM coder WHERE id = ?;";

            //4. preparamos el prepare
            PreparedStatement objprepare = objConection.prepareStatement(sql);

            //5. Darle valor al parametrod al Query
            objprepare.setInt(1,id);

            //6. Executamos el Query
            ResultSet objResult = objprepare.executeQuery();
            if (objResult.next()){
                objCoder = new CoderEntity();
                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setClan(objResult.getString("clan"));
                objCoder.setDni(objResult.getString("documento"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setCohote(objResult.getInt("cohorte"));
                objCoder.setHoja_de_vida(objResult.getString("cv"));
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //7 Cerramos la conexion
        ConfigDB.closeConnection();

        return objCoder;
    }

    public  List<CoderEntity> findByCohorte (int cohorte){
        List<CoderEntity> listCoder = new ArrayList<>();

        Connection objConnection = ConfigDB.openConection();

        try{

            String sql = "SELECT * FROM coder WHERE  cohorte = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, cohorte);

            ResultSet objResult = objPrepare.executeQuery();
            while(objResult.next()){
                CoderEntity objCoder = new CoderEntity();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setDni(objResult.getString("documento"));
                objCoder.setCohote(objResult.getInt("cohorte"));
                objCoder.setHoja_de_vida(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listCoder.add(objCoder);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCoder;
    }

    public  List<CoderEntity> findByClan (String clan){
        List<CoderEntity> listCoder = new ArrayList<>();

        Connection objConnection = ConfigDB.openConection();

        try{

            String sql = "SELECT * FROM coder WHERE  clan LIKE ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%"+clan+"%");

            ResultSet objResult = objPrepare.executeQuery();
            while(objResult.next()){
                CoderEntity objCoder = new CoderEntity();
                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setDni(objResult.getString("documento"));
                objCoder.setCohote(objResult.getInt("cohorte"));
                objCoder.setHoja_de_vida(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listCoder.add(objCoder);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCoder;
    }

    public List<CoderEntity> findByTecnology(String tecnologia){
        List<CoderEntity> listCoders = new ArrayList<>();

        Connection objConnection = ConfigDB.openConection();

        try {
            String sql = "SELECT * FROM coder WHERE cv LIKE ?;";

            PreparedStatement objprepare = objConnection.prepareStatement(sql);

            objprepare.setString(1,"%"+tecnologia+"%");

            ResultSet objResult = objprepare.executeQuery();
            while(objResult.next()){
                CoderEntity objCoder = new CoderEntity();
                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setDni(objResult.getString("documento"));
                objCoder.setCohote(objResult.getInt("cohorte"));
                objCoder.setHoja_de_vida(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listCoders.add(objCoder);
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return listCoders;
    }

}
