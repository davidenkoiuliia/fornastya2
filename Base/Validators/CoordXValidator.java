package Base.Validators;

public class CoordXValidator implements Validator<Double> {
    @Override
    public boolean validate(Double value) {
        return value <= 645;
    }
}
