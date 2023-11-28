import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import static java.lang.Integer.parseInt;
public class BingoJogo {
    public static void main(String[] args) {
        // Boas vindas e comandos

        char[] minusLine = new char[100];
        Arrays.fill(minusLine, '-');
        System.out.println(minusLine);

        String jogadorBranco = "          ";

        int cardNumbers = 5;
        int bigestNumber = 60;

        String names = new String();
        String[] gamers = new String[30];
        String winnerNames = new String(" *** ");
        int[][] cumulatedGamersPoint = new int[gamers.length][1];

        int[][] gamersMatch = new int[gamers.length][cardNumbers];
        int[][] tabelGamersCards = new int[gamers.length][cardNumbers];
        int[][] winnersIndex = new int[30][1];
        int[] sortedNumbers = new int[bigestNumber];
        int[] tabRoundNumbers = new int[cardNumbers];
        int winners = 0, roundNumber = 1, indicePrimeiro = 0, indiceSegundo = 0, indiceTerceiro = 0;

        boolean gameKey = true, reportKey = false;
        boolean newRoundKey = true;
        boolean winnerKey = false, roundKey = false;

        System.out.println("\t\t\t\t\tSeja muito bem-vindo ao BINGO DA TURMA 1076!!");
        System.out.println(minusLine);
        System.out.println("Vamos começar? \nEntre com (c)ontinuar ou (x) para sair.");
        Scanner digitedLine = new Scanner(System.in);
        String initialCommand = digitedLine.next().toLowerCase();

        while (gameKey) {

            if (!initialCommand.equals("c")) {
                System.out.println("Jogo encerrado!");
                gameKey = false;
                reportKey = false;
                break;
            }

            if (initialCommand.equals("c")) {
                // Entrada de Jogadores - entrada automatica pelo args ou manual?

                System.out.print("Entrar os nomes dos participantes (m)anual ou (a) para automatico ou (x) para sair: ");
                String gamersEntryType = digitedLine.next().toLowerCase();

                if (!gamersEntryType.equals("m") && !gamersEntryType.equals("a")) {
                    gameKey = false;
                    reportKey = false;
                    System.out.println("Jogo interrompido.");
                    break;
                }
                if (gamersEntryType.equals("m")) {
                    System.out.println("Entre com o nome/apelido do jogador separados por - : (ate 30 nomes)");
                    names = digitedLine.next();

                } else {    // vai ler o parametro args para entrada automatica
                    names = args[0].toString();
                }

                gamers = names.split("-");

                System.out.print(gamers.length + " participantes): ");
                int y, j = 0, remain = 0;
                for (y = 0; y < gamers.length; y++) {
                    //gamers[y][j] = y;
                    if (y % 5 == 0 && y > 0) {
                        System.out.printf("\n%10s | %10s | %10s | %10s | %10s", gamers[y - 5], gamers[y - 4],
                                gamers[y - 3], gamers[y - 2], gamers[y - 1]);
                    }
                }
                remain = y % 5;
                if (remain == 0) {
                    remain = 5;
                }  // ultima linha
                for (int k = (y - remain); k < y; k++) {
                    if (k == (y - remain)) {
                        System.out.printf("\n%10s ", gamers[k]);
                    } else {
                        System.out.printf("| %10s ", gamers[k]);
                    }
                }


                System.out.print("\nDeseja distribuir as cartelas no modo (m)anual, (a)utomático ou (x) sair: ");
                String inputCardsType = digitedLine.next().toLowerCase();

                while (!inputCardsType.equals("m") && !inputCardsType.equals("a") && !inputCardsType.equals("x")) {
                    System.out.print("\nDeseja distribuir as cartelas no modo (m)anual ou (a)utomático ou (x) para sair: ");
                    inputCardsType = digitedLine.next().toLowerCase();
                }

                if (inputCardsType.equals("x")) {
                    gameKey = false;
                    reportKey = false;
                    System.out.println("Jogo interrompido.");
                    break;
                }

                if (inputCardsType.equals("m")) {

                    tabelGamersCards = inputManualCards(gamers.length, cardNumbers);

                    // verifricar se tem cartelas repetidas

                } else if (inputCardsType.equals("a")) {

                    for (int i = 0; i < gamers.length; i++) {
                        tabelGamersCards[i] = getAutomaticCard(cardNumbers, bigestNumber);
                    }
                }


                // verifricar se tem cartelas repetidas
                int i = 0;
                System.out.println("Cartelas dos jogadores");
                System.out.println(minusLine);
                for (i = 0; i < gamers.length; i++) {
                    if (i % 3 == 0 && i > 0) {
                        System.out.printf("\n%10s: {%2d,%2d,%2d,%2d,%2d} | %10s: {%2d,%2d,%2d,%2d,%2d} | %10s: {" +
                                        "%2d,%2d,%2d,%2d,%2d}", gamers[i - 3], tabelGamersCards[i - 3][0], tabelGamersCards[i - 3][1],
                                tabelGamersCards[i - 3][2], tabelGamersCards[i - 3][3], tabelGamersCards[i - 3][4], gamers[i - 2],
                                tabelGamersCards[i - 2][0], tabelGamersCards[i - 2][1], tabelGamersCards[i - 2][2],
                                tabelGamersCards[i - 2][3], tabelGamersCards[i - 2][4], gamers[i - 1], tabelGamersCards[i - 1][0],
                                tabelGamersCards[i - 1][1], tabelGamersCards[i - 1][2], tabelGamersCards[i - 1][3],
                                tabelGamersCards[i - 1][4]);
                        System.out.println();
                        System.out.print(minusLine);

                    }
                }
                if (i == gamers.length && i % 3 == 0) {
                    System.out.printf("\n%10s: {%2d,%2d,%2d,%2d,%2d} | %10s: {%2d,%2d,%2d,%2d,%2d} | %10s: {" +
                                    "%2d,%2d,%2d,%2d,%2d}", gamers[i - 3], tabelGamersCards[i - 3][0], tabelGamersCards[i - 3][1],
                            tabelGamersCards[i - 3][2], tabelGamersCards[i - 3][3], tabelGamersCards[i - 3][4], gamers[i - 2],
                            tabelGamersCards[i - 2][0], tabelGamersCards[i - 2][1], tabelGamersCards[i - 2][2],
                            tabelGamersCards[i - 2][3], tabelGamersCards[i - 2][4], gamers[i - 1], tabelGamersCards[i - 1][0],
                            tabelGamersCards[i - 1][1], tabelGamersCards[i - 1][2], tabelGamersCards[i - 1][3],
                            tabelGamersCards[i - 1][4]);
                    System.out.println();
                    System.out.print(minusLine);
                } else if (i % 3 == 2) {
                    System.out.printf("\n%10s: {%2d,%2d,%2d,%2d,%2d} | %10s: {%2d,%2d,%2d,%2d,%2d}", gamers[i - 2],
                            tabelGamersCards[i - 2][0], tabelGamersCards[i - 2][1], tabelGamersCards[i - 2][2],
                            tabelGamersCards[i - 2][3], tabelGamersCards[i - 2][4], gamers[i - 1], tabelGamersCards[i-1][0],
                            tabelGamersCards[i - 1][1], tabelGamersCards[i - 1][2], tabelGamersCards[i - 1][3],
                            tabelGamersCards[i - 1][4]);
                    System.out.println();
                    System.out.print(minusLine);
                } else if (i % 3 == 1) {
                    System.out.printf("\n%10s: {%2d,%2d,%2d,%2d,%2d} ", gamers[i - 1], tabelGamersCards[i - 1][0],
                            tabelGamersCards[i - 1][1], tabelGamersCards[i - 1][2], tabelGamersCards[i - 1][3],
                            tabelGamersCards[i - 1][4]);
                    System.out.println();
                    System.out.print(minusLine);
                } else if (i == 0) {
                    System.out.printf("\n%10s: {%2d,%2d,%2d,%2d,%2d} ", gamers[0], tabelGamersCards[0][0],
                            tabelGamersCards[0], tabelGamersCards[0][2], tabelGamersCards[0][3], tabelGamersCards[0][4]);
                    System.out.println();
                    System.out.print(minusLine);
                }

                // Rodada de sorteio manual ou automatico
                System.out.println("\nDeseja fazer o sorteio (m)anual ou (a)utomático ou (x) para sair: ");
                String roundType = digitedLine.next().toLowerCase();

                while (!roundType.equals("m") && !roundType.equals("a") && !roundType.equals("x")) {
                    System.out.println("Deseja fazer o sorteio (m)anual, (a)utomático ou (x) para sair: ");
                    roundType = digitedLine.next().toLowerCase();
                }
                if (roundType.equals("x")) {
                    gameKey = false;
                    reportKey = false;
                    System.out.println("Jogo interrompido.");
                    break;
                }
                if (roundType.equals("a")) {

                    while (newRoundKey) {

                        tabRoundNumbers = getAutomaticCard(cardNumbers, bigestNumber);

                        System.out.println("Rodada " + roundNumber + ": ");
                        System.out.println("Numeros sorteados: {" + tabRoundNumbers[0] + "," + tabRoundNumbers[1] +
                                "," + tabRoundNumbers[2] + "," + tabRoundNumbers[3] + "," + tabRoundNumbers[4] + "}");
                        for (i = 0; i < cardNumbers; i++) {
                            sortedNumbers[tabRoundNumbers[i] - 1] = tabRoundNumbers[i];
                        }

                        getResults(gamersMatch, tabelGamersCards, tabRoundNumbers);

                        // top three da rodada

                        int primeiro = 0, segundo = 0, terceiro = 0;

                        for (i = 0; i < gamers.length; i++) {
                            cumulatedGamersPoint[i][0] = (gamersMatch[i][0] + gamersMatch[i][1] + gamersMatch[i][2] +
                                    gamersMatch[i][3] + gamersMatch[i][4]);
                            if (cumulatedGamersPoint[i][0] == 5) {
                                winnerKey = true;
                                winners++;
                                winnerKey = true;
                                winnerNames = winnerNames + gamers[i] + " *** ";
                                winnersIndex[i][0] = 1;
                            }
                        }

                        roundKey = false;

                        while (!winnerKey && !roundKey) {

                            primeiro = cumulatedGamersPoint[0][0];
                            for (j = 0; j < gamers.length; j++) {
                                if (primeiro < cumulatedGamersPoint[j][0]) {
                                    primeiro = cumulatedGamersPoint[j][0];
                                    indicePrimeiro = j;
                                }
                            }
                            segundo = 0;
                            for (j = 0; j < gamers.length; j++) {
                                if (indicePrimeiro != j) {
                                    if (segundo < cumulatedGamersPoint[j][0]) {
                                        segundo = cumulatedGamersPoint[j][0];
                                        indiceSegundo = j;
                                    }
                                }
                            }
                            terceiro = 0;
                            for (j = 0; j < gamers.length; j++) {
                                if (indicePrimeiro != j && indiceSegundo != j) {
                                    if (terceiro < cumulatedGamersPoint[j][0]) {
                                        terceiro = cumulatedGamersPoint[j][0];
                                        indiceTerceiro = j;
                                    }
                                }
                            }
                            roundKey = true;
                        }

                        if (!winnerKey) {
                            System.out.printf("*** top 3 da rodada --> %10s(%1d pontos), %10s(%1d pontos), %10s" +
                                    "(%1d pontos) ***\n", gamers[indicePrimeiro], primeiro, gamers[indiceSegundo], segundo,
                                    gamers[indiceTerceiro], terceiro);
                        } else if (winners == 1) {
                            System.out.println("\n *********** BINGO!! Parabens ao vencedor: " + winnerNames);
                        } else System.out.println("\n*********BINGO!! Parabens aos vencedores: " + winnerNames);

                        System.out.println(minusLine);

                        printTableRound(gamers, tabelGamersCards, gamersMatch);

                        if (winnerKey) {
                            System.out.println("\nJogo encerrado!");
                            newRoundKey = false;
                            reportKey = true;
                            gameKey = false;
                        } else {
                            System.out.println("\nDeseja nova rodada de sorteio (s)im ou (x) não? ");
                            String newRoundString = digitedLine.next();
                            if (newRoundString.equals("x")) {
                                System.out.println("Jogo encerrado!");
                                newRoundKey = false;
                                gameKey = true;
                            } else if (newRoundString.equals("s")) {
                                roundNumber++;
                            }
                        }


                    }
                } else if (roundType.equals("m")) {
                    String stringSortedNumber = new String();
                    Scanner reader = new Scanner(System.in);
                    String[] stringNumbers = new String[cardNumbers];
                    boolean validKey = true;
                    while (newRoundKey) {
                        //rodada = Bingo.gerarCartelaManual(rodada.length);
                        System.out.println("Rodada de sorteio " + roundNumber + ": ");
                        validKey = false;
                        while (!validKey) {
                            System.out.println("Entre com os números no formato 1,2,3,4,5 (sem repetição e números entre 1 e 60): ");
                            stringSortedNumber = reader.next();
                            stringNumbers = stringSortedNumber.split(",");
                            for (i = 0; i < cardNumbers; i++) {
                                tabRoundNumbers[i] = parseInt(stringNumbers[i]);
                                if (tabRoundNumbers[i] < 1 || tabRoundNumbers[i] > 60) {
                                    System.out.println("Números digitados inválidos. Entre novamente formato 1,2,3,4,5 " +
                                            "(sem repetição e números entre 1 e 60): ");
                                    validKey = false;
                                }
                            }
                            if (i == cardNumbers) validKey = true;
                        }

                        System.out.println("Rodada " + roundNumber + ": ");
                        System.out.println("Numeros sorteados: {" + tabRoundNumbers[0] + "," + tabRoundNumbers[1] +
                                "," + tabRoundNumbers[2] + "," + tabRoundNumbers[3] + "," + tabRoundNumbers[4] + "}");
                        for (i = 0; i < cardNumbers; i++) {
                            sortedNumbers[tabRoundNumbers[i] - 1] = tabRoundNumbers[i];
                        }

                        getResults(gamersMatch, tabelGamersCards, tabRoundNumbers);

                        int primeiro = 0, segundo = 0, terceiro = 0;

                        for (i = 0; i < gamers.length; i++) {
                            cumulatedGamersPoint[i][0] = (gamersMatch[i][0] + gamersMatch[i][1] + gamersMatch[i][2] +
                                    gamersMatch[i][3] + gamersMatch[i][4]);

                            if (cumulatedGamersPoint[i][0] == 5) {
                                winnerKey = true;
                                winners++;
                                winnerKey = true;
                                winnerNames = winnerNames + gamers[i] + " *** ";
                                winnersIndex[i][0] = 1;
                            }
                        }

                        roundKey = false;

                        while (!winnerKey && !roundKey) {
                            primeiro = cumulatedGamersPoint[0][0];

                            for (j = 0; j < gamers.length; j++) {
                                if (primeiro < cumulatedGamersPoint[j][0]) {
                                    primeiro = cumulatedGamersPoint[j][0];
                                    indicePrimeiro = j;
                                }
                            }
                            segundo = 0;
                            for (j = 0; j < gamers.length; j++) {
                                if (indicePrimeiro != j) {
                                    if (segundo < cumulatedGamersPoint[j][0]) {
                                        segundo = cumulatedGamersPoint[j][0];
                                        indiceSegundo = j;
                                    }
                                }
                            }
                            terceiro = 0;
                            for (j = 0; j < gamers.length; j++) {
                                if (indicePrimeiro != j && indiceSegundo != j) {
                                    if (terceiro < cumulatedGamersPoint[j][0]) {
                                        terceiro = cumulatedGamersPoint[j][0];
                                        indiceTerceiro = j;
                                    }
                                }
                            }
                            roundKey = true;
                        }

                        if (!winnerKey) {
                            System.out.printf("*** top 3 da rodada --> %10s(%1d pontos), %10s(%1d pontos), %10s" +
                                    "(%1d pontos) ***\n", gamers[indicePrimeiro], primeiro, gamers[indiceSegundo], segundo,
                                    gamers[indiceTerceiro], terceiro);
                        } else if (winners == 1) {
                            System.out.println("\n *********** BINGO!! Parabens ao vencedor: " + winnerNames);
                        } else System.out.println("\n*********BINGO!! Parabens aos vencedores: " + winnerNames);

                        System.out.println(minusLine);

                        printTableRound(gamers, tabelGamersCards, gamersMatch);

                        if (winnerKey) {
                            System.out.println("\nJogo encerrado!");
                            newRoundKey = false;
                            reportKey = true;
                            gameKey = false;
                        } else {
                            System.out.println("\nDeseja nova rodada de sorteio (s)im ou (x) não? ");
                            String newRound = digitedLine.next();
                            if (newRound.equals("x")) {
                                newRoundKey = false;
                                reportKey = false;
                                gameKey = false;
                            } else if (newRound.equals("s")) {
                                roundNumber++;
                            }
                        }
                    }
                }
            } // para comando = c
        }
        if (reportKey) {
            char[] minusLine60 = new char[60];
            Arrays.fill(minusLine60, '-');
            int finalPosition = 1;
            // end while gameKey is on --> gerar relatorio
            System.out.println();
            System.out.println(minusLine);
            System.out.println("********** Relatorio Geral do BINGO TURMA 1076 **********");
            System.out.println(minusLine);
            System.out.printf("\nQuantidade de rodadas: %2d Rodadas ", roundNumber);
            int index = 0;
            System.out.print("\n *** Cartelas premiadas: ");
            for (int i = 0; i < tabelGamersCards.length; i++) {
                if (winnersIndex[i][0] == 1) {
                    System.out.printf("\n\n****  %10s {%2d,%2d,%2d,%2d,%2d} ***", gamers[i], tabelGamersCards[i][0],
                            tabelGamersCards[i][1], tabelGamersCards[i][2], tabelGamersCards[i][3], tabelGamersCards[i][4]);
                }
            }
            System.out.print("\n\nNumeros sorteados: {");

            for (int i = 0; i < bigestNumber; i++) {
                if (sortedNumbers[i] != 0) {
                    System.out.printf("%2d ", sortedNumbers[i]);
                }
            }
            System.out.println("}");
            System.out.println(minusLine);

            int i = 0;
            System.out.println("\nRanking geral de jogadores: ");
            System.out.println(minusLine60);
            System.out.println("|  Jogador   |   Cartela         | Acertos | Classificação |");
            System.out.print(minusLine60);
            for (i = 0; i < gamers.length; i++) {
                if (cumulatedGamersPoint[i][0] == 5) {
                    System.out.printf("\n| %10s | {%2d,%2d,%2d,%2d,%2d}  |         |               |", gamers[i],
                            tabelGamersCards[i][0], tabelGamersCards[i][1], tabelGamersCards[i][2], tabelGamersCards[i][3],
                            tabelGamersCards[i][4]);
                    System.out.printf("\n| %10s | {%2d,%2d,%2d,%2d,%2d}  |   %2d    |      %2d       |", jogadorBranco,
                            gamersMatch[i][0], gamersMatch[i][1], gamersMatch[i][2], gamersMatch[i][3], gamersMatch[i][4],
                            cumulatedGamersPoint[i][0], finalPosition);
                    System.out.println();
                    System.out.print(minusLine60);
                }
            }
            for (i = 0; i < gamers.length; i++) {
                if (cumulatedGamersPoint[i][0] == 4) {
                    finalPosition++;
                    System.out.printf("\n| %10s | {%2d,%2d,%2d,%2d,%2d}  |         |               |", gamers[i],
                            tabelGamersCards[i][0], tabelGamersCards[i][1], tabelGamersCards[i][2], tabelGamersCards[i][3],
                            tabelGamersCards[i][4]);
                    System.out.printf("\n| %10s | {%2d,%2d,%2d,%2d,%2d}  |   %2d    |      %2d       |", jogadorBranco,
                            gamersMatch[i][0], gamersMatch[i][1], gamersMatch[i][2], gamersMatch[i][3], gamersMatch[i][4],
                            cumulatedGamersPoint[i][0], finalPosition);
                    System.out.println();
                    System.out.print(minusLine60);
                }
            }
            for (i = 0; i < gamers.length; i++) {
                if (cumulatedGamersPoint[i][0] == 3) {
                    finalPosition++;
                    System.out.printf("\n| %10s | {%2d,%2d,%2d,%2d,%2d}  |         |               |", gamers[i],
                            tabelGamersCards[i][0], tabelGamersCards[i][1], tabelGamersCards[i][2], tabelGamersCards[i][3],
                            tabelGamersCards[i][4]);
                    System.out.printf("\n| %10s | {%2d,%2d,%2d,%2d,%2d}  |   %2d    |      %2d       | ", jogadorBranco,
                            gamersMatch[i][0], gamersMatch[i][1], gamersMatch[i][2], gamersMatch[i][3], gamersMatch[i][4],
                            cumulatedGamersPoint[i][0], finalPosition);
                    System.out.println();
                    System.out.print(minusLine60);
                }
            }
            for (i = 0; i < gamers.length; i++) {
                if (cumulatedGamersPoint[i][0] == 2) {
                    finalPosition++;
                    System.out.printf("\n| %10s | {%2d,%2d,%2d,%2d,%2d}  |         |               |", gamers[i],
                            tabelGamersCards[i][0], tabelGamersCards[i][1], tabelGamersCards[i][2], tabelGamersCards[i][3],
                            tabelGamersCards[i][4]);
                    System.out.printf("\n| %10s | {%2d,%2d,%2d,%2d,%2d}  |   %2d    |      %2d       |", jogadorBranco,
                            gamersMatch[i][0], gamersMatch[i][1], gamersMatch[i][2], gamersMatch[i][3], gamersMatch[i][4],
                            cumulatedGamersPoint[i][0], finalPosition);
                    System.out.println();
                    System.out.print(minusLine60);
                }
            }
            for (i = 0; i < gamers.length; i++) {
                if (cumulatedGamersPoint[i][0] == 1) {
                    finalPosition++;
                    System.out.printf("\n| %10s | {%2d,%2d,%2d,%2d,%2d}  |         |               |", gamers[i],
                            tabelGamersCards[i][0], tabelGamersCards[i][1], tabelGamersCards[i][2], tabelGamersCards[i][3],
                            tabelGamersCards[i][4]);
                    System.out.printf("\n| %10s | {%2d,%2d,%2d,%2d,%2d}  |   %2d    |      %2d       |", jogadorBranco,
                            gamersMatch[i][0], gamersMatch[i][1], gamersMatch[i][2], gamersMatch[i][3], gamersMatch[i][4],
                            cumulatedGamersPoint[i][0], finalPosition);
                    System.out.println();
                    System.out.print(minusLine60);
                }
            }
            for (i = 0; i < gamers.length; i++) {
                if (cumulatedGamersPoint[i][0] == 0) {
                    finalPosition++;
                    System.out.printf("\n| %10s | {%2d,%2d,%2d,%2d,%2d}  |         |               |", gamers[i],
                            tabelGamersCards[i][0], tabelGamersCards[i][1], tabelGamersCards[i][2], tabelGamersCards[i][3],
                            tabelGamersCards[i][4]);
                    System.out.printf("\n| %10s | {%2d,%2d,%2d,%2d,%2d}  |   %2d    |      %2d       |", jogadorBranco,
                            gamersMatch[i][0], gamersMatch[i][1], gamersMatch[i][2], gamersMatch[i][3], gamersMatch[i][4],
                            cumulatedGamersPoint[i][0], finalPosition);
                    System.out.println();
                    System.out.print(minusLine60);
                }
            }

        }
    }


