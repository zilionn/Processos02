package config;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.KillController;

public class Menu {
	KillController kill = new KillController();
	public void menu(){
		
		String[] opcoes = {"Listar processos ativos ",
						   "Mata processo ",
						   "SAIR "};
		
		JLabel label = new JLabel("Escolha uma opção: ");
		label.setHorizontalAlignment(JLabel.CENTER);
		
		int selectop = JOptionPane.showOptionDialog(
				null, label, "Menu Processo", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
		
		switch (selectop) {
			case 0:
				kill.listaProcessos();
				break;
			case 1:
				String process = JOptionPane.showInputDialog("Digite o nome ou PID do processo que deseja matar:");
				kill.mataProcesso(process);
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Você escolheu sair, até logo");
				System.exit(0);
				break;
			default:
				if (selectop == -1) {
					System.exit(0);
				}
		}
		
	}

}