package tests.core.commands;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.HashMap;

import org.junit.Test;

import core.commands.CreateProductCommand;

public class CreateProductCommand_Test {
	
	@Test
	public void isParamsValid_true() {
		CreateProductCommand command = new CreateProductCommand();
		HashMap<String, String> cmdParamsList = new HashMap<>();
		cmdParamsList.put("name", "n");
		cmdParamsList.put("price", "10");
		cmdParamsList.put("status", "0");
		
		command.setParams(cmdParamsList);
		assertTrue(command.isParamsValid());
		
		cmdParamsList.put("status", "1");
		assertTrue(command.isParamsValid());
		
		cmdParamsList.put("status", "2");
		assertTrue(command.isParamsValid());
	}
	
	@Test
	public void isParamsValid_false() {
		CreateProductCommand command = new CreateProductCommand();
		command.setParams(null);
		assertFalse(command.isParamsValid());

		HashMap<String, String> cmdParamsList = new HashMap<>();
		command.setParams(cmdParamsList);
		assertFalse(command.isParamsValid());
		
		cmdParamsList.put("name", "n");
		assertFalse(command.isParamsValid());

		cmdParamsList.put("price", "10");
		assertFalse(command.isParamsValid());
		
		cmdParamsList.put("status", "3");
		assertFalse(command.isParamsValid());
		
		cmdParamsList.put("status", "0");
		cmdParamsList.put("name", "");
		assertFalse(command.isParamsValid());
		
		cmdParamsList.put("name", null);
		assertFalse(command.isParamsValid());

		cmdParamsList.put("name", "n");
		cmdParamsList.put("price", "");
		assertFalse(command.isParamsValid());		
	}
	
	@Test
	public void execute_true() throws Exception {
		CreateProductCommand command = new CreateProductCommand();
		HashMap<String, String> cmdParamsList = new HashMap<>();
		cmdParamsList.put("name", "n");
		cmdParamsList.put("price", "10");
		cmdParamsList.put("status", "0");
		command.setParams(cmdParamsList);
		
		assertTrue(command.execute());
	}
	
	@Test(expected = InvalidParameterException.class)
	public void execute_exceptionName() throws Exception {
		CreateProductCommand command = new CreateProductCommand();
		command.setParams(null);
		assertFalse(command.isParamsValid());

		HashMap<String, String> cmdParamsList = new HashMap<>();
		command.setParams(cmdParamsList);
		assertFalse(command.isParamsValid());
		
		cmdParamsList.put("price", "10");
		cmdParamsList.put("status", "0");
		command.execute();
	}
	
	@Test(expected = InvalidParameterException.class)
	public void execute_exceptionStatus() throws Exception {
		CreateProductCommand command = new CreateProductCommand();
		command.setParams(null);
		assertFalse(command.isParamsValid());

		HashMap<String, String> cmdParamsList = new HashMap<>();
		command.setParams(cmdParamsList);
		assertFalse(command.isParamsValid());
		
		cmdParamsList.put("name", "n");
		cmdParamsList.put("price", "10");
		cmdParamsList.put("status", null);
		command.execute();
	}
	
	@Test(expected = InvalidParameterException.class)
	public void execute_exceptionPrice() throws Exception {
		CreateProductCommand command = new CreateProductCommand();
		command.setParams(null);
		assertFalse(command.isParamsValid());

		HashMap<String, String> cmdParamsList = new HashMap<>();
		command.setParams(cmdParamsList);
		assertFalse(command.isParamsValid());
		
		cmdParamsList.put("name", "n");
		cmdParamsList.put("status", "0");
		command.execute();
	}
	
}
