package explicacao;
/*
// Importações de classes necessárias para o funcionamento do programa
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

// Classe principal que representa a janela principal do programa
public class TelaPrincipal extends JFrame {

    // SerialVersionUID é um número de versão que é usado durante a serialização
    private static final long serialVersionUID = 1L;
    // Painel principal da janela
    private JPanel contentPane;
    // Modelo para armazenar os nomes dos erros
    private DefaultListModel<String> modeloErros;
    // Campos de texto para inserção de dados
    private JTextField txtNome;
    private JTextField txtPlataforma;
    // Áreas de texto para inserção de descrição e resolução de erros
    JTextArea txtDescricao = new JTextArea();
    JTextArea txtResolucao = new JTextArea();
    // Lista para exibição dos erros
    JList listaErros = new JList();
    // Botão de confirmação de ação
    private final JButton btnConfirmar = new JButton("Confirmar");

    // Método principal para execução do programa
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Instanciação da janela principal
                    TelaPrincipal telaPrincipal = new TelaPrincipal();
                    telaPrincipal.setVisible(true); // Torna a janela visível
                } catch (Exception e) {
                    e.printStackTrace(); // Se houver exceção, imprime o rastreamento da pilha
                }
            }
        });
    }

    // Construtor da classe TelaPrincipal
    public TelaPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento padrão ao fechar a janela
        setResizable(false); // Impede o redimensionamento da janela
        setBounds(100, 100, 1000, 600); // Define o tamanho e a posição da janela
        contentPane = new JPanel(); // Cria um novo painel
        contentPane.setBackground(new Color(102, 102, 102)); // Define a cor de fundo do painel
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Define a borda do painel
        setContentPane(contentPane); // Define o painel como o conteúdo da janela
        contentPane.setLayout(null); // Define o layout como nulo para que os componentes possam ser posicionados livremente na janela

        // Configurações da lista de erros
        listaErros.setForeground(new Color(255, 255, 255)); // Define a cor do texto na lista
        listaErros.setBackground(new Color(51, 51, 51)); // Define a cor de fundo da lista
        listaErros.setBounds(22, 27, 315, 490); // Define a posição e o tamanho da lista na janela
        contentPane.add(listaErros); // Adiciona a lista ao painel
        listaErros.addListSelectionListener(new ListSelectionListener() { // Adiciona um ouvinte de evento de seleção à lista
            public void valueChanged(ListSelectionEvent e) { // Método chamado quando um item da lista é selecionado
                exibirErros(e); // Chama o método para exibir os erros
            }
        });

        // Botão de cadastro de erro
        JButton btnCadastro = new JButton("Cadastrar");
        btnCadastro.setForeground(Color.WHITE); // Define a cor do texto do botão
        btnCadastro.setBackground(new Color(0, 0, 0)); // Define a cor de fundo do botão
        btnCadastro.setFont(new Font("Tahoma", Font.BOLD, 10)); // Define a fonte do texto do botão
        btnCadastro.setBounds(10, 532, 110, 21); // Define a posição e o tamanho do botão
        btnCadastro.addActionListener(new ActionListener() { // Adiciona um ouvinte de evento ao botão
            public void actionPerformed(ActionEvent e) { // Método chamado quando o botão é clicado
                TelaCadastro telaCadastro = new TelaCadastro(); // Cria uma nova tela de cadastro
                telaCadastro.setVisible(true); // Torna a tela de cadastro visível
                dispose(); // Fecha a janela atual
            }
        });
        contentPane.add(btnCadastro); // Adiciona o botão ao painel
        btnConfirmar.setForeground(Color.WHITE); // Define a cor do texto do botão Confirmar
        btnConfirmar.setBackground(new Color(0, 0, 0)); // Define a cor de fundo do botão Confirmar
        btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 10)); // Define a fonte do texto do botão Confirmar

        btnConfirmar.setBounds(562, 532, 110, 21); // Define a posição e o tamanho do botão Confirmar
        contentPane.add(btnConfirmar); // Adiciona o botão Confirmar ao painel
        btnConfirmar.setVisible(false); // Define a visibilidade do botão Confirmar como falso (invisível)

        // Botão de edição de erro
        JButton btnEditar = new JButton("Editar");
        btnEditar.setForeground(Color.WHITE); // Define a cor do texto do botão Editar
        btnEditar.setBackground(new Color(0, 0, 0)); // Define a cor de fundo do botão Editar
        btnEditar.setFont(new Font("Tahoma", Font.BOLD, 10)); // Define a fonte do texto do botão Editar
        btnEditar.addActionListener(new ActionListener() { // Adiciona um ouvinte de evento ao botão Editar
            public void actionPerformed(ActionEvent e) { // Método chamado quando o botão Editar é clicado
                btnConfirmar.setVisible(true); // Torna o botão Confirmar visível
                editarErros(); // Chama o método para permitir a edição de erros
                btnConfirmar.addActionListener(new ActionListener() { // Adiciona um ouvinte de evento ao botão Confirmar
                    public void actionPerformed(ActionEvent e) { // Método chamado quando o botão Confirmar é clicado
                        confirmarErros(); // Chama o método para confirmar as edições nos erros
                    }
                });
            }
        });
        btnEditar.setBounds(127, 532, 110, 21); // Define a posição e o tamanho do botão Editar
        contentPane.add(btnEditar); // Adiciona o botão Editar ao painel

        // Botão de exclusão de erro
        JButton btnDeletar = new JButton("Deletar");
        btnDeletar.setForeground(Color.WHITE); // Define a cor do texto do botão Deletar
        btnDeletar.setBackground(new Color(0, 0, 0)); // Define a cor de fundo do botão Deletar
        btnDeletar.setFont(new Font("Tahoma", Font.BOLD, 10)); // Define a fonte do texto do botão Deletar
        btnDeletar.addActionListener(new ActionListener() { // Adiciona um ouvinte de evento ao botão Deletar
            public void actionPerformed(ActionEvent e) { // Método chamado quando o botão Deletar é clicado
                deletarErros(); // Chama o método para deletar os erros selecionados
            }
        });
        btnDeletar.setBounds(244, 532, 110, 21); // Define a posição e o tamanho do botão Deletar
        contentPane.add(btnDeletar); // Adiciona o botão Deletar ao painel

        // Campos de texto para inserção do nome e plataforma do erro
        txtNome = new JTextField();
        txtNome.setText("Nome"); // Define o texto inicial do campo
        txtNome.setForeground(Color.WHITE); // Define a cor do texto do campo
        txtNome.setFont(new Font("Tahoma", Font.PLAIN, 10)); // Define a fonte do texto do campo
        txtNome.setBackground(new Color(51, 51, 51)); // Define a cor de fundo do campo
        txtNome.setEnabled(false); // Define o campo como desabilitado
        txtNome.setEditable(false); // Define o campo como não editável
        txtNome.setBounds(403, 25, 422, 38); // Define a posição e o tamanho do campo
        contentPane.add(txtNome); // Adiciona o campo ao painel
        txtNome.setColumns(10); // Define o número de colunas do campo

        txtPlataforma = new JTextField();
        txtPlataforma.setText("Plataforma"); // Define o texto inicial do campo
        txtPlataforma.setForeground(Color.WHITE); // Define a cor do texto do campo
        txtPlataforma.setBackground(new Color(51, 51, 51)); // Define a cor de fundo do campo
        txtPlataforma.setEnabled(false); // Define o campo como desabilitado
        txtPlataforma.setEditable(false); // Define o campo como não editável
        txtPlataforma.setColumns(10); // Define o número de colunas do campo
        txtPlataforma.setBounds(403, 83, 422, 38); // Define a posição e o tamanho do campo
        contentPane.add(txtPlataforma); // Adiciona o campo ao painel

        // Área de texto para inserção da descrição do erro
        txtDescricao.setText("Descrição"); // Define o texto inicial da área de texto
        txtDescricao.setForeground(Color.WHITE); // Define a cor do texto da área de texto
        txtDescricao.setBackground(new Color(51, 51, 51)); // Define a cor de fundo da área de texto
        txtDescricao.setEnabled(false); // Define a área de texto como desabilitada
        txtDescricao.setEditable(false); // Define a área de texto como não editável
        txtDescricao.setBounds(403, 141, 422, 187); // Define a posição e o tamanho da área de texto
        contentPane.add(txtDescricao); // Adiciona a área de texto ao painel

        // Área de texto para inserção da resolução do erro
        txtResolucao.setText("Resolução"); // Define o texto inicial da área de texto
        txtResolucao.setForeground(Color.WHITE); // Define a cor do texto da área de texto
        txtResolucao.setBackground(new Color(51, 51, 51)); // Define a cor de fundo da área de texto
        txtResolucao.setEnabled(false); // Define a área de texto como desabilitada
        txtResolucao.setEditable(false); // Define a área de texto como não editável
        txtResolucao.setBounds(403, 338, 422, 187); // Define a posição e o tamanho da área de texto
        contentPane.add(txtResolucao); // Adiciona a área de texto ao painel

        // Define a cor do cursor nos campos de texto
        txtNome.setCaretColor(Color.WHITE);
        txtPlataforma.setCaretColor(Color.WHITE);
        txtDescricao.setCaretColor(Color.WHITE);
        txtResolucao.setCaretColor(Color.WHITE);
    }
    private void carregarErros() {
        modeloErros.clear(); // Limpa o modelo de lista para evitar duplicatas
        try {
            // Estabelece a conexão com o banco de dados MySQL
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/errorsystem", "root", "root");
            Statement estamento = conexao.createStatement(); // Cria uma instância do Statement
            String SQL = "SELECT Nome FROM erros"; // Consulta SQL para selecionar os nomes dos erros da tabela erros
            ResultSet resultSet = estamento.executeQuery(SQL); // Executa a consulta SQL e obtém o conjunto de resultados

            // Itera sobre os resultados do conjunto de resultados
            while (resultSet.next()) {
                String nomeErro = resultSet.getString("Nome"); // Obtém o nome do erro da coluna "Nome"
                modeloErros.addElement(nomeErro); // Adiciona o nome do erro ao modelo de lista
            }

            resultSet.close(); // Fecha o conjunto de resultados
            estamento.close(); // Fecha o Statement
            conexao.close(); // Fecha a conexão com o banco de dados
        } catch (SQLException ex) { // Captura exceções do tipo SQLException
            ex.printStackTrace(); // Imprime o rastreamento da pilha
            JOptionPane.showMessageDialog(null, "Erro ao carregar erros do banco de dados"); // Exibe uma mensagem de erro em um popup
        }
    }

    // Método para exibir detalhes do erro selecionado na lista
    private void exibirErros(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { // Verifica se o evento de seleção está completo
            int index = listaErros.getSelectedIndex(); // Obtém o índice do item selecionado na lista
            if (index != -1) { // Verifica se algum item está selecionado
                try {
                    // Estabelece a conexão com o banco de dados MySQL
                    Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/errorsystem", "root", "root");
                    String nomeErro = modeloErros.getElementAt(index); // Obtém o nome do erro selecionado

                    String SQL = "SELECT * FROM erros WHERE Nome = ?"; // Consulta SQL para selecionar os detalhes do erro com base no nome
                    PreparedStatement estamento = conexao.prepareStatement(SQL); // Cria uma instância do PreparedStatement
                    estamento.setString(1, nomeErro); // Define o nome do erro como parâmetro na consulta preparada
                    ResultSet resultSet = estamento.executeQuery(); // Executa a consulta SQL e obtém o conjunto de resultados

                    if (resultSet.next()) {
                        // Preenche os campos de texto com os detalhes do erro selecionado
                        txtNome.setText(resultSet.getString("Nome"));
                        txtDescricao.setText(resultSet.getString("Descricao"));
                        txtPlataforma.setText(resultSet.getString("Plataforma"));
                        txtResolucao.setText(resultSet.getString("Resolucao"));
                    }

                    resultSet.close(); // Fecha o conjunto de resultados
                    estamento.close(); // Fecha o PreparedStatement
                    conexao.close(); // Fecha a conexão com o banco de dados
                } catch (SQLException ex) { // Captura exceções do tipo SQLException
                    ex.printStackTrace(); // Imprime o rastreamento da pilha
                    JOptionPane.showMessageDialog(null, "Erro ao carregar informações do banco de dados"); // Exibe uma mensagem de erro em um popup
                }
            }
        }
    }

    // Método para deletar o erro selecionado na lista
    private void deletarErros() {
        DefaultListModel<String> modeloErros = (DefaultListModel<String>) listaErros.getModel(); // Obtém o modelo de lista
        try {
            // Estabelece a conexão com o banco de dados MySQL
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/errorsystem", "root", "root");
            String SQL = "DELETE FROM erros WHERE Nome = ?"; // Consulta SQL para deletar o erro com base no nome
            PreparedStatement pstmt = conexao.prepareStatement(SQL); // Cria uma instância do PreparedStatement
            int index = listaErros.getSelectedIndex(); // Obtém o índice do item selecionado na lista
            
            if (index != -1) { // Verifica se algum item está selecionado
                String nomeErroSelecionado = modeloErros.getElementAt(index); // Obtém o nome do erro selecionado
                pstmt.setString(1, nomeErroSelecionado); // Define o nome do erro como parâmetro na consulta preparada
                int linhasAfetadas = pstmt.executeUpdate(); // Executa a consulta SQL e obtém o número de linhas afetadas

                if (linhasAfetadas > 0) {
                    JOptionPane.showMessageDialog(null, "Deletado com sucesso"); // Exibe uma mensagem de sucesso em um popup
                    modeloErros.removeElementAt(index); // Remove o erro da lista
                    limparCampos(); // Limpa os campos de texto
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível deletar."); // Exibe uma mensagem de erro em um popup
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um item."); // Exibe uma mensagem para selecionar um item em um popup
            }
            pstmt.close(); // Fecha o PreparedStatement
            conexao.close(); // Fecha a conexão com o banco de dados
        } catch (SQLException ex) { // Captura exceções do tipo SQLException
            ex.printStackTrace(); // Imprime o rastreamento da pilha
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados"); // Exibe uma mensagem de erro em um popup
        }
    }

    // Método para limpar os campos de texto
    private void limparCampos() {
        txtNome.setText(""); // Limpa o campo de texto do nome do erro
        txtDescricao.setText(""); // Limpa o campo de texto da descrição do erro
        txtPlataforma.setText(""); // Limpa o campo de texto da plataforma do erro
        txtResolucao.setText(""); // Limpa o campo de texto da resolução do erro
    }
    
    // Método para habilitar a edição dos campos de texto
    private void editarErros() {
        txtPlataforma.setEnabled(true); // Habilita a edição do campo de texto da plataforma
        txtPlataforma.setEditable(true); // Permite a edição do campo de texto da plataforma
        txtDescricao.setEnabled(true); // Habilita a edição do campo de texto da descrição
        txtDescricao.setEditable(true); // Permite a edição do campo de texto da descrição
        txtResolucao.setEnabled(true); // Habilita a edição do campo de texto da resolução
        txtResolucao.setEditable(true); // Permite a edição do campo de texto da resolução
    }

    // Método para confirmar as edições e atualizar os dados no banco de dados
    private void confirmarErros() {
        try {
            // Estabelece a conexão com o banco de dados MySQL
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/errorsystem", "root", "root");
            String SQL = "UPDATE erros SET Nome = ?, Descricao = ?, Plataforma = ?, Resolucao = ? WHERE Nome = ?"; // Consulta SQL para atualizar os dados do erro
            PreparedStatement estamento = conexao.prepareStatement(SQL); // Cria uma instância do PreparedStatement
            
            // Define os parâmetros da consulta SQL
            estamento.setString(1, txtNome.getText()); // Nome do erro (não pode ser alterado)
            estamento.setString(2, txtDescricao.getText()); // Descrição do erro
            estamento.setString(3, txtPlataforma.getText()); // Plataforma do erro
            estamento.setString(4, txtResolucao.getText()); // Resolução do erro
            estamento.setString(5, txtNome.getText()); // Nome do erro (para a cláusula WHERE)
            
            int linhasAfetadas = estamento.executeUpdate(); // Executa a consulta SQL e obtém o número de linhas afetadas
            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(null, "Atualizado com sucesso."); // Exibe uma mensagem de sucesso em um popup
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um item."); // Exibe uma mensagem para selecionar um item em um popup
            }

            estamento.close(); // Fecha o PreparedStatement
            conexao.close(); // Fecha a conexão com o banco de dados
        } catch (SQLException ex) { // Captura exceções do tipo SQLException
            ex.printStackTrace(); // Imprime o rastreamento da pilha
            JOptionPane.showMessageDialog(null, "Erro ao atualizar informações no banco de dados"); // Exibe uma mensagem de erro em um popup
        }
        bloquearErros(); // Chama o método para bloquear os campos de texto após a confirmação das edições
    }

    // Método para bloquear a edição dos campos de texto
    private void bloquearErros() {
        txtPlataforma.setEnabled(false); // Desabilita a edição do campo de texto da plataforma
        txtPlataforma.setEditable(false); // Impede a edição do campo de texto da plataforma
        txtDescricao.setEnabled(false); // Desabilita a edição do campo de texto da descrição
        txtDescricao.setEditable(false); // Impede a edição do campo de texto da descrição
        txtResolucao.setEnabled(false); // Desabilita a edição do campo de texto da resolução
        txtResolucao.setEditable(false); // Impede a edição do campo de texto da resolução
        btnConfirmar.setVisible(false); // Torna o botão de confirmação invisível
    }
}

*/
