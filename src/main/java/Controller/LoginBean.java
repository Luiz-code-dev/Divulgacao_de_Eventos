package Controller;


import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import util.jsf.FacesUtil;

@ManagedBean(name = "loginController", eager = true)
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

		
	private FacesContext facesContext;
	
	
	private HttpServletRequest request;
	
	
	private HttpServletResponse response;
	
	private String email;

	private String senha;
	
	@Inject
	private UsuarioManagedBean usuarioDAOManagedBean;
	
	public LoginBean() {
		
	}
	
	public void init() {
		this.email = "";
		this.senha = "";
		UsuarioManagedBean.salvar(new Usuario("", null));
	}
	
	
	public void preRender() {
		if ("true".equals(request.getParameter("invalid"))) {
			FacesUtil.addErrorMessage("Usuário ou senha inválido!");
		}
	}
	
	public void login() throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);
		
		facesContext.responseComplete();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
