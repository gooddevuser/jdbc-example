package com.example.jdbc;

import java.util.List;
import java.util.Scanner;

public class DemoD {
	
	public void test() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("조회할 이름의 첫 문자(영문자) : ");
		String name = scanner.nextLine();
		
		EmployeeDao dao = new EmployeeDao();
		List<EmployeeVO> employees = dao.selectEmployeesByName(name);
		
		if (employees.size() == 0) {
			System.out.println("조회 결과가 없습니다.");
		} else {
			for (EmployeeVO employee : employees) {
				System.out.println(employee);
			}
		}
		
		scanner.close();

	}
	

	public static void main(String[] args) {

		DemoD demoA = new DemoD();
		demoA.test();

	}

}
