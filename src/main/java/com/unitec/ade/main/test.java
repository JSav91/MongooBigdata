package com.unitec.ade.main;

import java.util.Date;

import com.google.gson.Gson;

public class test {

	public static void main(String[] args) {
		Date date= new Date("02/01/1999");
		System.out.println(new Date(date.getTime()));
	}
}
