package boot;

import org.hibernate.HibernateException;


import org.hibernate.Session;

import domain.factory.HibernateUtil;

public class BootStrap {

	public BootStrap(){
		criaTabelas();
	}
	
	public static void criaTabelas() {
	//public static void main(String[] args){
		Session session = HibernateUtil.getSession();

		//Transaction transaction = null;
 
		try {

			//transaction = session.beginTransaction();

			//Produto produto = new Produto();
			//produto.setIdFornecedor((long) 1);
			//produto.setNome("Skol");
			//produto.setPreco(4.50);

			//session.save(produto);
			//transaction.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

}
