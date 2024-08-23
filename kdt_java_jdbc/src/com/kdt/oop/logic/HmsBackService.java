package com.kdt.oop.logic;

import java.util.List;

import com.kdt.oop.EmployeeVO;
import com.kdt.oop.PersonVO;
import com.kdt.oop.StudentVO;
import com.kdt.oop.TeacherVO;
import com.kdt.oop.dao.HmsDao;
import com.kdt.oop.util.HmsType;

// 배열로(stu, tea, emp) 객체 관리
// 수정, 삭제, 검색, 추가 		
public class HmsBackService {
	
	private HmsDao dao;

	public HmsBackService() {
		dao = new HmsDao();
	}
	
	public String createRequest(HmsType type, String name, int age, String address, String common) {
		
		String msg = null;
		PersonVO per = null;
		
		switch(type) {
			case STU :
				per = new StudentVO(type, name, age, address, common);
				msg = "학생 객체를 배열에 담았습니다.";
				break;
			case TEA :
				per = new TeacherVO(type, name, age, address, common);
				msg = "강사 객체를 배열에 담았습니다.";
				break;
			case EMP :
				per = new EmployeeVO(type, name, age, address, common);
				msg = "직원 객체를 배열에 담았습니다.";
				break;
			default :
				return "유효하지 않은 HmsType입니다.";
		}
		int flag = dao.insertRow(per);
		return (flag != 0) ? msg: "객체 생성실패";
		
	}
	
//	private void setAry(PersonVO per) {
//		lst.add(per);
//	}

	public List<PersonVO> list() {
		return dao.selectRow();
	}

	public PersonVO searchProcess(String name) {
		return dao.searchRow(name);
	}
//	
//	//배열에 담겨있는 객체 정보를 파일로 저장하는 기능
//	public boolean saveToFile() {
//		boolean flag = false;
//		String data = null;
//		
//		File file = null;
//		FileWriter writer = null;
//		BufferedWriter br = null;
//		
//		try {
//			br = new BufferedWriter(new FileWriter(new File("./HmsInfo.txt")));
//			
//			for(int idx = 0; idx < lst.size(); idx++) {
//				if(lst.get(idx) != null) {
//					data = lst.get(idx).Info();
//					br.write(data);
//					br.newLine();
//					br.flush();
//				}
//			}
//			flag = true;
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(br != null) { br.close(); }
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return flag;
//	}
//	
//	//파일저장이 완료되었을 때 구현하는 기능으로 
//	//프로그램이 시작될 때 파일로부터 읽어들인 데이터를 바탕으로 객체를 생성하고 배열에 할당하는 기능
//	public boolean loadToFile() {
//		
//		boolean flag = false;
//		int number = 0;
//		String data = null;
//		String [] strAry = null;
//		
//		File file = null;
//		FileReader reader = null;
//		BufferedReader br = null;
//		
//		try {
//			br = new BufferedReader(new FileReader(new File("./HmsInfo.txt")));
//			
//			while((data = br.readLine()) != null) {
//				strAry = data.split("-");
//				number = Integer.parseInt(strAry[2]);
//				
//				if (strAry[0].equals("stu")) {
//					createRequest(HmsType.STU, strAry[1], number, strAry[3], strAry[4]);
//				} else if (strAry[0].equals("tea")) {
//					createRequest(HmsType.TEA, strAry[1], number, strAry[3], strAry[4]);
//				} else if (strAry[0].equals("emp")) {
//					createRequest(HmsType.EMP, strAry[1], number, strAry[3], strAry[4]);
//				}
//			}
//			flag = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(br != null) { br.close(); }
//			} catch (Exception e){
//				e.printStackTrace();
//			}
//		}
//		return flag;
//	}
//	
//	// 직렬화란? -> 객체를 보존하여 그대로 파일 또는 다른 시스템에 전달하는 방법
//	// 직렬화를 구현하기위해서는 대상이되는 객체는 Serializable 인터페이스를 구현해야함.
//	// ObjectInput(output)Stream 객체를 통해서 객체형태로 저장 또는 로딩할 수 있다.
//	public boolean saveToSerial() {
//		boolean flag = false;
//		
//		ObjectOutputStream 	oos = null;
//		
//		try {
//			oos = new ObjectOutputStream(new FileOutputStream("./serial.txt"));
//			oos.writeObject(lst);
//			flag = true;
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (oos != null) { oos.close(); }
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return flag;
//	}
//	
//	public boolean loadToSerial() {
//		boolean flag = false;
//		
//		ObjectInputStream 	ois = null;
//		
//		try {
//			ois = new ObjectInputStream(new FileInputStream("./serial.txt"));
//			lst = (List<PersonVO>)ois.readObject();
//			int cnt = 0;
//			for(int idx = 0; idx < lst.size(); idx++) {
//				if(lst.get(idx) != null) {
//					cnt += 1;
//				}
//			}
//			flag = true;
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (ois != null) { ois.close(); }
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return flag;
//	}
//	
	public boolean removeProcess(String name) {
	    return dao.removeRow(name) == 1 ? true : false;
	}
	
	public void updateProcess(PersonVO person) {
		dao.updateRow(person);
	}
	
	
}





















