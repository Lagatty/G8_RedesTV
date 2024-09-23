/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g8_ant;
import UI.MainWindow;
import java.sql.*;
    

public class DB_Connection {
    //DB variables
    static Connection con;
    static Statement st;
    static ResultSet rs;
    String query;
    MainWindow menu;
    //constructor
    public DB_Connection(){
        
        try{
                
                
            }
            catch(Exception e){

                //mostrar excepcion
                System.out.println(e);
            }
        }
    
    public static void main(String args[]){
        
        //crear conexion a la base de datos
        DB_Connection con = new DB_Connection();
        //crear menu y abrir
        MainWindow menu = new MainWindow();
        menu.init();
        
        
    }
    
    //Consulta que devuelve en un string todos los sectores
    static public String getSectores(){
        
        String sectores = new String();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
                //se establece la conexion a la base de datos
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/suscripcionestv","root","G8_trabajo1");
                st = con.createStatement();
            String query = "select * from sector";
                rs = st.executeQuery(query);
                //mostrar todos los sectores
                while(rs.next()==true){
                    sectores += "\n";
                    sectores += rs.getString(2);   
                    rs.next();
                }
        }
        catch(Exception e){
            
            //mostrar excepcion
            System.out.println(e);
        }
        return sectores;
    }
    
}
