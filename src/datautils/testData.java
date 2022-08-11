/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datautils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author Shivatej Patil
 */
public class testData {
    private Connection c = null;
    public ResultSet rs = null;
    
    public testData(Connection c, String test_id) {
        try {
            this.c = c;
            String q = String.format("Select * from test_data where test_id = %s", test_id);
            this.rs = this.c.createStatement().executeQuery(q);
            this.rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet getQuestions() {
        try {
            String q = "select content, answer from test_questions tq, questions q where tq.question_id = q.question_id and tq.test_id = " + this.rs.getInt("test_id");
            return this.c.createStatement().executeQuery(q);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
