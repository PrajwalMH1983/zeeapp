package com.zee.zee5app.utils;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomNamingStrategy extends PhysicalNamingStrategyStandardImpl {
	private final static String POSTFIX = "_table";
	//By default all tables should be ended with name _table.
	//we don't want to apply this _table for each and every entity spec
	//we want to set it as thumb rule
	
	@Override
	public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment context) {
		// TODO Auto-generated method stub
		//identifier :  table name
		if(identifier == null)
			return null;
		
		final String newName = identifier.getText() + POSTFIX;
		//table name ====> 1. If @table annotation is available then it will use that name
		//				2.If @table is not there then it will take entity name is not available then by default
		//				it will take class name as entity name.
		
		return identifier.toIdentifier(newName);
	}
	
	@Override
	public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment context) {
		// TODO Auto-generated method stub
		if(identifier == null)
			return null;
		
		return Identifier.toIdentifier(identifier.getText().toLowerCase());
	}
}
