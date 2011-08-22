package edu.fatec.zl.test;

import org.junit.Test;

import edu.fatec.zl.util.CypherUtil;

public class CypherTest {

	@Test
	public void cypherTest(){
		CypherUtil cypher = new CypherUtil();
		System.out.println(cypher.cypherLogin("ivan", "ivan"));
	}
}
