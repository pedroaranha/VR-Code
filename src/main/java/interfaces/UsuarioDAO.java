package interfaces;

import java.util.List;

import domain.Usuario;

public interface UsuarioDAO {
	
	boolean inserirUsuario(Usuario usuario);
	
	boolean alterarUsuario(Usuario usuario);
	
	boolean excluirUsuario(Usuario usuario);
	
	Usuario procurarUsuario(Usuario usuario);
	
	List<Usuario> listarUsuarios();

}
