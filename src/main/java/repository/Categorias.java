package repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import model.Categoria;

public class Categorias implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private EntityManager manager;

	public Categoria porId(Long id) {
		return manager.find(Categoria.class, id);
	}

	public List<Categoria> raizes() {
		return manager.createQuery("from Categoria where categoriaPai is null", Categoria.class).getResultList();
	}

	public List<Categoria> subcategoriasDe(Categoria categoriaPai) {
		return manager.createQuery("from Categoria where categoriaPai = :raiz", Categoria.class)
				.setParameter("raiz", categoriaPai).getResultList();
	}

}
