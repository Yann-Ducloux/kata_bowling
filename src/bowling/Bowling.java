package bowling;

/**
*
* Programme Java pour récupérer le score d'une partie de bowling
*
* @author  Yann DUCLOUX
* @version 1.0
* @since   29/09/2021
*
**/
public class Bowling {

static String[] frames;
final static String SizeOfResultERROR_MESSAGE="The number of frame is False";
final static String ILLEGAL_CARACTER_ERROR_MESSAGE="The only authorized caracters are 1,2,3,4,5,6,7,8,9,-,/,X";
final static String LINE_EMPTY_ERROR_MESSAGE="The Line is Empty";
final static String SCORE_GREATER_ERROR_MESSAGE="The calculated score whithout Strike and spare is greater than 9";
final static String STRIKE="X";
final static String SPARE="/";
final static String MISS="-";
final static String EMPTY=" ";
final static String SCORE_MISS= "0";
final static char STRIKEChar='X';
final static char SPAREChar='/';
final static char MISSChar='-';
final static char MIN_NUMBER_AUTORIZED='1';
final static char MAX_NUMBER_AUTORIZED='9';
final static int MAX_FRAME=12;
final static int MAX_FRAME_=11;
final static int NUMBER_OF_FRAME=10;
final static int LAST_FRAME=9;
final static int SCORE_STRIKE_SPARE=10;
final static int SCORE_TWO_STRIKE=20;
final static int RECUP_FIRST_SHOOT=0;
final static int RECUP_SECOND_SHOOT=1;
final static int RECUP_LAST_SHOOT=2;
final static int SHOOT_NUMBER_TWO=2;
final static int SHOOT_NUMBER_THREE=3;
	
	/** 
	 * fonction qui récupére la partie de bowling joué.
	 *  
	 * @param  line la ligne joué d'une partie de bowling 
	 * @throws IllegalCaracterException 
	 * @throws SizeOfResultException 
	 */
    public void game(String line) throws IllegalCaracterException, SizeOfResultException {
    	String[] framesVerif;
    	String lineVerif = line.replaceAll(MISS, SCORE_MISS);
    	framesVerif = line.split(EMPTY);
    	if (!validLengthFrames(framesVerif)) {
            throw new SizeOfResultException(SizeOfResultERROR_MESSAGE);
    	}
    	if (!validControlInRolls(framesVerif)){
            throw new IllegalCaracterException(ILLEGAL_CARACTER_ERROR_MESSAGE);
    	}
    	frames = lineVerif.split(EMPTY);
    }

	/** 
	 * fonction qui calcul le score de la partie joué.
	 *  
	 * @return score on retourne le score de la partie 
	 * @throws IllegalCaracterException 
	 * @throws SumFrameTooHighException 
	 * @throws FrameIsNullException 
	 */
	public int score() throws IllegalCaracterException, SumFrameTooHighException, FrameIsNullException {
		int score = 0;
    	if (!validFramesIsNotEmpty(frames)){
            throw new FrameIsNullException(LINE_EMPTY_ERROR_MESSAGE);
    	}
		for (int turn=0; turn<NUMBER_OF_FRAME; turn++){
			if (frames[turn].equals(STRIKE)){
				score +=strikeScore(turn);
			}else if (frames[turn].charAt(1) == SPAREChar){
					if (turn<LAST_FRAME) {
						score +=spareScore(turn);
					} else {
						score += spareScoreLast();
					}
			}else {
				score += classicScore(turn);
			}
		}
		return score;
	}

	/** 
	 * fonction qui calcul le score de la frame lors d'un strike.
	 *  
	 * @param  frame le numéro de la frame joué
	 * @return score on retourne le score de la frame 
	 */
	private static int strikeScore(int frame){
		return (SCORE_STRIKE_SPARE + recupNextTwoShootOfStrike(frame+1));
	}

