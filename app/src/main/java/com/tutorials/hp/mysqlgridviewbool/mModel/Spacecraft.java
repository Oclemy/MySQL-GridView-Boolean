package com.tutorials.hp.mysqlgridviewbool.mModel;

/**
 * Created by Oclemy on 10/11/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class Spacecraft {


    /*
    INSTANCE FIELDS
     */
    private int id;
    private String name;
    private String propellant;
    private int technologyExists;

    /*
    GETTERS AND SETTERS
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPropellant() {
        return propellant;
    }

    public void setPropellant(String propellant) {
        this.propellant = propellant;
    }

    public int getTechnologyExists() {
        return technologyExists;
    }

    public void setTechnologyExists(int technologyExists) {
        this.technologyExists = technologyExists;
    }




    /*
    TOSTRING
     */
    @Override
    public String toString() {
        return name;
    }
}
