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

public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtErro;
	private JTextField txtPlataforma;
	private JTextPane txtDescricao;
	private JTextPane txtResolucao;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro telaCadastro = new TelaCadastro();
					telaCadastro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadastro() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	//Caixas de texto que são inseridas as informações
		txtErro = new JTextField();
		txtErro.setForeground(Color.WHITE);
		txtErro.setBackground(new Color(51, 51, 51));
		txtErro.setBounds(38, 81, 383, 37);
		contentPane.add(txtErro);
		txtErro.setColumns(10);
		
		txtPlataforma = new JTextField();
		txtPlataforma.setForeground(Color.WHITE);
		txtPlataforma.setBackground(new Color(51, 51, 51));
		txtPlataforma.setColumns(10);
		txtPlataforma.setBounds(460, 81, 383, 37);
		contentPane.add(txtPlataforma);
		
		txtDescricao = new JTextPane();
		txtDescricao.setForeground(Color.WHITE);
		txtDescricao.setBackground(new Color(51, 51, 51));
		txtDescricao.setBounds(38, 190, 383, 237);
		contentPane.add(txtDescricao);
		
		txtResolucao = new JTextPane();
		txtResolucao.setForeground(Color.WHITE);
		txtResolucao.setBackground(new Color(51, 51, 51));
		txtResolucao.setBounds(460, 190, 383, 237);
		contentPane.add(txtResolucao);
	//////////////////
		JLabel lblErro = new JLabel("Erro:");
		lblErro.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblErro.setBounds(38, 55, 115, 19);
		contentPane.add(lblErro);
		
		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDescricao.setBounds(38, 161, 115, 19);
		contentPane.add(lblDescricao);
		
		JLabel lblResolucao = new JLabel("Resolução:");
		lblResolucao.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblResolucao.setBounds(460, 161, 115, 19);
		contentPane.add(lblResolucao);
		
		JLabel lblPlataforma = new JLabel("Plataforma:");
		lblPlataforma.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPlataforma.setBounds(460, 55, 115, 19);
		contentPane.add(lblPlataforma);
	//////////////////
	//Botões	
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setBackground(new Color(0, 0, 0));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarErros();
				limparCampos();
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCadastrar.setBounds(38, 472, 133, 37);
		contentPane.add(btnCadastrar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setBackground(new Color(0, 0, 0));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal telaPrincipal = new TelaPrincipal();
				telaPrincipal.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(38, 519, 133, 37);
		contentPane.add(btnVoltar);
		
		txtErro.setCaretColor(Color.WHITE);
		txtPlataforma.setCaretColor(Color.WHITE);
		txtDescricao.setCaretColor(Color.WHITE);
		txtResolucao.setCaretColor(Color.WHITE);
	}

	//Método para cadastrar erros
	public void cadastrarErros() {
	//Informação dos erros
		Erros E = new Erros();
		E.Nome = txtErro.getText();
		E.Descricao = txtDescricao.getText();
		E.Plataforma = txtPlataforma.getText();
		E.Resolucao = txtResolucao.getText();
		
	//Conexão com o Banco de Dados	
		String DriveMySQL = "com.mysql.cj.jdbc.Driver";
		String SQL = "";
		
		try {
			Class.forName(DriveMySQL);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Connection Conexao = null;
		
		try {
			Conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/errorsystem" , "root" ,"root");
			Statement estamento = Conexao.createStatement();
			
			SQL = ("Insert into erros (Nome, Descricao, Plataforma, Resolucao) values ('" + E.Nome + "','" + E.Descricao + "','" + E.Plataforma + "', '" +  E.Resolucao + "' );");
			estamento.execute(SQL);
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");//Exibe um popup na tela
			estamento.close();
			Conexao.close();
			                 
		} catch(SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro de conexao com o banco de dados");
		}
	}
	
	//Método para limpar campos ao cadastrar
	public void limparCampos() {
		txtErro.setText("");
		txtDescricao.setText("");
		txtPlataforma.setText("");
		txtResolucao.setText("");
	}
}
