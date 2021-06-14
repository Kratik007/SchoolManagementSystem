/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class Student {
    private int enrollment;
    private String name;
    private Date dob;
    private String fname;
    private String mname;
    private String fmobile;
    private String foccupation;
    private String aadhar;
    private String sssmid;
    private boolean isStudying;
    private Date tcDate;
    private String address;
    private java.sql.Date addmission_date;
    public Student() {
    super();
    }
    public Student(String name, Date dob, String fname, String mname, String fmobile, String foccupation, String aadhar, String sssmid, String address) {
        this();
        this.name = name;
        this.dob = dob;
        this.fname = fname;
        this.mname = mname;
        this.fmobile = fmobile;
        this.foccupation = foccupation;
        this.aadhar = aadhar;
        this.sssmid = sssmid;
        this.address = address;
        SimpleDateFormat smpd=new SimpleDateFormat("yyyy/MM/dd");
        Date date=new Date();
        Date temp=new Date(smpd.format(date));
//        System.out.println(this.addmission_date);
        this.addmission_date=new java.sql.Date(temp.getYear(),temp.getMonth(),temp.getDate());
    }

    
    public Student(int enrollment, String name, Date dob, String fname, String mname, String fmobile, String foccupation, String aadhar, String sssmid, boolean isStudying,Date tcDate, String address, java.sql.Date addmission_date) {
        this.enrollment = enrollment;
        this.name = name;
        this.dob = dob;
        this.fname = fname;
        this.mname = mname;
        this.fmobile = fmobile;
        this.foccupation = foccupation;
        this.aadhar = aadhar;
        this.sssmid = sssmid;
        this.isStudying = isStudying;
        this.tcDate = tcDate;
        this.address = address;
        this.addmission_date = addmission_date;
        this.isStudying=true;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getFmobile() {
        return fmobile;
    }

    public void setFmobile(String fmobile) {
        this.fmobile = fmobile;
    }

    public String getFoccupation() {
        return foccupation;
    }

    public void setFoccupation(String foccupation) {
        this.foccupation = foccupation;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getSssmid() {
        return sssmid;
    }

    public void setSssmid(String sssmid) {
        this.sssmid = sssmid;
    }

    public boolean isIsStudying() {
        return isStudying;
    }

    public void setIsStudying(boolean isStudying) {
        this.isStudying = isStudying;
    }

    public Date getTcDate() {
        return tcDate;
    }

    public void setTcDate(Date tcDate) {
        this.tcDate = tcDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public java.sql.Date getAddmission_date() {
        return addmission_date;
    }

    public void setAddmission_date(java.sql.Date addmission_date) {
        this.addmission_date = addmission_date;
    }
    
}
