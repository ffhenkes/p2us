package com.ws.darkstar.parser;

/**
 * 
 * @author fabio
 *
 * @param <PARAM> whatever
 * @param <RETURN> whatever
 */
public interface IParser <PARAM, RETURN> {
	
	public RETURN parse(PARAM p);
}
