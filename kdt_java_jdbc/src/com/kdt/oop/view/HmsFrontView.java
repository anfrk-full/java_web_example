package com.kdt.oop.view;

import java.util.List;
import java.util.Scanner;

import com.kdt.oop.EmployeeVO;
import com.kdt.oop.PersonVO;
import com.kdt.oop.StudentVO;
import com.kdt.oop.TeacherVO;
import com.kdt.oop.ctrl.InsertController;
import com.kdt.oop.ctrl.ListController;
import com.kdt.oop.ctrl.RemoveController;
import com.kdt.oop.ctrl.SearchController;
import com.kdt.oop.ctrl.UpdateController;
import com.kdt.oop.util.HmsType;

public class HmsFrontView {
	
	private static Scanner scan = new Scanner(System.in);

	private ListController listCtrl;
	private SearchController searchCtrl;
	private RemoveController removeCtrl;
	private UpdateController updateCtrl;
	private InsertController insertCtrl;
	
	public HmsFrontView() {
		listCtrl = new ListController();
		searchCtrl = new SearchController();
		removeCtrl = new RemoveController();
		updateCtrl = new UpdateController();
		insertCtrl = new InsertController();
		
	}
	
	public void mainMenu() {
		boolean close = false;
		boolean open = false;
		int number = 0;
		// 파일 로딩 대신 JDBC 연동으로 변경
//		open = backend.loadToSerial();
//		if(open == true) {
//			System.out.println("저장된 데이터를 불러옵니다.");
//		} else {
//			System.out.println("저장된 데이터를 불러오지 못했습니다.");
//		}
		
		while(true) {
			System.out.println(">>> HMS Ver1.0 <<<");
			System.out.println("1. 전체출력");
			System.out.println("2. 이름으로 검색");
			System.out.println("3. 이름으로 찾아서 삭제");
			System.out.println("4. 이름으로 찾아서 수정");
			System.out.println("5. 사용자 추가");
			System.out.println("99. 시스템 종료");
			System.out.print("Input Number : ");
			
			try {
				number = scan.nextInt();
			} catch (Exception e) {
				System.out.println("올바른 메뉴를 입력해주세요");
				scan.next();
			}
			
			switch(number) {
				case 1 :
					listPrt();
					break;
				case 2 :
					search() ;
					break;
				case 3 :
					remove();
					break;
				case 4 :
					update();
					break;
				case 5 :
					subMenu();
					break;
				case 99 :
					System.out.println(">>> 프로그램을 종료합니다 <<<");
					System.exit(0);
			}
		}
	}
	
	/*
	수정 기능구현을 위한 FrontEnd 로써
	Scanner로부터 수정하고자하는 이름을 입력받아서
	경우의 수) 학생 - 학번수정, 강사 - 과목수정, 직원 - 부서수정
	각 경우의 수에 맞는 수정데이터를 다시 입력받고 수정
	HmsBackService - updateProcess(String updateName) 호출하고 PersonVO 객체를 반환하여
	수정을 진행한다.
	 */
	public void update() {
		String name = null;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("수정하고자 하는 이름을 입력하시오 : ");
		name = sc.nextLine();
		
		PersonVO per = searchCtrl.execute(name);
		
		if(per != null) {
			if(per instanceof StudentVO) {
				System.out.print("새 학번 : ");
				String newmsg = sc.nextLine();
				StudentVO stu = new StudentVO();
				stu.setType(HmsType.STU);
				stu.setName(name);
				stu.setStuId(newmsg);
				updateCtrl.execute(stu);
			} else if(per instanceof TeacherVO) {
				System.out.print("새 과목 : ");
				String newmsg = sc.nextLine();
				TeacherVO tea = new TeacherVO();
				tea.setType(HmsType.TEA);
				tea.setName(name);
				tea.setSubject(newmsg);
				updateCtrl.execute(tea);
				
			} else if(per instanceof EmployeeVO) {
				System.out.print("새 부서 : ");
				String newmsg = sc.nextLine();
				EmployeeVO emp = new EmployeeVO();
				emp.setType(HmsType.EMP);
				emp.setName(name);
				emp.setDept(newmsg);
				updateCtrl.execute(emp);
				
			} 
			System.out.println("수정이 완료되었습니다.");
		} else {
			System.out.println("수정하고자 하는 데이터가 존재하지 않습니다.");
		}
		
	}
	
	
	/*
	Scanner로부터 검색하고자하는 이름을 입력받아서
	HmsBack - searchProcess(String searchName) 인자로 입력받은 이름을 전달하여
	찾는 이름이 있을 경우 - 객체를 반환받아서 출력
	찾는 이름이 없을 경우 - '데이터가 존재하지 않습니다.'라는 메세지를 출력
	
	 */
	
