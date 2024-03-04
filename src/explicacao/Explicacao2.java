/*package explicacao;

//Importações de classes necessárias para o funcionamento do programa
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

//Definição da classe TelaCadastro que extende JFrame (representa a janela de cadastro de erros)
public class TelaCadastro extends JFrame {

 // SerialVersionUID é um número de versão que é usado durante a serialização
 private static final long serialVersionUID = 1L;
 // Painel principal da janela de cadastro
 private JPanel contentPane;
 // Campos de texto para inserção do nome do erro, plataforma, descrição e resolução
 private JTextField txtErro;
 private JTextField txtPlataforma;
 private JTextPane txtDescricao;
 private JTextPane txtResolucao;
 
 // Método principal para execução do programa
 public static void main(String[] args) {
     EventQueue.invokeLater(new Runnable() {
         public void run() {
             try {
                 // Instanciação da janela de cadastro
                 TelaCadastro telaCadastro = new TelaCadastro();
                 telaCadastro.setVisible(true); // Torna a janela de cadastro visível
             } catch (Exception e) {
                 e.printStackTrace(); // Se houver exceção, imprime o rastreamento da pilha
             }
         }
     });
 }

 // Construtor da classe TelaCadastro
 public TelaCadastro() { 
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento padrão ao fechar a janela
     setBounds(100, 100, 1000, 600); // Define o tamanho e a posição da janela
     contentPane = new JPanel(); // Cria um novo painel
     contentPane.setBackground(new Color(102, 102, 102)); // Define a cor de fundo do painel
     contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Define a borda do painel
     setContentPane(contentPane); // Define o painel como o conteúdo da janela
     contentPane.setLayout(null); // Define o layout como nulo para que os componentes possam ser posicionados livremente na janela

     // Campos de texto para inserção do nome do erro
     txtErro = new JTextField();
     txtErro.setForeground(Color.WHITE); // Define a cor do texto do campo
     txtErro.setBackground(new Color(51, 51, 51)); // Define a cor de fundo do campo
     txtErro.setBounds(38, 81, 383, 37); // Define a posição e o tamanho do campo
     contentPane.add(txtErro); // Adiciona o campo ao painel
     txtErro.setColumns(10); // Define o número de colunas do campo
     
     // Campos de texto para inserção da plataforma
     txtPlataforma = new JTextField();
     txtPlataforma.setForeground(Color.WHITE); // Define a cor do texto do campo
     txtPlataforma.setBackground(new Color(51, 51, 51)); // Define a cor de fundo do campo
     txtPlataforma.setColumns(10); // Define o número de colunas do campo
     txtPlataforma.setBounds(460, 81, 383, 37); // Define a posição e o tamanho do campo
     contentPane.add(txtPlataforma); // Adiciona o campo ao painel

     // Áreas de texto para inserção da descrição e resolução do erro
     txtDescricao = new JTextPane();
     txtDescricao.setForeground(Color.WHITE); // Define a cor do texto da área de texto
     txtDescricao.setBackground(new Color(51, 51, 51)); // Define a cor de fundo da área de texto
     txtDescricao.setBounds(38, 190, 383, 237); // Define a posição e o tamanho da área de texto
     contentPane.add(txtDescricao); // Adiciona a área de texto ao painel

     txtResolucao = new JTextPane();
     txtResolucao.setForeground(Color.WHITE); // Define a cor do texto da área de texto
     txtResolucao.setBackground(new Color(51, 51, 51)); // Define a cor de fundo da área de texto
     txtResolucao.setBounds(460, 190, 383, 237); // Define a posição e o tamanho da área de texto
     contentPane.add(txtResolucao); // Adiciona a área de texto ao painel
     
     // Rótulos para identificar os campos de texto
     JLabel lblErro = new JLabel("Erro:"); // Rótulo para o campo de inserção do nome do erro
     lblErro.setFont(new Font("Tahoma", Font.BOLD, 18)); // Define a fonte do texto do rótulo
     lblErro.setBounds(38, 55, 115, 19); // Define a posição e o tamanho do rótulo
     contentPane.add(lblErro); // Adiciona o rótulo ao painel

     JLabel lblDescricao = new JLabel("Descrição:"); // Rótulo para o campo de inserção da descrição do erro
     lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 18)); // Define a fonte do texto do rótulo
     lblDescricao.setBounds(38, 161, 115, 19); // Define a posição e o tamanho do rótulo
     contentPane.add(lblDescricao); // Adiciona o rótulo ao painel

     JLabel lblResolucao = new JLabel("Resolução:"); // Rótulo para o campo de inserção da resolução do erro
     lblResolucao.setFont(new Font("Tahoma", Font.BOLD, 18)); // Define a fonte do texto do rótulo
     lblResolucao.setBounds(460, 161, 115, 19); // Define a posição e o tamanho do rótulo
     contentPane.add(lblResolucao); // Adiciona o rótulo ao painel

     JLabel lblPlataforma = new JLabel("Plataforma:"); // Rótulo para o campo de inserção da plataforma
     lblPlataforma.setFont(new Font("Tahoma", Font.BOLD, 18)); // Define a fonte do texto do rótulo
     lblPlataforma.setBounds(460, 55, 115, 19); // Define a posição e o tamanho do rótulo
     contentPane.add(lblPlataforma); // Adiciona o rótulo ao painel

     // Botões para cadastrar e voltar
     JButton btnCadastrar = new JButton("Cadastrar"); // Botão para cadastrar o erro
     btnCadastrar.setForeground(Color.WHITE); // Define a cor do texto do botão
     btnCadastrar.setBackground(new Color(0, 0, 0)); // Define a cor de fundo do botão
     btnCadastrar.addActionListener(new ActionListener() { // Adiciona um ouvinte de evento ao botão
         public void actionPerformed(ActionEvent e) { // Método chamado quando o botão é clicado
             cadastrarErros(); // Chama o método para cadastrar os erros
             limparCampos(); // Chama o método para limpar os campos após o cadastro
         }
     });
     btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 14)); // Define a fonte do texto do botão
     btnCadastrar.setBounds(38, 472, 133, 37); // Define a posição e o tamanho do botão
     contentPane.add(btnCadastrar); // Adiciona o botão ao painel
     
     JButton btnVoltar = new JButton("Voltar"); // Botão para voltar à tela principal
     btnVoltar.setForeground(Color.WHITE); // Define a cor do texto do botão
     btnVoltar.setBackground(new Color(0, 0, 0)); // Define a cor de fundo do botão
     btnVoltar.addActionListener(new ActionListener() { // Adiciona um ouvinte de evento ao botão
         public void actionPerformed(ActionEvent e) { // Método chamado quando o botão é clicado
             TelaPrincipal telaPrincipal = new TelaPrincipal(); // Cria uma instância da tela principal
             telaPrincipal.setVisible(true); // Torna a tela principal visível
             dispose(); // Fecha a tela de cadastro
         }
     });
     btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14)); // Define a fonte do texto do botão
     btnVoltar.setBounds(38, 519, 133, 37); // Define a posição e o tamanho do botão
     contentPane.add(btnVoltar); // Adiciona o botão ao painel

     // Define a cor do cursor nos campos de texto
     txtErro.setCaretColor(Color.WHITE);
     txtPlataforma.setCaretColor(Color.WHITE);
     txtDescricao.setCaretColor(Color.WHITE);
     txtResolucao.setCaretColor(Color.WHITE);
 }

 // Método para cadastrar os erros
 public void cadastrarErros() {
     // Cria um objeto do tipo Erros para armazenar as informações do erro
     Erros E = new Erros();
     E.Nome = txtErro.getText(); // Obtém o nome do erro do campo de texto
     E.Descricao = txtDescricao.getText(); // Obtém a descrição do erro da área de texto
     E.Plataforma = txtPlataforma.getText(); // Obtém a plataforma do erro do campo de texto
     E.Resolucao = txtResolucao.getText(); // Obtém a resolução do erro da área de texto
     
     // Strings para armazenar as informações de conexão com o banco de dados e a consulta SQL
     String DriveMySQL = "com.mysql.cj.jdbc.Driver";
     String SQL = "";
     
     try {
         // Carrega o driver do MySQL
         Class.forName(DriveMySQL);
     } catch (ClassNotFoundException e1) {
         e1.printStackTrace(); // Se houver exceção, imprime o rastreamento da pilha
     }
     
     Connection Conexao = null; // Cria uma variável para a conexão
     
     try {
         // Estabelece a conexão com o banco de dados
         Conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/errorsystem" , "root" ,"root");
         Statement estamento = Conexao.createStatement(); // Cria uma instância do Statement
         
         // Define a consulta SQL para inserir os dados na tabela erros
         SQL = ("Insert into erros (Nome, Descricao, Plataforma, Resolucao) values ('" + E.Nome + "','" + E.Descricao + "','" + E.Plataforma + "', '" +  E.Resolucao + "' );");
         estamento.execute(SQL); // Executa a consulta SQL
         JOptionPane.showMessageDialog(null, "Cadastrado com sucesso"); // Exibe uma mensagem de sucesso em um popup
         estamento.close(); // Fecha o Statement
         Conexao.close(); // Fecha a conexão com o banco de dados
                          
     } catch(SQLException ex) { // Captura exceções do tipo SQLException
         ex.printStackTrace(); // Imprime o rastreamento da pilha
         JOptionPane.showMessageDialog(null, "Erro de conexao com o banco de dados"); // Exibe uma mensagem de erro em um popup
     }
 }
 
 // Método para limpar os campos após o cadastro
 public void limparCampos() {
     txtErro.setText(""); // Limpa o campo de texto do nome do erro
     txtDescricao.setText(""); // Limpa a área de texto da descrição do erro
     txtPlataforma.setText(""); // Limpa o campo de texto da plataforma do erro
     txtResolucao.setText(""); // Limpa a área de texto da resolução do erro
 }
}
*/