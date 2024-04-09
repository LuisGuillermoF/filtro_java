package controller;

import entity.EmpresaEntity;
import model.EmpresaModel;

public class EmpresaController {

    public String getallStrin() {
        EmpresaModel objModel = new EmpresaModel();

        String listEmpresas = "List empresas\n";

        for (Object empresa : objModel.findAll()) {
            EmpresaEntity objEmpresa = (EmpresaEntity) empresa;
            listEmpresas += objEmpresa.toString();
        }

        return listEmpresas;
    }
}
