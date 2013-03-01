package persistencia;

import interfaces.EmpresaDAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Empresa;
import domain.factory.HibernateUtil;

public class EmpresaDB implements EmpresaDAO {

	private Session session = null;
	private Transaction transaction = null;

	@Override
	public boolean inserirEmpresa(Empresa empresa) {
		boolean retorno = false;

		try {
			if (empresa != null) {
				session = HibernateUtil.getSession();
				transaction = session.beginTransaction();
				session.persist(empresa);
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
	public boolean alterarEmpresa(Empresa empresa) {
		boolean retorno = false;

		try {
			if (empresa != null) {
				session = HibernateUtil.getSession();
				transaction = session.beginTransaction();
				session.update(empresa);
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
	public boolean excluirEmpresa(Empresa empresa) {
		boolean retorno = false;

		try {
			if (empresa != null) {
				session = HibernateUtil.getSession();
				transaction = session.beginTransaction();
				session.delete(empresa);
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
	public Empresa procurarEmpresa(Empresa empresa) {
		Empresa empresaRetorno = null;

		try {
			if (empresa != null) {
				session = HibernateUtil.getSession();
				empresaRetorno = (Empresa) session.get(Empresa.class,
						empresa.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return empresaRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> listarEmpresas() {
		List<Empresa> lista = null;

		try {
			session = HibernateUtil.getSession();
			lista = session.createCriteria(Empresa.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return lista;
	}
	
	public Empresa procurarEmpresaByUsuario(Long idUsuario) {
		Empresa empresaRetorno = null;

		try {
			session = HibernateUtil.getSession();
			if (idUsuario != 0 && idUsuario != null) {
				session = HibernateUtil.getSession();
				String hql = "SELECT e FROM Empresa as e WHERE e.usuario.id = :idUsuario";
				Query consulta = session.createQuery(hql);
				consulta.setLong("idUsuario", idUsuario);

				if (consulta.list().size() == 1) {
					empresaRetorno = (Empresa) consulta.list().get(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return empresaRetorno;
	}
	
	
	

}
