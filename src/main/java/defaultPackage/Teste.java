package defaultPackage;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Teste {

	public static void main(String[] args) {
		// Run > Maven clean
		// Run > Maven install
		// Run as > Java application para gerar o banco de dados e tabelas
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("EventoPU");

		EntityManager manager = factory.createEntityManager();

		List<Usuario> usuarios = manager.createQuery("from Usuario", Usuario.class).getResultList();

		System.out.println(usuarios);

		manager.close();
		factory.close();
	}
}
