/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datta;

import dbase.Tools;
import dbase.Utility;
import java.sql.Date;

/**
 *
 * @author Alilo
 */
public class Membre {
    private int id;
    private String nom;
    private String prenom;
    private int age;
    private String telephone;
    private String image;
    private Date date_debut;
    private Date date_fin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Utility {
        if(null != nom)
		{
			if(nom.length()<3)
			{
				throw new Utility("Le nom de Membre doit contenir au moins 3 caractères");
			}
			
		}else
		{
			throw new Utility("Merci de saisir le nom de Membre");
		}
		this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) throws Utility {
        if(null != prenom)
		{
			if(prenom.length()<3)
			{
				throw new Utility("Le nom de l'utilisateur doit contenir au moins 3 caractères");
			}
			
		}else
		{
			throw new Utility("Merci de saisir le Prenom de Membre");
		}
		
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        
                String add="insert into membre(id_membre,nom_membre,prenom_membre,age,telephone,image) values( "
			+""+id+" ,"	
                        +"'"+nom+"',"
			+"'"+prenom+"',"
                        +""+age+" ,"
			+"'"+telephone+"',"
			+"'"+image+"')";
			
                
                boolean isadd=dbase.Go.runNonQuery(add);
		if(isadd)
		{
			new Tools().msgbox("Membre enregistré");
		}else
		{
			new Tools().alertmsgbox("le nom_utilisateur existe dejà");
		}	
                
                
				
    }

    public Membre(int id, String nom, String prenom, int age, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.telephone = telephone;
    }
    public Membre()
    {
        
    }
    
    public void update_date()
    {
        String update="update membre set "
                +"date_debut='"+date_debut+"',"
                +"date_fin='"+date_fin+"' "
                +"where id_membre= "+id;
        boolean isupdate=dbase.Go.runNonQuery(update);
        if(isupdate)
        {
           new Tools().msgbox("تم تفعيل الاشتراك");
        }
        else{
             new Tools().msgbox("لم يتم تفعيل الاشتراك ");
        }
    }

    public void delete()
    {
        String delete="delete from membre where id_membre="+id;
        boolean isdelete=dbase.Go.runNonQuery(delete);
        if(isdelete)
        {
            new Tools().msgbox("تم حذف العضو");
        }
        else
        {
            new Tools().msgbox("لا يمكن حذف العضو \n هذا العضو يملك اشتراك");
        }
    }
    
    public void update()
    {
        String update="update membre set "
                +"nom_membre='"+nom+"', "
                +"prenom_membre='"+prenom+"',"
                +"age= "+age+" ,"
                +"telephone ='"+telephone+"', "
                +"image='"+image+"' "
                +"where id_membre= "+id;
        boolean isupdate=dbase.Go.runNonQuery(update);
        if(isupdate)
        {
            new Tools().msgbox("تم التعديل");
        }
        else
        {
            new Tools().msgbox("تعذر التعديل");
        }
    }
}
