package persistencia;

import interfaces.FuncionarioDAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Funcionario;
import domain.factory.HibernateUtil;

public class FuncionarioDB implements FuncionarioDAO {

	private Session session = null;
	private Transaction transaction = null;

	@Override
	public boolean inserirFuncionario(Funcionario funcionario) {
		boolean retorno = false;

		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.persist(funcionario);
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
	public boolean alterarFuncionario(Funcionario funcionario) {
		boolean retorno = false;

		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.update(funcionario);
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
	public boolean excluirFuncionario(Funcionario funcionario) {
		boolean retorno = false;

		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.delete(funcionario);
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
	public Funcionario procurarFuncionario(Funcionario funcionario) {
		Funcionario funcionarioRetorno = null;

		try {
			session = HibernateUtil.getSession();
			funcionarioRetorno = (Funcionario) session.get(Funcionario.class,
					funcionario.getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return funcionarioRetorno;
	}

	public Funcionario procurarFuncionario(String idFuncionario) {
		Funcionario funcionarioRetorno = null;

		try {
			session = HibernateUtil.getSession();
			if (!idFuncionario.equals("") && idFuncionario != null) {
				funcionarioRetorno = (Funcionario) session.get(
						Funcionario.class, Long.parseLong(idFuncionario));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return funcionarioRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> listarFuncionarios() {
		List<Funcionario> lista = null;

		try {
			session = HibernateUtil.getSession();
			lista = session.createCriteria(Funcionario.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return lista;
	}

	public Funcionario procurarFuncionarioByUsuario(Long idUsuario) {
		Funcionario funcionarioRetorno = null;

		try {
			session = HibernateUtil.getSession();
			if (idUsuario != 0 && idUsuario != null) {
				session = HibernateUtil.getSession();
				String hql = "SELECT f FROM Funcionario as f WHERE f.usuario.id = :idUsuario";
				Query consulta = session.createQuery(hql);
				consulta.setLong("idUsuario", idUsuario);

				if (consulta.list().size() == 1) {
					funcionarioRetorno = (Funcionario) consulta.list().get(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return funcionarioRetorno;
	}

}
