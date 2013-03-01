package listeners;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import domain.factory.GerarTabelas;

/**
 * @author Davi
 * 
 */
public class CreateDatabaseListener implements ServletContextListener {

	@SuppressWarnings("unused")
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out
				.println("CreateDatabaseListener is called- Aplica��o Construida.");
		System.out.println("\n\n\n\n\n");
		System.out.println("Criando as tabelas... ");
		GerarTabelas gerarTabelas = new GerarTabelas();
		System.out.println("Tabelas criadas com sucesso!");
		System.out.println("\n\n\n\n\n");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out
				.println("CreateDatabaseListener is called - Aplica��o Destruida.");
	}

}
