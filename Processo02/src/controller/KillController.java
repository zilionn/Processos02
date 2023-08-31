package controller;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class KillController {
	
	public KillController() {
		super();
	}
	
	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void listaProcessos() {
		String process = (os().contains("Windows")) ? "tasklist /fo table" : "ps -ef";
        try {
            Process pro = Runtime.getRuntime().exec(process);
            InputStream flu = pro.getInputStream();
            InputStreamReader leitor = new InputStreamReader(flu);
            BufferedReader buffer = new BufferedReader(leitor);
            String linha;
            StringBuilder mostrar = new StringBuilder();

            while ((linha = buffer.readLine()) != null) {
                mostrar.append(linha).append("\n");
            }

            buffer.close();
            leitor.close();
            flu.close();

            JTextArea textArea = new JTextArea(mostrar.toString());
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600, 400));

            JOptionPane.showMessageDialog(null, scrollPane, "Lista de Processos", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void mataPid(int pid) {
		StringBuffer process = new StringBuffer();
		process.append((os().contains("Windows")) ? "taskkill /pid " : "kill -9 ");
		process.append(pid);
		try {
			Runtime.getRuntime().exec(process.toString());
			System.out.println("Processo encerrado!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mataNome(String nome) {
		StringBuffer process = new StringBuffer();
		process.append((os().contains("Windows")) ? "taskkill /im " : "pkill -f ");
		process.append(nome);
		try {
			Runtime.getRuntime().exec(process.toString());
			System.out.println("Processo encerrado!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mataProcesso(String processo) {
		try {
			int pid = Integer.parseInt(processo);
			mataPid(pid);
		} catch (NumberFormatException e) {
			mataNome(processo);
		}
	}

}