import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private Connection conect;
    String nome_Usuario = System.getenv("NOME_USUARIO_BD");
    String senha = System.getenv("SENHA_BD");
    String nomeBD = System.getenv("PROTO_BD");
    String porta = System.getenv("PORTA_BD");
    String host = System.getenv("HOST_BD");

    public Connection getConect() {
        return conect;
    }
    public boolean conectarBanco() {
        try {
            Class.forName("org.postgresql.Driver");
            conect = DriverManager.getConnection("JDBC:postgresql://" + host + ":" + porta + "/" + nomeBD, nome_Usuario, senha);
            return true;
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        catch(SQLException sql) {
            sql.printStackTrace();
        }
        return false;
    }
    public void desconectarBanco() {
        try{
            if (conect != null && !conect.isClosed()) {
                conect.close();
                System.out.println("Banco de Dados desconectado!");
            }
        }
        catch(SQLException sqle) {
            System.out.println("A conexão com o Banco de Dados já está desligada ou é nula: \n" + sqle.getMessage());
        }
    }

}
