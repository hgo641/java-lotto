package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lotto.domain.LottoStringConstant.*;

public class LottoGenerator {
    private static int LOTTO_MIN_NUMBER = 1;
    private static int LOTTO_MAX_NUMBER = 45;
    private static int LOTTO_SIZE = 6;

    private LottoGenerator() {}

    public static List<Lotto> buyMultipleRandomLotto(Money userMoney){
        List<Lotto> userLottos = new ArrayList<>();
        int countOfLotto = userMoney.getCountOfLotto();
        for(int count = 0; count < countOfLotto; count++){
            userLottos.add(getRandomLotto());
        }
        return userLottos;
    }

    private static Lotto getRandomLotto(){
        List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER, LOTTO_SIZE);
        return new Lotto(lottoNumbers);
    }

    public static Lotto getInputLotto(String inputLottoNumbers){
        List<Integer> lottoNumbers = parseLottoNumbers(inputLottoNumbers);
        return new Lotto(lottoNumbers);
    }

    private static List<Integer> parseLottoNumbers(String inputNumbers){
        List<Integer> lottoNumbers = new ArrayList<>();
        Arrays.stream(inputNumbers.split(NUMBER_SEPARATOR)).forEach(digit -> {
            int numberConvert = covertDigitToNumber(digit);
            validateNumberRange(numberConvert);
            lottoNumbers.add(numberConvert);
        });
        return lottoNumbers;
    }

    private static int covertDigitToNumber(String digit){
        try{
            Integer.parseInt(digit);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("당첨 번호는 숫자만 입력할 수 있습니다.");
        }
        return Integer.parseInt(digit);
    }

    private static void validateNumberRange(int number){
        if(number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER){
            throw new IllegalArgumentException(
                    String.format("당첨 번호는 %d부터 %d까지의 수만 입력할 수 있습니다.", LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
            );
        }
    }
}