	/** 
	 * fonction qui calcul le score de la frame lors d'un spare.
	 *  
	 * @param  frame le numéro de la frame joué
	 * @return score on retourne le score de la frame 
	 */
	private static int spareScore(int frame){
		return (SCORE_STRIKE_SPARE + recupNextShootSpare(frame+1, RECUP_FIRST_SHOOT));
	}

	/** 
	 * fonction qui calcul le score de la frame lors du dernier spare.
	 *  
	 * @return score on retourne le score de la frame 
	 */
	private static int spareScoreLast(){
		return (SCORE_STRIKE_SPARE + recupNextShootSpare(LAST_FRAME, RECUP_LAST_SHOOT));
	}


	/** 
	 * fonction qui calcul le score de la frame classique.
	 *  
	 * @param  frameInt le numéro de la frame joué
	 * @return score on retourne le score de la frame 
	 * @throws IllegalCaracterException 
	 * @throws SumFrameTooHighException 
	 */
	private static int classicScore(int frameInt) throws IllegalCaracterException, SumFrameTooHighException{
		final String frame = frames[frameInt];
		int score = Character.getNumericValue(frame.charAt(RECUP_FIRST_SHOOT)) + 
				Character.getNumericValue(frame.charAt(RECUP_SECOND_SHOOT));
		if (score>9){
            throw new SumFrameTooHighException(SCORE_GREATER_ERROR_MESSAGE);
		}
		return score;
	}

	/** 
	 * fonction qui calcul les deux prochain lancé lors d'un strike.
	 *  
	 * @param  frameInt le numéro de la frame joué
	 * @return score on retourne le score des 2 prochains lancés
	 */
	private static int recupNextTwoShootOfStrike(int frameInt){
		final String frame = frames[frameInt];
		String frameNext;
			if (frame.equals(STRIKE)) {
				frameNext = frames[frameInt+1];
				if (frameNext.equals(STRIKE)) {
					return SCORE_TWO_STRIKE;
				}
				return SCORE_STRIKE_SPARE + Character.getNumericValue(frameNext.charAt(RECUP_FIRST_SHOOT));
			} else {
				if (frame.charAt(RECUP_SECOND_SHOOT) == SPAREChar) {
					return SCORE_STRIKE_SPARE;
				} 
				return Character.getNumericValue(frame.charAt(RECUP_FIRST_SHOOT)) +
						Character.getNumericValue(frame.charAt(RECUP_SECOND_SHOOT));
			}
	}

	/** 
	 * fonction qui calcul le prochain lancé en cas de spare.
	 *  
	 * @param    frame le numéro de la frame joué
	 * @return   score on retourne le score du prochain lancé
	 */
	private static int recupNextShootSpare(int frameInt, int shootNext){
		final String frame = frames[frameInt];
		if (frame.charAt(shootNext) == STRIKEChar) {
			return SCORE_STRIKE_SPARE;
		}
		return Character.getNumericValue(frame.charAt(shootNext));
	}

	/** 
	 * fonction qui controle le nombre de frame joué.
	 *  
	 * @param    framesControle l'ensemble des caractères de la partie
	 * @return   on retourne la validé du nombre de frame joué
	 */
	private static boolean validLengthFrames(String[] framesControle){
		if(framesControle.length==MAX_FRAME) {
			if(framesControle[LAST_FRAME].equals(STRIKE) && framesControle[NUMBER_OF_FRAME].equals(STRIKE)){
				return true;
			}
		}
		if(framesControle.length==MAX_FRAME_) {
			if(framesControle[LAST_FRAME].equals(STRIKE)){
				return true;
			}
		}
		return framesControle.length==10;
	}

	/** 
	 * fonction qui controle la totalité des caractère.
	 *  
	 * @param    framesControle l'ensemble des caractères de la partie
	 * @return   valid on retourne la validé des caractère des frames
	 */
	private static boolean validControlInRolls(String[] framesControle){
		boolean valid = true;
		for (int turn=0; turn<MAX_FRAME && turn<framesControle.length && valid ;turn++) {
			if (!((framesControle[turn].equals(STRIKE)) || validShootNotFinal(framesControle[turn], turn)
					|| ValidShootFinalClassic(framesControle[turn], turn)
					|| ValidShootFinalSpare(framesControle[turn], turn))) {
				valid = false;
			}
		}
		return valid;
	}

