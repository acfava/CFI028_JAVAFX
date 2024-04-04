/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package cfi028_prova1_2024_Solucao;

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

/**
 *
 * @author Aluno
 */
public class TelaController implements Initializable {
    
    @FXML
    private Button btnFechar;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtData;
    @FXML
    private TextField txtPeso;
    @FXML
    private ComboBox<String> cbTipos;
    @FXML
    private CheckBox chkPenta;
    @FXML
    private CheckBox chkMeningite;
    @FXML
    private Button btnCadastrar;
    @FXML
    private ListView<Vacinacao> lxtVacinas;
    
    
    ObservableList<Vacinacao> obsVacina = FXCollections.observableArrayList();
    @FXML
    private CheckBox chkTriplice;
    @FXML
    private CheckBox chkPneumo;
    @FXML
    private RadioButton radPix;
    @FXML
    private ToggleGroup ToPagar;
    @FXML
    private RadioButton radCartao;
    @FXML
    private RadioButton radBoleto;
    @FXML
    private Button btnApagar;
    @FXML
    private Button btnCalcularTotal;
    @FXML
    private Label labTotal;
    @FXML
    private Button btnApagarTudo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lxtVacinas.setItems(obsVacina);
        cbTipos.getItems().addAll(
         "Masculino", "Feminino");
        ToPagar.selectToggle(radPix);
        cbTipos.getSelectionModel().selectFirst();
        txtNome.requestFocus();
        UnaryOperator<TextFormatter.Change> filtro = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("-?([0-9]*)\\.?([0-9]*)")) {
        return change;
        }
        return null;
        };
        TextFormatter<String> textoFormatado = new TextFormatter<>(filtro);
        txtPeso.setTextFormatter(textoFormatado);
    }    

    @FXML
    private void btnFecharClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void btnCadastrarClick(ActionEvent event) {
    if (!cbTipos.getSelectionModel().isEmpty()) {
   RadioButton rbTipo = (RadioButton) ToPagar.getSelectedToggle();
   double preco = 0.0;
   String texto = "";
   String Pagamento;
   String nome = txtNome.getText();
   Double peso = Double.parseDouble(txtPeso.getText());
   String data = txtData.getText();
   if (nome.isEmpty()) {
    alerta("Verifique o nome digitado !");
    txtNome.requestFocus();
   } else {
    
    if (chkPenta.isSelected()) {
     preco = preco + 320.0;
     texto = texto + "Pentavalente, ";
    }
    if (chkMeningite .isSelected()) {
     texto = texto + "Meningite , ";
     preco = preco + 420.0;
    }
    if (chkTriplice.isSelected()) {
     texto = texto + "Tríplice Viral, ";
     preco = preco + 150.0;
    }
    if (chkPneumo.isSelected()) {
     texto = texto + "Pneumo, ";
     preco = preco + 320.0;
    }
    Pagamento = rbTipo.getText();
    texto = texto + ".";
    Double precoDes = 0.0;
    if(Pagamento.equalsIgnoreCase("Pix (10%)")){
        precoDes = preco * 0.9;
    }
    if(Pagamento.equalsIgnoreCase("Cartão Crédito")){
        precoDes = preco;
    }
    if(Pagamento.equalsIgnoreCase("Boleto (5%)")){
        precoDes = preco * 0.95;
    }             
    String genero = cbTipos.getSelectionModel().getSelectedItem();
    Vacinacao p = new Vacinacao(nome, data, peso, genero, texto, preco, precoDes);
    obsVacina.add(p);
    limpar();
   }
  } else {
   alerta("Selecione um Gênero");
  }
    }

    public void alerta(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
    
    @FXML
    private void handleButtonActionbtnApagar(ActionEvent event) {
        int posicao = lxtVacinas.getSelectionModel().getSelectedIndex();
        if (posicao >= 0) {
            obsVacina.remove(posicao);
        }
        limpar();
    }

    public void limpar() {
       txtNome.clear();
       txtData.clear();
       txtPeso.clear();
       chkPenta.setSelected(false);
       chkMeningite.setSelected(false);
       chkTriplice.setSelected(false);
       chkPneumo.setSelected(false);
       ToPagar.selectToggle(radPix);
       cbTipos.setValue("Gênero");
    }

    
    @FXML
    private void handleButtonActionbtnCalcularTotal(ActionEvent event) {
        Double total = 0.0;
        for(Vacinacao c : obsVacina){
            total = total + c.getPrecoDesconto();
        }
        labTotal.setText(String.format("R$%.2f", total));
    }

    @FXML
    private void handleButtonbtnApagarTudo(ActionEvent event) {
        obsVacina.clear();
        limpar();
    }
    
}
