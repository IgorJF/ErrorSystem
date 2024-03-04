import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;


public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> modeloErros; //armazenar o nome dos erros
	private JTextField txtNome;
	private JTextField txtPlataforma;
	JTextArea txtDescricao = new JTextArea();
	JTextArea txtResolucao = new JTextArea();
	JList listaErros = new JList();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal telaPrincipal = new TelaPrincipal();
					telaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setResizable(false);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		listaErros.setBounds(22, 27, 315, 490);
		contentPane.add(listaErros);
		listaErros.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		    	exibirErros(e);
		    }
		});
		
		modeloErros = new DefaultListModel<>();
        listaErros.setModel(modeloErros);
        
        carregarErros();
		
		JButton btnCadastro = new JButton("Cadastrar");
		btnCadastro.setBounds(10, 532, 110, 21);
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastro telaCadastro = new TelaCadastro();
				telaCadastro.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnCadastro);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(127, 532, 110, 21);
		contentPane.add(btnEditar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//deletarErros();
			}
		});
		btnDeletar.setBounds(244, 532, 110, 21);
		contentPane.add(btnDeletar);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setEditable(false);
		txtNome.setBounds(403, 25, 422, 38);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtPlataforma = new JTextField();
		txtPlataforma.setEnabled(false);
		txtPlataforma.setEditable(false);
		txtPlataforma.setColumns(10);
		txtPlataforma.setBounds(403, 83, 422, 38);
		contentPane.add(txtPlataforma);
		txtDescricao.setBackground(new Color(255, 255, 255));
		txtDescricao.setEnabled(false);
		
		txtDescricao.setEditable(false);
		txtDescricao.setBounds(403, 141, 422, 187);
		contentPane.add(txtDescricao);
		txtResolucao.setEnabled(false);
		
		txtResolucao.setEditable(false);
		txtResolucao.setBounds(403, 338, 422, 187);
		contentPane.add(txtResolucao);
	}
	
	private void carregarErros() {
        modeloErros.clear(); 
        try {
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/errorsystem", "root", "root");
            Statement estamento = conexao.createStatement();
            String SQL = "SELECT Nome FROM erros";
            ResultSet resultSet = estamento.executeQuery(SQL);

            while (resultSet.next()) {
                String nomeErro = resultSet.getString("Nome");
                modeloErros.addElement(nomeErro);
            }

            resultSet.close();
            estamento.close();
            conexao.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar erros do banco de dados");
        }
    }
	
	private void exibirErros(ListSelectionEvent e){ //gambiarra 
	    if (!e.getValueIsAdjusting()) { //Evento de selecao
	        try {
	            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/errorsystem", "root", "root");
	            String SQL = "SELECT Nome, Descricao, Plataforma, Resolucao FROM erros";
	            PreparedStatement estamento = conexao.prepareStatement(SQL);
	            ResultSet resultSet = estamento.executeQuery();

	            if (resultSet.next()) {
	                txtNome.setText(resultSet.getString("Nome"));
	                txtDescricao.setText(resultSet.getString("Descricao"));
	                txtPlataforma.setText(resultSet.getString("Plataforma"));
	                txtResolucao.setText(resultSet.getString("Resolucao"));
	            }

	            resultSet.close();
	            estamento.close();
	            conexao.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Erro ao carregar informações do banco de dados");
	        }
	    }
	}


	/*private void deletarErros() {
	    try {
	        int index = listaErros.getSelectedIndex();
	        if (index != -1) { 
	            String nomeErroSelecionado = modeloErros.getElementAt(index);

	            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "root");
	            String SQL = "DELETE FROM erros WHERE Nome = ?";
	            PreparedStatement statement = conexao.prepareStatement(SQL);
	            statement.setString(1, nomeErroSelecionado);
	            int linhasAfetadas = statement.executeUpdate();

	            if (linhasAfetadas > 0) {
	                JOptionPane.showMessageDialog(null, "Erro deletado com sucesso");
	                modeloErros.removeElementAt(index);
	            } else {
	                JOptionPane.showMessageDialog(null, "Falha ao deletar o erro");
	            }

	            statement.close();
	            conexao.close();
	        } else {
	            JOptionPane.showMessageDialog(null, "Nenhum erro selecionado para deletar");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erro ao deletar do banco de dados");
	    }
	}*/


}
