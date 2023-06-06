package com.login.service.security;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.Getter;

@Getter
@SuppressWarnings("deprecation")
public class HashPassword
{
    private Argon2 argon2;
    private String finalKey;

    public HashPassword()
    {
        argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    }
	public void setFinalKey(String finalKey) {
        this.finalKey = argon2.hash(1,1024,1,finalKey);
    }

    public boolean verifyKey(String token,String clave){
        return argon2.verify(token,clave);
    }


}
