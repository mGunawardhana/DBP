/*
 *  * Developed by - mGunawardhana
 *  * Contact email - mrgunawardhana27368@gmail.com
 *  * what's app - 071 - 9043372
 */

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;
import util.CrudUtil;

import java.sql.SQLException;

public class StudentFormController {
    public JFXTextField studentTxt;
    public JFXTextField emailTxt;
    public JFXTextField ContactTxt;
    public JFXTextField AddressTxt;
    public JFXTextField NicTxt;
    public Label idLbl;
    public TableView<Student> studentTbl;
    public TableColumn<Student, String> idColumnName;
    public TableColumn<Student, String> NameColumn;
    public TableColumn<Student, String> emailColumn;
    public TableColumn<Student, String> ContactColumn;
    public TableColumn<Student, String> AddressColumn;
    public TableColumn<Student, String> nicColumn;
    public JFXButton btnSave;
    public JFXButton btnAddNew;
    public JFXButton btnDelete;
    public JFXTextField searchID;

    public void initialize() {
        idColumnName.setCellValueFactory(new PropertyValueFactory<>(""));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        ContactColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        nicColumn.setCellValueFactory(new PropertyValueFactory<>(""));

    }


    public void btnSave_OnAction(ActionEvent actionEvent) {
        Student s =new Student(idLbl.getText(),studentTxt.getText(),emailTxt.getText(),ContactTxt.getText(),AddressTxt.getText(),NicTxt.getText());
        try {
            if (CrudUtil.execute("INSERT INTO Student VALUES (?,?,?,?,?,?)",s.getStudentId(),s.getStudentName(),s.getEmail(),s.getContact(),s.getAddress(),s.getNic())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }

    public void searchOnIDOnAction(ActionEvent actionEvent) {
    }
}
