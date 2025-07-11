import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
//        Atribuição de variáveis
        Conexao conexao = new Conexao();
        PreparedStatement comando;
        Statement comando2 = null;
        ResultSet resultado;
        boolean validacaoConect = conexao.conectarBanco();
//        Validando a conexão com o Banco de Dados
        if (validacaoConect) {
            System.out.println("Banco de Dados conectado com sucesso!");
//            Bloco de tratamento de exceções e execução do comandos em SQL
            try {
                String sql = "INSERT INTO public.login(email,nome_Usuario,senha) VALUES ('anegue@involucre.org.br','Anegue','Invo123')";
                comando = conexao.getConect().prepareStatement(sql);
                comando.executeUpdate();
                comando2.execute("SELECT * FROM public.login");
                comando = conexao.getConect().prepareStatement("SELECT * FROM public.login");
                resultado = comando.executeQuery();
//                Lendo e mostrando o SELECT
                while (resultado.next()) {
                    System.out.printf("Email: %s | Usuário: %s | Senha: %s\n", resultado.getString("email"), resultado.getString("nome_Usuario"), resultado.getString("senha"));
                }
            }
            catch (SQLException sql){
                sql.printStackTrace();
            }finally {
                conexao.desconectarBanco();
            }
        }else {
            System.out.println("A conexão foi interrompida!");
        }
    }
}