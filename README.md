# Sistema de Gerenciamento de Erros
Em diversas atividades diante das telas, diversos erros acontecem e são apresentados, trazendo muitas vezes a urgência de resolvê-los para seguir adiante na atividade que estava sendo executada. Muitas vezes, erros podem ser recorrentes ou acontecerem em outros dispositivos. E toda vez que isso ocorre, vem a urgência de ir atrás de todo o processo para a resolução do erro novamente. Para isso, um software capaz de armazenar e gerenciar erros foi desenvolvido por mim. Tem como objetivo armazenar as eventualidades que acontecem e todas as vezes que for preciso consultar, ter disponível um banco de informações em seu desktop. O sistema foi feito de forma simples por mim para fins de estudo com Java Swing na construção de todo sistema e MySQL para gerenciamento de banco de dados, nas plataformas Eclipse IDE e MySQL Workbench.

---
### Tela Inicial
![telainicial](https://github.com/IgorJF/ErrorSystem/assets/111748228/f8df2357-d12d-451c-b3a1-bd0a14a5e1d5)
Esta é a tela inicial, onde é exibida a lista de ERROS e as funcionalidades que podemos executar.

---
### Tela de Cadastro
![telacadastro](https://github.com/IgorJF/ErrorSystem/assets/111748228/4d988cdd-4942-4585-bb37-28a37bc5fb2f)
A tela de cadastro pode ser acessada ao clicar no botão "Cadastrar" na tela inicial. Neste momento, será necessário informar todas as informações do erro: o Nome ou Código de Erro, a Plataforma ou Tecnologia onde o erro aconteceu, a Descrição detalhada de como aconteceu e a Solução do erro.
![adicioandoinformacoes](https://github.com/IgorJF/ErrorSystem/assets/111748228/8db2605a-1f8b-4b2b-81fd-93887c998397)
Após isso, apenas é necessário clicar no botão "Cadastrar" e as informações serão enviadas ao Banco de Dados.]

---
### Exibição
![exibicaodeinformacoes](https://github.com/IgorJF/ErrorSystem/assets/111748228/4061e71c-7eb5-4bbb-85da-7731a1d347f7)
Ao clicar no botão voltar na Tela de Cadastro, será redirecionado para a tela inicial novamente, onde agora no elemento JList, terá o nome de todos os erros que foram cadastrados e que estão no banco de dados. Note que não é possível editar as informações, visto que as caixas de texto estão bloqueadas.

---
### Edição
![editarinformacoes](https://github.com/IgorJF/ErrorSystem/assets/111748228/119828e6-99ca-4ca8-8ca3-a1125886db56)
Ao selecionar o nome do erro e clicar no botão "Editar", as caixas de texto, exceto a caixa onde está o nome do erro, serão desbloqueadas. Sendo possível alterar quaisquer informações. Um botão de "Confirmar" surgirá na tela, e ao clicar no botão, tudo que foi alterado nas caixas de texto será enviado ao banco de dados, confirmando a atualização de informações com sucesso.

---
![atulizadosucesso](https://github.com/IgorJF/ErrorSystem/assets/111748228/4dc59e00-954f-496a-b70f-fe674e6a74bf)

---
### Deletar
![deletar](https://github.com/IgorJF/ErrorSystem/assets/111748228/80e4bb6a-6fa1-486c-b81c-4f5c2bd6471d)
Ainda se quiser, é possível deletar uma informação que foi guardada anteriormente. Ao selecionar o nome do erro e clicar no botão "Deletar", imediatamente, quaisquer registros daquela informação serão deletados da lista e do banco de dados.
![deletado](https://github.com/IgorJF/ErrorSystem/assets/111748228/0bf70647-ab17-4e05-90a7-0187492a623b)

---
Por fim, ao realizar todas as operações, o software estará disponível para normal utilização. Não é necessário o cadastro das mesmas informações em toda execução do software, pois todas as informações ficam armazenadas no banco de dados. Apenas é necessário que o usuário tenha alguma versão do Java instalada no computador, uma IDE capaz de executar o código Java e uma ferramenta capaz de executar a query SQL.
![telainicial](https://github.com/IgorJF/ErrorSystem/assets/111748228/eb7136a8-cdbe-40e8-84be-47437810bb25)

---
Igor Jorge Ferraz.
Feito para fins de estudo.
Referências:
- https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/package-summary.html
- https://www.javatpoint.com/java-swing
- https://docs.oracle.com/en/java/
- https://www.youtube.com/watch?v=UI_HnR3ZTd0
- https://www.youtube.com/watch?v=kBtzipEWqu8
- https://www.dei.isep.ipp.pt/~nfreire/JAVA%20-%20GUI.pdf
