package domain.factory;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class GerarTabelas {

	public GerarTabelas() {
		Configuration configuration = new Configuration();
		configuration.configure();
		SchemaExport se = new SchemaExport(configuration);
		se.execute(true, true, false, true);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		GerarTabelas gerarTabelas = new GerarTabelas();
	}

}
