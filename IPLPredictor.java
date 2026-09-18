import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * IPLPredictor
 */
public class IPLPredictor extends JFrame {

    private static final Color BG = new Color(15, 18, 30);
    private static final Color CARD_BG = new Color(24, 28, 44);
    private static final Color BORDER = new Color(255, 255, 255, 28);
    private static final Color TEXT_PRIMARY = new Color(235, 238, 245);
    private static final Color TEXT_SECONDARY = new Color(150, 157, 176);
    private static final Color ACCENT = new Color(247, 197, 72);
    private static final Color ACCENT_TEXT = new Color(32, 26, 8);

    private static final Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 22);
    private static final Font FONT_SUBTITLE = new Font("SansSerif", Font.PLAIN, 12);
    private static final Font FONT_LABEL = new Font("SansSerif", Font.PLAIN, 13);
    private static final Font FONT_RESULT = new Font("SansSerif", Font.BOLD, 19);
    private static final Font FONT_PROB = new Font("SansSerif", Font.PLAIN, 13);
    private static final Font FONT_BADGE = new Font("SansSerif", Font.BOLD, 14);

    private final Map<String, String> teamLogos = new LinkedHashMap<>();
    private final Map<String, Integer> teamStrength = new LinkedHashMap<>();

    private JComboBox<String> team1Combo;
    private JComboBox<String> team2Combo;
    private JLabel team1LogoLabel;
    private JLabel team2LogoLabel;
    private JLabel resultLabel;
    private JLabel probLabel;

    public IPLPredictor() {
        setupData();
        setupUI();
    }

    private void setupData() {
        teamLogos.put("Mumbai Indians", "logos/mi.png");
        teamLogos.put("Chennai Super Kings", "logos/csk.png");
        teamLogos.put("Royal Challengers Bengaluru", "logos/rcb.png");
        teamLogos.put("Kolkata Knight Riders", "logos/kkr.png");
        teamLogos.put("Rajasthan Royals", "logos/rr.png");
        teamLogos.put("Punjab Kings", "logos/pk.png");
        teamLogos.put("Delhi Capitals", "logos/dc.png");
        teamLogos.put("Lucknow Super Giants", "logos/lsg.png");

        teamStrength.put("Mumbai Indians", 90);
        teamStrength.put("Chennai Super Kings", 99);
        teamStrength.put("Royal Challengers Bengaluru", 70);
        teamStrength.put("Kolkata Knight Riders", 75);
        teamStrength.put("Rajasthan Royals", 70);
        teamStrength.put("Punjab Kings", 65);
        teamStrength.put("Delhi Capitals", 60);
        teamStrength.put("Lucknow Super Giants", 55);
    }

    private void setupUI() {
        setTitle("IPL Match Predictor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620, 480);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(0, 16));
        mainPanel.setBorder(new EmptyBorder(20, 24, 20, 24));
        mainPanel.setBackground(BG);

        mainPanel.add(buildHeader(), BorderLayout.NORTH);
        mainPanel.add(buildBody(), BorderLayout.CENTER);
        mainPanel.add(buildResultCard(), BorderLayout.SOUTH);

        setContentPane(mainPanel);
        updateLogos();
        onPredict(null);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("IPL Match Predictor");
        title.setFont(FONT_TITLE);
        title.setForeground(TEXT_PRIMARY);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Pick two franchises to see the projected edge");
        subtitle.setFont(FONT_SUBTITLE);
        subtitle.setForeground(TEXT_SECONDARY);
        subtitle.setBorder(new EmptyBorder(4, 0, 0, 0));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        header.add(title);
        header.add(subtitle);
        return header;
    }

    private JPanel buildBody() {
        JPanel body = new JPanel();
        body.setOpaque(false);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));

        JPanel selectPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        selectPanel.setOpaque(false);

        String[] teamNames = teamLogos.keySet().toArray(new String[0]);
        team1Combo = styledCombo(teamNames);
        team2Combo = styledCombo(teamNames);
        team2Combo.setSelectedIndex(1);
        team1Combo.addActionListener(e -> updateLogos());
        team2Combo.addActionListener(e -> updateLogos());

        JLabel vsLabel = new JLabel("VS", SwingConstants.CENTER);
        vsLabel.setForeground(TEXT_SECONDARY);
        vsLabel.setFont(new Font("SansSerif", Font.BOLD, 13));

        selectPanel.add(team1Combo);
        selectPanel.add(vsLabel);
        selectPanel.add(team2Combo);

        JPanel logoPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        logoPanel.setOpaque(false);
        logoPanel.setBorder(new EmptyBorder(16, 0, 16, 0));
        team1LogoLabel = new JLabel("", SwingConstants.CENTER);
        team2LogoLabel = new JLabel("", SwingConstants.CENTER);
        team1LogoLabel.setPreferredSize(new Dimension(72, 72));
        team2LogoLabel.setPreferredSize(new Dimension(72, 72));
        logoPanel.add(team1LogoLabel);
        logoPanel.add(new JLabel("", SwingConstants.CENTER));
        logoPanel.add(team2LogoLabel);

        JButton predictBtn = new JButton("Predict Winner");
        predictBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        predictBtn.setBackground(ACCENT);
        predictBtn.setForeground(ACCENT_TEXT);
        predictBtn.setFocusPainted(false);
        predictBtn.setBorder(new EmptyBorder(10, 22, 10, 22));
        predictBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        predictBtn.addActionListener(this::onPredict);
        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.add(predictBtn);

        body.add(selectPanel);
        body.add(logoPanel);
        body.add(btnPanel);
        return body;
    }

    private JPanel buildResultCard() {
        resultLabel = new JLabel(" ", SwingConstants.CENTER);
        resultLabel.setFont(FONT_RESULT);
        resultLabel.setForeground(ACCENT);

        probLabel = new JLabel(" ", SwingConstants.CENTER);
        probLabel.setFont(FONT_PROB);
        probLabel.setForeground(TEXT_SECONDARY);
        probLabel.setBorder(new EmptyBorder(6, 0, 0, 0));

        JPanel card = new JPanel(new GridLayout(2, 1, 0, 0));
        card.setBackground(CARD_BG);
        card.setBorder(new CompoundBorder(
                new LineBorder(BORDER, 1),
                new EmptyBorder(16, 16, 16, 16)));
        card.add(resultLabel);
        card.add(probLabel);
        return card;
    }

    private JComboBox<String> styledCombo(String[] items) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setFont(FONT_LABEL);
        combo.setBackground(CARD_BG);
        combo.setForeground(TEXT_PRIMARY);
        combo.setBorder(new CompoundBorder(new LineBorder(BORDER, 1), new EmptyBorder(4, 6, 4, 6)));
        return combo;
    }

    private void onPredict(ActionEvent e) {
        String team1 = (String) team1Combo.getSelectedItem();
        String team2 = (String) team2Combo.getSelectedItem();

        if (team1.equals(team2)) {
            JOptionPane.showMessageDialog(this, "Select two different teams!",
                    "Invalid Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int t1 = teamStrength.getOrDefault(team1, 50);
        int t2 = teamStrength.getOrDefault(team2, 50);
        int total = t1 + t2;

        double prob1 = Math.round((t1 * 1000.0) / total) / 10.0;
        double prob2 = Math.round((t2 * 1000.0) / total) / 10.0;

        String winner = t1 >= t2 ? team1 : team2;

        resultLabel.setText("Predicted Winner: " + winner);
        probLabel.setText(team1 + ": " + prob1 + "%    " + team2 + ": " + prob2 + "%");
    }

    private void updateLogos() {
        styleBadge(team1LogoLabel, (String) team1Combo.getSelectedItem());
        styleBadge(team2LogoLabel, (String) team2Combo.getSelectedItem());
    }

    private void styleBadge(JLabel label, String team) {
        ImageIcon icon = loadLogo(team);
        if (icon != null) {
            label.setIcon(icon);
            label.setText(null);
            label.setOpaque(false);
            label.setBorder(null);
        } else {
            label.setIcon(null);
            label.setText(abbreviate(team));
            label.setFont(FONT_BADGE);
            label.setForeground(TEXT_PRIMARY);
            label.setOpaque(true);
            label.setBackground(CARD_BG);
            label.setBorder(new LineBorder(BORDER, 1));
        }
    }

    private ImageIcon loadLogo(String team) {
        String path = teamLogos.get(team);
        File f = new File(path);
        if (!f.exists()) return null;
        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    private String abbreviate(String team) {
        StringBuilder sb = new StringBuilder();
        for (String word : team.split(" ")) {
            if (!word.isEmpty()) sb.append(Character.toUpperCase(word.charAt(0)));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        SwingUtilities.invokeLater(() -> new IPLPredictor().setVisible(true));
    }
}
