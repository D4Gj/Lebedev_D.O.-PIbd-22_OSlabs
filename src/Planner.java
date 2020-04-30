import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Planner {
	private static Map<Integer, Integer> tablePages = new HashMap<Integer, Integer>();
	PhysicalMemory phis = new PhysicalMemory();
	VirtualMemory vir = new VirtualMemory();
	Page[] massiv=phis.getPhisMem();
	ArrayList<Page> v=vir.getVirMem();

	public Page Request(int idVir) {
		if (vir.getPage(idVir).getAge() <= idVir & idVir < vir.getPage(idVir).getAge() + v.size()) {
			if (tablePages.containsKey(idVir)) {
				grow(tablePages.get(idVir));
				return phis.getPhisPage(tablePages.get(idVir));
			} else {
				int freePlace = findFree();
				if (freePlace != -1) {
					if (phis.setPhisPage(vir.getPage(idVir), findFree())) {
						tablePages.put(idVir, freePlace);
						grow(tablePages.get(idVir));
						return phis.getPhisPage(tablePages.get(idVir));
					} else {
						System.out.println("Displaying error");
						return null;
					}
				} else {
					int idPhis = choosePage();
					findKey(idPhis);
					if (phis.setPhisPage(vir.getPage(idVir), idPhis)) {

						tablePages.put(idVir, idPhis);
						grow(tablePages.get(idVir));
						return phis.getPhisPage(tablePages.get(idVir));
					} else {
						System.out.println("Displaying error");
						return null;
					}
				}
			}
		} else {
			System.out.println("Access error");
			return null;
		}
	}

	public void findKey(int idPhis) {
		for (Integer vals : tablePages.values()) {
			if (vals.equals(idPhis)) {

				tablePages.remove(vals);

				return;
			}
		}
	}

	private int findFree() {
		Page[] pages = phis.getPhisMem();
		for (int i = 0; i < phis.getSize(); i++) {
			if (pages[i] == null) {
				return i;
			}
		}
		return -1;
	}

	private void grow(int idVir) {
		for (int i = 0; i < phis.getSize(); i++) {
			Page res=phis.getPhisPage(i);
			if (res != null) {
				if (i == idVir) {
					res.use();
				} else {
					res.grow();
				}
				System.out.print(" ");
			}
		}
	}

	private int choosePage() {
		Page[] pages = phis.getPhisMem();
		int res = 0;
		int maxAge = pages[res].getAge();
		for (int i = 1; i < phis.getSize(); i++) {
			if (pages[i].getAge() > maxAge) {
				res = i;
				maxAge = pages[i].getAge();
			}
		}
		Page p=phis.getPhisPage(res);
		System.out.println();
		System.out.println("Unloaded page "+p.getData());

		return res;
	}
}
