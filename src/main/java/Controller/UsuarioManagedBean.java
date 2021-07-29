package Controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import model.Usuario;

@ManagedBean(name="UsuarioMB")
public class UsuarioManagedBean {
	
	private Usuario usuario = new Usuario();

    public String cadastraUsuario() throws SQLException {

               Connect con = new Connect();


               if (con.insertUsuario(usuario)) {
                    FacesContext.getCurrentInstance().addMessage(
                     null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                     "Sucesso!", "Usuário cadastrado com sucesso!"));
               } else {
                    FacesContext.getCurrentInstance().addMessage(
                       null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro!",
                       "Erro no cadastr de usuário!"));

               }
               con.closeConnection();



         return "";
    }

    public List<Usuario> getUsuarios() throws SQLException {

         Connect con = new Connect();
         List<Usuario> listaUsuarios = con.listUsuarios();

         return listaUsuarios;
    }

    public Usuario getUsuario() {
         return usuario;
    }

    public void setUsuario(Usuario usuario) {
         this.usuario = usuario;
    }
}