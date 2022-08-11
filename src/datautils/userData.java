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
public class userData {
    private Connection c = null;
    public ResultSet rs = null;

    public userData(Connection c, int userid) {
        this.c = c;
        String q = String.format("call get_user_data(%d)", userid);
        try {
            ResultSet _rs = this.c.createStatement().executeQuery(q);
            if (!_rs.next()) {
                System.out.println("User not found!");
                System.exit(0);
            }
            
            this.rs = _rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public userData(Connection c, String username) {
        this.c = c;
        String q = String.format("call get_username_data('%s')", username);
        try {
            ResultSet _rs = this.c.createStatement().executeQuery(q);
            if (!_rs.next()) {
                System.out.println("User not found!");
                System.exit(0);
            }
            
            this.rs = _rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet getCourses() {
        try {
            String q = String.format("Select * from student_enrollement where user_id = %d", this.rs.getInt("user_id"));
            return this.c.createStatement().executeQuery(q);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
