/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package esprit.services;

import esprit.enities.Category;
import esprit.tools.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;


/**
 *
 * @author Lenovo
 */
public class CategoryService {
    
Connection cnx=DataSource.getInstance().getConnection();
    
    
    /**
     *
     * @param C
     */
    public void ajouterCategory (Category C){
        
        
            try {
            String req = "INSERT INTO `Category`( `DescriptionCategorie` , `NomCategorie`) VALUES ('"+C.getDescriptionCategorie()+"','"+C.getNomCategorie()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Category Added successfully!");
            }
            catch (SQLException ex) {
           System.out.println("failed!"); 
        }

           }
    
    
    
    public void modifier(Category C) {

        try {
            String req = "UPDATE `Category` SET `DescriptionCategorie`=?,`NomCategorie`=? WHERE IdCategorie=?";
            PreparedStatement st = cnx.prepareStatement(req);

            st.setString(1, C.getDescriptionCategorie());
            st.setString(2, C.getNomCategorie());
        
            st.setInt(3, C.getIdCategorie());
            st.executeUpdate();
            System.out.println("Categorie Modifié avec succès");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void supprimer_category( int IdCategorie) {
        try {
            String req = "DELETE FROM `category` WHERE IdCategorie=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, IdCategorie);
            st.executeUpdate();
            System.out.println("categorie supprimer avec succès");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public List<Category> afficher(){
        List<Category> categorie = new ArrayList<>();
         //1
         String req = "SELECT * FROM category";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Category E = new Category ();
                E.setDescriptionCategorie(rs.getString("DescriptionCategorie"));
                 E.setNomCategorie(rs.getString("NomCategorie"));
            
              
                
                E.setIdCategorie(rs.getInt("IdCategorie"));
                
                 
                
                categorie.add(E);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return categorie ;
    }

}




       

   
   

    







          
   
    