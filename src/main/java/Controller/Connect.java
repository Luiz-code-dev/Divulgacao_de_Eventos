package Controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.RequestScoped;

import model.Usuario;


@RequestScoped
public class Connect  {
	
	public Usuario usuario;

	Connection con = null;

    public Connect() throws SQLException {

         try {
               Class.forName("org.postgresql.Driver");
               System.out.println("Instalou driver");
         } catch (ClassNotFoundException e) {
               
               e.printStackTrace();
         }

         String url = "jdbc:postgresql://localhost:5432/eventosportdb";
         String user = "postgres";
         String password = "800529";
         con = DriverManager.getConnection(url, user, password);
    }

    public void closeConnection() throws SQLException {

         con.close();
    }
    
    @SuppressWarnings("unused")
	public boolean insertUsuario(Usuario usuario) {

        Statement st = null;
        ResultSet rs = null;
		return false;
    }
   

    //lista todos os usuarios cadastrados no banco de dados
    
    public List<Usuario> listUsuarios() {

         ArrayList<Usuario> lista = new ArrayList<Usuario>();

         Statement st = null;
         ResultSet rs = null;

         try {
               st = con.createStatement();
               String sql = "select * from usuario ";
              rs = st.executeQuery(sql);

               while (rs.next()) {

                    Usuario usuario = new Usuario();
                    usuario.setNomeCompleto(rs.getString(2));
                    usuario.setSenha(rs.getDouble(3));
                    usuario.setApelido(rs.getString(4));
                    usuario.setEmail(rs.getString(5));
                    lista.add(usuario);
               }

         } catch (SQLException ex) {
               Logger lgr = Logger.getLogger(Connect.class.getName());
               lgr.log(Level.SEVERE, ex.getMessage(), ex);

         } finally {
               try {
                    if (rs != null) {
                         rs.close();
                    }
                    if (st != null) {
                         st.close();
                    }
                    if (con != null) {
                         con.close();
                    }

               } catch (SQLException ex) {
                    Logger lgr = Logger.getLogger(Connect.class.getName());
                    lgr.log(Level.WARNING, ex.getMessage(), ex);
               }
         }
         return lista;
    }
	
}
