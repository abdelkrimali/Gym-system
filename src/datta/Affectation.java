/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datta;

import dbase.Tools;
import java.sql.Date;


/**
 *
 * @author Alilo
 */
public class Affectation {
    private int id_affectation;
    private int membre;
    private int abonement;
    private Date date_debut;
    private Date date_fin;

    public int getMembre() {
        return membre;
    }

    public void setMembre(int membre) {
        this.membre = membre;
    }

    public int getAbonement() {
        return abonement;
    }

    public void setAbonement(int abonement) {
        this.abonement = abonement;
    }
      public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
       
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }


   
    
    
    public void add()
    {
        String add="insert into affectation values( "
                +id_affectation+" ,"
                +membre+", "
                +abonement+", "
                +"'"+date_debut+"',"
                +"'"+ date_fin+"')" ;
        boolean isadd=dbase.Go.runNonQuery(add);
        if(isadd)
        {
           // new Tools().msgbox("تم تفعيل الاشتراك");
            
        }else{
            
        }
    }
     public void add_renouvlement()
    {
        String add="insert into affectation values( "
                +id_affectation+" ,"
                +membre+", "
                +abonement+", "
                +"'"+date_debut+"',"
                +"'"+ date_fin+"')" ;
        boolean isadd=dbase.Go.runNonQuery(add);
        if(isadd)
        {
           // new Tools().msgbox("تم تفعيل الاشتراك");
            
        }else{
            
        }
    }

    public int getId_affectation() {
        return id_affectation;
    }

    public void setId_affectation(int id_affectation) {
        this.id_affectation = id_affectation;
    }
    
   public void delete()
   {
       String delete="delete from affectation where abonement ="+abonement;
       boolean isdelete=dbase.Go.runNonQuery(delete);
       if(isdelete)
       {
           System.out.println("affectation supprimé");
       }else
       {
            System.out.println("Failed");
       }
   }

  

    
    
    
}
