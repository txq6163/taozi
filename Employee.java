/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.tao;

import java.util.Date;

/**
 *
 * @author user
 */
public class Employee {

    /**
     * 社員ID
     */
    private String eId;

    /**
     * 社員名称
     */
    private String eName;

    /**
     * 性別
     */
    private String eSex;

    /**
     * 社員職位
     */
    private String ePos;

    /**
     * 入社日
     */
    private Date eEntryDate;

    /**
     * @return the eId
     */
    public String geteId() {
        return eId;
    }

    /**
     * @param eId the eId to set
     */
    public void seteId(String eId) {
        this.eId = eId;
    }

    /**
     * @return the eName
     */
    public String geteName() {
        return eName;
    }

    /**
     * @param eName the eName to set
     */
    public void seteName(String eName) {
        this.eName = eName;
    }

    /**
     * @return the eSex
     */
    public String geteSex() {
        return eSex;
    }

    /**
     * @param eSex the eSex to set
     */
    public void seteSex(String eSex) {
        this.eSex = eSex;
    }

    /**
     * @return the ePos
     */
    public String getePos() {
        return ePos;
    }

    /**
     * @param ePos the ePos to set
     */
    public void setePos(String ePos) {
        this.ePos = ePos;
    }

    /**
     * @return the eEntryDate
     */
    public Date geteEntryDate() {
        return eEntryDate;
    }

    /**
     * @param eEntryDate the eEntryDate to set
     */
    public void seteEntryDate(Date eEntryDate) {
        this.eEntryDate = eEntryDate;
    }

}
