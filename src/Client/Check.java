package Client;

import java.util.List;
import java.util.Scanner;

public class Check {
	Scanner sc = new Scanner(System.in);
	
	public String overlapCheck(List<ClientDTO> list) {
		String reslut;
		while(true) {
			reslut = sc.next();
			boolean find = false;
			
			for(ClientDTO o :list) {
				if(o.getId().equals(reslut)) {
					System.out.println("중복된 아이디입니다");
					find = true;
					
				}
			}
			if(!find) {
				break;
			}
		}
		return reslut;
	}
}