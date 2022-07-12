/*
 *  * Developed by - mGunawardhana
 *  * Contact email - mrgunawardhana27368@gmail.com
 *  * what's app - 071 - 9043372
 */

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentFormController {
    public JFXTextField studentTxt;
    public JFXTextField emailTxt;
    public JFXTextField ContactTxt;
    public JFXTextField AddressTxt;
    public JFXTextField NicTxt;
    public Label idLbl;
    public TableView studentTbl;
    public TableColumn idColumnName;
    public TableColumn NameColumn;
    public TableColumn emailColumn;
    public TableColumn ContactColumn;
    public TableColumn AddressColumn;
    public TableColumn nicColumn;
    public JFXButton btnSave;
    public JFXButton btnAddNew;
    public JFXButton btnDelete;
    public JFXTextField searchID;

    public void initialize(){
        idColumnName.setCellValueFactory(new PropertyValueFactory<>(""));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        ContactColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        nicColumn.setCellValueFactory(new PropertyValueFactory<>(""));

    }


    public void btnSave_OnAction(ActionEvent actionEvent) {
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }

    public void searchOnIDOnAction(ActionEvent actionEvent) {
    }
}
