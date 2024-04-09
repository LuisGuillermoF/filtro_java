package model;

import database.CRUD;
import database.ConfigDB;
import entity.EmpresaEntity;
import entity.VacanteEntity;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacanteModel implements CRUD {
    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConection();

        List<Object> listVacante = new ArrayList<>();

        try {

            String sql = "SELECT * FROM vacante " +
                    "INNER JOIN empresa ON  empresa.id = vacante.id_empresa;";

            PreparedStatement objprepared = objConnection.prepareStatement(sql);

            ResultSet objResult = objprepared.executeQuery();

            while (objResult.next()){
                VacanteEntity objVacante = new VacanteEntity();
                EmpresaEntity objEmpresa = new EmpresaEntity();

                objVacante.setId(objResult.getInt("id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("description"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setTegnologia(objResult.getString("tecnoligia"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setId_empresa(objResult.getInt("id_empresa"));


                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setContacto(objResult.getString("contacto"));
                objEmpresa.setId(objResult.getInt("id"));

                objVacante.setObjEmpresa(objEmpresa);

                listVacante.add(objVacante);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return listVacante;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConection();

        VacanteEntity objVacante = (VacanteEntity) obj;

        try {

            String sql = "UPDATE vacante SET titulo=?,description=?,duracion=?,tecnoligia=?,estado=?,id_empresa=? WHERE id=?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1,objVacante.getTitulo());
            objPrepared.setString(2,objVacante.getDescripcion());
            objPrepared.setString(3,objVacante.getDuracion());
            objPrepared.setString(4,objVacante.getTegnologia());
            objPrepared.setInt(5,objVacante.getId());
            objPrepared.setInt(6,objVacante.getId_empresa());
            objPrepared.setString(7,objVacante.getEstado());

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

        VacanteEntity objVacante = (VacanteEntity) obj;

        try {

            String sql = "DELETE FROM vacante WHERE id =?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1,objVacante.getId());

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

        VacanteEntity objVacante = (VacanteEntity) obj;

        try {

            String sql = "INSERT INTO vacante (titulo,description,duracion,estado,tecnoligia,id_empresa) VALUES (?,?,?,?,?,?);";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepared.setString(1,objVacante.getTitulo());
            objPrepared.setString(2,objVacante.getDescripcion());
            objPrepared.setString(3,objVacante.getDuracion());
            objPrepared.setString(4,objVacante.getEstado());
            objPrepared.setString(5,objVacante.getTegnologia());
            objPrepared.setInt(6,objVacante.getObjEmpresa().getId());

            objPrepared.execute();

            ResultSet objResult = objPrepared.getGeneratedKeys();

            while (objResult.next()){
                objVacante.setId(objResult.getInt(1));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objVacante;    }

    public VacanteEntity findById(int id) {
        Connection objConection = ConfigDB.openConection();

        VacanteEntity objVacante = null;

        EmpresaEntity objEmpresa = new EmpresaEntity();

        try {

            String sql = "SELECT * FROM coder WHERE id = ?;";

            PreparedStatement objprepare = objConection.prepareStatement(sql);

            objprepare.setInt(1, id);

            ResultSet objResult = objprepare.executeQuery();
            if (objResult.next()) {
                objVacante = new VacanteEntity();

                objVacante.setId(objResult.getInt("id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("description"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setTegnologia(objResult.getString("tecnoligia"));
                objVacante.setEstado(objResult.getString("estado"));

                objEmpresa.setUbicacion(objResult.getString("ubicaion"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setContacto(objResult.getString("contacto"));
                objEmpresa.setId(objResult.getInt("id"));

            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objVacante;
    }

    public List<VacanteEntity> findBytitle (String title){
        List<VacanteEntity> listVacancis = new ArrayList<>();
        EmpresaEntity objEmpresa = new EmpresaEntity();

        Connection objConnection = ConfigDB.openConection();

        try {

            String sql = "  SELECT * FROM vacante INNER JOIN empresa on empresa.id = vacante.id_empresa WHERE titulo =?;";

            PreparedStatement objPreare = objConnection.prepareStatement(sql);

            objPreare.setString(1,"%"+title+"%");

            ResultSet objResult = objPreare.executeQuery();

            while (objResult.next()){
                VacanteEntity objVacante = new VacanteEntity();

                objVacante.setId(objResult.getInt("id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("description"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setTegnologia(objResult.getString("tecnoligia"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setId_empresa(objResult.getInt("id_empresa"));

                objEmpresa.setUbicacion(objResult.getString("ubicaion"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setContacto(objResult.getString("contacto"));
                objEmpresa.setId(objResult.getInt("id"));

                objVacante.setObjEmpresa(objEmpresa);

                listVacancis.add( objVacante);

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        return listVacancis;
    }

    public List<VacanteEntity> findByTegnologi (String tegnoligy){
        List<VacanteEntity> listVacancis = new ArrayList<>();
        EmpresaEntity objEmpresa = new EmpresaEntity();

        Connection objConnection = ConfigDB.openConection();

        try {

            String sql = "SELECT * FROM vacante INNER JOIN empresa ON empresa.id = vacante.id_empresa WHERE  tecnoligia LIKE ?;";

            PreparedStatement objPreare = objConnection.prepareStatement(sql);

            objPreare.setString(1,"%"+tegnoligy+"%");

            ResultSet objResult = objPreare.executeQuery();

            while (objResult.next()){
                VacanteEntity objVacante = new VacanteEntity();

                objVacante.setId(objResult.getInt("id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("description"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setTegnologia(objResult.getString("tecnoligia"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setId_empresa(objResult.getInt("id_empresa"));

                objEmpresa.setUbicacion(objResult.getString("ubicaion"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setContacto(objResult.getString("contacto"));
                objEmpresa.setId(objResult.getInt("id"));

                objVacante.setObjEmpresa(objEmpresa);

                listVacancis.add( objVacante);

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        return listVacancis;
    }

    public VacanteEntity changeEstadoInactivo (int vacante_id){
        Connection objConnection = ConfigDB.openConection();


        try {
            String sql = "UPDATE vacante SET estado = 'desactivo' WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, vacante_id);

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                JOptionPane.showMessageDialog(null, "La vacante paso a ser INACTIVA");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return findById(vacante_id);
    }

    public VacanteEntity changeEstadoActivo (int vacante_id){
        Connection objConnection = ConfigDB.openConection();


        try {
            String sql = "UPDATE vacante SET estado = 'activa' WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, vacante_id);

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                JOptionPane.showMessageDialog(null, "La vacante paso a ser ACTIVA");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return findById(vacante_id);
    }

    public List<VacanteEntity> findByVacantesActivas(){
        List<VacanteEntity> listVacante = new ArrayList<>();
        EmpresaEntity objEmpresa = new EmpresaEntity();

        Connection objConnection = ConfigDB.openConection();

        try{

            String sql = "SELECT * FROM vacante INNER JOIN empresa ON empresa.id = vacante.empresa_id WHERE  estado = 'activo';";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();
            while(objResult.next()){
                VacanteEntity objVacante = new VacanteEntity();
                objVacante.setId(objResult.getInt("id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("description"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setTegnologia(objResult.getString("tecnoligia"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setId_empresa(objResult.getInt("id_empresa"));

                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEmpresa);

                listVacante.add(objVacante);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacante;
    }


}
