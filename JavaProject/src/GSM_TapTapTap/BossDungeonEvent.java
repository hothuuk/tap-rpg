package GSM_TapTapTap;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class BossDungeonEvent {
	public void bossDungeonEventer() {
		Main.buildingButton[3].addActionListener(event->{
			for (int i = 0; i < 4; i++) {
				Main.buildingButton[i].setVisible(false);
			}
			Main.save.setVisible(false);
			Main.load.setVisible(false);
			
			Main.bossDungeon.setVisible(true);
			Main.bossDungeonExit.setVisible(true);
			Main.b1.setVisible(true);
			Main.b2.setVisible(true);
			Main.b3.setVisible(true);
			Main.dontUseAuto.setVisible(true);
		});
		
		Main.bossDungeonExit.addActionListener(event->{
			Main.bossDungeon.setVisible(false);
			Main.bossDungeonExit.setVisible(false);
			Main.b1.setVisible(false);
			Main.b2.setVisible(false);
			Main.b3.setVisible(false);
			Main.dontUseAuto.setVisible(false);
			
			Main.save.setVisible(true);
			Main.load.setVisible(true);
			for (int i = 0; i < 4; i++) {
				Main.buildingButton[i].setVisible(true);
			}
		});
		
		Main.b1.addActionListener(event->{
			Main.bossDungeon.setVisible(false);
			Main.bossDungeonExit.setVisible(false);
			Main.b1.setVisible(false);
			Main.b2.setVisible(false);
			Main.b3.setVisible(false);
			Main.dontUseAuto.setVisible(false);
			
			Main.boss.setVisible(true);
			Main.bossHpBar.setVisible(true);
			Main.bossCountdownBar.setVisible(true);
			
			Main.boss.setIcon(new ImageIcon(Main.class.getResource("/monsterImage/boss1.png")));
			index = 0;
			hp = bossHp[index];
			hpBar = hp / 100;
			Timer();
		});
		
		Main.b2.addActionListener(event->{
			Main.bossDungeon.setVisible(false);
			Main.bossDungeonExit.setVisible(false);
			Main.b1.setVisible(false);
			Main.b2.setVisible(false);
			Main.b3.setVisible(false);
			Main.dontUseAuto.setVisible(false);
			
			Main.boss.setVisible(true);
			Main.bossHpBar.setVisible(true);
			Main.bossCountdownBar.setVisible(true);
			
			Main.boss.setIcon(new ImageIcon(Main.class.getResource("/monsterImage/boss2.png")));
			index = 1;
			hp = bossHp[index];
			hpBar = hp / 100;
			Timer();
		});
		
		Main.b3.addActionListener(event->{
			Main.bossDungeon.setVisible(false);
			Main.bossDungeonExit.setVisible(false);
			Main.b1.setVisible(false);
			Main.b2.setVisible(false);
			Main.b3.setVisible(false);
			Main.dontUseAuto.setVisible(false);
			
			Main.boss.setVisible(true);
			Main.bossHpBar.setVisible(true);
			Main.bossCountdownBar.setVisible(true);
			
			Main.boss.setIcon(new ImageIcon(Main.class.getResource("/monsterImage/boss3.png")));
			index = 2;
			hp = bossHp[index];
			hpBar = hp / 100;
			Timer();
		});
	}
	
	int index = -1;
	double[] bossHp = {2370000, 35500000000.0, 5000000000000.0};
	double[] bossMoney = {23700000, 355000000000.0, 50000000000000.0};
	int[] bossDia = {20, 300, 10000}; 
	
	double hp;
	double hpBar;
	double damagedHp;
	int setXSize = 400;
	
	int timex = 400;
	int time = 200;
	Timer t;
	TimerTask task;
	public void Timer() {
		t = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				time -= 1;
				if (time > 0) {
					timex -= 2;
					Main.bossCountdownBar.setBounds(250, 90, timex, 20);
				}
				else {
					t.cancel();
					Main.boss.setVisible(false);
					Main.bossHpBar.setVisible(false);
					Main.bossCountdownBar.setVisible(false);
					
					damagedHp = 0;
					setXSize=400;
					timex = 400;
					time = 200;
					
					Main.save.setVisible(true);
					Main.load.setVisible(true);
					for (int i = 0; i < 4; i++) {
						Main.buildingButton[i].setVisible(true);
					}
				}
			}
		};
		
		t.schedule(task, 100, 100);
	}
	
	public void bossEventer() {
		Main.boss.addActionListener(event->{
			if (Main.job1Check == false) {
				hp -= Main.damage;
				damagedHp += Main.damage;
			}
			else {
				hp -= Main.damage * 2;
				damagedHp += Main.damage * 2;
			}
			
			if (hp > 0) { 
				if (damagedHp >= hpBar) {
					int cnt = 0;
					while(damagedHp > hpBar) {
						damagedHp -= hpBar;
						cnt++;
					}
					setXSize -= 4 * cnt;
					Main.bossHpBar.setBounds(250, 50, setXSize, 40);
				}
			}
			else {
				damagedHp = 0;
				setXSize=400;
				timex = 400;
				time = 200;
				t.cancel();
				Main.bossHpBar.setBounds(250, 50, setXSize, 40);
				Main.bossCountdownBar.setBounds(250, 90, timex, 20);
				
				Main.money += bossMoney[index];
				Main.dia += bossDia[index];
				Main.moneyL.setText("???  : " + Change.change(Main.money));
				Main.diaL.setText("???  : " + Main.dia);
				
				Main.boss.setVisible(false);
				Main.bossHpBar.setVisible(false);
				Main.bossCountdownBar.setVisible(false);
				
				Main.save.setVisible(true);
				Main.load.setVisible(true);
				for (int i = 0; i < 4; i++) {
					Main.buildingButton[i].setVisible(true);
				}
			}
		});
	}
}
