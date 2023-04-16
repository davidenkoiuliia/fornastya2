package Base.Validators;

public class CoordYValidator implements Validator<Double> {
    @Override
    public boolean validate(Double value) {
        return value > -634 && value <= Double.MAX_VALUE;
    }
}