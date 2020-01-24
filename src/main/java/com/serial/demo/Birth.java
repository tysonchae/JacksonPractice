package com.serial.demo;

import java.util.Date;

public class Birth {

    private Date birthDate;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Birth(){
    }

    public Birth(Date birthDate) {
        this.birthDate = birthDate;
    }
}
