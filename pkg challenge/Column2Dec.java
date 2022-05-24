package challenge;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Column2Dec implements ActionListener {

    private final JButton generate;
    private final JTextField inputPath;
    private final JTextField outputPath;
    private static final Map<Character, Integer> alphabet = new HashMap<>();
    private static final char[] chars = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public Column2Dec() {
        this.fillMap();

        JFrame janel = new JFrame("Convert Column To Number");
        janel.setSize(400, 500);
        janel.setLayout(null);

        JLabel allArea = new JLabel();
        allArea.setBounds(0, 0, 400, 500);

        this.generate = new JButton("Generate");
        this.generate.setBounds(125, 320, 125, 30);
        this.generate.addActionListener(this);

        JTextField inputText = new JTextField("Input File");
        inputText.setBounds(80, 80, 220, 30);
        inputText.setHorizontalAlignment(JTextField.CENTER);
        inputText.setEditable(false);
        inputText.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        this.inputPath = new JTextField();
        this.inputPath.setText(null);
        this.inputPath.setBounds(80, 130, 220, 30);

        JTextField outputText = new JTextField("Output File");
        outputText.setBounds(80, 180, 220, 30);
        outputText.setHorizontalAlignment(JTextField.CENTER);
        outputText.setEditable(false);
        outputText.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        this.outputPath = new JTextField();
        this.outputPath.setText(null);
        this.outputPath.setBounds(80, 230, 220, 30);

        allArea.add(outputText);
        allArea.add(this.outputPath);
        allArea.add(inputText);
        allArea.add(this.inputPath);
        allArea.add(this.generate);
        janel.add(allArea);
        janel.setResizable(false);
        janel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janel.setVisible(true);
    }

    private void fillMap() {
        for (int x = 0; x < chars.length; x++) {
            alphabet.put(chars[x], x);
        }
    }

    private void convertBases() throws IOException {
        int length = 0;

        File text = new File(this.inputPath.getText());
        FileReader in = new FileReader(text);
        BufferedReader reader = new BufferedReader(in);
        Scanner scan = new Scanner(text);

        String line;
        String[] list = new String[(int) text.length()];

        list[length] = scan.next();
        length++;
        reader.readLine();

        for (int x = Integer.parseInt(list[0]); x < list.length; x++) {
            if ((line = reader.readLine()) != null) {
                list[length] = line.toUpperCase() + " = " + this.xls2Dec(line.toUpperCase());
            }
            length++;
        }

        in.close();
        reader.close();
        scan.close();

        FileWriter out = new FileWriter(this.outputPath.getText());
        PrintWriter save = new PrintWriter(out);
        for (String s : list) {
            if (s != null) {
                save.print(s);
                save.println();
            }
        }
        out.close();
    }

    private int xls2Dec(String line) {
        int cod = 0;
        int pow = 0;

        for (int x = line.length() - 1; x >= 0; x--) {
            int aux = alphabet.get(line.toCharArray()[x]);
            cod += (aux * (Math.pow(26, pow)));
            pow += 1;
        }
        return cod;
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.generate) {
            if (!inputPath.getText().isEmpty() && !outputPath.getText().isEmpty()) {
                try {
                    this.convertBases();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
