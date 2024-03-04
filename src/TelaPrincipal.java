import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;
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
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;


public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> modeloErros; //armazenar o nome dos erros
	private JTextField txtNome;
	private JTextField txtPlataforma;
	JTextArea txtDescricao = new JTextArea();
	JTextArea txtResolucao = new JTextArea();
	JList listaErros = new JList();
	private final JButton btnConfirmar = new JButton("Confirmar");

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
		contentPane.setBackground(new Color(102, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	////Listas
		listaErros.setForeground(new Color(255, 255, 255));
		listaErros.setBackground(new Color(51, 51, 51));
		listaErros.setBounds(22, 27, 315, 490);
		contentPane.add(listaErros);
		listaErros.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		    	exibirErros(e);
		    }
		});

	/////Botões para cadastrar, editar e deletar
		modeloErros = new DefaultListModel<>();
        listaErros.setModel(modeloErros);
        
        carregarErros();
	
		JButton btnCadastro = new JButton("Cadastrar");
		btnCadastro.setForeground(Color.WHITE);
		btnCadastro.setBackground(new Color(0, 0, 0));
		btnCadastro.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCadastro.setBounds(10, 532, 110, 21);
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastro telaCadastro = new TelaCadastro();
				telaCadastro.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnCadastro);
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBackground(new Color(0, 0, 0));
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		btnConfirmar.setBounds(562, 532, 110, 21);
		contentPane.add(btnConfirmar);
		btnConfirmar.setVisible(false);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(new Color(0, 0, 0));
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConfirmar.setVisible(true);
				editarErros();
				btnConfirmar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						confirmarErros();
					}
				});
			}
		});
		btnEditar.setBounds(127, 532, 110, 21);
		contentPane.add(btnEditar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setForeground(Color.WHITE);
		btnDeletar.setBackground(new Color(0, 0, 0));
		btnDeletar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarErros();
			}
		});
		btnDeletar.setBounds(244, 532, 110, 21);
		contentPane.add(btnDeletar);
		
	/////////////////	
		txtNome = new JTextField();
		txtNome.setText("Nome");
		txtNome.setForeground(Color.WHITE);
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtNome.setBackground(new Color(51, 51, 51));
		txtNome.setEnabled(false);
		txtNome.setEditable(false);
		txtNome.setBounds(403, 25, 422, 38);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtPlataforma = new JTextField();
		txtPlataforma.setText("Plataforma");
		txtPlataforma.setForeground(Color.WHITE);
		txtPlataforma.setBackground(new Color(51, 51, 51));
		txtPlataforma.setEnabled(false);
		txtPlataforma.setEditable(false);
		txtPlataforma.setColumns(10);
		txtPlataforma.setBounds(403, 83, 422, 38);
		contentPane.add(txtPlataforma);
		
		txtDescricao.setText("Descrição");
		txtDescricao.setForeground(Color.WHITE);
		txtDescricao.setBackground(new Color(51, 51, 51));
		txtDescricao.setEnabled(false);
		txtDescricao.setEditable(false);
		txtDescricao.setBounds(403, 141, 422, 187);
		contentPane.add(txtDescricao);
		
		txtResolucao.setText("Resolução");
		txtResolucao.setForeground(Color.WHITE);
		txtResolucao.setBackground(new Color(51, 51, 51));
		txtResolucao.setEnabled(false);
		txtResolucao.setEditable(false);
		txtResolucao.setBounds(403, 338, 422, 187);
		contentPane.add(txtResolucao);
		
		txtNome.setCaretColor(Color.WHITE);
		txtPlataforma.setCaretColor(Color.WHITE);
		txtDescricao.setCaretColor(Color.WHITE);
		txtResolucao.setCaretColor(Color.WHITE);
	//////////////////
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
            // Obter o índice do item selecionado na lista
            int index = listaErros.getSelectedIndex();
            if (index != -1) { // Verificar se algum item está selecionado
                try {
                    Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/errorsystem", "root", "root");
                    String nomeErro = modeloErros.getElementAt(index);

                    String SQL = "SELECT * FROM erros WHERE Nome = ?";
                    PreparedStatement estamento = conexao.prepareStatement(SQL);
                    estamento.setString(1, nomeErro);
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
	}

	private void deletarErros() { //gambiarra
	    DefaultListModel<String> modeloErros = (DefaultListModel<String>) listaErros.getModel();
	    try {
	        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/errorsystem", "root", "root");
	        String SQL = "DELETE FROM erros WHERE Nome = ?";
	        PreparedStatement pstmt = conexao.prepareStatement(SQL);
	        int index = listaErros.getSelectedIndex();
	        
	        if (index != -1) { 
	            String nomeErroSelecionado = modeloErros.getElementAt(index);
	            // Atribuir o nome do erro como valor do parâmetro na consulta preparada
	            pstmt.setString(1, nomeErroSelecionado);
	            int linhasAfetadas = pstmt.executeUpdate();

	            if (linhasAfetadas > 0) {
	                JOptionPane.showMessageDialog(null, "Deletado com sucesso");
	                modeloErros.removeElementAt(index);
	                limparCampos();
	            } else {
	                JOptionPane.showMessageDialog(null, "Não foi possível deletar.");
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Selecione um item.");
	        }
	        pstmt.close();
	        conexao.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados");
	    }
	}

	private void limparCampos() {
		txtNome.setText("");
		txtDescricao.setText("");
		txtPlataforma.setText("");
		txtResolucao.setText("");
	}
	
	private void editarErros() {
		txtPlataforma.setEnabled(true);
		txtPlataforma.setEditable(true);
		txtDescricao.setEnabled(true);
		txtDescricao.setEditable(true);
		txtResolucao.setEnabled(true);
		txtResolucao.setEditable(true);
	}
	
	private void confirmarErros() {
	    try {
	        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/errorsystem", "root", "root");
	        String SQL = "UPDATE erros SET Nome = ?, Descricao = ?, Plataforma = ?, Resolucao = ? WHERE Nome = ?";
	        PreparedStatement estamento = conexao.prepareStatement(SQL);
	        
	        //localização para a query SQL
	        estamento.setString(1, txtNome.getText()); // Não é possível atualizar o nome do erro
	        estamento.setString(2, txtDescricao.getText());
	        estamento.setString(3, txtPlataforma.getText());
	        estamento.setString(4, txtResolucao.getText());
	        estamento.setString(5, txtNome.getText()); // WHERE
	        
	        int linhasAfetadas = estamento.executeUpdate();
	        if (linhasAfetadas > 0) {
	            JOptionPane.showMessageDialog(null, "Atualizado com sucesso.");
	        } else {
	            JOptionPane.showMessageDialog(null, "Selecione um item.");
	        }

	        estamento.close();
	        conexao.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erro ao atualizar informações no banco de dados");
	    }
	    bloquearErros();  
	}
	
	private void bloquearErros() {
		txtPlataforma.setEnabled(false);
		txtPlataforma.setEditable(false);
		txtDescricao.setEnabled(false);
		txtDescricao.setEditable(false);
		txtResolucao.setEnabled(false);
		txtResolucao.setEditable(false);
		btnConfirmar.setVisible(false);
	}

}

