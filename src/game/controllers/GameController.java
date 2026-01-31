package game.controllers;

import game.Game;
import game.model.Player;
import game.service.loggers.GameLogger;
import game.service.loggers.GuiLogger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class GameController {

	@FXML
	private TextArea logArea;
	@FXML
	private ProgressBar hpBar;
	@FXML
	private Label hpTextLabel;
	@FXML
	private Label coinsLabel;
	@FXML
	private Label nameLabel;

	@FXML
	private Button btnMove;
	@FXML
	private Button btnInventory;
	@FXML
	private Button btnStats;
	@FXML
	private Button btnQuit;

	private Game game;
	private Player player;

	/**
	 * Metodo chiamato dal Main dopo aver caricato l'FXML per inizializzare la
	 * partita.
	 */
	public void initializeGame(Player player) {
		this.player = player;
		// 1. Crea il Logger che scrive sulla TextArea
		GameLogger guiLogger = new GuiLogger(logArea);

		// 2. Crea il gioco iniettando il logger grafico
		this.game = new Game(player, guiLogger);

		// 3. Aggiorna la GUI inizialmente
		updateUI();

		// 4. Avvia il gioco (messaggio di benvenuto)
		game.start();
	}

	private void updateUI() {
		if (player != null) {
			nameLabel.setText(player.getName() + " (" + player.getCharacterClass() + ")");

			// Calcola la percentuale della vita (cast a double per avere decimali)
			double progress = (double) player.getHp() / player.getMaxHp();
			hpBar.setProgress(progress);

			hpTextLabel.setText(player.getHp() + "/" + player.getMaxHp());
			coinsLabel.setText(String.valueOf(player.getCoins()));
		}
	}

	@FXML
	private void onMove() {
		game.movePlayer();
		updateUI();
	}

	@FXML
	private void onInventory() {
		game.showInventory();
		updateUI();
	}

	@FXML
	private void onStats() {
		game.showCharacterSheet();
		updateUI();
	}

	@FXML
	private void onQuit() {
		game.quitGame();
		Stage stage = (Stage) btnQuit.getScene().getWindow();
		stage.close();
	}
}