    public static int gerarNumeroRandomico(int input) {
        Random random = new Random();
        int numeroRandomico = 1;
        double numeroRandom = random.nextDouble();

        double v = numeroRandom * input + 1; // garante randomico entre 1 e 60
        numeroRandomico = (int) v;
        return numeroRandomico;
    }

    public static int[] getAutomaticCard(int input, int bigestNumber) {
        int numeroRandomico, w = 0;
        boolean repetido = false;
        int[] cartelaAutomatica = new int[input];
        numeroRandomico = gerarNumeroRandomico(bigestNumber);
        cartelaAutomatica[0] = numeroRandomico;
        for (int j = 1; j < input; j++) {
            repetido = false;
            while (!repetido) {
                numeroRandomico = gerarNumeroRandomico(60);
                w = 0;
                while (numeroRandomico != cartelaAutomatica[w]) {
                    w++;
                    if (w >= input) {
                        repetido = true;
                        cartelaAutomatica[j] = numeroRandomico;
                        break;
                    }
                }
            }
        }
        Arrays.sort(cartelaAutomatica);
        return cartelaAutomatica;
    }

    public static int[][] inputManualCards(int gamersNumber, int cardNumbers) {
        Scanner reader = new Scanner(System.in);
        String stringMinusCommas = new String();
        int[][] manualGamersCards = new int[gamersNumber][cardNumbers];
        String[] readCard = new String[cardNumbers];
        String[] stringIndividualCard = new String[cardNumbers];
        int readNumber = 0;
        int[] intReadCard = new int[cardNumbers];
        boolean repeatKey = true;

        while (repeatKey) {
            System.out.println("Entre com os numeros de 1 a 60 separados por (,) e com (-) para cada jogador" +
                    "\nExemplo: 1,2,3,4,5-6,7,8,9,1-2,3,4,5,6 >> ");
            stringMinusCommas = reader.nextLine();

            int l = 0;
            String[] stringGamerCard = stringMinusCommas.split("-");
            for (int k = 0; k < gamersNumber; k++) {
                readCard = stringGamerCard[k].split(",");
                l = 0;
                while (l < cardNumbers) {
                    intReadCard[l] = parseInt(String.valueOf(readCard[l]));
                    if (intReadCard[l] < 1 || intReadCard[l] > 60) {
                        System.out.println("Valores invalidos. Por favor, digite novamente.");
                        k = 999;
                        repeatKey = true;
                        break;
                    } else l++;
                }
                // ver se tem nros repetidos
                Arrays.sort(intReadCard);
                // cartela repetida?
                if (k != 999) {
                    int x = 1;
                    while (x < gamersNumber) {
                        if (manualGamersCards[x] == manualGamersCards[x - 1]) {
                            repeatKey = true;
                            break;
                        } else x++;
                    }
                    repeatKey = false;
                    for (int z = 0; z < cardNumbers; z++) {
                        manualGamersCards[k][z] = intReadCard[z];
                    }
                } else repeatKey = true;
            }
        }
        return manualGamersCards;
    }