	public void search() {
		Scanner sc = new Scanner(System.in);
		System.out.print("찾고자 하는 이름을 입력해주세요 : ");
		
		String name = sc.nextLine();
		PersonVO information = searchCtrl.execute(name);
		if (information != null) {
	        System.out.println(information.perInfo());
	    } else {
	        System.out.println("데이터가 존재하지 않습니다.");
	    }
	}
	
	public void listPrt() {
		System.out.println();
		System.out.println(">>> 전체 데이터를 보여줍니다. <<< ");
		
		
		List<PersonVO> ary = listCtrl.execute();
		ary.stream().forEach(obj -> System.out.println( obj.perInfo() ));
	}
	
	public void subMenu() {
		int number = 0;
		while(true) {
			System.out.println();
			System.out.println(">>> 사용자 추가를 위해서 메뉴를 선택해 주세요. <<<");
			System.out.println("1. student");
			System.out.println("2. teacher");
			System.out.println("3. employee");
			System.out.println("99. up-to");
			System.out.println("Input Number : ");
			try {
				number = scan.nextInt();
			} catch (Exception e) {
				System.out.println("올바른 메뉴를 입력해주세요.");
				scan.next();
			}
			switch(number) {
				case 1 :
				case 2 :
				case 3 :
					makeView(number);
					break;
				case 99 : 
					return;
			}
		}
	}
	
	public void makeView(int number) {
		System.out.println();
		System.out.println(">>> 객체 생성 <<<");
		
		scan.nextLine(); // scanner 객체를 공유해서 사용할 대 버그가 존재하기 때문에
		System.out.print("이름 : ");
		String name = scan.nextLine();
		System.out.print("나이 : ");
		int age = scan.nextInt();
		scan.nextLine();
		System.out.print("주소 : ");
		String address = scan.nextLine();
		
//		String commonMsg = 	(number == 1) ? "학번 : " :
//							(number == 2) ? "과목 : " : "부서" ;
//									
//		System.out.print(commonMsg);
		String common = null;
		String msg = null;
		switch(number) {
			case 1 :
				System.out.print("학번 : ");
				common = scan.nextLine();
				msg = insertCtrl.execute(HmsType.STU, name, age, address, common);
				break;
			case 2 :
				System.out.print("과목 : ");
				common = scan.nextLine();
				msg = insertCtrl.execute(HmsType.TEA, name, age, address, common);
				break;
			case 3 :
				System.out.print("부서 : ");
				common = scan.nextLine();
				msg = insertCtrl.execute(HmsType.EMP, name, age, address, common);
				break;
		}
		System.out.println(msg);
	}
	
	/*
	삭제 기능구현을 위한 FrontEnd 로써
	Scanner 로부터 삭제하고자하는 이름을 입력받아서
	HmsBackService - removeProcess(String removeName) 인자로 입력받은 이름을 전달하여
	삭제하고자하는 이름이 있을 경우 - 객체를 삭제 true
	삭제하고자하는 이름이 없을 경우 - 반환타입으로 false 리턴하여
	FrontEnd 에서 반환값을 가지고 삭제완료 또는 삭제하고자하는 데이터가 존재하지 않습니다 메시지 출력
	*/
	public void remove() {
		String name = null;
		boolean flag = false;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제하고자 하는 이름을 입력하시오 : ");
		name = sc.nextLine();
		flag = removeCtrl.execute(name);
		if(flag == true) {
			System.out.println("삭제완료");
		} else {
			System.out.println("삭제하고자하는 데이터가 존재하지 않습니다.");
		}
		
	}
}























