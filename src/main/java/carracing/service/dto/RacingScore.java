package carracing.service.dto;

/**
 * RacingScore
 * description 차량별 점수
 * version 0.0.1
 *
 * history
 *  0.0.1) 클래스 신규생성
 */
public class RacingScore {

    private int carNbr;
    private int score;

    public int getCarNbr() {
        return carNbr;
    }

    public void setCarNbr(int carNbr) {
        this.carNbr = carNbr;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
