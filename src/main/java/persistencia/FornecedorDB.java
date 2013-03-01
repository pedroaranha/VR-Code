package persistencia;

import interfaces.FornecedorDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Fornecedor;
import domain.factory.HibernateUtil;

public class FornecedorDB implements FornecedorDAO {

	private Session session = null;
	private Transaction transaction = null;

	@Override
	public boolean inserirFornecedor(Fornecedor fornecedor) {
		boolean retorno = false;

		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.persist(fornecedor);
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
	public boolean alterarFornecedor(Fornecedor fornecedor) {
		boolean retorno = false;

		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.update(fornecedor);
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
	public boolean excluirFornecedor(Fornecedor fornecedor) {
		boolean retorno = false;

		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.delete(fornecedor);
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
	public Fornecedor procurarFornecedor(Fornecedor fornecedor) {
		Fornecedor fornecedorRetorno = null;

		try {
			session = HibernateUtil.getSession();
			fornecedorRetorno = (Fornecedor) session.get(Fornecedor.class,
					fornecedor.getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return fornecedorRetorno;
	}

	public Fornecedor procurarFornecedor(String idFornecedor) {
		Fornecedor fornecedorRetorno = null;

		try {
			session = HibernateUtil.getSession();
			fornecedorRetorno = (Fornecedor) session.get(Fornecedor.class, Long.parseLong(idFornecedor));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return fornecedorRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fornecedor> listarFornecedores() {
		List<Fornecedor> lista = null;

		try {
			session = HibernateUtil.getSession();
			lista = session.createCriteria(Fornecedor.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return lista;
	}

}
