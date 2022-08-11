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
public class classData {
    private Connection c = null;
    public ResultSet rs = null;
    
    public classData(Connection c, String class_id) {
        try {
            this.c = c;
            String q = String.format("Select * from classroom_data where classroom_id = %s", class_id);
            this.rs = this.c.createStatement().executeQuery(q);
            this.rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet getTests() {
        try {
            String q = String.format("Select * from test_data where classroom_id = %d", this.rs.getInt("classroom_id"));
            return this.c.createStatement().executeQuery(q);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
