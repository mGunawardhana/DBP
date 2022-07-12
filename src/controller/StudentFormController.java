/*
 *  * Developed by - mGunawardhana
 *  * Contact email - mrgunawardhana27368@gmail.com
 *  * what's app - 071 - 9043372
 */

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.student;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentFormController {
    public JFXTextField studentTxt;
    public JFXTextField emailTxt;
    public JFXTextField ContactTxt;
    public JFXTextField AddressTxt;
    public JFXTextField NicTxt;
    public Label idLbl;
    public TableView<student> studentTbl;
    public TableColumn<student, String> idColumnName;
    public TableColumn<student, String> NameColumn;
    public TableColumn<student, String> emailColumn;
    public TableColumn<student, String> ContactColumn;
    public TableColumn<student, String> AddressColumn;
    public TableColumn<student, String> nicColumn;
    public JFXButton btnSave;
    public JFXButton btnAddNew;
    public JFXButton btnDelete;
    public JFXTextField searchID;

    public void initialize() {
        idColumnName.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        ContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        nicColumn.setCellValueFactory(new PropertyValueFactory<>("nic"));

        idLbl.setDisable(true);

        try {
            loadAllStudent();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        student s = new student(idLbl.getText(), studentTxt.getText(), emailTxt.getText(), ContactTxt.getText(), AddressTxt.getText(), NicTxt.getText());
        try {
            if (CrudUtil.execute("INSERT INTO Student VALUES (?,?,?,?,?,?)", s.getStudentId(), s.getStudentName(), s.getEmail(), s.getContact(), s.getAddress(), s.getNic())) {
                studentTbl.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        studentTxt.setDisable(false);
        emailTxt.setDisable(false);
        ContactTxt.setDisable(false);
        NicTxt.setDisable(false);
        AddressTxt.setDisable(false);

        studentTxt.clear();
        emailTxt.clear();
        ContactTxt.clear();
        NicTxt.clear();
        AddressTxt.clear();


        idLbl.setText(generateNewId());
        studentTxt.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        studentTbl.getSelectionModel().clearSelection();
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        try {
            if (CrudUtil.execute("DELETE FROM Student WHERE studentId=?", searchID.getText())) {
                studentTbl.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        } catch (SQLException | ClassNotFoundException ignored) {
        }
    }

    public void searchOnIDOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (SQLException | ClassNotFoundException ignored) {
        }
    }

    private void loadAllStudent() throws ClassNotFoundException, SQLException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Student");
        ObservableList<student> obList = FXCollections.observableArrayList();

        while (result.next()) {
            obList.add(
                    new student(
                            result.getString("studentId"),
                            result.getString("studentName"),
                            result.getString("email"),
                            result.getString("contact"),
                            result.getString("address"),
                            result.getString("nic")
                    )
            );
        }
        studentTbl.setItems(obList);
        studentTbl.refresh();
    }

    public void search() throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT * FROM Student WHERE studentId=?", searchID.getText());
        if (result.next()) {

            idLbl.setText(result.getString(1));
            studentTxt.setText(result.getString(2));
            emailTxt.setText(result.getString(3));
            ContactTxt.setText(result.getString(4));
            AddressTxt.setText(result.getString(5));
            NicTxt.setText(result.getString(6));
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
        studentTbl.refresh();
    }

    private String generateNewId() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet rst = connection.createStatement().executeQuery("SELECT studentId FROM Student ORDER BY studentId DESC LIMIT 1");
            if (rst.next()) {
                String id = rst.getString("studentId");
                int newItemId = Integer.parseInt(id.replace("STU-", "")) + 1;
                return String.format("STU-%03d", newItemId);
            } else {
                return "STU-001";
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException ignored) {}
        return "STU-001";
    }

    public void updateOnAction(ActionEvent actionEvent) {
        student s = new student(idLbl.getText(), studentTxt.getText(), emailTxt.getText(), ContactTxt.getText(), AddressTxt.getText(), NicTxt.getText());
        try {
            boolean isUpdated = CrudUtil.execute("UPDATE Student SET studentName=? , email=? , contact=?,address=?,nic=? WHERE studentId=?", s.getStudentName(), s.getEmail(), s.getContact(), s.getAddress(), s.getNic(), s.getStudentId());
            if (isUpdated) {
                studentTbl.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

