/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entities;

/**
 *
 * @author DELL
 */
public class Fees {
    private int enrollment;
    private String class_id;
    private double installment1;
    private String receipt1;
    private double installment2;
    private String receipt2;
    private double installment3;
    private String receipt3;
    private boolean clear_status;

    public Fees() {
    }

    public Fees(int enrollment, String class_id, double installment1, String receipt1, double installment2, String receipt2, double installment3, String receipt3, boolean clear_status) {
        this.enrollment = enrollment;
        this.class_id = class_id;
        this.installment1 = installment1;
        this.receipt1 = receipt1;
        this.installment2 = installment2;
        this.receipt2 = receipt2;
        this.installment3 = installment3;
        this.receipt3 = receipt3;
        this.clear_status = clear_status;
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public double getInstallment1() {
        return installment1;
    }

    public void setInstallment1(double installment1) {
        this.installment1 = installment1;
    }

    public String getReceipt1() {
        return receipt1;
    }

    public void setReceipt1(String receipt1) {
        this.receipt1 = receipt1;
    }

    public double getInstallment2() {
        return installment2;
    }

    public void setInstallment2(double installment2) {
        this.installment2 = installment2;
    }

    public String getReceipt2() {
        return receipt2;
    }

    public void setReceipt2(String receipt2) {
        this.receipt2 = receipt2;
    }

    public double getInstallment3() {
        return installment3;
    }

    public void setInstallment3(double installment3) {
        this.installment3 = installment3;
    }

    public String getReceipt3() {
        return receipt3;
    }

    public void setReceipt3(String receipt3) {
        this.receipt3 = receipt3;
    }

    public boolean isClear_status() {
        return clear_status;
    }

    public void setClear_status(boolean clear_status) {
        this.clear_status = clear_status;
    }
    
}
