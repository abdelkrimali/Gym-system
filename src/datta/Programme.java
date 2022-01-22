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
public class Programme {
    private int id;
    private String designation;
    private double prix;
    
     public Programme()
     {
         
     }

    public Programme(int id, String designation, double prix) {
        this.id = id;
        this.designation = designation;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) throws Utility {
        if(null != designation)
		{
			if(designation.length()<3)
			{
				throw new Utility("وصف البرنامج يجب ان يحتوي ثلاث حروف على الاقل");
			}
			
		}else
		{
			throw new Utility("Merci de saisir le nom de l'utilisateur");
		}
		
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) throws Utility {
        String p=String.valueOf(prix);
        if(null != p)
		{
			if(p.length()<3)
			{
				throw new Utility("الرجاء تحديد سعر حقيقي");
			}
			
		}else
		{
			throw new Utility("Merci de saisir le nom de l'utilisateur");
		}
        this.prix = Double.parseDouble(p);
    }
    
    public void add()
    {
        String insert="insert into programme values("
                +id+","
                +"'"+designation+"',"
                +prix+")";
        boolean isadd=dbase.Go.runNonQuery(insert);
        if(isadd)
        {
            new Tools().msgbox("Programme Ajouté ");
        }else
        {
            new Tools().msgbox("Programme existe deja");
        }
    }
    
    public void update()
    {
        String update="update programme set "
                +"nom_programme='"+designation+"',"
                +"prix_programme ="+prix+""
                +" where id_programme="+id;
               
        boolean isupdate=dbase.Go.runNonQuery(update);
       if(isupdate ) {
            new Tools().msgbox("Programme Modifié ");
        }
       else
       {
           new Tools().msgbox("Impossible de modifier le programme ");
       }
    }
    public void delete()
    {
        String delete="delete from programme "
                +"where id_programme="+id;
        
        boolean isdelete=dbase.Go.runNonQuery(delete);
        if(isdelete){
            new Tools().msgbox("Programme supprime");
             }else
        {
            new Tools().msgbox("هذا البرنامج يحتوي على اشتراكات");
        }
    }
    
}
