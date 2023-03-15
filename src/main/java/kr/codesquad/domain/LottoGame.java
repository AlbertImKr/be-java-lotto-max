package kr.codesquad.domain;

import java.util.ArrayList;
import java.util.List;

import kr.codesquad.view.InputManager;
import kr.codesquad.view.OutputManager;

public class LottoGame {

	private final InputManager inputManager;
	private final LottoManger lottoManger;
	private final OutputManager outputManager;

	public LottoGame() {
		this.inputManager = new InputManager();
		this.lottoManger = new LottoManger();
		this.outputManager = new OutputManager();
	}

	public void playGame() {
		int purchaseAmount = askPurchaseAmount();
		int quantity = purchaseAmount / BallConfig.TICKET_PRICE;
		List<Ticket> tickets = generateTickets(quantity);
		printTickets(quantity, tickets);
		WinningNumbers winningNumbers = askWinningNumbers();
		printLottoResult(purchaseAmount, tickets, winningNumbers);
	}

	private void printLottoResult(int purchaseAmount, List<Ticket> tickets, WinningNumbers winningNumbers) {
		Player player = new Player(purchaseAmount, tickets);
		LottoResult lottoResult = lottoManger.checkPlayerTickets(player, winningNumbers);
		outputManager.printLottoResult(lottoResult);
	}

	private WinningNumbers askWinningNumbers() {
		return inputManager.askWiningNumbers().orElseGet(this::askWinningNumbers);
	}

	private void printTickets(int quantity, List<Ticket> tickets) {
		outputManager.printTickets(quantity, new ArrayList<>(tickets));
	}

	private List<Ticket> generateTickets(int quantity) {
		return lottoManger.generateTickets(quantity);
	}

	private int askPurchaseAmount() {
		return inputManager.askPurchaseAmount().orElseGet(this::askPurchaseAmount);
	}
}
