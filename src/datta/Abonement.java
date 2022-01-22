/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datta;

import dbase.Tools;
import dbase.Utility;

/**
 *
 * @author Alilo
 */
public class Abonement {
    private int idabon;
    private String prg;
    private int programmeabon;
    private String designation;
    private int dure;
    private double ancien_prix;
    private double nouveau_prix;

    public int getIdabon() {
        return idabon;
    }

    public void setIdabon(int idabon) {
        this.idabon = idabon;
    }

    public int getProgrammeabon() {
        return programmeabon;
    }

    public void setProgrammeabon(int programmeabon) {
        
        this.programmeabon = programmeabon;
    }

    public String getDesignation() {
         
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getDure() {
        return dure;
    }

    public void setDure(int dure) {
        this.dure = dure;
    }

    public double getAncien_prix() {
        return ancien_prix;
    }

    public void setAncien_prix(double ancien_prix) {
        this.ancien_prix = ancien_prix;
    }

    public double getNouveau_prix() {
        return nouveau_prix;
    }

    public void setNouveau_prix(double nouveau_prix) {
        this.nouveau_prix = nouveau_prix;
    }
      public String getPrg() {
        return prg;
    }

    public void setPrg(String prg) {
        this.prg = prg;
    }
    public Abonement(){
        
    }
    public Abonement(int id,String designation, String programme,int dure,double prix)
    {this.idabon=id;
        this.designation=designation;
        this.prg=programme;
        this.dure=dure;
        this.nouveau_prix=prix;
    }
    
    public void add()
    {
        String add="insert into abonement values("
                +""+idabon+","
                +""+programmeabon+","
                +"'"+designation+"',"
                +""+dure+","
                +""+ancien_prix+","
                +""+nouveau_prix+" )";
        boolean isadd=dbase.Go.runNonQuery(add);
        if(isadd)
        {
            new Tools().msgbox("Abonement Enregistre");
        }
        else
        {
            new Tools().msgbox("Failed");
        }
                
    }
    public void update()
    {
        String update="Update abonement set "
                +"designation='"+designation+"',"
              +  "nouveau_prix_abonement="+nouveau_prix+" "
                 +"where id_abonement= "+idabon;
        boolean isupdate=dbase.Go.runNonQuery(update);
        if(isupdate)
        {
            new Tools().msgbox("Abonement Modifie");
        }else
        {
            new Tools().msgbox("Impossible");       }
    }
    public void delete()
    {
        String delete ="delete from abonement "
                +"where id_abonement= "+idabon;
        boolean isdelete=dbase.Go.runNonQuery(delete);
        if(isdelete)
        {
            new Tools().msgbox("Abonement Supprime");
        }else
        {
            new Tools().msgbox("Failed");
        }
                
    }

  
    
}
