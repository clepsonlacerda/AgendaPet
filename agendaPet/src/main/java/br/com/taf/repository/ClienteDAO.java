package br.com.taf.repository;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.petaf.model.Cliente;

public class ClienteDAO extends GenericDAO<Cliente> {

	SessionFactory getSession(){
		return new Configuration().configure().buildSessionFactory();
	}
	
	public ClienteDAO() {
		super(Cliente.class);
	}
	public boolean salvar(Cliente c){
		if(c.getId() == null){
			// Se salvar mostra essa mensagem
			JOptionPane.showMessageDialog(null, "Salvou!");
			return super.salvar(c); 
		} else {
			// Se editar mostra essa mensagem
			JOptionPane.showMessageDialog(null, "Editou!");
			return super.editar(c);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listarPorNome(String nome){
		Session session  = getSession().openSession();
		return session.createQuery("from Cliente as c where c.nome like '%"+nome+"%' or c.endereco like '%"+nome+"%'")
				.list();
	}

}
