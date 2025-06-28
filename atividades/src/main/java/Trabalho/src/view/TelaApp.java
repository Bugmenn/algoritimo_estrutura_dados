package Trabalho.src.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import Trabalho.src.model.Tag.TagInfo;
import Trabalho.src.validator.Validator;

public class TelaApp extends JFrame {
    private final JTextField txtCaminho = new JTextField(30);
    private final JTextArea txtResultado = new JTextArea(6, 50);

    private final String[] colunasTabela = { "Tag", "Contador" };
    private final DefaultTableModel tableModel = new DefaultTableModel(colunasTabela, 0);
    private final JTable tblTags = new JTable(tableModel);

    public TelaApp() {
        setTitle("Validador HTML");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        tblTags.setFont(new Font("Monospaced", Font.PLAIN, 14));
        tblTags.setEnabled(false);
        tblTags.setBackground(new Color(197, 197, 198));
        tblTags.setRowHeight(22);

        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtResultado.setBackground(new Color(158, 158, 160));

        JPanel topo = new JPanel();
        JButton btnEscolher = new JButton("Escolher arquivo");
        btnEscolher.setBackground(new Color(0,122,204));
        btnEscolher.setForeground(Color.WHITE);
        JButton btnValidar = new JButton("Validar arquivo escolhido");
        btnValidar.setBackground(new Color(255, 128, 64));
        btnValidar.setForeground(Color.WHITE);

        topo.add(new JLabel("Arquivo:"));
        topo.add(txtCaminho);
        topo.add(btnEscolher);
        topo.add(btnValidar);

        // Evento de escolher o arquivo
        btnEscolher.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();

            // Filtro para só conseguir escolher html
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos HTML", "html");
            chooser.setFileFilter(filtro);

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                txtCaminho.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        });

        // Evento para começar a validação do arquivo selecionado
        btnValidar.addActionListener(e -> {
            String path = txtCaminho.getText();
            if (!path.isBlank()) {
                Validator validator = new Validator();
                boolean validatorResult = validator.validar(path);
                txtResultado.setText(validator.getReport());

                // Limpa a tabela
                tableModel.setRowCount(0);

                // Ordena a lista de tags e adiciona na tabela
                if (validatorResult) {
                    for (TagInfo tag : validator.getCountTag().getTagsOrdenadas()) {
                        tableModel.addRow(new Object[]{tag.getNome(), tag.getQuantidade() });
                    }
                }
            }
        });

        JScrollPane scrollTabela = new JScrollPane(tblTags);
        scrollTabela.getViewport().setBackground(new Color(158, 158, 160));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(txtResultado), scrollTabela);
        splitPane.setResizeWeight(0.5);

        getContentPane().add(topo, BorderLayout.NORTH);
        getContentPane().add(splitPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