	/**
	 * fonction qui controle la totalité des caractère hors dernier frame.
	 *  
	 * @param    framesControle l'ensemble des caractères de la partie
	 * @param    turn le numéro du tour en cours moins un
	 * @return   on retourne la validé des caractère des frames
	 */
	private static boolean validShootNotFinal(String framesControle, int turn){
		return ((isNumberORMiss(framesControle.charAt(RECUP_FIRST_SHOOT)))
			 && (isNumberORMissOrSpare(framesControle.charAt(RECUP_SECOND_SHOOT))) && turn<LAST_FRAME);
	}

	/**
	 * fonction qui controle la totalité des caractère du dernier frame en cas de frame classique.
	 *  
	 * @param    framesControle l'ensemble des caractères de la partie
	 * @param    turn le numéro du tour en cours moins un
	 * @return   on retourne la validé des caractère des frames
	 */
	private static boolean ValidShootFinalClassic(String framesControle, int turn){
		 return ((isNumberORMiss(framesControle.charAt(RECUP_FIRST_SHOOT)))
			&& (isNumberORMiss(framesControle.charAt(RECUP_SECOND_SHOOT))) &&
			turn>=LAST_FRAME && framesControle.length()== SHOOT_NUMBER_TWO);
	}

	/**
	 * fonction qui controle la totalité des caractère du dernier frame en cas de spare.
	 *  
	 * @param    framesControle l'ensemble des caractères de la partie
	 * @param    turn le numéro du tour en cours moins un
	 * @return   on retourne la validé des caractère des frames
	 */
	private static boolean ValidShootFinalSpare(String framesControle, int turn){
		 return ((isNumberORMiss(framesControle.charAt(RECUP_FIRST_SHOOT)))&& 
				 (framesControle.charAt(RECUP_SECOND_SHOOT)== SPAREChar) 
				 &&  (isNumberORMissOrStrike(framesControle.charAt(RECUP_LAST_SHOOT))) &&
				 turn>=LAST_FRAME && framesControle.length() == SHOOT_NUMBER_THREE);
	}
	/** 
	 * fonction qui controle les frames ont été remplit.
	 *  
	 * @param    framesControle l'ensemble des caractères de la partie
	 * @return   valid on retourne la validé du nombre de frame joué
	 */
	private static boolean validFramesIsNotEmpty(String[] framesControle){
		return framesControle!=null;
	}
	/** 
	 * fonction qui contrôle que le champ est bien un numéro ou un caractère autorisé du
	 * type tire raté ou strike.
	 *  
	 * @return   boolean on retourne la vérification du caractère
	 */
	private static boolean isNumberORMissOrStrike(char roll) {
	    return (roll == STRIKEChar) || (roll == MISSChar) ||
	           (roll >= MIN_NUMBER_AUTORIZED && roll <= MAX_NUMBER_AUTORIZED);
	}


	/** 
	 * fonction qui contrôle que le champ est bien un numéro ou un caractère autorisé du
	 * type tire raté, spare ou strike.
	 *  
	 * @return   boolean on retourne la vérification du caractère
	 */
	private static boolean isNumberORMissOrSpare(char roll) {
	    return (roll == SPAREChar) || (roll == MISSChar) ||
	           (roll >= MIN_NUMBER_AUTORIZED && roll <= MAX_NUMBER_AUTORIZED);
	}

	/** 
	 * fonction qui contrôle que le champ est bien un numéro ou un caractère autorisé du
	 * type tire raté.
	 *  
	 * @return   boolean on retourne la vérification du caractère
	 */
	private static boolean isNumberORMiss(char roll) {
	    return (roll == MISSChar) ||
	           (roll >= MIN_NUMBER_AUTORIZED && roll <= MAX_NUMBER_AUTORIZED);
	}
	
}