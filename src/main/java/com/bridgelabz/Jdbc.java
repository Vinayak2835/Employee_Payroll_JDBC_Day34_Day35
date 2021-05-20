package com.bridgelabz;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Scanner;

public class Jdbc {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pselect = null;
		PreparedStatement pinsert = null;
		PreparedStatement pupdate = null;
		PreparedStatement pdelete = null;
		PreparedStatement pParticularrecord;		
		ResultSet rs = null;
	        Scanner scanner = new Scanner(System.in);
		Scanner input = new Scanner(System.in);
		Scanner input1 = new Scanner(System.in);
		
		int id;
		String name,department,gender;
		double basic_pay, deductions,taxable_pay,tax,net_pay;
		String start ,phonenumber,address;
		int ch = 1;
		
		try {
		   //UC1 UC2	
		   Class.forName(DbConnection.driverName);
		   con = DriverManager.getConnection(DbConnection.url, DbConnection.userName, DbConnection.password);
		   //System.out.println("Connection Established");
		   pselect = con.prepareStatement(	"SELECT * FROM employee_payroll");
		   pinsert = con.prepareStatement("INSERT INTO employee_payroll(name,department,gender,basic_pay,deductions,taxable_pay,tax,net_pay,start,phonenumber,address) values(?,?,?,?,?,?,?,?,?,?,?)");
		   pupdate = con.prepareStatement("UPDATE employee_payroll set basic_pay = ? where name = ?");
		   pdelete = con.prepareStatement("DELETE FROM employee_payroll where name = ?");
		   pParticularrecord = con.prepareStatement("SELECT name from employee_payroll where tax between ? AND ?");
		    
		   while(ch != 5) {
		   System.out.println("Welcome to Employee payroll Services ");	
		   // System.out.println();
		   System.out.println("Press 1.Retrieving data from the database"+"\n"+"Press 2.Inserting data to the database"+"\n"+"Press 3.Updating data to the database"+"\n"+"Press 4.Delete a data from the database"+"\n"+"Press 5.List the Drivers"+" \n"+"Press 6.Access particular Record"+""+"\n"+"Press 7 to find Sum and Average");
		   
		   switch(scanner.nextInt()) {
		   //UC3
		   case 1:pselect.execute();
		          rs = pselect.getResultSet();
		          while(rs.next()) {
		        	  id = rs.getInt("id");
		        	  name = rs.getString("name");
		        	  department  = rs.getString("department");
		        	  gender = rs.getString("gender");
		        	  basic_pay = rs.getDouble("basic_pay");
		        	  deductions = rs.getDouble("deductions");
		        	  taxable_pay = rs.getDouble("taxable_pay");
		        	  tax = rs.getDouble("tax");
		        	  net_pay = rs.getDouble("net_pay");
		        	  start = rs.getString("start");
		        	  phonenumber = rs.getString("phonenumber");
		        	  address = rs.getString("address");
		        	  
		        	  System.out.println(id+" "+name+"  "+department+"    "+gender+"  "+basic_pay+ "   "+deductions+" "+taxable_pay+"   " +tax+"   "+net_pay+"   "+start+"  "+phonenumber+" "+address+"");
		          }
			     break;
			     
			     //Inserting new value to the database
		   case 2:System.out.println("Enter a new Name to insert");
		          name = input.nextLine();
		          System.out.println("Enter department to insert");
		          department = input1.nextLine();
		          System.out.println("Enter a gander");
		          gender = input1.nextLine();
		          System.out.println("Enter  a Basic pay");
		          basic_pay = input1.nextDouble();
		          System.out.println("Enter  a  deductions");
		          deductions = input1.nextDouble();
		          System.out.println("Enter taxable pay");
		          taxable_pay = input1.nextDouble();
		          System.out.println("Enter a tax");
		          tax = input1.nextDouble();
		          System.out.println("Enter a net pay");
		          net_pay = input1.nextDouble();
		          System.out.println("Enter start");
		          start = input.nextLine();
		          System.out.println("Enter a phone number");
		          phonenumber = input.nextLine();
		          System.out.println("Enter a Address");
		          address = input.nextLine();
		          
		          pinsert.setString(1,name);
		          pinsert.setString(2, department);
		          pinsert.setString(3, gender);
		          pinsert.setDouble(4, basic_pay);
		          pinsert.setDouble(5, deductions);
		          pinsert.setDouble(6, taxable_pay);
		          pinsert.setDouble(7, tax);
		          pinsert.setDouble(8, net_pay);
		          pinsert.setString(9,start);
		          pinsert.setString(10, phonenumber);
		          pinsert.setString(11, address);
		          pinsert.execute();
		          System.out.println("Data insert successfully");
		          break; 
		          //UC3-UC4
		      case 3:System.out.println("Enter the name do you wants to update the basic_pay");
		             String name1 = input.nextLine();
		             pselect.execute();
		             rs = pselect.getResultSet();
		             while(rs.next()) {
		            	 if(rs.getString("name").equals(name1)) {
		            		 System.out.println(name1+" Name matches");
		            		 System.out.println("Enter the basic salary do you wants to update");
		            		  basic_pay = scanner.nextDouble();
		            		 pupdate.setDouble(1, basic_pay);
		            		 pupdate.setString(2, name1);
		            		 pupdate.executeUpdate();
		            		 System.out.println("Data updated successfully");
		            	 }
		             }
                  	 break;
		      case 4:System.out.println("Enter a name do you wants to delete");
		             name = input.nextLine();
		             pdelete.setString(1, name);
		             pdelete.execute();
		             System.out.println("Deleted successfully");
		    	     break;
		      case 5:System.out.println("List all drivers");
		             Jdbc jdbc = new Jdbc();
		             jdbc.listDrivers();
		             break;
		      case 6:System.out.println("Access particular record");
		              pselect.execute();
		              rs = pselect.getResultSet();
		              while(rs.next()) {
		            	  System.out.println("Enter first range of tax");
		            	  double firstTax = input1.nextDouble();
		            	  double secondTax = input1.nextDouble();
		              }
		              break;
		      	     //UC6
		      case 7:System.out.println("1.Find Sum "+"\n"+"2.Find AVG"+"\n"+"3.max"+"\n"+"4.Min"+"\n");
		    	    switch(scanner.nextInt()) {
		    	    case 1:System.out.println("SUM of the Employee");
		    	          PreparedStatement pSum = null;
		    	          
		    	          pSum = con.prepareStatement("SELECT SUM(basic_pay) from employee_payroll");
		    	          pSum.execute();
		    	          rs = pSum.getResultSet();
		    	       
		    	          while(rs.next()) {
		    	        	  double sum = rs.getDouble("sum(basic_pay)");
		    	        	  System.out.println("Sum:"+sum);
		    	          }
		    	          break;
		    	    case 2: PreparedStatement pAverage = null;  
		    	    	    pAverage = con.prepareStatement("SELECT AVG(basic_pay) from employee_payroll");  
		    	    	    pAverage.execute();
		    	    	    rs = pAverage.getResultSet();
		    	    	   
		    	    	    while(rs.next()) {
		    	    	    	double avg = rs.getDouble("avg(basic_pay)");
		    	    	    	System.out.println("Average:"+avg);
		    	    	    }
		    	    	    break;
		    	    case 3:PreparedStatement pMax = null;
		    	           pMax = con.prepareStatement("SELECT MAX(basic_pay) from employee_payroll");
		    	           pMax.execute();
		    	           rs = pMax.getResultSet();
		    	           while(rs.next()) {
		    	        	   double max = rs.getDouble("max(basic_pay)");
		    	        	   System.out.println("Max value:"+max);
		    	           }
		    	           break;
		    	           
		    	    case 4:PreparedStatement pMin = null;
		    	          pMin = con.prepareStatement("SELECT MIN(basic_pay) from employee_payroll");
		    	          pMin.execute();
		    	          rs = pMin.getResultSet();
		    	          while(rs.next()) {
		    	        	  double min = rs.getDouble("min(basic_pay)");
		    	        	  System.out.println("Min value:"+min);
		    	          }
		    	          break;
			    case 5:PreparedStatement pNumberOfMaleEmployees = null;
		    	           pNumberOfMaleEmployees = con.prepareStatement("SELECT COUNT(*) FROM employee_payroll where gender = 'M' ");		    	           
		    	           pNumberOfMaleEmployees.execute();
		    	           rs = pNumberOfMaleEmployees.getResultSet();
		    	           while(rs.next()) {
		    	        	   int numberOfMaleEmployees = rs.getInt("count(*)");
		    	        	   System.out.println("Number of male employees"+numberOfMaleEmployees);
		    	           }
		    	           break;
		    	           
		    	    case 6:PreparedStatement pNumberOffemaleEmployees = null;
	    	                   pNumberOffemaleEmployees = con.prepareStatement("SELECT COUNT(*) FROM employee_payroll where gender = 'F' ");		    	           
	    	                   pNumberOffemaleEmployees .execute();
	    	                   rs =  pNumberOffemaleEmployees .getResultSet();
	    	                   while(rs.next()) {
	    	        	       int  numberOffemaleEmployees  = rs.getInt("count(*)");
	    	        	       System.out.println("Number of female employees: "+numberOffemaleEmployees);
	    	                   }
	    	                   break;
	    	           
	    	         default :System.out.println("Invalid choice");
		    	     }
		         }
		      }
		 }
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void listDrivers() {
		
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while(driverList.hasMoreElements()) {
			Driver driverClass = driverList.nextElement();
			System.out.println(driverClass.getClass().getName());
		}
	 }
}
