package org.una.aeropuerto;
/*
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.una.aeropuerto.loaders.Permisos;
import org.una.aeropuerto.services.IRolService;
import org.una.aeropuerto.services.IUsuarioService;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.entities.Usuario;
@Component
public class DataLoader implements ApplicationRunner {

    @Value("${app.admin-user}")
    private String cedula;

    @Value("${app.password}")
    private String password;

    @Autowired
    private IRolService rolService;

    @Autowired
    private IUsuarioService usuarioService;

   // @Autowired
   // private IPermisoOtorgadoService permisoOtorgadoService;

    @Override
    public void run(ApplicationArguments args) {
        if (rolService.countByEstado(true) <= 0) {
            createPermisos();

        }
        if (usuarioService.findByCedulaAproximate(cedula).isEmpty()) {
            createAdmin(getPermisoCrearUsuario());
            System.out.println("Se agrega el usuario inicial");

        } else {
            System.out.println("Se encontro el admin");

        }

    }

    private void createPermisos() {
        for (Permisos permiso : Permisos.values()) {
            Rol nuevoRol = new Rol();
            nuevoRol.setTipo(permiso.name());
            rolService.create(nuevoRol);
        } 
    }

    private Rol getPermisoCrearUsuario() {

        Rol rolCrearUsuario;
        final String codigoRol = "GES";
        Optional<Rol> rolBuscado = rolService.findByTipo(codigoRol);

        if (rolBuscado.isPresent()) {
            rolCrearUsuario = rolBuscado.get();
        } else {
            rolCrearUsuario = new Rol();
            rolCrearUsuario.setTipo(codigoRol);
            rolCrearUsuario.setEstado(true);
            rolCrearUsuario = rolService.create(rolCrearUsuario);
        }
        return rolCrearUsuario;
    }

    private void createAdmin(Rol rolCrearUsuario) {
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto("Usuario Admin");
        usuario.setCedula(cedula);
        usuario.setPasswordEncriptado(password);
        usuario = usuarioService.create(usuario);
        
          rolCrearUsuario = new Rol();
          rolCrearUsuario.setTipo("GES");
          
          rolCrearUsuario.setEstado(true);
          rolCrearUsuario = rolService.create(rolCrearUsuario);
        
          usuario.setRol(rolCrearUsuario);
          
        PermisoOtorgado permisoOtorgado = new PermisoOtorgado();
        permisoOtorgado.setPermiso(permisoCrearUsuario);
        permisoOtorgado.setUsuario(usuario);
        permisoOtorgadoService.create(permisoOtorgado);
    }
}
*/
