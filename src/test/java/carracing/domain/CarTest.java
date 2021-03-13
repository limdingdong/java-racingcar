package carracing.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CarTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -1000})
    @DisplayName("차량번호는 0이나 음수가 올 수 없음")
    public void registerCar_carNumberIsZeroOrNegative(int param) {
        // given when then
        assertThatIllegalArgumentException()
                .isThrownBy( () -> new Car(param) )
                .withMessageMatching("차량번호는 1 이상의 숫자여야 합니다.");
    }

    @RepeatedTest(100)
    @DisplayName("차량이 움직이면 움직인 거리는 기존보다 줄어들지 않음")
    public void car_move() {
        // given
        Car car = new Car(1);

        // when
        int beforeScore = car.inquiryRacingScore().getScore();
        car.drive();
        int afterScore = car.inquiryRacingScore().getScore();

        // then
        assertThat(beforeScore).isLessThanOrEqualTo(afterScore);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9, 10})
    @DisplayName("Engine 클래스 - 랜덤값이 4 이상이면 반드시 움직인다")
    public void engine_move(int testParam) {
        // given
        Engine engine = new Engine(new Random() {
           @Override
           public int nextInt(int bound) {
               return testParam;
           }
        });

        // when then
        assertThat(engine.drive()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 2, 1, 0, -1, -2})
    @DisplayName("Engine 클래스 - 랜덤값이 4 미만이면 반드시 멈춘다")
    public void engine_stop(int testParam) {
        // given
        Engine engine = new Engine(new Random() {
            @Override
            public int nextInt(int bound) {
                return testParam;
            }
        });

        // when then
        assertThat(engine.drive()).isEqualTo(0);
    }
}