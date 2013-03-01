package persistencia;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Usuario;
import domain.factory.HibernateUtil;
import interfaces.UsuarioDAO;

public class UsuarioDB implements UsuarioDAO {

	private Session session = null;
	private Transaction transaction = null;
	
	@Override
	public boolean inserirUsuario(Usuario usuario) {
		boolean retorno = false;

		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.persist(usuario);
			transaction.commit();
			session.flush();
			retorno = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return retorno;
	}

	@Override
	public boolean alterarUsuario(Usuario usuario) {
		boolean retorno = false;

		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.update(usuario);
			transaction.commit();
			session.flush();
			retorno = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return retorno;
	}

	@Override
	public boolean excluirUsuario(Usuario usuario) {
		boolean retorno = false;

		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.delete(usuario);
			 //session.delete(session.get(Usuario.class, usuario.getId()));
			transaction.commit();
			session.flush();
			retorno = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return retorno;
	}

	@Override
	public Usuario procurarUsuario(Usuario usuario) {
		Usuario usuarioRetorno = null;

		try {
			session = HibernateUtil.getSession();
			usuarioRetorno = (Usuario) session.get(Usuario.class, usuario.getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return usuarioRetorno;
	}
	
	public Usuario procurarUsuario(Long idUsuario) {
		Usuario usuarioRetorno = null;

		try {
			session = HibernateUtil.getSession();
			usuarioRetorno = (Usuario) session.get(Usuario.class, idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return usuarioRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarUsuarios() {
		List<Usuario> lista = null;

		try {
			session = HibernateUtil.getSession();
			lista = session.createCriteria(Usuario.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return lista;
	}

}
