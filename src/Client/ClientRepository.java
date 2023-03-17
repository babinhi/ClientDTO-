package Client;

import java.util.*;

//import Board.BoardDTO;

public class ClientRepository {
// 아이디를 받아서 중복체크하기 
	Scanner sc = new Scanner(System.in);
	private static ClientRepository repository = new ClientRepository();

	private ClientRepository() {
	}

	public static ClientRepository getInstance() {
		return repository;
	}

	// List<ClientDTO> cList = new ArrayList<>();
	Map<String, ClientDTO> cmap = new HashMap<>();
//	Map<String, ClientDTO> cmap = new HashMap<String, ClientDTO>(); 도 가능
	Map<String, BreakdownDTO> bmap = new HashMap<>();

	// List<BreakdownDTO> bList = new ArrayList<>();

	public boolean save(ClientDTO clientDTO) {

//		ClientDTO result = cmap.put(clientDTO.getId(), clientDTO);
		if (cmap.put(clientDTO.getAccount(), clientDTO) == null) {
			return true;
		} else {
			return false;
		}
//		if (result == null) {
//			return true;
//		} else {
//			return false;
//
//		}

	}
	public String overlapCheck() {
		String result;
		
		while(true) {
			boolean find = false;
			result = sc.next();
			for(String c : cmap.keySet()) {
				if(result.equals(cmap.get(c).getId())){
					System.out.print("중복된 ID \n다시 입력하세요> ");
					find = true;
					break;
				}
			}
			if(!find) {
				break;
			}
		}
		return result;
	}


	public boolean loginCheck(String id, String password) {
		for (String c : cmap.keySet()) {
			if (cmap.get(c).getId().equals(id) && cmap.get(c).getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public Map<String, ClientDTO> findAll() {
		return cmap;
	}

	public ClientDTO findById(String id, String password) {
		for (String c : cmap.keySet()) {
			if (cmap.get(c).getId().equals(id) && cmap.get(c).getPassword().equals(password)) {
				return cmap.get(c);
			}
		}
		return null;
	}

	public List<BreakdownDTO> breakList(String account) {
		List<BreakdownDTO> list = new ArrayList<>();

		for (String b : bmap.keySet()) {
			if (bmap.get(b).equals(account)) {
				list.add(bmap.get(account));
			}
		}
		return list;
	}

	public String getAccount(String id, String password) {
		for (String c : cmap.keySet()) {
			if (cmap.get(c).getId().equals(id) && cmap.get(c).getPassword().equals(password)) {
				return cmap.get(c).getAccount();
			}
		}
		return null;
	}

	public boolean deposit(String account, long money) {
		// 코드를 볼땐 우변부터, ()가 가장 많이 둘러 싸여진것부터 봐야한다
		for (String c : cmap.keySet()) {
			if (cmap.get(c).getAccount().equals(account)) {
				cmap.get(c).setBalance(cmap.get(c).getBalance() + money);
				BreakdownDTO breakdownDTO = new BreakdownDTO();
				breakdownDTO.setAccount(account);
				breakdownDTO.setDivision("입금");
				breakdownDTO.setDealMoney(money);
				breakdownDTO.setTotalMoney(cmap.get(c).getBalance());
				bmap.put(breakdownDTO.getM(), breakdownDTO);
//				bList.add(breakdownDTO);
				return true;
			}
		}
		return false;
	}

	public boolean withdraw(String account, long money) {
		for (String c : cmap.keySet()) {
			if (cmap.get(c).getAccount().equals(account)) {
				if (cmap.get(c).getBalance() >= money) {
					cmap.get(c).setBalance(cmap.get(c).getBalance() - money);
					BreakdownDTO breakdownDTO = new BreakdownDTO();
					breakdownDTO.setAccount(account);
					breakdownDTO.setDivision("출금");
					breakdownDTO.setDealMoney(money);
					breakdownDTO.setTotalMoney(cmap.get(c).getBalance());
					bmap.put(breakdownDTO.getM(), breakdownDTO);
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public boolean transferCheck(String transferAccount) {
		for (String c : cmap.keySet()) {
			if (cmap.get(c).getAccount().equals(transferAccount)) {
				return true;
			}
		}
		return false;
	}

	public boolean update(String id, String password, String updatePassword) {
		for (String c : cmap.keySet()) {
			if (cmap.get(c).getId().equals(id) && cmap.get(c).getPassword().equals(password)) {
				cmap.get(c).setPassword(updatePassword);
				return true;
			}
		}
		return false;
	}

	public boolean delete(String id, String password) {
		for (String c : cmap.keySet()) {
			if (cmap.get(c).getId().equals(id) && cmap.get(c).getPassword().equals(password)) {
				cmap.remove(c);
				return true;
			}
		}
		return false;
	}
}