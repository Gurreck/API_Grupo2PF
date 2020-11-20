package org.una.aeropuerto.loaders;

public enum Permisos {
    
    GESTOR("GES"),
    GERENTE("GER"),
    ADMINISTRADOR("ADM"),
    AUDITOR("AUD");
    
    
    private String codigo;

    Permisos(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}

