/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.control;

/**
 * 用来表现玩家当前操作状态的类。作为参数被传给AbilityLevel和AbilitySkill。
 * @author WeAthFolD
 *
 */
public class PlayerControlStat {

	public boolean keyDown[] = new boolean[4]; 
	
	/**
	 * 
	 */
	public PlayerControlStat() {
		// TODO Auto-generated constructor stub
	}
	
	public void onKeyDown(int keyID) {
		keyDown[keyID] = true;
	}
	
	public void onKeyUp(int keyID) {
		keyDown[keyID] = false;
	}

}
