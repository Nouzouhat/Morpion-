import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Morpion extends JFrame implements ActionListener {
    private JButton[][] boutons;
    private char joueur;

    public Morpion() {
        initialiserUI();
    }

    private void initialiserUI() {
        this.setTitle("Morpion");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(200, 200, 300, 300);
        this.setLayout(new GridLayout(3, 3));

        boutons = new JButton[3][3]; 
        joueur = 'X';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boutons[i][j] = new JButton("");
                boutons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                boutons[i][j].addActionListener(this);
                this.add(boutons[i][j]);
            }
        }

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();

        if (boutonClique.getText().equals("")) {
            boutonClique.setText(String.valueOf(joueur));

            if (verifierGagnant()) {
                JOptionPane.showMessageDialog(this, "Le joueur " + joueur + " a gagné !");
                reinitialiserPartie();
            } else if (verifierEgalite()) {
                JOptionPane.showMessageDialog(this, "Match nul !");
                reinitialiserPartie();
            } else {
                changerJoueur();
            }
        }
    }

    private void changerJoueur() {
        joueur = (joueur == 'X') ? 'O' : 'X';
    }

    private boolean verifierGagnant() {
        // Vérifier les lignes, colonnes et diagonales pour déterminer s'il y a un vainqueur
        for (int i = 0; i < 3; i++) {
            if (boutons[i][0].getText().equals(String.valueOf(joueur))
                    && boutons[i][1].getText().equals(String.valueOf(joueur))
                    && boutons[i][2].getText().equals(String.valueOf(joueur))) {
                return true; // Ligne
            }

            if (boutons[0][i].getText().equals(String.valueOf(joueur))
                    && boutons[1][i].getText().equals(String.valueOf(joueur))
                    && boutons[2][i].getText().equals(String.valueOf(joueur))) {
                return true; // Colonne
            }
        }

        if (boutons[0][0].getText().equals(String.valueOf(joueur))
                && boutons[1][1].getText().equals(String.valueOf(joueur))
                && boutons[2][2].getText().equals(String.valueOf(joueur))) {
            return true; // Diagonale (de gauche à droite)
        }

        if (boutons[0][2].getText().equals(String.valueOf(joueur))
                && boutons[1][1].getText().equals(String.valueOf(joueur))
                && boutons[2][0].getText().equals(String.valueOf(joueur))) {
            return true; // Diagonale (de droite à gauche)
        }

        return false;
    }

    private boolean verifierEgalite() {
        // Vérifier s'il y a une égalité (match nul)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boutons[i][j].getText().equals("")) {
                    return false; // Il y a encore des cases vides, le jeu n'est pas terminé
                }
            }
        }
        return true; // Toutes les cases sont remplies, match nul
    }

    private void reinitialiserPartie() {
        // Réinitialiser le plateau de jeu
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boutons[i][j].setText("");
            }
        }
        joueur = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Morpion morpion = new Morpion();
        });
    }
}

