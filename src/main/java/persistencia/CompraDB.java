package persistencia;

import interfaces.CompraDAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Compra;
import domain.Funcionario;
import domain.factory.HibernateUtil;

public class CompraDB implements CompraDAO {

	private Session session = null;
	private Transaction transaction = null;

	@Override
	public boolean inserirCompra(Compra compra) {
		boolean retorno = false;

		try {
			if (compra != null) {
				session = HibernateUtil.getSession();
				transaction = session.beginTransaction();
				session.persist(compra);
				transaction.commit();
				session.flush();
				retorno = true;
			}
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return retorno;
	}

	@Override
	public boolean alterarCompra(Compra compra) {
		boolean retorno = false;

		try {
			if (compra != null) {
				session = HibernateUtil.getSession();
				transaction = session.beginTransaction();
				session.update(compra);
				transaction.commit();
				session.flush();
			}
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
	public boolean excluirCompra(Compra compra) {
		boolean retorno = false;

		try {
			if (compra != null) {
				session = HibernateUtil.getSession();
				transaction = session.beginTransaction();
				session.delete(compra);
				transaction.commit();
				session.flush();
				retorno = true;
			}
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return retorno;
	}

	@Override
	public Compra procurarCompra(Compra compra) {
		Compra compraRetorno = null;

		try {
			if (compra != null) {
				session = HibernateUtil.getSession();
				compraRetorno = (Compra) session.get(Compra.class,
						compra.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return compraRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compra> listarCompras() {
		List<Compra> lista = null;

		try {
			session = HibernateUtil.getSession();
			lista = session.createCriteria(Compra.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compra> listarComprasPorFuncionario(Funcionario funcionario) {
		List<Compra> lista = null;

		try {
			if (funcionario != null) {
				session = HibernateUtil.getSession();
				String hql = "SELECT c FROM Compra as c WHERE c.funcionario.id = :idFuncionario";
				Query consulta = session.createQuery(hql);
				consulta.setLong("idFuncionario", funcionario.getId());
				lista = consulta.list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Compra> listarComprasPorFuncionario(String idFuncionario) {
		List<Compra> lista = null;

		try {
			if (!idFuncionario.equals("") && idFuncionario != null) {
				session = HibernateUtil.getSession();
				String hql = "SELECT c FROM Compra as c WHERE c.funcionario.id = :idFuncionario";
				Query consulta = session.createQuery(hql);
				consulta.setLong("idFuncionario", Long.parseLong(idFuncionario));
				lista = consulta.list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return lista;
	}

}
