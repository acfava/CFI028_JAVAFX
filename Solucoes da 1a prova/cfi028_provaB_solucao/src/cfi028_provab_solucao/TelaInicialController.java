/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package cfi028_provab_solucao;

import Model.Ingressos;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Aluno
 */
public class TelaInicialController implements Initializable {

 private Label label;
 @FXML
 private Button btnFechar;
 @FXML
 private TextField txtNome;
 @FXML
 private ComboBox<String> cbTipos;
 @FXML
 private Button btnCadastrar;
 @FXML
 private ListView<Ingressos> lstVendas;
 @FXML
 private Label lblNome;
 @FXML
 private Label lblIdade;
 @FXML
 private TextField txtIdade;
 @FXML
 private Label lblTipo;
 @FXML
 private CheckBox chkPipoca;
 @FXML
 private CheckBox chkRefrigerante;
 @FXML
 private CheckBox chkSuco;
 @FXML
 private CheckBox chkDoces;
 @FXML
 private RadioButton rbComum;
 @FXML
 private ToggleGroup tgAssentos;
 @FXML
 private RadioButton rbEspecial;
 @FXML
 private RadioButton rbLuxo;
 @FXML
 private Button btnApagar;
 @FXML
 private Button btnCalcular;
 @FXML
 private Label lblTotal;
 @FXML
 private Label lblAssento;

 // define as variáveis globais a serem utilizadas
 // um observable do mesmo tipo da classe definida
 ObservableList<Ingressos> obsIngressos = FXCollections.observableArrayList();
 // o preco a ser calculado
 Double preco = 0.0;
 // preco dos ingressos: meia, inteira e cortesia
 Double[] precoIngresso = {25.0, 50.0, 0.0};
 // preco das cadeiras: acrescentar o valor de acordo com a cadeira
 Double[] precoCadeira = {00.0, 10.0, 25.0};
 @FXML
 private Label lblFechamento;
 @FXML
 private AnchorPane containerPrincipal;

 @Override
 public void initialize(URL url, ResourceBundle rb) {
  // chama metodo que vai inicializar os componentes
  inicializaComponentes();
 }

 @FXML
 private void btnFecharbtnFecharClick(ActionEvent event) {
  System.exit(0);
 }

 @FXML
 private void handleButtonActionbtnCadastrarClick(ActionEvent event) {
  // todos os campos dever ser preenchidos -> && significa OU
  if (!cbTipos.getSelectionModel().isEmpty() && !txtNome.getText().isEmpty() && !txtIdade.getText().isEmpty()) {
   // retorna posição do tipo de ingresso selecionado
   int posicao = cbTipos.getSelectionModel().getSelectedIndex();
   // associa este index retornado com o vetor de precoIngresso, retornando assim o valor do ingresso em preco
   preco = precoIngresso[posicao];
   //cria um rbCadeiras interno, lido a partir de qual dos tgAssentos foi selecionado
   RadioButton rbCadeiras = (RadioButton) tgAssentos.getSelectedToggle();
   // retorna qual a posição que o rbCadeiras retornado era no tsAssentos
   int index = tgAssentos.getToggles().indexOf(rbCadeiras);
   // e associa com o preco das cadeiras, sendo somado também com o preco. Em preco, agora temos o preco do ingresso mais o preco do assento escolhido
   preco = preco + precoCadeira[index];

   String texto = "";
   String Cadeiras;
   // lendo da tela
   String nome = txtNome.getText();
   String idade = txtIdade.getText();
   Integer Idade = Integer.parseInt(idade);
   Cadeiras = rbCadeiras.getText();
   texto = texto + Cadeiras + ", ";
   // montando a variável texto: comeca com o tipo de cadeiras e vai acrescentando o texto dos produtos selecionados abaixo
   texto = texto + cbTipos.getValue().toUpperCase() + ", ";
   // vai somando no preco se o produto foi selecionado
   if (chkDoces.isSelected()) {
    preco = preco + 15.0;
    texto = texto + "Chocolate, ";
   }
   if (chkPipoca.isSelected()) {
    preco = preco + 32.0;
    texto = texto + "Pipoca, ";

   }
   if (chkRefrigerante.isSelected()) {
    preco = preco + 20.0;
    texto = texto + "Refrigerante, ";
   }
   if (chkSuco.isSelected()) {
    preco = preco + 25.0;
    texto = texto + "Suco, ";
   }

   // a classe aqui a classe ingresso armazena nome, idade, preco e texto. Foi criado um objeto i a partir da classe ingressos
   Ingressos i = new Ingressos(nome, Idade, preco, texto);
   // o objeto i foi inserido no observablelist, e como já foi setado a associacao deste com o listview (linha ____) 
   // a compra é mostrada no listview de acordo como definido no toString de ingressos
   obsIngressos.add(i);
   // apresenta o valor a pagar e a cadeira comprada
   lblTotal.setText(String.format("%.2f", preco));
   lblAssento.setText(Cadeiras);
   txtNome.clear();
   txtNome.requestFocus();
  } else {
   alerta("Preencha os campos corretamente");
  }

 }

 @FXML
 private void handleButtonActionbtnApagarClick(ActionEvent event) {
  int posicao = lstVendas.getSelectionModel().getSelectedIndex();
  if (posicao >= 0) {
   obsIngressos.remove(posicao);
  } else if (obsIngressos.isEmpty()) {
   btnApagar.setDisable(false);
  } else {
   btnApagar.setDisable(true);
  }
 }

 @FXML
 private void handleButtonActionbtnCalcularClick(ActionEvent event) {
  // ZERA A VARIAVEL DE TOTALIZADOR
  Double total = 0.0;
  // PARA CADA INGRESSO I EXISTENTE NO OBSINGRESSOS
  for (Ingressos i : obsIngressos) {
   // RETORNA O VALOR DO PRECO DO INGRESSO DO OBJETO I E SOMA NO TOTALIZADOR
   total = total + i.getPreco();
  }
  // converte para string e apresenta na tela, com duas casas decimais

  lblFechamento.setText("R$ " + String.format("%.2f", total));
 }

 // MÉTODOS DEFINIDOS PELO USUÁRIO
 public void inicializaComponentes() {
  // SETA O LISTVIEW PARA MOSTRAR OS DADOS DO OBSERVABLELIST. AMBOS SÃO DO TIPO INGRESSOS 
  lstVendas.setItems(obsIngressos);
  // CARREGA TEXTO NO COMBOBOX. 
  cbTipos.getItems().addAll("Meia", "Inteira", "Cortesia");
  // deixa a cadeira COMUM como padrão, para não dar erro na leitura do valor das cadeiras 
  tgAssentos.selectToggle(rbComum);
// setando campo IDADE para permitir somente numeros inteiros
  UnaryOperator<TextFormatter.Change> filtro = change -> {
   String newText = change.getControlNewText();
   if (newText.matches("-?[0-9]+")) {  //-?[0-9]+
    return change;
   }
   return null;
  };
  TextFormatter<String> textoFormatado = new TextFormatter<>(filtro);
  txtIdade.setTextFormatter(textoFormatado);
 }
// RECEBE UMA MENSAGEM E MOSTRA NA TELA

 public void alerta(String mensagem) {
  Alert alerta = new Alert(Alert.AlertType.ERROR);
  alerta.setTitle("Erro");
  alerta.setContentText(mensagem);
  alerta.showAndWait();
 }

 
}