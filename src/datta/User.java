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
public class User {
    
        private String nom;
    private String prenom;
    private String username;
    private String password;
    private String telephone;
    private String email;
    private String image;

    public User(String username,String nom, String prenom, String telephone, String email) {
        this.username=username;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
    }
    public User()
    {
        
    }
    
    
   
    
   

    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Utility {
        if(null != nom)
		{
			if(nom.length()<3)
			{
				throw new Utility("Le nom de l'utilisateur doit contenir au moins 3 caractères");
			}
			
		}else
		{
			throw new Utility("Merci de saisir le nom de l'utilisateur");
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
				throw new Utility("Le prenom de l'utilisateur doit contenir au moins 3 caractères");
			}
			
		}else
		{
			throw new Utility("Merci de saisir le prenom de l'utilisateur");
		}
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    
    public void add() {
		
	
		String add="insert into utilisateur values( "
				+"'"+nom+"',"
				+"'"+prenom+"',"
				+"'"+username+"',"
				+"'"+password+"',"
				+"'"+telephone+"',"
				+"'"+email+"',"
				+"'"+image+"')";
		boolean isadd=dbase.Go.runNonQuery(add);
		if(isadd)
		{
			new Tools().msgbox("Utilisateur enregistré");
		}else
		{
			new Tools().alertmsgbox("le nom_utilisateur existe dejà");
		}	
	}
    
    
	public void update() {
		String update ="update utilisateur set "
				+"mot_pass='"+password+"',"
                                +"nom='"+nom+"',"
                                +"prenom='"+prenom+"',"
				+"email='"+email+"' "
				+"where nom_utilisateur ='"+username+"'";
		boolean isupdate=dbase.Go.runNonQuery(update);
		if(isupdate)
		{
			new Tools().msgbox("Modification Enregistrées");
		}
		else
		{
			new Tools().alertmsgbox("failed");
		}
	}
        public void delete()
        {
            String delete="delete from utilisateur "
                    +"where nom_utilisateur='"+username+"'";
            boolean isdelete=dbase.Go.runNonQuery(delete);
            if(isdelete)
            {
                new Tools().msgbox("Utilisateur Supprime");
            }
            else
            {
                new Tools().msgbox("Impossible ");
            }
        }
    
    
}
