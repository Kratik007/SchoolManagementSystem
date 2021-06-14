/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entities;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class Staff {
    private int staffId;
    private String fname_hname;
    private Date dob;
    private String qualification;
    private String mobile;
    private String address;
    private String name;
    
    public Staff() {
        super();
    }

    public Staff(String fname_hname, Date dob, String qualification, String mobile, String address, String name) {
        this.fname_hname = fname_hname;
        this.dob = dob;
        this.qualification = qualification;
        this.mobile = mobile;
        this.address = address;
        this.name = name;
    }

    public Staff(int staffId, String fname_hname, Date dob, String qualification, String mobile, String address, String name) {
        this.staffId = staffId;
        this.fname_hname = fname_hname;
        this.dob = dob;
        this.qualification = qualification;
        this.mobile = mobile;
        this.address = address;
        this.name = name;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getFname_hname() {
        return fname_hname;
    }

    public void setFname_hname(String fname_hname) {
        this.fname_hname = fname_hname;
    }

    public Date getDob() {
        return dob;
    }
    public java.sql.Date getSQLDob() {
//        return dob;
        java.sql.Date date=new java.sql.Date(dob.getYear(),dob.getMonth(),dob.getDay());
        return date;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Staff{" + "staffId=" + staffId + ", fname_hname=" + fname_hname + ", dob=" + dob + ", qualification=" + qualification + ", mobile=" + mobile + ", address=" + address + ", name=" + name + '}';
    }

}
