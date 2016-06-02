/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author joanne.ong.2014
 */
public class User {
    private int id;
    private String email;
    private String name;
    private String password;
    private String userType;

    /**
     * Creates an user with the specified email, name, password and user type
     *
     * @param email the email of the user
     * @param name the name of the user
     * @param password the password hash of the user
     * @param userType the type of user - admin/workshop
     */
    public User(int id, String email, String name, String password, String userType) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.userType = userType;
    }

    //getter methods
        /**
     * Returns the email.
     *
     * @return the email
     */
    public int getId() {
        return id;
    }
    
    /**
     * Returns the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Returns the name.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the user type.
     *
     * @return the user type
     */
    public String getUserType() {
        return userType;
    }

}