    public static void getResults(int[][] tabelaPontos, int[][] tabela, int[] rodada) {
        int contadorJogador = 0;

        for (int i = 0; i < tabela.length; i++) {
            contadorJogador = 0;
            for (int z = 0; z < rodada.length; z++) {

                for (int x = 0; x < rodada.length; x++) {
                    if (tabela[i][x] == rodada[z]) {
                        tabelaPontos[i][x] = 1;
                    }
                }

            }
        }
    }

    public static void printTableRound(String[] gamers, int[][] tabelGamersCards, int[][] gamersMatch) {

        String jogadorBranco = "          ";
        char[] minusLine = new char[100];
        Arrays.fill(minusLine, '-');
        int i = 0;
        for (i = 0; i < gamers.length; i++) {

            if (i % 3 == 0 && i > 0) {
                System.out.printf("\n%10s {%2d,%2d,%2d,%2d,%2d} | %10s {%2d,%2d,%2d,%2d,%2d} | %10s " +
                        "{%2d,%2d,%2d,%2d,%2d}", gamers[i-3], tabelGamersCards[i-3][0], tabelGamersCards[i-3][1],
                        tabelGamersCards[i-3][2], tabelGamersCards[i-3][3], tabelGamersCards[i-3][4], gamers[i-2],
                        tabelGamersCards[i-2][0], tabelGamersCards[i-2][1], tabelGamersCards[i-2][2], tabelGamersCards[i-2][3],
                        tabelGamersCards[i-2][4], gamers[i-1], tabelGamersCards[i-1][0], tabelGamersCards[i-1][1],
                        tabelGamersCards[i-1][2], tabelGamersCards[i-1][3], tabelGamersCards[i-1][4]);
                System.out.printf("\n%10s {%2d,%2d,%2d,%2d,%2d} | %10s {%2d,%2d,%2d,%2d,%2d} | %10s " +
                        "{%2d,%2d,%2d,%2d,%2d}", jogadorBranco, gamersMatch[i-3][0], gamersMatch[i-3][1], gamersMatch[i-3][2],
                        gamersMatch[i-3][3], gamersMatch[i-3][4], jogadorBranco, gamersMatch[i-2][0], gamersMatch[i-2][1],
                        gamersMatch[i-2][2], gamersMatch[i-2][3], gamersMatch[i-2][4], jogadorBranco, gamersMatch[i-1][0],
                        gamersMatch[i-1][1], gamersMatch[i-1][2], gamersMatch[i-1][3], gamersMatch[i-1][4]);
                System.out.println();
                System.out.print(minusLine);
            }
        }
        if (i == gamers.length && i % 3 == 0) {
            System.out.printf("\n%10s {%2d,%2d,%2d,%2d,%2d} | %10s {%2d,%2d,%2d,%2d,%2d} | %10s {" +
                            "%2d,%2d,%2d,%2d,%2d}", gamers[i-3], tabelGamersCards[i-3][0], tabelGamersCards[i-3][1],
                    tabelGamersCards[i-3][2],tabelGamersCards[i-3][3], tabelGamersCards[i-3][4], gamers[i-2], tabelGamersCards[i-2][0],
                    tabelGamersCards[i-2][1], tabelGamersCards[i-2][2], tabelGamersCards[i-2][3], tabelGamersCards[i-2][4],
                    gamers[i-1], tabelGamersCards[i-1][0], tabelGamersCards[i-1][1], tabelGamersCards[i-1][2],
                    tabelGamersCards[i-1][3], tabelGamersCards[i-1][4]);
            System.out.printf("\n%10s {%2d,%2d,%2d,%2d,%2d} | %10s {%2d,%2d,%2d,%2d,%2d} | %10s {" +
                            "%2d,%2d,%2d,%2d,%2d}", jogadorBranco, gamersMatch[i-3][0], gamersMatch[i-3][1], gamersMatch[i-3][2],
                    gamersMatch[i-3][3], gamersMatch[i-3][4], jogadorBranco, gamersMatch[i-2][0], gamersMatch[i-2][1],
                    gamersMatch[i-2][2], gamersMatch[i-2][3], gamersMatch[i-2][4], jogadorBranco, gamersMatch[i-1][0],
                    gamersMatch[i-1][1], gamersMatch[i-1][2], gamersMatch[i-1][3], gamersMatch[i-1][4]);
            System.out.println();
            System.out.print(minusLine);
        } else if (i % 3 == 2) {

            System.out.printf("\n%10s {%2d,%2d,%2d,%2d,%2d} | %10s {%2d,%2d,%2d,%2d,%2d}", gamers[i - 2], tabelGamersCards[i - 2][0], tabelGamersCards[i - 2][1],
                    tabelGamersCards[i - 2][2], tabelGamersCards[i - 2][3], tabelGamersCards[i - 2][4], gamers[i - 1], tabelGamersCards[i - 1][0],
                    tabelGamersCards[i - 1][1], tabelGamersCards[i - 1][2], tabelGamersCards[i - 1][3], tabelGamersCards[i - 1][4]);
            System.out.printf("\n%10s {%2d,%2d,%2d,%2d,%2d} | %10s {%2d,%2d,%2d,%2d,%2d} ", jogadorBranco, gamersMatch[i - 2][0], gamersMatch[i - 2][1],
                    gamersMatch[i - 2][2], gamersMatch[i - 2][3], gamersMatch[i - 2][4], jogadorBranco, gamersMatch[i - 1][0],
                    gamersMatch[i - 1][1], gamersMatch[i - 1][2], gamersMatch[i - 1][3], gamersMatch[i - 1][4]);
            System.out.println();
            System.out.print(minusLine);

        } else {

            System.out.printf("\n%10s {%2d,%2d,%2d,%2d,%2d}", gamers[i - 1], tabelGamersCards[i - 1][0],
                    tabelGamersCards[i - 1][1], tabelGamersCards[i - 1][2], tabelGamersCards[i - 1][3], tabelGamersCards[i - 1][4]);
            System.out.printf("\n%10s {%2d,%2d,%2d,%2d,%2d} ", jogadorBranco, gamersMatch[i - 1][0],
                    gamersMatch[i - 1][1], gamersMatch[i - 1][2], gamersMatch[i - 1][3], gamersMatch[i - 1][4]);
            System.out.println();
            System.out.print(minusLine);

        }
    }
}

