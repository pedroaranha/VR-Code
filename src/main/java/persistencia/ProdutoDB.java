package persistencia;

import interfaces.ProdutoDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Produto;
import domain.factory.HibernateUtil;

public class ProdutoDB implements ProdutoDAO {

	private Session session = null;
	private Transaction transaction = null;

	@Override
	public boolean inserirProduto(Produto produto) {
		boolean retorno = false;

		try {
			if (produto != null) {
				session = HibernateUtil.getSession();
				transaction = session.beginTransaction();
				session.persist(produto);
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
	public boolean alterarProduto(Produto produto) {
		boolean retorno = false;

		try {
			if (produto != null) {
				session = HibernateUtil.getSession();
				transaction = session.beginTransaction();
				session.update(produto);
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
	public boolean excluirProduto(Produto produto) {
		boolean retorno = false;

		try {
			if (produto != null) {
				session = HibernateUtil.getSession();
				transaction = session.beginTransaction();
				session.delete(produto);
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
	public Produto procurarProduto(Produto produto) {
		Produto produtoRetorno = null;

		try {
			if (produto != null) {
				session = HibernateUtil.getSession();
				produtoRetorno = (Produto) session.get(Produto.class,
						produto.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return produtoRetorno;
	}

	public Produto procurarProduto(String idProduto) {
		Produto produtoRetorno = null;

		try {
			session = HibernateUtil.getSession();
			if (!idProduto.equals("") && idProduto != null) {
				produtoRetorno = (Produto) session.get(Produto.class,
						Long.parseLong(idProduto));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return produtoRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> listarProdutos() {
		List<Produto> lista = null;

		try {
			session = HibernateUtil.getSession();
			lista = session.createCriteria(Produto.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return lista;
	}

}
